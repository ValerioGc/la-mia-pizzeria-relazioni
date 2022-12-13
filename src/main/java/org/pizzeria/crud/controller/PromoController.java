package org.pizzeria.crud.controller;

import java.util.List;
import java.util.Optional;

import org.pizzeria.crud.pojo.Pizza;
import org.pizzeria.crud.pojo.Promotion;
import org.pizzeria.crud.serv.PizzaService;
import org.pizzeria.crud.serv.PromotionService;
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

@RequestMapping("/promos")
@Controller
public class PromoController {

	@Autowired
	private PromotionService promotionService;
	
	@Autowired
	private PizzaService pizzaService;
	
//  Index
	@RequestMapping("/index")
	public String indexPromos(Model model) {
		
		List<Promotion> promos = promotionService.findAll();

		List<Pizza> pizzas = pizzaService.findAll();
		
		model.addAttribute("promos", promos);
		model.addAttribute("pizzas", pizzas);
		model.addAttribute("routeName", "promozioni");
		
		Promotion prom = new Promotion();
		model.addAttribute("promo", prom);
		
		return "CRUDtemplates/promo/index";
	}

// create
	@GetMapping("/create")
	public String getPromotionCreate(Model model) {
		
		Promotion promotion = new Promotion();
		List<Pizza> pizzas = pizzaService.findAll();
		model.addAttribute("promotion", promotion);
		model.addAttribute("pizzas", pizzas);
		
		return "promotion-create";
	}
	
// Store 
	@PostMapping("/store")
	public String storePromotion(@Valid Promotion promotion) {
		
		List<Pizza> promotionPizzas = promotion.getPizzas();

		promotion.setPizzas(promotionPizzas);
		
		promotionService.save(promotion);
		
		return "redirect:/promotion/index";
	}


// Edit
	@GetMapping("/update/{id}")
	public String editPromo(@PathVariable("id") int id, Model model) {
		
		Optional<Promotion> optPromo = promotionService.findPromotionById(id);
		Promotion promotion = optPromo.get();
		
	
		List<Pizza> pizzas = pizzaService.findAll();
		
		model.addAttribute("promo", promotion);
		model.addAttribute("pizzas", pizzas);
		model.addAttribute("routeName", "edit");
		model.addAttribute("action", "/promos/update");
		
		return "CRUDtemplates/promo/edit";
	}

	@PostMapping("/update")
	public String updatePromo(@Valid Promotion promotion, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if(bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			return "redirect:/promos/update/" + promotion.getId();
		}
		
		redirectAttributes.addFlashAttribute("successMsg", "Modifica avvenuta con successo");
		
		promotionService.save(promotion);
		
		return "redirect:/promos/index";
	}
		
	
// Delete
	@GetMapping("/delete/{id}")
	public String deletePromotion(@PathVariable("id") int id) {
		
		promotionService.deletePromotionById(id);
		
		return "redirect:/promos/index";
	}
}
