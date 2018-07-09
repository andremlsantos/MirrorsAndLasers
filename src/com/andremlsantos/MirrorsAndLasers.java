package com.andremlsantos;

import java.util.Scanner;

import com.andremlsantos.extra.Cell;

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
			// safe dimensions
			r = sc.nextInt();
			c = sc.nextInt();

			// number and type of mirrors
			m = sc.nextInt();
			n = sc.nextInt();

			// lets create a new SAFE object
			Safe safe = new Safe(r, c);

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
			
			boolean isSafe = safeClone.checkSafe();
			int caseNumber = 0;

			if (isSafe) {
				System.out.println("Case " + caseNumber + ": 0");
			} else {
				calcMissingMirrors(safe.clone(), caseNumber);
			}
		}
	}

	private static void calcMissingMirrors(Safe safe, int caseNumber) {
		int k = 0, r = safe.getRows(), c = safe.getColumns();

		for (int i = 0; i < safe.getRows(); i++) {
			for (int j = 0; j < safe.getColumns(); j++) {
				if (safe.getGrid()[i][j].equals(Cell.EMPYT)) {

					// type of mirror /
					MissingMirror missingMirror = new MissingMirror(i, j, safe);
					missingMirror.setTypeOfMirror(Cell.MirrorSlash);
					missingMirror.putMissingMirror();

					// type of mirror \
					MissingMirror missingMirrorBack = new MissingMirror(i, j, safe);
					missingMirrorBack.setTypeOfMirror(Cell.MirrorBackslash);
					missingMirrorBack.putMissingMirror();

					missingMirror.checkSafe();
					missingMirrorBack.checkSafe();

					if (missingMirror.isSafe() || missingMirrorBack.isSafe()) {
						
						//we increase the number of possible mirrors solutions
						k++;
						
						//we want the lexicographic smallest position
						if ((i + j) < (r + c)) {
							r = i + 1;
							c = j + 1;
						}
					}
				}
			}
		}

		if (k == 0) {
			System.out.println("Case " + caseNumber + ": impossible");
		} else {
			System.out.println("Case " + caseNumber + ": " + k + " " + r + " " + c);
		}
	}
}
