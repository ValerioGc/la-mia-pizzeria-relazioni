package org.pizzeria.crud.serv;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.pizzeria.crud.pojo.Pizza;
import org.pizzeria.crud.repo.PizzaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class PizzaService {
	
	@Autowired
	private PizzaRepo pizzaRepo;
	
	public void save(Pizza pizza) {
		pizzaRepo.save(pizza);
	}
		
	public Optional<Pizza> findPizzaById(int id) {
		return pizzaRepo.findById(id);
	}
	
	public void deletePizzaById(int id) {
		pizzaRepo.deleteById(id);
	}
	
	public List<Pizza> findByName(String name) {
		return pizzaRepo.findByNameContainingIgnoreCase(name);
	}
	
// Find
	public List<Pizza> findAll() {
		return pizzaRepo.findAll();
	}
	@Transactional
	public List<Pizza> findAllPromotion() {
		
		List<Pizza> pizzas = pizzaRepo.findAll();
		
		for (Pizza pizza : pizzas) {
			Hibernate.initialize(pizza.getPromotions());
		}
		
		return pizzas;
	}
}