package org.ecommerce.products.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    @Test
    void testEnumLength() {
        Category[] categories = Category.values();

        assertEquals(9, categories.length);
    }

    @Test
    void testElectronics() {
        assertEquals(Category.ELECTRONICS, Category.valueOf("ELECTRONICS"));
    }

    @Test
    void testClothing() {
        assertEquals(Category.CLOTHING, Category.valueOf("CLOTHING"));
    }

    @Test
    void testGroceries() {
        assertEquals(Category.GROCERIES, Category.valueOf("GROCERIES"));
    }

    @Test
    void testAutomotive() {
        assertEquals(Category.AUTOMOTIVE, Category.valueOf("AUTOMOTIVE"));
    }

    @Test
    void testEducation() {
        assertEquals(Category.EDUCATION, Category.valueOf("EDUCATION"));
    }

    @Test
    void testEntertainment() {
        assertEquals(Category.ENTERTAINMENT, Category.valueOf("ENTERTAINMENT"));
    }

    @Test
    void testFashion() {
        assertEquals(Category.FASHION, Category.valueOf("FASHION"));
    }

    @Test
    void testFurniture() {
        assertEquals(Category.FURNITURE, Category.valueOf("FURNITURE"));
    }

    @Test
    void testTravel() {
        assertEquals(Category.TRAVEL, Category.valueOf("TRAVEL"));
    }


}