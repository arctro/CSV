package com.arctro.csv;

import com.arctro.csv.exceptions.HeaderBodyMismatchException;

public class Main {

	public static void main(String[] args) {
		CSV csv = new CSV("timestamp,Machine 1,Machine 2,Machine 3\n21/3/2016 21:50,0,0,0\n21/3/2016 22:50,0,0,0\n21/3/2016 23:50,0,0,0\n22/3/2016 24:50,0,0,0\n22/3/2016 1:50,0,0,0\n22/3/2016 2:50,0,0,0\n22/3/2016 3:50,0,0,0\n22/3/2016 4:50,0,0,0\n22/3/2016 5:50,0,0,0\n22/3/2016 6:50,0,0,0\n22/3/2016 7:50,0,0,0\n22/3/2016 8:50,0,0,0\n22/3/2016 9:50,0,0,0\n22/3/2016 10:50,0,0,0\n22/3/2016 11:50,0,0,0\n22/3/2016 12:50,0,0,0\n22/3/2016 13:50,0,0,0\n22/3/2016 14:50,0,0,0\n22/3/2016 15:50,0,0,0\n22/3/2016 16:50,0,0,0\n22/3/2016 17:50,0,0,0\n22/3/2016 18:50,0,0,0\n22/3/2016 19:50,0,0,0\n22/3/2016 20:50,0,0,0\n22/3/2016 21:50,0,0,0\n");
		
		for(int i = 0; i < 10; i++){
			try {
				csv.add(new Object[]{i, "test " + i, "time " + i, "test"});
			} catch (HeaderBodyMismatchException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(csv.toString());
	}

}
