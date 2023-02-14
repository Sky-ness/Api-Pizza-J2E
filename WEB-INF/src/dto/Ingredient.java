package dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Ingredient {
	@JsonProperty
	private int id;
	@JsonProperty
	private String name;
	@JsonProperty
	private double prix;
		
		
		public Ingredient() {
			
		}
		
		public Ingredient(int id, String name, double prix) {
			
			this.id = id;
			this.name = name;
			this.prix = prix;
		}
		
		
		
		



		@Override
		public String toString() {
			return "Ingredient [id=" + id + ", name=" + name + ", prix=" + prix + "]";
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

		public double getPrix() {
			return prix;
		}

		public void setPrix(double prix) {
			this.prix = prix;
		}

		
		
		
		
		

}
