package cititradeweb.strategies;

public class BollingerBands {

	public static void movingAverage(double ask, double bid) {

		int movAvg = 0, capture = 0;
		double bidTotal = 0;
		double askTotal = 0;
		double bidAvg = 0;
		double askAvg = 0;
		double bidTemp = ask;
		double askTemp = bid;
		double stdev = 0;
		int ind = 0;

		double[] bidMoving = new double[capture];
		double[] askMoving = new double[capture];

		for (int loop = ind; loop > ind - movAvg && loop >= 0; loop--) {
			bidTemp += bidMoving[loop];
			askTemp += askMoving[loop];
		}

		ind++;

		if (ind >= movAvg) {
			bidAvg = bidTemp / movAvg;
			askAvg = askTemp / movAvg;
		} else {
			bidAvg = 0;
			askAvg = 0;
		}

		stdev = Math.sqrt(i*powerSum2 - Math.pow(powerSum1, 2))/i;
		
	}
	
	 

}
