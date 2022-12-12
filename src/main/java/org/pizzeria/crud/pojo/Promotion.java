package org.pizzeria.crud.pojo;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table
public class Promotion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull(message = "La data non può essere vuota")
	private LocalDate startDate;
	
	@NotNull (message = "La data non può essere vuota")
	private LocalDate endDate;
	
	@Column(unique = true)
	@NotEmpty(message = "Il titolo non può essere vuoto")
	@NotNull(message = "Il titolo non può essere null")
	private String title;
	
	@JoinColumn(nullable = false)
	@OneToMany(mappedBy = "promotion", cascade = CascadeType.REMOVE)
	private List<Pizza> pizzas;
	
	public Promotion(String startDate, String endDate, String title) {
		setStartDate(startDate);
		setEndDate(endDate);
		setTitle(title);
	}
	
//  start_date
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		LocalDate lsDt = LocalDate.parse(startDate);
		this.startDate = lsDt;
	}

//  End date
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {

		LocalDate leDt = LocalDate.parse(endDate);
		this.endDate = leDt;
	}
	
// Title
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
// Pizzas
	public List<Pizza> getPizzas() {
		return pizzas;
	}
	public void setPizzas(List<Pizza> pizzas) {
		this.pizzas = pizzas;
	}
	
	
	@Override
	public String toString() {
		return  "Titolo: " + getTitle() 
				+ "\nInzio: " + getStartDate()
				+ "\nFine: " + getEndDate();
	}
	
	
}
