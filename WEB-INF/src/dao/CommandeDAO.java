package dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dto.Commande;
import dto.Ingredient;
import dto.Pizza;

public class CommandeDAO {

	protected Connection con;
	protected PizzaDao pizzaDao;

	public CommandeDAO() {
		super();
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://psqlserv:5432/but2", "benoitmisplonetu", "moi");
			this.pizzaDao = new PizzaDao();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Commande> findAll() {
		try {
			List<Commande> commandes = new ArrayList<>();
			String query = "SELECT * FROM commande";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				commandes.add(this.findByID(rs.getInt("idC")));
			}
			return commandes;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Commande findByID(int id) {
		try {
			Commande c = new Commande();
			String query = "SELECT * from commande where idC=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				c.setId(rs.getInt("idC"));
				c.setDate(rs.getDate("dateC"));
			}else {
				return null;
			}
			try {
				List<Pizza> pizzas = new ArrayList<>();
				query = "SELECT * from commandepizzas where idC = ?";
				PreparedStatement ps2 = con.prepareStatement(query);
				ps2.setInt(1, c.getId());
				ResultSet rs2 = ps2.executeQuery();
				while(rs2.next()) {
					Pizza res = this.pizzaDao.findByID(rs2.getInt("idP"));
					pizzas.add(res);
				}
				c.setPizzas(pizzas);
				return c;
			}catch(Exception e) {
				e.printStackTrace();
			}

			return c;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void save(Commande commande) {
		try {
			String query = "Insert into commande values(?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, commande.getId());
			java.sql.Date sqlDate = new java.sql.Date(commande.getDate().getTime());
			ps.setDate(2, sqlDate);
			ps.executeUpdate();
			for(Pizza pizza : commande.getPizzas()) {
				this.savePizzaCommande(commande.getId(), pizza.getId());
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	private void savePizzaCommande(int idC, int idP) {
		try {
			String query = "INSERT INTO commandepizzas values(?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, idC);
			ps.setInt(2, idP);
			ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public double cost(Commande commande) {
		double prixFinal=0.0;
		try {
			for(Pizza p:commande.getPizzas()) {
				prixFinal+= this.pizzaDao.findByIdPrix(p.getId());
			}
			return prixFinal;
		} catch (Exception e) {
			e.printStackTrace();
			return -1.0;
		}
	}
}
