package cititradeweb.actions;

import cititradeweb.ordermanager.OrderManager;
import cititradeweb.ordermanager.OrderManager.OrderResult;

public class Orders {
	
	public static void main(String[] args){
		OrderResult or = OrderManager.getInstance().buyOrder("AAPL", 10.00, 50);
	}

}
