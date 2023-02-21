package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Base64.Decoder;

import javax.servlet.http.HttpServletRequest;

public class UserDao {

	Connection con;

	public UserDao() {
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://psqlserv:5432/but2", "benoitmisplonetu", "moi");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	public boolean veriftoken(HttpServletRequest req) {
//		String connect = req.getHeader("Authorization");
//		Decoder decoder = Base64.getDecoder();
//		if(!connect.isBlank() && connect.startsWith("Basic") || !connect.isEmpty() && connect.startsWith("Basic")) {
//			String[] split = connect.split(" ");	
//
//			String data = new String(decoder.decode(split[1]));
//			String[] loginMdp = data.split(":");
//			String query = "Select * from users where login=? AND pwd=?";
//			try {
//				PreparedStatement ps = con.prepareStatement(query);
//				ps.setString(1, loginMdp[0]);
//				ps.setString(2, loginMdp[1]);
//				ResultSet rs = ps.executeQuery();
//				if (rs.next()) {
//					return true;
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return false;
//
//	}

	public boolean veriftoken(HttpServletRequest req) {
	String login = req.getParameter("login");
	String password = req.getParameter("password");
	Decoder decoder = Base64.getDecoder();
	
		String query = "Select * from users where login=? AND pwd=?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, login);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}


	public String createToken(HttpServletRequest req) {
		String login = req.getParameter("login");
		String mdp = req.getParameter("password");
		String encode = login+":"+mdp;
		String token = Base64.getEncoder().encodeToString(encode.getBytes());
		return token;		
	}
}
