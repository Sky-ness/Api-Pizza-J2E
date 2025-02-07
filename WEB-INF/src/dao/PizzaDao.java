package dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Ingredient;
import dto.Pizza;


public class PizzaDao {
	
	protected Connection con;

	public PizzaDao() {
		super();
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://psqlserv:5432/but2", "benoitmisplonetu", "moi");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Pizza> findAll() {
		try {
			List<Pizza> pizzas = new ArrayList<>();
			String query = "SELECT * FROM pizza";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				pizzas.add(this.findByID(rs.getInt("idP")));
			}
			return pizzas;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Pizza findByID(int id) {
		try {
			Pizza p = new Pizza();
			String query = "SELECT * from pizza where idP=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				p.setId(rs.getInt("idP"));
				p.setName(rs.getString("name"));
				p.setTypePate(rs.getString("TypePate"));
				p.setPrixBase(rs.getFloat("prixBase"));
			}else {
				return null;
			}
			try {
				List<Ingredient> ingredients = new ArrayList<>();
				query = "SELECT * from pizzaingredients where idp = ?";
				String query2 = "SELECT * from ingredient where id = ?";
				PreparedStatement ps2 = con.prepareStatement(query);
				ps2.setInt(1, p.getId());
				ResultSet rs2 = ps2.executeQuery();
				while(rs2.next()) {
					PreparedStatement ps3 = con.prepareStatement(query2);
					ps3.setInt(1, rs2.getInt("id"));
					ResultSet rs3 = ps3.executeQuery();
					if(rs3.next()) {
						Ingredient res = new Ingredient();
						res.setId(rs3.getInt("id"));
						res.setName(rs3.getString("name"));
						res.setPrix(rs3.getDouble("prix"));
						ingredients.add(res);
					}
				}
				p.setIngredients(ingredients);
				return p;
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			return p;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Double findByIdPrix(int id) {
			Pizza p = this.findByID(id);
			double res = 0;
			for(Ingredient ingredient : p.getIngredients()) {
				res += ingredient.getPrix();
			}
			res += p.getPrixBase();
			return res;
		
	}
	
	public void deleteById(int id) {
		try {
			String query = "Delete from ingredient where id=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1,id);
			ps.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void save(Pizza pizza) {
		try {
			String query = "Insert into pizza values(?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, pizza.getId());
			ps.setString(2, pizza.getName());
			ps.setString(3, pizza.getTypePate());
			ps.setDouble(4, pizza.getPrixBase());
			ps.executeUpdate();
			for(Ingredient ingredient : pizza.getIngredients()) {
				this.saveIngredientsPizza(pizza.getId(), ingredient.getId());
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	public int patchById(int id,String value) {
		String[] table = value.split(":");
		String update = "Update pizza set "+ table[0] +"=? where idP=?";
		try {
			PreparedStatement ps = con.prepareStatement(update);
			if(table[0].equals("prixBase")) {
				ps.setDouble(1, Double.valueOf(table[1]));
			}else ps.setString(1, table[1]);
			
			ps.setInt(2, id);
			System.out.println(ps.toString());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public void deletePizzaById(int id) {
		try {
			String query = "Delete from pizza where idp = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deletePizzaIngredientById(int idP,int id) {
		try {
			String query = "DELETE from pizzaingredients where idP =? and id = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, idP);
			ps.setInt(2, id);
			ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean checkPizzaIngredients(int idP,int id) {
		try {
			String query = "SELECT * from pizzaingredients where idp = ? and id = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, idP);
			ps.setInt(2, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void saveIngredientsPizza(int idP,int id) {
		try {
			String query = "INSERT INTO pizzaingredients values(?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, idP);
			ps.setInt(2, id);
			ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
