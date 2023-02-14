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

import dao.IngredientsDao;
import dao.PizzaDao;
import dto.Ingredient;
@WebServlet("/Pizza/*")
public class PizzaRestApi extends MyServlet{

	PizzaDao dao ;
	
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		dao = new PizzaDao();
		System.out.println("Démarrage de la servlet");
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("application/json;charset=UTF-8");
		PrintWriter out = res.getWriter();
		ObjectMapper objectMapper = new ObjectMapper();

		String info = req.getPathInfo();
		String jsonString = null;
		if(info == null) {
			jsonString = objectMapper.writeValueAsString(dao.findAll());	
		}
		else{
			String[] parts = info.split("/");
			String param1 = parts[1];
			try {
				int i = Integer.valueOf(param1);
				if (i>dao.findAll().size()) {
					res.sendError(404);
				}
				jsonString = objectMapper.writeValueAsString(dao.find(i));
			}catch (Exception e) {
				res.sendError(404);
			}
		}
		out.println(jsonString);
		out.close();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("application/json;charset=UTF-8");
		PrintWriter out = res.getWriter();
		ObjectMapper objectMapper = new ObjectMapper();

		StringBuilder data = new StringBuilder();
		BufferedReader reader = req.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			data.append(line);
		}
		Ingredient i = objectMapper.readValue(data.toString(), Ingredient.class);
		if(dao.find(i.getId()) != null) {
			res.sendError(409);	
		}
		else {
			dao.save(i);	
		}
		out.println(data);

	}
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("application/json;charset=UTF-8");
		PrintWriter out = res.getWriter();

	}
	@Override
	public void doPatch(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
	
}
