package cititradeweb.rest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.*;

import org.jboss.logging.Logger;

import cititradeweb.dal.DataAccess;
import cititradeweb.dataobjects.StockObject;
import cititradeweb.ordermanager.OrderManager;

@Path("/quotes")
public class MostRecentQuotes {
	
	@GET
	@Produces("text/plain")
	public String getText(@QueryParam("str") String str) throws SQLException{

		String temp = "";
		Connection cn = null;

		try{
			List<StockObject> quotes = DataAccess.returnMostRecentQuote();
			//System.out.println(quotes);

			//temp += "<input type=\"text\" list=\"Contacts\">";
			
			temp += "<table style= 'border: 1px'><tr><th style= 'border: 1px'>ID</th><th style= 'border: 1px'>Symbol</th><th style= 'border: 1px'>Bid Price</th><th style= 'border: 1px'>Ask Price </th></tr>";

			for(StockObject s: quotes){		
				temp += "<tr><td style= 'border: 1px solid black'>" + s.getStockSymbol() + "</td>" + "<td style= 'border: 1px solid black'>" + s.getAskPrice() + "</td>" + "<td style= 'border: 1px solid black'>" + s.getBidPrice() + "</td></tr>" ;
			}
			temp += "</table>";
		}
		catch(SQLException ex){
			System.out.println("Error getting data: " + ex);
			Logger log = Logger.getLogger(MostRecentQuotes.class.getClass());
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
			Logger log = Logger.getLogger(MostRecentQuotes.class.getClass());
			log.error("ERROR "+ ex.getMessage());
		}
		catch(ClassNotFoundException ex){
			System.out.println("Class not found: " + ex);
			Logger log = Logger.getLogger(MostRecentQuotes.class.getClass());
			log.error("ERROR "+ ex.getMessage());
		}
		return cn;
	}

}
