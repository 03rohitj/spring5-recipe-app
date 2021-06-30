package guru.springframework.converters;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.model.Category;
import guru.springframework.model.Ingredient;
import guru.springframework.model.Recipe;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    private CategoryToCategoryCommand categoryConverter;
    private IngredientToIngredientCommand ingredientConverter;
    private NotesToNotesCommand notesConverter;

    @Autowired
    public RecipeToRecipeCommand(CategoryToCategoryCommand categoryConverter, IngredientToIngredientCommand ingredientConverter,
                                 NotesToNotesCommand notesConverter) {
        this.categoryConverter = categoryConverter;
        this.ingredientConverter = ingredientConverter;
        this.notesConverter = notesConverter;
    }

    @Override
    @Nullable
    @Synchronized
    public RecipeCommand convert(Recipe source) {
        final RecipeCommand command = new RecipeCommand();
        command.setId(source.getId());
        command.setCookTime(source.getCookTime());
        command.setDescription(source.getDescription());
        command.setDifficulty(source.getDifficulty());
        command.setDirections(source.getDirections());
        command.setImage(source.getImage());
        command.setPrepTime(source.getPrepTime());
        command.setServings(source.getServings());
        command.setSource(source.getSource());
        command.setUrl(source.getUrl());
        command.setNotes(notesConverter.convert(source.getNotes()));

        if (source.getCategories() != null && source.getCategories().size() > 0){
            /*
            for(Category category : source.getCategories())
                command.getCategories().add(categoryConverter.convert(category));
            */

            source.getCategories().forEach((Category category) ->
                    command.getCategories().add(categoryConverter.convert(category)));
        }

        if (source.getIngredients() != null && source.getIngredients().size() > 0){
            source.getIngredients().forEach((Ingredient ingredient) ->
                    command.getIngredients().add(ingredientConverter.convert(ingredient)));
        }

        return command;
    }
}
