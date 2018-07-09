package com.andremlsantos;

import java.util.ArrayList;
import java.util.Scanner;

import com.andremlsantos.extra.Cell;
import com.andremlsantos.extra.TypesInput;

public class Input {

	private ArrayList<String> inputs;
	private ArrayList<Safe> safes;

	/*
	 * READ from stdin 
	 * RETURN ArrayList of strings
	 */
	public ArrayList<String> readUserInput() {
		// where we save the data
		this.inputs = new ArrayList<>();

		// stdin
		Scanner input = new Scanner(System.in);

		// we save the user input
		String line = input.nextLine();
		
		// while the input doesn't end
		while (line.length() != 0) {
			inputs.add(line);
			line = input.nextLine();
		}

		return this.inputs;
	}

	/*
	 * PARSE user input 
	 * RETURN list of SAFEs objects
	 */
	public ArrayList<Safe> parseUserInput(ArrayList<String> inputs) {
		// expected line input from the console
		TypesInput expectedInput = TypesInput.TESTCASE;

		// index of the current safe
		int safeIndex = 0;

		int r = 0, c = 0, m = 0, n = 0;
		this.safes = new ArrayList<>();

		// we parse the user input
		for (int i = 0; i < inputs.size(); i++) {
			if (expectedInput.equals(TypesInput.TESTCASE)) {
				String safeDetailsArray[] = inputs.get(i).split(" ");

				// number of rows
				r = Integer.parseInt(safeDetailsArray[0]);

				// number of columns
				c = Integer.parseInt(safeDetailsArray[1]);

				// number of / mirrors
				m = Integer.parseInt(safeDetailsArray[2]);

				// number of \ mirrors
				n = Integer.parseInt(safeDetailsArray[3]);

				Safe safe = new Safe(r, c);
				safes.add(safe);
				
				// The next type of input
				if (m > 0) {
					expectedInput = TypesInput.M_MIRRORS;
				} else if (m == 0 && n > 0) {
					expectedInput = TypesInput.N_MIRRORS;
				} else if (m == 0 && n == 0) {
					expectedInput = TypesInput.TESTCASE;
				}
			} else if (expectedInput.equals(TypesInput.M_MIRRORS)) {
				if (m > 0) {
					String[] mMirrorString = inputs.get(i).split(" ");
					int yy = Integer.parseInt(mMirrorString[0]) - 1;
					int xx = Integer.parseInt(mMirrorString[1]) - 1;

					safes.get(safeIndex).putCell(yy, xx, Cell.MirrorSlash);

					m--;

					// The next type of input
					if (m == 0 && n > 0) {
						expectedInput = TypesInput.N_MIRRORS;
					} else if (m == 0 && n == 0) {
						expectedInput = TypesInput.TESTCASE;
					}
				}
			} else if (expectedInput.equals(TypesInput.N_MIRRORS)) {
				if (n > 0) {
					String[] nMirrorString = inputs.get(i).split(" ");
					int yy = Integer.parseInt(nMirrorString[0]) - 1;
					int xx = Integer.parseInt(nMirrorString[1]) - 1;

					safes.get(safeIndex).putCell(yy, xx, Cell.MirrorBackslash);

					n--;

					// The next type of input
					if (n == 0) {
						expectedInput = TypesInput.TESTCASE;
						safeIndex++;
					}
				}
			}
		}

		return safes;
	}

	/*
	 * GET && SET
	 */
	public ArrayList<String> getInputs() {
		return inputs;
	}

	public void setInputs(ArrayList<String> inputs) {
		this.inputs = inputs;
	}

	public ArrayList<Safe> getSafes() {
		return safes;
	}

	public void setSafes(ArrayList<Safe> safes) {
		this.safes = safes;
	}
}
