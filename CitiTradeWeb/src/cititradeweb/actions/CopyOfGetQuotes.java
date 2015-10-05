package cititradeweb.actions;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class CopyOfGetQuotes {

	public static void DELETETHISCLASS(String[] args) throws Exception{
		// TODO Auto-generated method stub
		Scanner kb = new Scanner(System.in);
		String sym = "";
		int movAvg = 0, capture = 0;
		double bidTotal = 0, askTotal = 0, bidAvg = 0, askAvg = 0, bidTemp = 0, askTemp = 0;
		int ind = 0;

		System.out.print("\nPlease enter the stock symbol you wish you query: ");
		sym = kb.next().toUpperCase();
		System.out.print("\nPlease enter the moving average number of trades: ");
		movAvg = kb.nextInt();
		System.out.print("\nPlease enter capture number of trades: ");
		capture = kb.nextInt();		

		double[] bidMoving = new double[capture];
		double[] askMoving = new double[capture];

		System.out.println("\nSymbol, Ask Price, Bid Price");
		//DataAccess.initTable();

		for(int index = 0; index < capture; index++){
			StringBuilder url = new StringBuilder("http://finance.yahoo.com/d/quotes.csv?s=");

			url.append(sym);
			url.append("&f=sab&e=.csv");

			String theUrl = url.toString();
			URL obj = new URL(theUrl);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;

			while((inputLine = in.readLine()) != null)	//(essentially while(true))
			{
				//System.out.println(inputLine);
				String[] fields = inputLine.split(",");

				for(int i = 0; i < fields.length; i++){
					System.out.print(fields[i].replaceAll("\"", "") + " | ");
				}

				bidMoving[ind] = Double.parseDouble(fields[1]);	     
				askMoving[ind] = Double.parseDouble(fields[2]);	  
				bidTemp = 0;
				askTemp = 0;

				for(int loop = ind; loop > ind - movAvg && loop >= 0; loop--){
					bidTemp += bidMoving[loop];
					askTemp += askMoving[loop];
				} 
				
				ind++;

				if(ind >= movAvg){
					bidAvg = bidTemp/movAvg;
					askAvg = askTemp/movAvg;
				}else{
					bidAvg = 0;
					askAvg = 0;
				}
				
				//DataAccess.addStockQuote(fields[0], fields[1], fields[2], askAvg, bidAvg, capture, movAvg);
				bidTotal += Double.parseDouble(fields[1]);
				askTotal += Double.parseDouble(fields[2]);
				System.out.println();
				System.out.println("-----------------------------------");
			}			
		}
		System.out.printf("Bid Price Average: %.2f", bidTotal/capture);	       
		System.out.printf("\nAsk Price Average: %.2f", askTotal/capture);
		System.out.printf("\nBid Price Moving Average (Last %s): %.2f", movAvg, bidAvg);
		System.out.printf("\nAsk Price Moving Average (Last %s): %.2f", movAvg, askAvg);
		kb.close();
	}
}
