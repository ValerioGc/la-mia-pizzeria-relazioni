package org.pizzeria.crud.serv;

import java.util.List;
import java.util.Optional;


import org.pizzeria.crud.pojo.Promotion;
import org.pizzeria.crud.repo.PromotionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PromotionService {
	
	@Autowired
	private PromotionRepo promotionRepo;
	
	public void save(Promotion promotion) {
		promotionRepo.save(promotion);
	}
	
	public List<Promotion> findAll() {
		return promotionRepo.findAll();
	}
	
	public Optional<Promotion> findPromotionById(int id) {
		return promotionRepo.findById(id);
	}
	
	public void deletePromotionById(Promotion promotion) {
		promotionRepo.delete(promotion);
	}
}
