package com.andremlsantos;

import com.andremlsantos.extra.Cell;

public class MissingMirror {

	//position of the missing mirror in the new SAFE
	private int r, c;
	
	//clone of the original safe
	private Safe safe;
	
	//if the current safe with the missing mirror is safe
	private boolean isSafe;
	
	//type of the missing mirror inserted
	private Cell typeOfMirror;
	
	public MissingMirror() {
		
	}
	
	public MissingMirror(int rPosition, int cPosition, Safe originalSafe) {
		this.r = rPosition;
		this.c = cPosition;
		this.safe = originalSafe.clone();
	}

	public void putMissingMirror() {
		this.safe.putCell(r, c, typeOfMirror);
	}
	
	public boolean checkSafe() {
		this.isSafe = this.safe.checkSafe();
		return this.isSafe;
	}
	
	/*
	 * GET & SET
	 * */
	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}

	public Safe getSafe() {
		return safe;
	}

	public void setSafe(Safe safe) {
		this.safe = safe;
	}

	public boolean isSafe() {
		return isSafe;
	}

	public void setSafe(boolean isSafe) {
		this.isSafe = isSafe;
	}

	public Cell getTypeOfMirror() {
		return typeOfMirror;
	}

	public void setTypeOfMirror(Cell typeOfMirror) {
		this.typeOfMirror = typeOfMirror;
	}	
	
	
}
