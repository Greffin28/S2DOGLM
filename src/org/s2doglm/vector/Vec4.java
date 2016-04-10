package org.s2doglm.vector;

import org.s2doglm.matrix.Matrix;

public class Vec4 {

	private float[] vec;
	
	public Vec4() {
		vec = new float[] {0.0f, 0.0f, 0.0f, 1.0f};
	}
	
	public Vec4(float x, float y) {
		vec = new float[] {x, y, 0.0f, 1.0f};
	}
	
	public Vec4(float x, float y, float z) {
		vec = new float[] {x, y, z, 1.0f};
	}
	
	public void transform(Matrix mat) {
		float[] matVal = mat.getMatrix();
		float[] tempvec = new float[vec.length];
		for (int i = 0; i < vec.length; i++) {
			tempvec[i] = vec[i];
			vec[i] = 0;
		}
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				vec[i] += matVal[i + j * 4] * tempvec[j];
 			}
		}
	}
	
	public void printVector() {
		System.out.println("----------");
		System.out.println(vec[0] + ", " + vec[1] + ", " + vec[2] + ", " + vec[3]);
	}
	
	public float[] getVector() {
		return vec;
	}
	
}
