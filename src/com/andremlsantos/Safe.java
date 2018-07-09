package com.andremlsantos;

import com.andremlsantos.extra.Cell;
import com.andremlsantos.extra.Orientation;

public class Safe {

	// SAFE dimensions
	private int rows;
	private int columns;

	// grid world
	private Cell[][] grid;

	// PUBLIC METHODS
	// ----------------------------------------------------------------
	public Safe() {
	}

	public Safe(int r, int c) {
		this.rows = r;
		this.columns = c;

		this.grid = new Cell[r][c];

		// we fill all cells with empty tag
		fillEmpty();
	}

	/*
	 * CLONABLE DP for creating copies of the original safe
	 */
	public Safe clone() {
		Safe clone = new Safe(this.rows, this.columns);

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				clone.putCell(i, j, grid[i][j]);
			}
		}

		return clone;
	}

	/*
	 * Put a CELL object into position (r,c)
	 */
	public void putCell(int r, int c, Cell lazer) {
		this.grid[r][c] = lazer;
	}

	/*
	 * PRINTS the current SAFE obj matrix
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
	 * We check if the bean is detected
	 * */
	public boolean checkSafe() {
		Laser laser = new Laser();
		boolean outOfBounds = false;
		
		while (!outOfBounds) {
			// current position of the laser
			int r = laser.getR();
			int c = laser.getC();

			// we continuing the movements while we don't exit the safe
			outOfBounds = move(laser, grid[r][c]);
		}

		// where the laser exits the grid world
		int r = laser.getR();
		int c = laser.getC();
		Orientation laserOrientation = laser.getBeanOrientation();

		if (r == rows - 1 && c >= columns && laserOrientation.equals(Orientation.RIGHT)) {
			// bean detected
			return true;
		} else {
			// bean not detected
			return false;
		}
	}

	/*
	 * Determines the next laser movement 
	 * Based on the current laser orientation, position and CELL occupied
	 */
	private boolean move(Laser laser, Cell cell) {
		// current position of the laser
		int r = laser.getR();
		int c = laser.getC();

		if (cell.equals(Cell.EMPYT) || cell.equals(Cell.Lazer)) {
			// we continue our current orientation
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
				laser.setBeanOrientation(Orientation.RIGHT);
				c += 1;
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
				laser.setBeanOrientation(Orientation.TOP);
				r -= 1;
				break;
			default:
				break;
			}			
		}

		boolean isOut = (r < 0 || r >= rows || c < 0 || c >= columns);

		// for debugging, we put into the grid world, the current laser location
		if (!isOut && grid[r][c].equals(Cell.EMPYT)) {
			putCell(r, c, Cell.Lazer);
		}

		// we update the laser location
		laser.setR(r);
		laser.setC(c);

		return isOut;
	}

	/*
	 * GET & SET
	 */
	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

	public Cell[][] getGrid() {
		return grid;
	}

	public void setGrid(Cell[][] grid) {
		this.grid = grid;
	}

	// PRIVATE METHODS
	// ----------------------------------------------------------------
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
