package cititradeweb.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import cititradeweb.actions.GetStockSymbolsFromCSV;
import cititradeweb.dataobjects.StockObject;

public class DataAccess {

	public static Connection getConnection(){

		Connection cn = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://localhost/cititrade", "root", "password");
		}
		catch(SQLException ex){
			System.out.println("Database connection error: " + ex);
			Logger log = Logger.getLogger(DataAccess.class.getClass());
			log.error("ERROR "+ ex.getMessage());
		}
		catch(ClassNotFoundException ex){
			System.out.println("Class not found: " + ex);
			Logger log = Logger.getLogger(DataAccess.class.getClass());
			log.error("ERROR "+ ex.getMessage());
		}
		return cn;
	}
	
	public static void addFavouriteStock(StockObject stock) throws SQLException{
		
		Connection cn = null;

		try{
			cn = getConnection();
			String query = "INSERT INTO " + stock + "')";
			System.out.println(query);
			Statement st = cn.createStatement();
			st.executeUpdate(query);
			
		}
		catch(SQLException ex){
			System.out.println("Error getting data: " + ex);
		}
		finally{
			if(cn != null){
				cn.close();
			}
		}
		
	}
	
	public static void test () {
		
	}

	public static void addStockQuote(String symbol, String askPrice, String bidPrice, String change, String changePercent, String open, String close) throws SQLException{

		Connection cn = null;

		try{
			cn = getConnection();
			if(!symbol.contains("^") && !symbol.contains(".") && !symbol.contains("'") && !symbol.contains("''")){
				/*PreparedStatement pst = cn.prepareStatement("INSERT INTO quotes(symbol, askprice, bidprice, changed, changedpercent, open, close) VALUES (?, ?, ?, ?, ?, ?, ?)");

				pst.setString(1, symbol.replaceAll("\"", ""));
				if(!askPrice.equals("N/A") || !bidPrice.equals("N/A")){
					pst.setDouble(2, Double.parseDouble(askPrice));
					pst.setDouble(3, Double.parseDouble(bidPrice));
					pst.setDouble(4, Double.parseDouble(change));
					pst.setDouble(5, Double.parseDouble(changePercent));
					pst.setDouble(6, Double.parseDouble(open));
					pst.setDouble(7, Double.parseDouble(close));
				}else{
					System.out.println("No ask/bid price data for " + symbol);
				}					

				int rows = pst.executeUpdate();

				if(rows == 1){
					System.out.println(symbol + " added to quotes table.");
				}*/

				String query = "INSERT INTO " + symbol.toLowerCase().replaceAll("\"", "").replaceAll("'", "") + " (symbol, askprice, bidprice, changed, changedPercent, open, close) VALUES ('" + symbol.replaceAll("\"", "") + "', '" + Double.parseDouble(askPrice) + "', '" + Double.parseDouble(bidPrice) + "', '" + Double.parseDouble(change) + "', '" + changePercent + "', '" + Double.parseDouble(open) + "', '" + Double.parseDouble(close) + "')";
				System.out.println(query);
				Statement st = cn.createStatement();
				st.executeUpdate(query);
			}
		}
		catch(SQLException ex){
			System.out.println("Error getting data: " + ex);
			Logger log = Logger.getLogger(DataAccess.class.getClass());
			log.error("ERROR "+ ex.getMessage());
		}
		finally{
			if(cn != null){
				cn.close();
			}
		}
	}

<<<<<<< HEAD
	/*public static void initTable() throws SQLException{

		Connection cn = null;
		String createStatement = "CREATE TABLE quotes (id int auto_increment PRIMARY KEY, stocktime TIMESTAMP DEFAULT CURRENT_TIMESTAMP, symbol nvarchar(100) NOT NULL, askprice DECIMAL(10,2) NOT NULL, bidprice DECIMAL(10,2) NOT NULL, askmoving DECIMAL(10,2) NOT NULL, bidmoving DECIMAL(10,2) NOT NULL, capture int NOT NULL, movingaverage int NOT NULL);";

		try{
			cn = getConnection();
			PreparedStatement pst = cn.prepareStatement("DROP TABLE quotes");
			pst.executeUpdate();
			PreparedStatement pst2 = cn.prepareStatement(createStatement);
			int rows = pst2.executeUpdate();

			if(rows == 1){
				System.out.println("Quotes table created!");
			}
		}
		catch(SQLException ex){
			System.out.println("Error getting data: " + ex);
			Logger log = Logger.getLogger(DataAccess.class.getClass());
			log.error("ERROR "+ ex.getMessage());
		}
		finally{
			if(cn != null){
				cn.close();
			}
		}
	}*/

=======
>>>>>>> origin/master
	public static void createTablesFromStockSymbols(String symbol) throws SQLException{

		Connection cn = null;

		try{
			cn = getConnection();
			if(!symbol.contains("^") && !symbol.contains(".") && !symbol.contains("'") && !symbol.contains("''")){	//change to statements
				/*PreparedStatement pst = cn.prepareStatement("CREATE TABLE ? (id int auto_increment PRIMARY KEY, stocktime TIMESTAMP DEFAULT CURRENT_TIMESTAMP, symbol nvarchar(100) NOT NULL, askprice DECIMAL(10,2) NOT NULL, bidprice DECIMAL(10,2) NOT NULL, askmoving DECIMAL(10,2) NOT NULL, bidmoving DECIMAL(10,2) NOT NULL, capture int NOT NULL, movingaverage int NOT NULL);");
				pst.setString(1, symbol.replaceAll("\'", ""));
				int rows = pst.executeUpdate();

				if(rows == 1){
					System.out.printf("\n%s table created!", symbol);
				}*/

				PreparedStatement pst = cn.prepareStatement("CREATE TABLE IF NOT EXISTS symbols (id int auto_increment PRIMARY KEY, symbol nvarchar(100));");
				int rows = pst.executeUpdate();
				if(rows == 1){
					System.out.println("Symbols table created!");
				}
				
				PreparedStatement pst2 = cn.prepareStatement("INSERT INTO symbols (symbol) VALUES(?);");
				pst2.setString(1, symbol);
				int rows2 = pst2.executeUpdate();
				if(rows2 == 1){
					System.out.println("Symbol added!");
				}
				
				String query = "CREATE TABLE " + symbol.toLowerCase() + " (id int auto_increment PRIMARY KEY, stocktime TIMESTAMP DEFAULT CURRENT_TIMESTAMP, symbol nvarchar(100) NOT NULL, askprice DECIMAL(10,2) NOT NULL, bidprice DECIMAL(10,2) NOT NULL, changed DECIMAL(10,2) NOT NULL DEFAULT 0, changedPercent nvarchar(100) NOT NULL DEFAULT 0, open DECIMAL(10,2) NOT NULL DEFAULT 0, close DECIMAL(10,2) NOT NULL DEFAULT 0);";
				System.out.println(query);
				//symbol, askprice, bidprice, change, changepercent, open, close

				Statement st = cn.createStatement();
				st.executeUpdate(query);
				//System.out.println(symbol + " table created!");				
			}			
		}
		catch(SQLException ex){
			System.out.println("Error getting data: " + ex);
			Logger log = Logger.getLogger(DataAccess.class.getClass());
			log.error("ERROR "+ ex.getMessage());
		}
		finally{
			if(cn != null){
				cn.close();
			}
		}
	}
	
	public List<String> getSymbolsFromDataBase() throws SQLException{
		
		List<String> temp = null;
		Connection con = null;
		try {
			con = getConnection ();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT symbol FROM symbols");
			while (rs.next()) {
				temp.add(rs.getString(1));
			}//while
		} //try 
		catch (SQLException ex) {
			System.out.println("Error getting data " + ex);
		}

		finally {
			if (con != null) {
				con.close();
			}
		}
		return temp;		
	}	

	public static void dropTablesFromStockSymbols(String symbol) throws SQLException{

		Connection cn = null;

		try{
			cn = getConnection();
			if(!symbol.contains("^") && !symbol.contains(".") && !symbol.contains("'") && !symbol.contains("''")){
				
				String query = "DROP TABLE " + symbol;
				Statement st = cn.createStatement();
				st.executeUpdate(query);
			}
		}
		catch(SQLException ex){
			System.out.println("Error getting data: " + ex);
			Logger log = Logger.getLogger(DataAccess.class.getClass());
			log.error("ERROR "+ ex.getMessage());
		}
		finally{
			if(cn != null){
				cn.close();
			}
		}
	}

	public static List<StockObject> getStockData (String stockSymbol) throws SQLException {

		List<StockObject> temp = new ArrayList <StockObject>();
		Connection con = null;
		try {
			con = getConnection ();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM symbol WHERE symbol='" + stockSymbol + "'");
			while (rs.next()) {
				temp.add(new StockObject (rs.getInt("id"), rs.getDate("stocktime"),rs.getString("symbol"),
						rs.getDouble("askprice"),
						rs.getDouble("bidprice"),
						rs.getDouble("open"),
						rs.getDouble("close")));
			}//while
		} //try 
		catch (SQLException ex) {
			System.out.println("Error getting data " + ex);
			Logger log = Logger.getLogger(DataAccess.class.getClass());
			log.error("ERROR "+ ex.getMessage());
		}

		finally {
			if (con != null) {
				con.close();
			}
		}
		return temp;
	}

	public static List<StockObject> returnMostRecentQuote() throws SQLException {

		List<String> listOfSymbols = GetStockSymbolsFromCSV.getSymbols();
		//System.out.println(listOfSymbols);
		List<StockObject> stocks = new ArrayList <>();
		Connection con = null;
		con = getConnection();
		try {
			for (String s : listOfSymbols) {	

				Statement st = con.createStatement();
				if(!s.equals("symbol")){
				ResultSet rs = st.executeQuery("SELECT * FROM " + s + " ORDER BY stocktime DESC LIMIT 1");	
				while (rs.next()) {
					stocks.add (new StockObject (rs.getInt("id"), rs.getDate("stocktime"),rs.getString("symbol"),
							rs.getDouble("askprice"),
							rs.getDouble("bidprice"),
							rs.getDouble("open"),
							rs.getDouble("close")));
				}//while
				}
			}//for
		}//try
		catch (SQLException ex) {
			System.out.println("Error getting data " + ex);
			Logger log = Logger.getLogger(DataAccess.class.getClass());
			log.error("ERROR "+ ex.getMessage());
		}

		finally {

			if (con != null) {
				//System.out.println("No files");
				con.close();
			}//if
		}//finally
		return stocks;	

	}
	

}
