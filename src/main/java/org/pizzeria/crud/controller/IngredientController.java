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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {
	
	@Autowired
	private IngredientService ingredientService;

	@Autowired
	private PizzaService pizzaService;


// Index
	@GetMapping
	public String index(Model model) {
		
		List<Ingredient> ingredients = ingredientService.findAllPizzas();
		model.addAttribute("ingredients", ingredients);

		List<Pizza> pizzas = pizzaService.findAll();
		model.addAttribute("pizzas", pizzas);
		
		Ingredient ingr = new Ingredient();
		model.addAttribute("ingr", ingr);
		
		model.addAttribute("routeName", "ingredients");
		model.addAttribute("type", "display");
			
		return "CRUDtemplates/ingredients/index";
	}
	
// Store
	@PostMapping("/store")
	public String storeIngredient(@Valid Ingredient ingredient,  BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if(bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			return "redirect:/ingredients";
		}
		
		redirectAttributes.addFlashAttribute("successMsg", "Creazione avvenuta con successo");
		
		List<Pizza> ingredientP = ingredient.getPizzas();

		for (Pizza pizza : ingredientP) {
			pizza.getIngredients().add(ingredient);
		}	
	
		ingredientService.save(ingredient);
	
		return "redirect:/ingredients";
	}
	

// Edit
	@GetMapping("/edit/{id}")
	public String editIngredient(@PathVariable("id") int id, Model model) {
		
		Ingredient ingr = ingredientService.findIngredientById(id).get();
		model.addAttribute("ingr", ingr);
		
		List<Pizza> pizzas = pizzaService.findAll();
		model.addAttribute("pizzas", pizzas);
		
		model.addAttribute("routeName", "edit");
		
		return "CRUDtemplates/ingredients/edit";
	}
	
// Update
	@PostMapping("/update")
	public String updateIngredient(@Valid Ingredient ingredient) {
		
		List<Pizza> ingredientP = ingredient.getPizzas();
		
		for (Pizza pizza : ingredientP) {
			pizza.getIngredients().add(ingredient);
		}
		
		ingredientService.save(ingredient);
		
		return "redirect:/ingredients";
	}
	
	
// Delete
	@GetMapping("/delete/{id}")
	public String deleteingredient(@PathVariable("id") int id) {
		
		ingredientService.deleteIngredientById(id);
		
		return "redirect:/ingredients";
	}
}
