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
			List<Pizza> res = new ArrayList<Pizza>();
			String query = "SELECT * from Pizza JOIN Ingredient";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Pizza p = new Pizza();
				//TODO a modifier
				p.setId(rs.getInt("idP"));
				p.setName(rs.getString("name"));
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
	
	public Pizza findByID(int id) {
		try {
			Pizza p = new Pizza();
			String query = "SELECT * from ingredients where id=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				//TODO a modifier
				p.setId(rs.getInt("idP"));
				p.setName(rs.getString("name"));
				p.setTypePate(rs.getString("TypePate"));
				p.setPrixBase(rs.getInt("prix"));
			}else {
				return null;
			}
			return p;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void deleteById(int id) {
		try {
			String query = "Delete from ingredients where id=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1,id);
			ps.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int save(Pizza pizza) {
		try {
			String query = "Insert into Pizza values(?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			//TODO a modifier
			ps.setInt(1, pizza.getId());
			ps.setString(2, pizza.getName());
			ps.setString(4, pizza.getTypePate());
			ps.setDouble(5, pizza.getPrixBase());

			ps.executeUpdate();
			return 0;

		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
	}
	public int patchById() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
