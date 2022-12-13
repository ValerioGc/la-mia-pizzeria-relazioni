package org.pizzeria.crud.pojo;


import java.util.List;

import org.pizzeria.crud.intf.PriceableInt;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

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
	
	@ManyToOne
	@JoinColumn(nullable = true)
	private Promotion promotion;

	@ManyToMany
	private List<Ingredient> ingredients;
	
	
	
	public Pizza() { }
	public Pizza(String name, String description, int price) {
		setName(name);
		setDescription(description);
		setPrice(price);
	}
	// con promozioni
	public Pizza(String name, String description, int price, Promotion promotion) {
		this(name, description, price);
		setPromotion(promotion);
	}
	// con ingredienti
	public Pizza(String name, String description, int price, List<Ingredient> ingredients) {
		this(name, description, price);
		setIngredients(ingredients);
	}
	// con ingredienti e promozioni
	public Pizza(String name, String description, int price, Promotion promotion, List<Ingredient> ingredients) {
		this(name, description, price, promotion);
		setIngredients(ingredients);
	}

	
// ID
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

//  Name
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

//  Description
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
//  promo 
	public Promotion getPromotion() {
		return promotion;
	}
	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}
	
// Ingredients
	public List<Ingredient> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
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

