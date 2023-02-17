package controleurs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.CommandeDAO;
import dto.Ingredient;

public class CommandeRestAPI extends HttpServlet{
	CommandeDAO commandeDao ;
	
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		commandeDao = new CommandeDAO();
		System.out.println("Démarrage de la servlet");
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("application/json;charset=UTF-8");
		PrintWriter out = res.getWriter();
		
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		PrintWriter out = res.getWriter();
	}
}
