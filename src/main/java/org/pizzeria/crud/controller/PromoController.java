package org.pizzeria.crud.controller;

import java.util.List;
import java.util.Optional;

import org.pizzeria.crud.pojo.Pizza;
import org.pizzeria.crud.pojo.Promotion;
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
	PromotionService pizzaService;
	
	@RequestMapping("/index")
	public String indexPromos(Model model) {
		
		List<Promotion> promos = promotionService.findAll();

		List<Promotion> pizzas = pizzaService.findAll();
		
		model.addAttribute("promos", promos);
		model.addAttribute("pizzas", pizzas);
		model.addAttribute("routeName", "promozioni");
		
		Promotion prom = new Promotion();
		model.addAttribute("promo", prom);
		
		return "CRUDtemplates/promo/index";
	}
	

// Store 
	@PostMapping("/store")
	public String storePromo(@Valid Promotion promotion) {
		
		System.out.println(promotion);
		
		return "redirect:/promo/index";
	}
	

// Edit
	@GetMapping("/update/{id}")
	public String editPromo(@PathVariable("id") int id, Model model) {
		
		Optional<Promotion> optPromo = promotionService.findPromotionById(id);
		Promotion promotion = optPromo.get();
		
		model.addAttribute("obj", promotion);
		
		model.addAttribute("routeName", "edit");
		model.addAttribute("element", "pizza");
		model.addAttribute("action", "/pizza/update");
		
		return "CRUDtemplates/promo/edit";
	}
	
	@PostMapping("/update")
	public String updatePromo(@Valid Promotion promotion, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if(bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			return "redirect:/promos/update/" + promotion.getId();
		}
		
		redirectAttributes.addFlashAttribute("successMsg", "Modifica avvenuta con successo");
		pizzaService.save(promotion);
		
		return "redirect:/promos";
	}
		
	
// Delete
	@GetMapping("/delete/{id}")
	public String deletePromotion(@PathVariable("id") int id) {
		
		promotionService.deletePromotionById(id);
		
		return "redirect:/promos";
	}
}
