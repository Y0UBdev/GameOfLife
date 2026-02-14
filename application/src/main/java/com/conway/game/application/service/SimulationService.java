package com.conway.game.application.service;

import com.conway.game.application.usecase.ComputeNextGenerationUseCase;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class SimulationService {
    
    private final ComputeNextGenerationUseCase computeNextGenerationUseCase;
    private final ScheduledExecutorService executor;
    
    private ScheduledFuture<?> simulationTask;
    private boolean running;
    private int delayMillis;
    
    public SimulationService(ComputeNextGenerationUseCase computeNextGenerationUseCase) {
        this.computeNextGenerationUseCase = computeNextGenerationUseCase;
        this.executor = Executors.newSingleThreadScheduledExecutor();
        this.running = false;
        this.delayMillis = 1000;
    }
    
    public void start() {
        if (!running) {
            running = true;
            simulationTask = executor.scheduleAtFixedRate(
                this::computeGeneration,
                0,
                delayMillis,
                TimeUnit.MILLISECONDS
            );
        }
    }
    
    public void stop() {
        if (running && simulationTask != null) {
            running = false;
            simulationTask.cancel(false);
        }
    }
    
    public void toggle() {
        if (running) {
            stop();
        } else {
            start();
        }
    }
    
    public void setSpeed(int delayMillis) {
        boolean wasRunning = running;
        
        if (wasRunning) {
            stop();
        }
        
        this.delayMillis = delayMillis;
        
        if (wasRunning) {
            start();
        }
    }
    
    public void increaseSpeed() {
        int newDelay;
        if (delayMillis > 100) {
            newDelay = delayMillis - 100;
        } else if (delayMillis > 10) {
            newDelay = Math.max(10, delayMillis - 10);
        } else {
            return;
        }
        setSpeed(newDelay);
    }
    
    public void decreaseSpeed() {
        if (delayMillis < 1000) {
            setSpeed(delayMillis + 100);
        }
    }

    private void computeGeneration() {
        try {
            computeNextGenerationUseCase.execute();
        } catch (Exception e) {
            System.err.println("Error computing generation: " + e.getMessage());
        }
    }
}
