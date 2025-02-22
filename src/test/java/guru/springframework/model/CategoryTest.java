package guru.springframework.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryTest {
    Category category;

    @Before
    public void setUp() throws Exception {
        category = new Category();
    }

    @Test
    public void getId() {
        Long idValue = 4l;
        category.setId(idValue);

        assertEquals(idValue, category.getId());
    }

    @Test
    public void getCategoryName() {
        String categoryNameVal = "Indian";
        category.setCategoryName(categoryNameVal);

        assertEquals(categoryNameVal, category.getCategoryName());
    }

    @Test
    public void getRecipes() {
    }
}