package controleurs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.IngredientDAO;
import dao.PizzaDao;
import dao.UserDao;
import dto.Ingredient;
import dto.Pizza;

@WebServlet("/pizza/*")
public class PizzaRestApi extends Patch {

	PizzaDao dao;
	UserDao protect;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.dao = new PizzaDao();
		this.protect = new UserDao();
		System.out.println("Démarrage de la servlet");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
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
			if (parts.length > 2 && parts[2].equals("prixfinal")) {
				try {
					int i = Integer.valueOf(parts[1]);
					if (dao.findByID(i) != null) {
						jsonString = objectMapper.writeValueAsString(dao.findByIdPrix(i));
					} else {
						res.sendError(404);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
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
		}
		out.println(jsonString);
		out.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
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
			if(parts == null) {
				Pizza p = objectMapper.readValue(data.toString(), Pizza.class);
				if(dao.findByID(p.getId()) == null) {
					dao.save(p);
				}else {
					res.sendError(409);
				}
			}else {

				Ingredient p = objectMapper.readValue(data.toString(), Ingredient.class);
				if (dao.findByID(Integer.valueOf(parts[1])) == null) {
					res.sendError(409);
				} else {
					dao.saveIngredientsPizza(Integer.valueOf(parts[1]), p.getId());
				}
			}
		}else {
			res.sendError(401);
		}

	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("application/json;charset=UTF-8");
		if(protect.verifTokenApi(req)) {
			String info = req.getPathInfo();
			String[] parts = null;
			parts = info.split("/");
			if(parts.length > 2) {
				try {
					int idP = Integer.valueOf(parts[1]);
					int id = Integer.valueOf(parts[2]);
					if(dao.checkPizzaIngredients(idP,id)) {
						dao.deletePizzaIngredientById(idP, id);					
					}else {
						res.sendError(409);
					}
				}catch(Exception e) {
					res.sendError(409);
				}
			}else {

				try {
					int i = Integer.valueOf(parts[1]);
					if (dao.findByID(i) != null) {;
					String line;
					dao.deletePizzaById(i);
					} else {
						res.sendError(409);
					}

				} catch (Exception e) {
					res.sendError(409);
				}
			}
		}else res.sendError(401);
	}

	@Override
	public void doPatch(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("application/json;charset=UTF-8");
		if(protect.verifTokenApi(req)) {
			String info = req.getPathInfo();
			String[] parts = null;
			parts = info.split("/");
			StringBuilder data = new StringBuilder();
			BufferedReader reader = req.getReader();
			String line;
			while ((line = reader.readLine()) != null) {
				data.append(line);
			}
			try {
				dao.patchById(Integer.valueOf(parts[1]), data.substring(1, data.length()-1).replaceAll("\"",""));
			} catch (Exception e) {
				res.sendError(409);
			}				
		}else res.sendError(401);



	}

}
