package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import dto.Commande;
import dto.Ingredient;

public class CommandeDAO {

	protected Connection con;

	public void CommandeDAO() {
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://psqlserv:5432/but2", "benoitmisplonetu", "moi");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Commande> findAll() {
		try {
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Commande findByID(int id) {
		try {
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Commande save(Commande commande) {
		try {
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public double cost(Commande commande) {
		try {
			return 0.0;
		} catch (Exception e) {
			e.printStackTrace();
			return 0.0;
		}
	}
}
