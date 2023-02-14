package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.Ingredient;
import dto.Pizza;

public class PizzaDao {
	public Pizza find(int id) {
		try {
			Pizza res = new Pizza();
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection("jdbc:postgresql://psqlserv:5432/but2", "benoitmisplonetu",
					"moi");
			String query = "SELECT * from Pizza where id=?";

			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				res.setId(rs.getInt("id"));
				res.setName(rs.getString("name"));
				res.setIngredients();
				res.setTypePate(rs.getString("TypePate"));
				res.setPrixBase(rs.getInt("prix"));
			} else {
				return null;
			}
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Pizza> findAll() {
		try {
			List<Pizza> res = new ArrayList<Pizza>();
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection("jdbc:postgresql://psqlserv:5432/but2", "benoitmisplonetu",
					"moi");
			String query = "SELECT * from Pizza";

			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Pizza p = new Pizza();
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setIngredients();
				p.setTypePate(rs.getString("TypePate"));
				p.setPrixBase(rs.getInt("prix"));
				res.add(p);
			}
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public int save(Pizza pizza) {
		try {
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection("jdbc:postgresql://psqlserv:5432/but2", "benoitmisplonetu",
					"moi");
			String query = "Insert into Pizza values(?,?,?,?,?)";

			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, pizza.getId());
			ps.setString(2, pizza.getName());
			ps.setString(3, pizza.getIngredients());
			ps.setString(4, pizza.getTypePate());
			ps.setDouble(5, pizza.getPrixBase());

			ps.executeUpdate();
			return 0;

		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
	}
}
