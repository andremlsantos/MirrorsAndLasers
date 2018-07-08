package com.andremlsantos;

public class Safe implements Cloneable {

	// SAFE dimensions
	int rows;
	int columns;

	// grid world
	Cell[][] grid;

	// PUBLIC METHODS ----------------------------------------------------------------
	public Safe() {
	}

	public Safe(int r, int c) {
		this.rows = r;
		this.columns = c;

		this.grid = new Cell[r][c];

		//after we created our world, we fill all cells with empty tag
		fillEmpty();
	}

	/*
	 * CLONABLE DP
	 * for creating copies of the original safe
	 * */
	public Safe clone() {
		try {
			return (Safe) super.clone();
		} catch (CloneNotSupportedException e) {
			System.out.println("CloneNotSupportedException comes out : " + e.getMessage());
		}
		return null;
	}

	/*
	 * Put a CELL object into position (r,c)
	 * */
	public void putCell(int r, int c, Cell lazer) {
		this.grid[r][c] = lazer;
	}

	/*
	 * */
	public void print() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				System.out.print(this.grid[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	/*
	 * */
	public boolean checkSafe() {
		Laser laser = new Laser();
		boolean outOfBounds = false;

		if (grid[0][0].equals(Cell.EMPYT)) {
			putCell(0, 0, Cell.Lazer);
		}

		// print();

		while (!outOfBounds) {
			// current position of the lazer
			int r = laser.getR();
			int c = laser.getC();

			outOfBounds = move(laser, grid[r][c]);

			// print();
		}

		int r = laser.getR();
		int c = laser.getC();
		Orientation laserOrientation = laser.getBeanOrientation();

		if (r == rows - 1 && c >= columns && laserOrientation.equals(Orientation.RIGHT)) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * Determines the next laser movement 
	 * Based on the current laser orientation, position and CELL occupied
	 * */
	private boolean move(Laser laser, Cell cell) {
		// current position of the laser
		int r = laser.getR();
		int c = laser.getC();

		if (cell.equals(Cell.EMPYT) || cell.equals(Cell.Lazer)) {
			//we continue our current orientation
			switch (laser.getBeanOrientation()) {
			case TOP:
				r -= 1;
				break;
			case DOWN:
				r += 1;
				break;
			case LEFT:
				c -= 1;
				break;
			case RIGHT:
				c += 1;
				break;
			default:
				break;
			}
		} else if (cell.equals(Cell.MirrorBackslash)) {
			switch (laser.getBeanOrientation()) {
			case TOP:
				laser.setBeanOrientation(Orientation.LEFT);
				c -= 1;
				break;
			case DOWN:
				laser.setBeanOrientation(Orientation.RIGHT);
				c += 1;
				break;
			case LEFT:
				laser.setBeanOrientation(Orientation.TOP);
				r -= 1;
				break;
			case RIGHT:
				laser.setBeanOrientation(Orientation.DOWN);
				r += 1;
				break;
			default:
				break;
			}

		} else if (cell.equals(Cell.MirrorSlash)) {
			switch (laser.getBeanOrientation()) {
			case TOP:
				laser.setBeanOrientation(Orientation.TOP);
				r -= 1;
				break;
			case DOWN:
				laser.setBeanOrientation(Orientation.LEFT);
				c -= 1;
				break;
			case LEFT:
				laser.setBeanOrientation(Orientation.DOWN);
				r += 1;
				break;
			case RIGHT:
				laser.setBeanOrientation(Orientation.RIGHT);
				c += 1;
				break;
			default:
				break;
			}

			if (grid[r][c].equals(Cell.EMPYT)) {
				putCell(r, c, Cell.Lazer);
			}
		}

		boolean isOut = (r < 0 || r >= rows || c < 0 || c >= columns);

		if (!isOut && grid[r][c].equals(Cell.EMPYT)) {
			putCell(r, c, Cell.Lazer);
		}

		// we update the laser location
		laser.setR(r);
		laser.setC(c);

		return isOut;
	}

	// PRIVATE METHODS ----------------------------------------------------------------
	/*
	 * FILL ALL CELLS EMPTY
	 */
	private void fillEmpty() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				grid[i][j] = Cell.EMPYT;
			}
		}
	}

}
