package org.pizzeria.crud.controller;

import java.util.List;

import org.pizzeria.crud.pojo.Drink;
import org.pizzeria.crud.pojo.Pizza;
import org.pizzeria.crud.pojo.Promotion;
import org.pizzeria.crud.serv.DrinkService;
import org.pizzeria.crud.serv.PizzaService;
import org.pizzeria.crud.serv.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/search")
public class SearchController {

	@Autowired
	private PizzaService pizzaService;

	@Autowired
	private DrinkService drinkService;

	@Autowired
	private PromotionService promotionService;
	
	@GetMapping
	public String searchByName(Model model,
			@RequestParam(name = "query", required = false) 
			String query) {
		
		List<Drink> drinks = query == null ? drinkService.findAll() : drinkService.findByName(query);
		List<Pizza> pizzas = query == null ? pizzaService.findAll() : pizzaService.findByName(query);
		
		model.addAttribute("pizzas", pizzas);
 		model.addAttribute("drinks", drinks);
		model.addAttribute("query", query);
		model.addAttribute("routeName", "search");
		
		return "SRCtemplates/commonSearch";
 	}
	
	
	@GetMapping("/promos")
	public String getPromos(Model model) {
		
		List<Promotion> promos = promotionService.findAll();
		model.addAttribute("routeName", "promozioni");
		model.addAttribute("obj", promos);
		return "CRUDtemplates/index";
	}
}