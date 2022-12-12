package org.pizzeria.crud.controller;

import java.util.List;
import java.util.Optional;

import org.pizzeria.crud.pojo.Pizza;
import org.pizzeria.crud.serv.PizzaService;

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
@RequestMapping("/")
public class PizzaController {
	
	@Autowired
	private PizzaService pizzaService;

// index
	@GetMapping
	public String index(Model model) {
		
		List<Pizza> pizzas = pizzaService.findAll();
		model.addAttribute("obj", pizzas);
		model.addAttribute("routeName", "pizza");
		
		return "CRUDtemplates/index";
	}
	
// home
	@GetMapping("/home")
	public String goHome(Model model) {
		model.addAttribute("routeName", "home");
		return "home" ;
	}
	
// Show
	@GetMapping("/pizza/{id}")
	public String getPizza(@PathVariable("id") int id, Model model) {
		
		Optional<Pizza> optPizza = pizzaService.findPizzaById(id);
		Pizza pizza = optPizza.get();
		model.addAttribute("obj", pizza);
		model.addAttribute("routeName", "show");
		model.addAttribute("element", "pizza");
		
		return "CRUDtemplates/show";
	}
	
// Create	
	@GetMapping("/pizza/create")
	public String getCreatePizza(Model model) {
		
		Pizza pizza = new Pizza();
		model.addAttribute("obj", pizza);
		
		model.addAttribute("routeName", "new");
		model.addAttribute("element", "pizza");
		model.addAttribute("action", "/pizza/create");
		
		return "CRUDtemplates/new";
	}
	
	@PostMapping("/pizza/create")
	public String storePizza(@Valid Pizza pizza, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
	
		
		if(bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			return "redirect:/pizza/create";
		}
		
		redirectAttributes.addFlashAttribute("successMsg", "Creazione avvenuta con successo");
		pizzaService.save(pizza);
		
		return "redirect:/";
	}
	
// Edit
	@GetMapping("/pizza/update/{id}")
	public String getPizzaUpdate(@PathVariable("id") int id, Model model) {
		
		Optional<Pizza> optPizza = pizzaService.findPizzaById(id);
		Pizza pizza = optPizza.get();
		
		model.addAttribute("obj", pizza);
		
		model.addAttribute("routeName", "edit");
		model.addAttribute("element", "pizza");
		model.addAttribute("action", "/pizza/update");
		
		return "CRUDtemplates/edit";
	}

	@PostMapping("/pizza/update")
	public String updatePizza(@Valid Pizza pizza, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if(bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			return "redirect:/pizza/update/" + pizza.getId();
		}
		
		redirectAttributes.addFlashAttribute("successMsg", "Modifica avvenuta con successo");
		pizzaService.save(pizza);
		
		return "redirect:/";
	}
	
// Delete
	@GetMapping("/pizza/delete/{id}")
	public String deletePizza(@PathVariable("id") int id) {
		
		pizzaService.deletePizzaById(id);
		
		return "redirect:/";
	}
	
// Search
	@GetMapping("/pizza/search")
	public String getSearchPizzaByName(Model model, @RequestParam(name = "query", required = false) String query) {
		
		List<Pizza> pizzas = query == null ? pizzaService.findAll() : pizzaService.findByName(query);
		
		model.addAttribute("obj", pizzas);
		model.addAttribute("query", query);
		model.addAttribute("routeName", "searchPizza");
		model.addAttribute("element", "pizza");
		
		return "SRCtemplates/search";
	}

}