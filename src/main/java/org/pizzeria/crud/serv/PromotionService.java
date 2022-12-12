package org.pizzeria.crud.serv;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.pizzeria.crud.pojo.Pizza;
import org.pizzeria.crud.pojo.Promotion;
import org.pizzeria.crud.repo.PromotionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;


@Service
public class PromotionService {
	
	@Autowired
	private PromotionRepo promotionRepo;
	
	public void save(Promotion promotion) {
		promotionRepo.save(promotion);
	}
	
	public Optional<Promotion> findPromotionById(int id) {
		return promotionRepo.findById(id);
	}
	
	public void deletePromotionById(int id) {
		promotionRepo.deleteById(id);
	}
	
	public List<Promotion> findAll() {
		return promotionRepo.findAll();
	}
	@Transactional
	public List<Promotion> findPizzas() {
		
		List<Promotion> promotions  = promotionRepo.findAll();
		
		for (Promotion promotion : promotions ) {
			Hibernate.initialize(promotion.getPizzas());
		}
		
		return promotions;
	}
}
