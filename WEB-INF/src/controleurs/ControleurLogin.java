package controleurs;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
@WebServlet("/users/token")
public class ControleurLogin extends HttpServlet{

	/*
	 * Page qui vérifie la connection
	 */
	
	UserDao users;
	
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		users = new UserDao();
		System.out.println("Démarrage de la servlet");
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		if(users.veriftoken(req)) {
			String token = users.createToken(req);
			out.println("Votre token est : "+token);
		}
		else {
			res.sendError(401);
		}
		out.close();
	}
}
