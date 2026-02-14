package com.conway.game.domain.model;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import java.util.Set;

/**
 * Tests unitaires pour la classe Cell
 */
class CellTest {
    
    @Test
    void should_create_cell_with_coordinates() {
        Cell cell = Cell.at(5, 10);
        
        assertThat(cell.x()).isEqualTo(5);
        assertThat(cell.y()).isEqualTo(10);
    }
    
    @Test
    void should_have_8_neighbors() {
        Cell cell = Cell.at(5, 5);
        Set<Cell> neighbors = cell.neighbors();
        
        assertThat(neighbors).hasSize(8);
    }
    
    @Test
    void should_calculate_correct_neighbors() {
        Cell cell = Cell.at(0, 0);
        Set<Cell> neighbors = cell.neighbors();
        
        assertThat(neighbors).containsExactlyInAnyOrder(
            Cell.at(-1, -1), Cell.at(-1, 0), Cell.at(-1, 1),
            Cell.at(0, -1),                  Cell.at(0, 1),
            Cell.at(1, -1),  Cell.at(1, 0),  Cell.at(1, 1)
        );
    }
    
    @Test
    void should_be_equal_when_same_coordinates() {
        Cell cell1 = Cell.at(3, 7);
        Cell cell2 = Cell.at(3, 7);
        
        assertThat(cell1).isEqualTo(cell2);
        assertThat(cell1.hashCode()).isEqualTo(cell2.hashCode());
    }
    
    @Test
    void should_not_be_equal_when_different_coordinates() {
        Cell cell1 = Cell.at(3, 7);
        Cell cell2 = Cell.at(3, 8);
        
        assertThat(cell1).isNotEqualTo(cell2);
    }
    
    @Test
    void should_have_meaningful_toString() {
        Cell cell = Cell.at(5, 10);
        
        assertThat(cell.toString()).isEqualTo("Cell(5,10)");
    }
}
