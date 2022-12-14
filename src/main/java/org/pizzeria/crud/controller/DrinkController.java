package org.pizzeria.crud.controller;

import java.util.List;
import java.util.Optional;

import org.pizzeria.crud.pojo.Drink;
import org.pizzeria.crud.pojo.Pizza;
import org.pizzeria.crud.serv.DrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/drink")
public class DrinkController {

	@Autowired
	private DrinkService drinkService;

// Index
	@GetMapping
	public String index(Model model) {
		
		List<Drink> drinks = drinkService.findAll();
		model.addAttribute("obj", drinks);
		
		
		model.addAttribute("routeName", "drink");		
		model.addAttribute("element", "drink");
		model.addAttribute("typeRel", "ty2");
		
		return "CRUDtemplates/pizzas-drinks/index";
	}
	
// Show
	@GetMapping("/{id}")
	public String getDrink(@PathVariable("id") int id, Model model) {
		
		Optional<Drink> optDrink = drinkService.findDrinkById(id);
		Drink drink = optDrink.get();
		model.addAttribute("obj", drink);
		model.addAttribute("element", "drink");
		model.addAttribute("routeName", "showDrink");
		
		return "CRUDtemplates/pizzas-drinks/show";
	}
		
		
// Create	
	@GetMapping("/create")
	public String createDrink(Model model) {
		
		Drink drink = new Drink();
		model.addAttribute("obj", drink);
		model.addAttribute("routeName", "newDrink");
		model.addAttribute("element", "drink");
		model.addAttribute("action", "/drink/create");
		
		return "CRUDtemplates/pizzas-drinks/new";
	}
	
	@PostMapping("/create")
	public String storeDrink(@Valid Drink drink, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if(bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			return "redirect:/drink/create";
		}
		
		redirectAttributes.addFlashAttribute("successMsg", "Creazione avvenuta con successo");
		drinkService.save(drink);
		
		return "redirect:/drink";
	}
	
// Edit
	@GetMapping("/update/{id}")
	public String editDrink(@PathVariable("id") int id, Model model) {
		
		Optional<Drink> optDrink = drinkService.findDrinkById(id);
		Drink drink = optDrink.get();
		model.addAttribute("obj", drink);
		model.addAttribute("routeName", "drinkEdit");
		model.addAttribute("element", "drink");
		model.addAttribute("action", "/drink/update");
		
		return "CRUDtemplates/pizzas-drinks/edit";
	}

	@PostMapping("/update")
	public String updateDrink(@Valid Drink drink, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if(bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			return "redirect:/drink/update/" + drink.getId();
		}
		
		redirectAttributes.addFlashAttribute("successMsg", "Modifica avvenuta con successo");
		drinkService.save(drink);
		
		return "redirect:/drink";
	}
	
// Delete
	@GetMapping("/delete/{id}")
	public String deleteDrink(@PathVariable("id") int id) {
		
		drinkService.deleteDrinkById(id);
		
		return "redirect:/drink";
	}
	
// Search
	@GetMapping("/search")
	public String getSearchPizzaByName(Model model, @RequestParam(name = "query", required = false) String query) {
		
		List<Drink> drinks = query == null ? drinkService.findAll() : drinkService.findByName(query);
		
		model.addAttribute("obj", drinks);
		model.addAttribute("query", query);
		model.addAttribute("routeName", "searchDrink");
		model.addAttribute("element", "drink");

		return "SRCtemplates/search";
	}
}

