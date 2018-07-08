package com.andremlsantos;

public class Laser {
	int xxPosition;
	int yyPosition;
	Orientation beanOrientation;
	
	public Laser() {
		this.xxPosition = 0;
		this.yyPosition = 0;
		this.beanOrientation = Orientation.RIGHT;
	}
	
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
