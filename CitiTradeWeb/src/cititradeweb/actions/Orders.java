package cititradeweb.actions;

import cititradeweb.ordermanager.OrderManager;
import cititradeweb.ordermanager.OrderManager.OrderResult;

import org.jboss.logging.Logger;

public class Orders {
	
	public static void main(String[] args){
		
		try{
			
		OrderResult or = OrderManager.getInstance().buyOrder("AAPL", 10.00, 50);
		
		}catch(Exception e){
			Logger log = Logger.getLogger(Orders.class.getClass());
			log.error("ERROR "+ e.getMessage());
		}
	}

}
