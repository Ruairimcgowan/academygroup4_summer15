package cititradeweb.rest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.*;

import org.jboss.logging.Logger;

@Path("/symbols")
public class Symbols {
	
	@GET
	@Produces("text/plain")
	public String getText(@QueryParam("str") String str) throws SQLException{

		String temp = "";
		Connection cn = null;

		try{
			cn = getConnection();
			PreparedStatement pst = cn.prepareStatement("SELECT symbol FROM symbols");
			ResultSet rs = pst.executeQuery();			

			//temp += "<input type=\"text\" list=\"Contacts\">";
			
			temp += "<datalist id=\"Symbols\">";

			while(rs.next()){		
				temp += "<option value =\"" + rs.getString(1) +"\">" + rs.getString(1) + "</option>";
			}
			temp += "</datalist>";
		}
		catch(SQLException ex){
			System.out.println("Error getting data: " + ex);
			Logger log = Logger.getLogger(Symbols.class.getClass());
			log.error("ERROR "+ ex.getMessage());
		}
		finally{
			if(cn != null){
				cn.close();
			}
		}
		//System.out.println(temp);
		return temp;
	}
		
	public static Connection getConnection(){

		Connection cn = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://localhost/cititrade", "root", "password");
		}
		catch(SQLException ex){
			System.out.println("Database connection error: " + ex);
			Logger log = Logger.getLogger(Symbols.class.getClass());
			log.error("ERROR "+ ex.getMessage());
		}
		catch(ClassNotFoundException ex){
			System.out.println("Class not found: " + ex);
			Logger log = Logger.getLogger(Symbols.class.getClass());
			log.error("ERROR "+ ex.getMessage());
		}
		return cn;
	}

}
