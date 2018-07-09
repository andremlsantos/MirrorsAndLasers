package com.andremlsantos;

import java.util.ArrayList;

import com.andremlsantos.extra.Cell;

public class MirrorsAndLasers {

	// PUBLIC METHODS
	// ----------------------------------------------------------------
	public static void main(String[] args) {
		// Aux class for dealing with the user input
		Input input = new Input();

		// Read the user input from the console
		ArrayList<String> userInputs = input.readUserInput();

		// Convert the user input to a List of Safe obj
		ArrayList<Safe> safes = input.parseUserInput(userInputs);

		for (int i = 0; i < safes.size(); i++) {
			// we clone from the original safe
			Safe safeClone = safes.get(i).clone();

			//safeClone.print();
			
			// we compute if the bean is detected, safe open
			boolean beamDetected = safeClone.checkSafe();

			if (beamDetected) {
				System.out.println("Case " + i + ": 0");
			} else {
				calcMissingMirrors(safes.get(i).clone(), i);
			}
		}
	}

	// PRIVATE METHODS
	// ----------------------------------------------------------------
	/*
	 * AUX function 
	 * for each EMPTY position, we put a new MIRROR, then we compute if the bean is detected
	 */
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
						// we increase the number of possible mirrors solutions
						k++;

						// we want the lexicographic smallest position
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
