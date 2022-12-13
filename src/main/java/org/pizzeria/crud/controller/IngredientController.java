package org.pizzeria.crud.controller;


import java.util.List;

import org.pizzeria.crud.pojo.Ingredient;
import org.pizzeria.crud.pojo.Pizza;
import org.pizzeria.crud.pojo.Promotion;
import org.pizzeria.crud.serv.IngredientService;
import org.pizzeria.crud.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {
	
	@Autowired
	private IngredientService ingredientService;

	@Autowired
	private PizzaService pizzaService;


// index
	@GetMapping
	public String index(Model model) {
		
		List<Ingredient> ingredients = ingredientService.findAllPizzas();
		List<Pizza> pizzas = pizzaService.findAll();
		
		model.addAttribute("ingredients", ingredients);
		model.addAttribute("pizzas", pizzas);
		
		model.addAttribute("routeName", "ingredients");
		model.addAttribute("type", "display");

		
		Ingredient ingr = new Ingredient();
		model.addAttribute("ingr", ingr);
		
		return "CRUDtemplates/ingredients/index";
	}
	
	
// Create
	@GetMapping("/create")
	public String createIngredient(Model model) {
		
		Ingredient ingredient = new Ingredient();
		List<Pizza> pizzas = pizzaService.findAll();
		
		model.addAttribute("ingredient", ingredient);
		model.addAttribute("pizzas", pizzas);
		
		return "CRUDtemplates/ingredients/new";
	}
// Store
	@PostMapping("/store")
	public String storeIngredient(@Valid Ingredient ingredient) {
		
		List<Pizza> ingredientP = ingredient.getPizzas();

		
		for (Pizza pizza : ingredientP) {
			pizza.getIngredients().add(ingredient);
		}	
	
		ingredientService.save(ingredient);
	
		return "redirect:/ingredients";
	}
	

// Edit
	
// Update
	
	
// Delete
	@GetMapping("/delete/{id}")
	public String deleteingredient(@PathVariable("id") int id) {
		
		ingredientService.deleteIngredientById(id);
		
		return "redirect:/ingredients";
	}
}
