package org.pizzeria.crud.pojo;

import org.pizzeria.crud.repo.IngredientsRepo;
import org.pizzeria.crud.serv.IngredientsService;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table
public class Ingredients {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	@Column(unique = true, nullable = false )
	@NotNull(message = "Il nome dell'ingrediente non deve essere vuoto")
	@NotEmpty(message = "Il nome dell'ingrediente non deve essere vuoto")
	private String name;
	
	
	
	public Ingredients() { }
	public Ingredients(String name) {
		setName(name);
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
	
	
	@Override
	public String toString() {
		return "\nNome: " + getName();
	}
}
