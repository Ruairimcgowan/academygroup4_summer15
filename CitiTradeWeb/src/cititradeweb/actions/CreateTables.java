package cititradeweb.actions;

import java.util.ArrayList;
import java.util.List;
import org.jboss.logging.*;
import cititradeweb.dal.DataAccess;

public class CreateTables {

	public static void main(String[] args) throws Exception{
		
		try{
		
		List<String> symbols = new ArrayList<String>();
		symbols = GetStockSymbolsFromCSV.getSymbols();
		
		for(String s: symbols){
			DataAccess.dropTablesFromStockSymbols(s);
			DataAccess.createTablesFromStockSymbols(s);
		}
		
		}catch(Exception e){
			Logger log = Logger.getLogger(CreateTables.class.getClass());
			log.error("ERROR "+ e.getMessage());
		}
	}
}
