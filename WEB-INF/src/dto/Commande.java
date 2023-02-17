package dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Commande {
	@JsonProperty
	private int id;
	@JsonProperty
	private Date date;
	@JsonProperty
	private List<Pizza> pizzas;
	
	public Commande() {
		super();
	}
	
	public Commande(int id, Date date, List<Pizza> pizzas) {
		super();
		this.id = id;
		this.date = date;
		this.pizzas = pizzas;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public List<Pizza> getPizzas() {
		return pizzas;
	}
	public void setPizzas(List<Pizza> pizzas) {
		this.pizzas = pizzas;
	}
	
}
