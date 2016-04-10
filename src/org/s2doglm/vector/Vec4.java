package org.s2doglm.vector;

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
	
	public float[] getVector() {
		return vec;
	}
	
}
