package org.s2doglm.testClass;

import org.s2doglm.matrix.*;

public class Main {
	
	public Main() {
		Matrix mat = new Matrix(new float[] {
				1.0f, 7.0f, 0.0f, 1.0f,
				0.0f, 1.0f, 5.0f, 2.0f,
				0.0f, 0.0f, 1.0f, 3.0f,
				0.0f, 0.0f, 0.0f, 1.0f
		});
		mat.transpose();
		mat.printMatrix();
	}
	
	public static void main(String[] args) {
		new Main();
	}
	
}
