package dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Pizza {
	
	@JsonProperty
	private String name;
	@JsonProperty
	private int id;
	@JsonProperty
	private String typePate;
	@JsonProperty
	private double prixBase;
	@JsonProperty
	private List<Ingredient> ingredients;
	
	public Pizza() {
		super();
	}
	
	public Pizza(String name, int id, String typePate, double prixBase,List<Ingredient> ingredients) {
		super();
		this.name = name;
		this.id = id;
		this.typePate = typePate;
		this.prixBase = prixBase;
		this.ingredients = ingredients;
	}

	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getTypePate() {
		return typePate;
	}
	public double getPrixBase() {
		return prixBase;
	}
	public List<Ingredient> getIngredients() {
		return ingredients;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String nom) {
		this.name = nom;
	}

	public void setTypePate(String typePate) {
		this.typePate = typePate;
	}

	public void setPrixBase(double prixBase) {
		this.prixBase = prixBase;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
	@Override
	public String toString() {
		return "Pizza [name=" + name + ", typePate=" + typePate + ", prixBase=" + prixBase + "]";
	}	

}
