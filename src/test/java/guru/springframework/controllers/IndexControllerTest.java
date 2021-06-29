package guru.springframework.controllers;

import guru.springframework.model.Recipe;
import guru.springframework.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class IndexControllerTest {

    @Mock
    private RecipeService recipeService;

    @Mock
    private Model model;

    IndexController controller;

    @Before
    public void setUp() throws Exception {
        //Initialize Mock annotation with the current class
        MockitoAnnotations.initMocks(this);

        controller = new IndexController(recipeService);
    }

    //MockMvc is used to do unit test on controllers without loading the actual server
    @Test
    public void testMockMvc() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void indexPage() {
        //given
        Set<Recipe> inputRecipes = new HashSet<>();
        inputRecipes.add(new Recipe());
        Recipe recipe = new Recipe();
        recipe.setId(2l);
        inputRecipes.add(recipe);

        when(recipeService.getRecipes()).thenReturn(inputRecipes);

        //ArgumentCaptor is used to capture argument from mocked method
        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        //when
        String viewName = controller.indexPage(model);

        //then
        assertEquals("index",viewName);
        verify(recipeService,times(1)).getRecipes();
        //anySet() method checks for any non-null set, part of mockito
        verify(model,times(1)).addAttribute(eq("recipes"),argumentCaptor.capture());

        Set<Recipe> capturedRecipes = argumentCaptor.getValue();
        assertEquals(2, capturedRecipes.size());
    }
}