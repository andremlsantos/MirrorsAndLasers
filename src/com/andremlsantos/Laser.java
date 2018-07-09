package com.andremlsantos;

import com.andremlsantos.extra.Orientation;

public class Laser {
	private int xxPosition;
	private int yyPosition;
	private Orientation beanOrientation;
	
	public Laser() {
		this.xxPosition = 0;
		this.yyPosition = 0;
		this.beanOrientation = Orientation.RIGHT;
	}
	
	/*
	 * GET & SET
	 * */
	public int getC() {
		return xxPosition;
	}
	public void setC(int xxPosition) {
		this.xxPosition = xxPosition;
	}
	public int getR() {
		return yyPosition;
	}
	public void setR(int yyPosition) {
		this.yyPosition = yyPosition;
	}
	public Orientation getBeanOrientation() {
		return beanOrientation;
	}
	public void setBeanOrientation(Orientation beanOrientation) {
		this.beanOrientation = beanOrientation;
	}
}
