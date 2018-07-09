package com.andremlsantos.extra;

//Types of cells in the Safe
public enum Cell {
	// m Mirror
	MirrorSlash {
		@Override
		public String toString() {
			return "/";
		}
	},
	// n Mirror
	MirrorBackslash {
		@Override
		public String toString() {
			return "\\";
		}
	},
	// empty cell
	EMPYT {
		@Override
		public String toString() {
			return "-";
		}
	},
	// cell occupied by a laser
	Lazer {
		@Override
		public String toString() {
			return "*";
		}
	}
}
