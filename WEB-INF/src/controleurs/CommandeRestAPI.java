package controleurs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.CommandeDAO;
import dao.UserDao;
import dto.Commande;
import dto.Ingredient;
import dto.Pizza;

@WebServlet("/commande/*")
public class CommandeRestAPI extends HttpServlet{

	CommandeDAO dao ;
	UserDao protect;

	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		this.dao = new CommandeDAO();
		this.protect = new UserDao();
		System.out.println("Démarrage de la servlet");
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("application/json;charset=UTF-8");
		PrintWriter out = res.getWriter();
		ObjectMapper objectMapper = new ObjectMapper();
		
		String info = req.getPathInfo();
		String jsonString = null;
		if (info == null) {
			jsonString = objectMapper.writeValueAsString(this.dao.findAll());
		} else {
			String[] parts = info.split("/");
			String param1 = parts[1];
			try {
				int i = Integer.valueOf(param1);
				if (dao.findByID(i) != null)
					jsonString = objectMapper.writeValueAsString(this.dao.findByID(i));
				else
					res.sendError(404);
			} catch (Exception e) {
				res.sendError(404);
			}
			if(parts.length == 3) {
				try {
					String param2 = parts[2];
					int i = Integer.valueOf(param1);
					if (param2.equals("prixfinal"))
						jsonString = objectMapper.writeValueAsString(this.dao.cost(this.dao.findByID(i)));
					else
						res.sendError(404);
				} catch (Exception e) {
					res.sendError(404);
				}
			}
		}
		out.println(jsonString);
		out.close();
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("application/json;charset=UTF-8");
		if(protect.verifTokenApi(req)) {
			ObjectMapper objectMapper = new ObjectMapper();
			StringBuilder data = new StringBuilder();
			BufferedReader reader = req.getReader();
			String line;
			while ((line = reader.readLine()) != null) {
				data.append(line);
			}
			String info = req.getPathInfo();
			String[] parts = null;
			if(info != null) parts = info.split("/");
			else {
				Commande c = objectMapper.readValue(data.toString(), Commande.class);
				if(dao.findByID(c.getId()) == null) {
					dao.save(c);
				}else {
					res.sendError(409);
				}
			}
		}
		else {
			res.sendError(401);
		}

	}
}
