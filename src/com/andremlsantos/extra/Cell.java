package com.andremlsantos.extra;

//https://www.owasp.org/index.php/Password_special_characters
public enum Cell {
	MirrorSlash {
		@Override
		public String toString() {
			return "/";
		}
	},		
	MirrorBackslash {
		@Override
		public String toString() {
			return "\\";
		}
	},
	EMPYT {
		@Override
		public String toString() {
			return "-";
		}
	},
	Lazer {
		@Override
		public String toString() {
			return "*";
		}
	}
}
