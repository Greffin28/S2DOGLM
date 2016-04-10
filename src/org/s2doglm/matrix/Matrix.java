package org.s2doglm.matrix;

public class Matrix {

	private float[] mat;
	
	public Matrix() {
		mat = new float[]{
			1.0f, 0.0f, 0.0f, 0.0f,
			0.0f, 1.0f, 0.0f, 0.0f,
			0.0f, 0.0f, 1.0f, 0.0f,
			0.0f, 0.0f, 0.0f, 1.0f
		};
	}
	
	public Matrix(float[] f) {
		if (f.length != 4 * 4) {
			System.err.println("Matrix should be in the form of 4x4");
			return;
		}
		mat = f;
	}
	
	public void transpose() {
		
	}
	
}
