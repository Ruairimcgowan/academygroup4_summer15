package cititradeweb.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;

import cititradeweb.actions.Orders;
import cititradeweb.dal.DataAccess;
import cititradeweb.dataobjects.StockObject;
/**
 * Servlet implementation class getTopQuotesServlet
 */
@WebServlet("/getTopQuotesServlet")
public class getTopQuotesServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getTopQuotesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h2>Top Quote List: </h2>");
		out.println ("<table style= 'border: 1px'>");
		out.println ("<th style= 'border: 1px'>Id</th>");
		out.println ("<th style= 'border: 1px'>Symbol</th>");
		out.println ("<th style= 'border: 1px'>Bid Price</th>");
		out.println ("<th style= 'border: 1px'>Ask Price </th></tr>");
		try{
		List <StockObject> stocks = DataAccess.returnMostRecentQuote();
			for (StockObject so : stocks) {
				out.println("<tr><td style= 'border: 1px solid black'>" + so.getId() + "</td>");
				//out.println("<td style= 'border: 1px solid black'><a href= 'EditShipper.jsp?id=" + p.getId() +"'>" + p.getShipName() + "</a></td>");
				out.println("<td style= 'border: 1px solid black'>" + so.getStockSymbol() + "</td>");
				out.println("<td style= 'border: 1px solid black'>" + so.getBidPrice() + "</td>");
				out.println("<td style= 'border: 1px solid black'>" + so.getAskPrice() + "</td>");
				//out.println("<td style='border: 1px solid black'><a href= 'DeleteShipper.jsp?id=" + p.getId() + "'>Delete</a></td></tr>");
			
			}
		out.println("</table>");
		}
		catch (SQLException e){
			out.print("error" + e);
		}
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<h2>Top Quote List: </h2>");
			
			try {
				out.println ("<style> tr:nth-child(odd) {background-color: yellow; } ");
				out.println ("tr:nth-child(even) { background-color: orange; }</style> ");
			out.println ("<table style= 'border: 1px'>");
			out.println ("<th style= 'border: 1px'>Id</th>");
			out.println ("<th style= 'border: 1px'>Symbol</th>");
			out.println ("<th style= 'border: 1px'>Bid Price</th>");
			out.println ("<th style= 'border: 1px'>Ask Price </th></tr>");

			List <StockObject> stocks = DataAccess.returnMostRecentQuote();
				for (StockObject so : stocks) {
					out.println("<tr><td style= 'border: 1px solid black'>" + so.getId() + "</td>");
					//out.println("<td style= 'border: 1px solid black'><a href= 'EditShipper.jsp?id=" + p.getId() +"'>" + p.getShipName() + "</a></td>");
					out.println("<td style= 'border: 1px solid black'>" + so.getStockSymbol() + "</td>");
					out.println("<td style= 'border: 1px solid black'>" + so.getBidPrice() + "</td>");
					out.println("<td style= 'border: 1px solid black'>" + so.getAskPrice() + "</td>");
					//out.println("<td style='border: 1px solid black'><a href= 'DeleteShipper.jsp?id=" + p.getId() + "'>Delete</a></td></tr>");
				
				}
			out.println("</table>");
			}
			catch (SQLException e){
				out.print("error" + e);
				Logger log = Logger.getLogger(getTopQuotesServlet.class.getClass());
				log.error("ERROR "+ e.getMessage());
			}
		}

			



}

