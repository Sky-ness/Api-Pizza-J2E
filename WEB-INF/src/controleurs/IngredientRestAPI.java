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

import dao.IngredientDAO;
import dto.Ingredient;

@WebServlet("/ingredient/*")
public class IngredientRestAPI extends HttpServlet {
	
	IngredientDAO ingrDao ;
	
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		ingrDao = new IngredientDAO();
		System.out.println("Démarrage de la servlet");
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("application/json;charset=UTF-8");
		PrintWriter out = res.getWriter();
		String info = req.getPathInfo();
		String[] parts = null;
		String jsonstring = null;
		ObjectMapper mapper = new ObjectMapper();
		if (info != null) {
			parts = info.split("/");
			if (parts.length > 2) {
				try {
					int i = Integer.valueOf(parts[1]);
					String j = parts[2];
					if (ingrDao.findByIDAndName(i,j) != null) {
						jsonstring = mapper.writeValueAsString(ingrDao.findByIDAndName(i,j));
						out.println(jsonstring);
					} else {
						res.sendError(404);
					}
				} catch (Exception e) {
					res.sendError(404);
				}
			} else {
				try {

					int i = Integer.valueOf(parts[1]);
					if (ingrDao.findByID(i) != null) {
						jsonstring = mapper.writeValueAsString(ingrDao.findByID(i));
						out.println(jsonstring);
					} else {
						res.sendError(404);
					}
				} catch (Exception e) {
					res.sendError(404);
				}
			}
		} else {
			jsonstring = mapper.writeValueAsString(ingrDao.findAll());
			out.println(jsonstring);

		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		PrintWriter out = res.getWriter();
		res.setContentType("application/json;charset=UTF-8");
		StringBuilder data = new StringBuilder();
		BufferedReader reader = req.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			data.append(line);
		}

		ObjectMapper mapper = new ObjectMapper();

		try {

			Ingredient ingr = mapper.readValue(data.toString(), Ingredient.class);
			if (ingrDao.findByID(ingr.getId()) != null) {
				res.sendError(409);
			} else {
				ingrDao.save(ingr);
			}

		} catch (Exception e) {
			res.sendError(409);
		}

	}

	public void doDelete(HttpServletRequest req, HttpServletResponse res) throws IOException {
		PrintWriter out = res.getWriter();
		res.setContentType("application/json;charset=UTF-8");
	

		try {
			String info = req.getPathInfo();
			String[] parts = null;
			parts = info.split("/");
			int i = Integer.valueOf(parts[1]);
			
			if (ingrDao.findByID(i) != null) {
				ingrDao.deleteById(i);
			} else {
				res.sendError(409);
			}

		} catch (Exception e) {
			res.sendError(409);
		}
	}

}