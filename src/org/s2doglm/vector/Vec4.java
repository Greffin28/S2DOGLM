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

	public float x, y, z, w;
	
	/**
	 * Calling Vec4() as a constructor will create a vector with the following coordinates:</br>
	 * {@code x = 0.0f;}</br>
	 * {@code y = 0.0f;}</br>
	 * {@code z = 0.0f;}</br>
	 * {@code w = 1.0f;}
	 */
	public Vec4() {
		x = 0.0f;
		y = 0.0f;
		z = 0.0f;
		w = 1.0f;
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
		this.x = x;
		this.y = y;
		z = 0.0f;
		w = 1.0f;
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
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = 1.0f;
	}
	
	/**
	 * Used to transform the current vector by multiplying a transformation matrix with the current vector itself.
	 * </br></br>
	 * @param mat {@link org.s2doglm.matrix.Matrix Matrix} that will be multiplied by the current vector.
	 */
	public void transform(Matrix mat) {
		float[] matVal = mat.getMatrix();
		float[] vec = new float[4];
		float[] tempvec = new float[] {x, y, z, w};
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				vec[i] += matVal[i + j * 4] * tempvec[j];
 			}
		}
		
		x = vec[0];
		y = vec[1];
		z = vec[2];
		w = vec[3];
	}
	
	/**
	 * Used to get the current vector values as a FloatBuffer.</br></br>
	 * </br></br>
	 * @return {@link java.nio.FloatBuffer FloatBuffer}
	 */
	public FloatBuffer getVectorBuffer() {
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * Float.BYTES);
		byteBuffer.order(ByteOrder.nativeOrder());
		FloatBuffer floatBuffer = byteBuffer.asFloatBuffer();
		floatBuffer.put(x);
		floatBuffer.put(y);
		floatBuffer.put(z);
		floatBuffer.put(w);
		floatBuffer.flip();
		return floatBuffer;
	}
	
	/**
	 * Used to get the current vector values as a float array.
	 * </br></br>
	 * @return float[]
	 */
	public float[] getVector() {
		float[] vec = new float[] {x, y, z, w};
		return vec;
	}
	
	/**
	 * Used to simply print the vector values to the console.
	 */
	public void printVector() {
		System.out.println("----------");
		System.out.println(x + ", " + y + ", " + z + ", " + w);
	}
	
}
