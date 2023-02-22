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
import dto.Ingredient;

@WebServlet("/commande/*")
public class CommandeRestAPI extends HttpServlet{
	CommandeDAO dao ;

	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		dao = new CommandeDAO();
		System.out.println("Démarrage de la servlet");
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("application/json;charset=UTF-8");
		PrintWriter out = res.getWriter();
		ObjectMapper objectMapper = new ObjectMapper();

		String info = req.getPathInfo();
		String jsonString = null;
		if (info == null) {
			jsonString = objectMapper.writeValueAsString(dao.findAll());
		} else {
			String[] parts = info.split("/");
			String param1 = parts[1];
			try {
				int i = Integer.valueOf(param1);
				if (dao.findByID(i) != null)
					jsonString = objectMapper.writeValueAsString(dao.findByID(i));
				else
					res.sendError(404);

			} catch (Exception e) {
				res.sendError(404);
			}

		}
		out.println(jsonString);
		out.close();
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		PrintWriter out = res.getWriter();
	}
}
