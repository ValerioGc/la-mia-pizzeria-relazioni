package org.pizzeria.crud;


import java.util.List;

import org.pizzeria.crud.pojo.Drink;
import org.pizzeria.crud.pojo.Pizza;
import org.pizzeria.crud.pojo.Promotion;
import org.pizzeria.crud.serv.DrinkService;
import org.pizzeria.crud.serv.PizzaService;
import org.pizzeria.crud.serv.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLaMiaPizzeriaCrudApplication implements CommandLineRunner{
	
	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private DrinkService drinkService;
	
	@Autowired
	private PromotionService promotionService;
	
	
	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaCrudApplication.class, args);
	}
	
	
	@Override
	public void run(String... args) throws Exception {
		
		
	// Promo
		Promotion prom1 = new Promotion("2022-10-12", "2022-12-12", "promo1");
		Promotion prom2 = new Promotion("2023-04-01", "2023-05-01","promo2" );
		Promotion prom3 = new Promotion("2022-12-24", "2023-01-15", "promo3");
		Promotion prom4 = new Promotion("2022-12-24", "2023-01-15", "promo4");
		Promotion prom5 = new Promotion("2022-12-24", "2023-01-15", "promo5");
		Promotion prom6 = new Promotion("2022-12-24", "2023-01-15", "promo6");
		
		promotionService.save(prom1);
		promotionService.save(prom2);
		promotionService.save(prom3);
		promotionService.save(prom4);
		promotionService.save(prom5);
		promotionService.save(prom6);
		
//	 Pizza
		Pizza p1 = new Pizza("bufala", "lorem pizzum", 15, prom1);
		Pizza p2 = new Pizza("boscaiola", "lorem pizzum", 7, prom2);
		Pizza p3 = new Pizza("quattro formaggi", "lorem ipsum", 12, prom5);
		Pizza p4 = new Pizza("margherita", "lorem pizzum", 6, prom3);
		Pizza p5 = new Pizza("capricciosa", "lorem pizzum", 6, prom4);
		Pizza p6 = new Pizza("bu", "lorem pizzum", 15, prom3);
		Pizza p7 = new Pizza("norcina", "lorem pizzum", 17, prom5);
		Pizza p8 = new Pizza("crostino", "lorem ipsum", 12, prom3);
		Pizza p9 = new Pizza("margherita con bufala", "lorem pizzum", 16, prom1);
		Pizza p10 = new Pizza("diavola", "lorem pizzum", 19, prom5);
		Pizza p11 = new Pizza("noci e pere", "lorem ipsum", 12, prom1);
		Pizza p12= new Pizza("margherit con bufala", "lorem pizzum", 16, prom2);
		Pizza p13 = new Pizza("diavol", "lorem pizzum", 19, prom5);
		
		pizzaService.save(p1);
		pizzaService.save(p2);
		pizzaService.save(p3);
		pizzaService.save(p4);
		pizzaService.save(p5);
		pizzaService.save(p6);
		pizzaService.save(p7);
		pizzaService.save(p8);
		pizzaService.save(p9);
		pizzaService.save(p10);
		pizzaService.save(p11);
		pizzaService.save(p12);
		pizzaService.save(p13);

	//drinks
		Drink d1 = new Drink("coca cola", "lorem ipsum", 2);
		Drink d2 = new Drink("fanta", "lorem ipsumm", 2);
		Drink d3 = new Drink("pepsi", "lorem ipsumm", 2);
		Drink d4 = new Drink("vino", "lorem ipsumm", 16);
		Drink d5 = new Drink("sprite", "lorem ipsumm", 2);
		Drink d6 = new Drink("peroni", "lorem pizzum", 2);
		Drink d7 = new Drink("nastro azzurro", "lorem ipsum", 3);
		Drink d8 = new Drink("heineken", "lorem ipsum", 3);
		Drink d9 = new Drink("acqua", "lorem ipsum", 1);
		Drink d10 = new Drink("chinotto", "lorem ipsum", 2);

		drinkService.save(d1);
		drinkService.save(d2);
		drinkService.save(d3);
		drinkService.save(d4);
		drinkService.save(d5);
		drinkService.save(d6);
		drinkService.save(d7);
		drinkService.save(d8);
		drinkService.save(d9);
		drinkService.save(d10);
		

// ----------------------------------------------------- TEST ---------------------------------------------------
	
	// Delete 
		promotionService.deletePromotionById(prom2.getId());	
				
	// Pizze
		System.out.println("-----------------------------------------------");
		
		List<Pizza> pizzasT = pizzaService.findAll();
		
		for(Pizza pizza : pizzasT) {
				System.out.println("----------- Result pizzas---------------\n" + pizza + "\n" + pizza.getPromotion());
		}
		
		
	// Promos
		System.out.println("\n-----------------------------------------------\n"); 
		
		List<Promotion> promotion = promotionService.findPizzas();
		
		for(Promotion promo : promotion) {
				
			System.out.println("----------- Result Promo: ---------------\n" + promo);
				
			for (Pizza pizza : promo.getPizzas()) {
				System.err.println("\nPizza associata:" 
									+ pizza);
			}
		}
		
		System.out.println("-----------------------------------------------");

		

	}
}
