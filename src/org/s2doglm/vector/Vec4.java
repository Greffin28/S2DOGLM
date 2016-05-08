package org.s2doglm.vector;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import org.s2doglm.matrix.Matrix;

/**
 * <h1>S2DOGLM - Simple 2D OpenGL Matrix</h1>
 * <h3>About S2DOGLM</h3>
 * Created to help me use matrix for 2D OpenGL.</br>
 * Containing several simple functions which doesn't contain anything related to Z axis.</br>
 * Or at least for now..
 * </br>
 * <h3>About this class</h3>
 * Contain several simple functions such as creating vector and transforming a vector with a transformation matrix.
 * </br></br>
 * @author Greffin28
 * @see {@link org.s2doglm.matrix.Matrix Matrix}
 */
public class Vec4 {

	private float[] vec;
	
	/**
	 * Calling Vec4() as a constructor will create a vector with the following coordinates:</br>
	 * {@code x = 0.0f;}</br>
	 * {@code y = 0.0f;}</br>
	 * {@code z = 0.0f;}</br>
	 * {@code w = 1.0f;}
	 */
	public Vec4() {
		vec = new float[] {0.0f, 0.0f, 0.0f, 1.0f};
	}
	
	/**
	 * Calling Vec4(float x, float y) as a constructor will create a vector with the given x and y.</br>
	 * {@code z = 0.0f;}</br>
	 * {@code w = 1.0f;}
	 * </br></br>
	 * @param x Horizontal coordinate.
	 * @param y Vertical coordinate.
	 */
	public Vec4(float x, float y) {
		vec = new float[] {x, y, 0.0f, 1.0f};
	}
	
	/**
	 * Calling Vec4(float x, float y, float z) as a constructor will create a vector with the given x, y, and z.</br>
	 * {@code w = 1.0f}
	 * </br></br>
	 * This function is the only one that use Z axis.
	 * </br></br>
	 * @param x Horizontal coordinate.
	 * @param y Vertical coordinate.
	 * @param z -
	 */
	public Vec4(float x, float y, float z) { //Probably useless as this is going to be used for 2D.
		vec = new float[] {x, y, z, 1.0f};
	}
	
	/**
	 * Used to transform the current vector by multiplying a transformation matrix with the current vector itself.
	 * </br></br>
	 * @param mat {@link org.s2doglm.matrix.Matrix Matrix} that will be multiplied by the current vector.
	 */
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
	
	/**
	 * Used to get the current vector values as a FloatBuffer.</br></br>
	 * </br></br>
	 * @return {@link java.nio.FloatBuffer FloatBuffer}
	 */
	public FloatBuffer getVectorBuffer() {
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(vec.length * Float.BYTES);
		byteBuffer.order(ByteOrder.nativeOrder());
		FloatBuffer floatBuffer = byteBuffer.asFloatBuffer();
		floatBuffer.put(vec);
		floatBuffer.flip();
		return floatBuffer;
	}
	
	/**
	 * Used to get the current vector values as a float array.
	 * </br></br>
	 * @return float[]
	 */
	public float[] getVector() {
		return vec;
	}
	
	/**
	 * Used to simply print the vector values to the console.
	 */
	public void printVector() {
		System.out.println("----------");
		System.out.println(vec[0] + ", " + vec[1] + ", " + vec[2] + ", " + vec[3]);
	}
	
	public float x() {
		return vec[0];
	}
	
	public float y() {
		return vec[1];
	}
	
	public float z() {
		return vec[2];
	}
	
	public float w() {
		return vec[3];
	}
	
}
