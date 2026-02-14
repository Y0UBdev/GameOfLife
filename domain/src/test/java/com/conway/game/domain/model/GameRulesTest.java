package com.conway.game.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import java.util.Set;

/**
 * Tests unitaires pour GameRules
 */
class GameRulesTest {
    
    private GameRules gameRules;
    
    @BeforeEach
    void setUp() {
        gameRules = new GameRules();
    }
    
    // Tests pour shouldSurvive
    
    @Test
    void cell_with_less_than_2_neighbors_should_not_survive() {
        assertThat(gameRules.shouldSurvive(0)).isFalse();
        assertThat(gameRules.shouldSurvive(1)).isFalse();
    }
    
    @Test
    void cell_with_2_neighbors_should_survive() {
        assertThat(gameRules.shouldSurvive(2)).isTrue();
    }
    
    @Test
    void cell_with_3_neighbors_should_survive() {
        assertThat(gameRules.shouldSurvive(3)).isTrue();
    }
    
    @Test
    void cell_with_more_than_3_neighbors_should_not_survive() {
        assertThat(gameRules.shouldSurvive(4)).isFalse();
        assertThat(gameRules.shouldSurvive(5)).isFalse();
        assertThat(gameRules.shouldSurvive(8)).isFalse();
    }
    
    // Tests pour shouldBirth
    
    @Test
    void dead_cell_with_exactly_3_neighbors_should_birth() {
        assertThat(gameRules.shouldBirth(3)).isTrue();
    }
    
    @Test
    void dead_cell_with_less_than_3_neighbors_should_not_birth() {
        assertThat(gameRules.shouldBirth(0)).isFalse();
        assertThat(gameRules.shouldBirth(1)).isFalse();
        assertThat(gameRules.shouldBirth(2)).isFalse();
    }
    
    @Test
    void dead_cell_with_more_than_3_neighbors_should_not_birth() {
        assertThat(gameRules.shouldBirth(4)).isFalse();
        assertThat(gameRules.shouldBirth(5)).isFalse();
        assertThat(gameRules.shouldBirth(8)).isFalse();
    }
    
    // Tests pour countAliveNeighbors
    
    @Test
    void should_count_alive_neighbors_correctly() {
        // Créer une grille avec pattern:
        //   X X
        //   X .
        Grid grid = Grid.withCells(Set.of(
            Cell.at(0, 0),
            Cell.at(1, 0),
            Cell.at(0, 1)
        ));
        
        // Cellule morte en (1, 1) a 3 voisins vivants
        Cell deadCell = Cell.at(1, 1);
        assertThat(gameRules.countAliveNeighbors(deadCell, grid)).isEqualTo(3);
        
        // Cellule vivante en (0, 0) a 2 voisins vivants
        Cell aliveCell = Cell.at(0, 0);
        assertThat(gameRules.countAliveNeighbors(aliveCell, grid)).isEqualTo(2);
    }
    
    @Test
    void should_count_zero_neighbors_for_isolated_cell() {
        Grid grid = Grid.withCells(Set.of(Cell.at(5, 5)));
        Cell isolatedCell = Cell.at(10, 10);
        
        assertThat(gameRules.countAliveNeighbors(isolatedCell, grid)).isEqualTo(0);
    }
}
