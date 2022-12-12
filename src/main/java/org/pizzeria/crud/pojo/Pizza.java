package org.pizzeria.crud.pojo;

import java.util.List;

import org.pizzeria.crud.intf.PriceableInt;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


@Entity
@Table
public class Pizza implements PriceableInt {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(unique = true, nullable = false)
	@NotEmpty(message = "Il nome della pizza non deve essere vuoto")
	@NotNull(message = "Il nome della pizza non deve essere vuoto")
	private String name;
	
	@Lob
	private String description;
	
	@Min(value = 1, message = "Il prezzo deve essere superiore a 0€")
	@Max(value = 100, message = "Il prezzo non deve essere superiore a 100€")
	@NotNull(message = "Il prezzo non deve esser vuoto")
	private int price;
	
	
	@OneToMany(mappedBy = "pizza", cascade = CascadeType.REMOVE)
	private List<Promotion> promotion;
	
	
	public Pizza() { }
	public Pizza(String name, String description, int price, List<Promotion> promotion) {
		setName(name);
		setDescription(description);
		setPrice(price);
		setPromotions(promotion);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
// promo 
	public List<Promotion> getPromotions() {
		return promotion;
	}
	public List<Promotion> setPromotions(List<Promotion> promotion) {
		return this.promotion = promotion;
	}
	
	
// price
	@Override
	public int getPrice() {
		return price;
	}

	@Override
	public void setPrice(int price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "\nNome: " + getName()
			+ "\nDescription: " + getDescription()
			+ "\nPrice: " + getPrice();
	}
	
}

