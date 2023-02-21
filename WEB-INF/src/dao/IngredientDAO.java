package dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.Ingredient;

public class IngredientDAO {
	

	protected Connection con;

	public IngredientDAO() {
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://psqlserv:5432/but2", "benoitmisplonetu", "moi");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Ingredient findByID(int id) {
		try {
			Ingredient res = new Ingredient();
			String query = "SELECT * from ingredients where id=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				res.setId(rs.getInt("id"));
				res.setName(rs.getString("name"));
				res.setPrix(rs.getDouble("prix"));
			}else {
				return null;
			}
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void deleteById(int id) {
		try {
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection("jdbc:postgresql://psqlserv:5432/but2", "benoitmisplonetu", "moi");
			String query = "Delete from ingredients where id=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1,id);
			ps.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public String findByIDAndName(int id, String name) {
		try {
			Ingredient res = new Ingredient();
			String query = "SELECT * from ingredients where id=? and name=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1,id);
			ps.setString(2, name);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				res.setId(rs.getInt("id"));
				res.setName(rs.getString("name"));
			}else {
				return null;
			}
			return res.getName();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Ingredient> findAll() {
		try {
			List<Ingredient> res = new ArrayList<>();
			String query = "SELECT * from ingredients";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Ingredient ingr = new Ingredient();
				ingr.setId(rs.getInt("id"));
				ingr.setName(rs.getString("name"));
				ingr.setPrix(rs.getDouble("prix"));
				res.add(ingr);	
			}
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Ingredient save(Ingredient ingr) {
		try {
			String query = "INSERT INTO ingredients VALUES (?, ?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, ingr.getId());
			ps.setString(2, ingr.getName());
			ps.executeUpdate();
			return ingr;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
