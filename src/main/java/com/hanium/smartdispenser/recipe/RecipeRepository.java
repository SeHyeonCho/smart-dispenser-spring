package com.hanium.smartdispenser.recipe;

import com.hanium.smartdispenser.recipe.domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
