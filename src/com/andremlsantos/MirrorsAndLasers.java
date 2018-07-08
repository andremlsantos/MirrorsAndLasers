package com.andremlsantos;

import java.util.Scanner;

public class MirrorsAndLasers {

	public static void main(String[] args) {
		// read from user input
		Scanner sc = new Scanner(System.in);

		// number of rows
		int r;

		// number of columns
		int c;

		// number of / mirrors
		int m;

		// number of \ mirrors
		int n;

		// single input
		{
			//safe dimensions
			r = sc.nextInt();
			c = sc.nextInt();
			
			//number and type of mirrors
			m = sc.nextInt();
			n = sc.nextInt();
			
			//lets create a new SAFE object
			Safe safe = new Safe(r,  c);

			// Slash mirrors
			for (int i = 0; i < m; i++) {
				int yy = sc.nextInt() - 1;
				int xx = sc.nextInt() - 1;

				safe.putCell(yy, xx, Cell.MirrorSlash);
			}

			// Backslash mirrors
			for (int i = 0; i < n; i++) {
				int yy = sc.nextInt() - 1;
				int xx = sc.nextInt() - 1;

				safe.putCell(yy, xx, Cell.MirrorBackslash);
			}
			
			Safe safeClone = safe.clone();
			
			//safe.print();

			//int nPossibleSimulations = r * c - m - n;

			//simulation(safe);
			
			//boolean isSafe = safe.checkSafe();
			boolean isSafe = safeClone.checkSafe();
			
			if(isSafe) {
				System.out.println("is safe");
			} else {
				System.out.println("is not safe");
			}
		}
	}	
}
