package org.pizzeria.crud.serv;

import java.util.List;
import java.util.Optional;

import org.pizzeria.crud.pojo.Ingredients;
import org.pizzeria.crud.repo.IngredientsRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientsService {

	@Autowired
	private IngredientsRepo ingredientsRepo;
	
// Save
	public void save(Ingredients ingredients) {
		ingredientsRepo.save(ingredients);
	}
		
// Find
	public List<Ingredients> findAll() {
		return ingredientsRepo.findAll();
	}
	
// Find by ID
	public Optional<Ingredients> findIngredientsById(int id) {
		return ingredientsRepo.findById(id);
	}
	
// Delete by ID
	public void deleteIngredientsById(int id) {
		ingredientsRepo.deleteById(id);
	}
}
