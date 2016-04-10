package org.s2doglm.testClass;

import org.s2doglm.matrix.*;

public class Main {
	
	public Main() {
		Matrix mat = Matrix.translate(100.0f, 50.0f);
		
		mat.printMatrix();
	}
	
	public static void main(String[] args) {
		new Main();
	}
	
}
