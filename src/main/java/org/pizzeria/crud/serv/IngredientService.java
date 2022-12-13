package org.pizzeria.crud.serv;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.pizzeria.crud.pojo.Ingredient;
import org.pizzeria.crud.repo.IngredientRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class IngredientService {

	@Autowired
	private IngredientRepo ingredientRepo;
	
// Save
	public void save(Ingredient ingredients) {
		ingredientRepo.save(ingredients);
	}
		
// Find
	public List<Ingredient> findAll() {
		return ingredientRepo.findAll();
	}
	
// Find by ID
	public Optional<Ingredient> findIngredientById(int id) {
		return ingredientRepo.findById(id);
	}
	
// Delete by ID
	public void deleteIngredientById(int id) {
		ingredientRepo.deleteById(id);
	}
	
	@Transactional
	public List<Ingredient> findAllIngredients() {
		List<Ingredient> ingredients = ingredientRepo.findAll();
		
		for (Ingredient i : ingredients) {
			Hibernate.initialize(i.getPizzas());
		}
		
		return ingredients;
		
	}
}
