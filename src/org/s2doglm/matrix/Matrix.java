package org.s2doglm.matrix;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * <h1>S2DOGLM - Simple 2D OpenGL Matrix</h1>
 * <h3>About S2DOGLM</h3>
 * Created to help me use matrix for 2D OpenGL.</br>
 * Containing several simple functions which doesn't contain anything related to Z axis.</br>
 * Or at least for now..
 * </br>
 * <h3>About this class</h3>
 * Contain several simple functions to create:
 * <ul>
 * 	<li>Translation matrix</li>
 * 	<li>Dilation matrix</li>
 * 	<li>Rotation matrix</li>
 * 	<li>Orthogonal projection matrix</li>
 * </ul>
 * </br>
 * @author Greffin28
 * @see {@link org.s2doglm.vector.Vec4 Vec4}
 */
public class Matrix {

	private float[] mat;
	
	/**
	 * Calling Matrix() as a constructor will create an <b>identity</b> matrix.
	 */
	public Matrix() {
		mat = new float[]{
			1.0f, 0.0f, 0.0f, 0.0f,
			0.0f, 1.0f, 0.0f, 0.0f,
			0.0f, 0.0f, 1.0f, 0.0f,
			0.0f, 0.0f, 0.0f, 1.0f
		};
	}
	
	/**
	 * Calling Matrix(float[] f) as a constructor will create a matrix with the given float array.
	 * The float array must be 4 * 4 in size in <b>column major</b> orientation.
	 */
	public Matrix(float[] f) {
		if (f.length != 4 * 4) {
			System.err.println("Matrix should be in the form of 4x4");
			return;
		}
		mat = f;
	}
	
	/**
	 * Calling transpose() will transpose the current matrix.
	 */
	public void transpose() {
		float[] tempmat = new float[4 * 4];
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				tempmat[x + y * 4] = mat[x + y * 4];
			}	
		}
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				mat[x + y * 4] = tempmat[y + x * 4];
			}	
		}
	}
	
	public void transform(Matrix matT) {
		float[] tempmat = new float[4 * 4];
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				tempmat[x + y * 4] = mat[x + y * 4];
				mat[x + y * 4] = 0;
			}
		}
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				for (int i = 0; i < 4; i++) {
					mat[x + y * 4] += matT.getMatrix()[x + i * 4] * tempmat[i + y * 4];
				}
			}
		}
	}
	
	/**
	 * Simple orthogonal matrix which doesn't interfere with Z axis.</br></br>
	 * Example of use:</br>
	 * {@code Matrix mat = Matrix.simpleOrtho(0, 0, 600, 800);}
	 * </br></br>
	 * @param l Left side.
	 * @param t Top side.
	 * @param r Right side.
	 * @param b Bottom side.
	 * 
	 * @return {@link org.s2doglm.matrix.Matrix Matrix}
	 */
	public static Matrix simpleOrtho(float l, float t, float r, float b) {
		Matrix mat = new Matrix(new float[] {
				2 / (r - l), 0.0f, 0.0f, -(r + l) / (r - l),
				0.0f, -2 / (b - t), 0.0f, (b + t) / (b - t),
				0.0f, 0.0f, 1.0f, 0.0f,
				0.0f, 0.0f, 0.0f, 1.0f
		});
		
		mat.transpose();
		return mat;
	}
	
	/**
	 * Rotation matrix relative to (X, Y).</br></br>
	 * Example of use:</br>
	 * {@code Matrix mat = Matrix.rotationMatrix(1.0f, 10.0f, (float) Math.toRadians(90));}
	 * </br></br>
	 * @param X Horizontal coordinate of the rotation point.
	 * @param Y Vertical coordinate of the rotation point.
	 * @param ang Angle of rotation in <b>radians</b>.
	 * 
	 * @return {@link org.s2doglm.matrix.Matrix Matrix}
	 */
	public static Matrix rotationMatrix(float X, float Y, float ang) {
		Matrix mat = new Matrix(new float[] {
				(float) Math.cos(ang)	, (float) -Math.sin(ang), 0.0f, (float) (-X * Math.cos(ang) + Y * Math.sin(ang) + X),
				(float) Math.sin(ang)	, (float) Math.cos(ang)	, 0.0f, (float) (-X * Math.sin(ang) - Y * Math.cos(ang) + Y),
				0.0f					, 0.0f					, 1.0f, 0.0f,
				0.0f					, 0.0f					, 0.0f, 1.0f
		});
		mat.transpose();
		return mat;
	}
	
	/**
	 * Rotation matrix relative to Origin(0, 0).</br></br>
	 * Example of use:</br>
	 * {@code Matrix mat = Matrix.oRotationMatrix((float) Math.toRadians(90));}
	 * </br></br>
	 * @param ang Angle of rotation in <b>radians</b>.
	 * 
	 * @return {@link org.s2doglm.matrix.Matrix Matrix}
	 */
	public static Matrix oRotationMatrix(float ang) {
		Matrix mat = new Matrix(new float[] {
				(float) Math.cos(ang)	, (float) -Math.sin(ang), 0.0f, 0.0f,
				(float) Math.sin(ang)	, (float) Math.cos(ang)	, 0.0f, 0.0f,
				0.0f					, 0.0f					, 1.0f, 0.0f,
				0.0f					, 0.0f					, 0.0f, 1.0f
		});
		mat.transpose();
		return mat;
	}
	
	/**
	 * Dilation matrix relative to (X, Y).</br></br>
	 * Example of use:</br>
	 * {@code Matrix mat = Matrix.dilationMatrix(1.0f, 10.0f, 2.0f, 3.0f);}
	 * </br></br>
	 * @param X Horizontal coordinate of the dilation point.
	 * @param Y Vertical coordinate of the dilation point.
	 * @param SX Horizontal scale.
	 * @param SY Vertical scale.
	 * 
	 * @return {@link org.s2doglm.matrix.Matrix Matrix}
	 */
	public static Matrix dilationMatrix(float X, float Y, float SX, float SY) {
		Matrix mat = new Matrix(new float[] {
				SX	, 0.0f	, 0.0f, X * (1 - SX),
				0.0f, SY	, 0.0f, Y * (1 - SY),
				0.0f, 0.0f	, 1.0f, 0.0f,
				0.0f, 0.0f	, 0.0f, 1.0f
		});
		mat.transpose();
		return mat;
	}
	
	/**
	 * Dilation matrix relative to Origin(0, 0).</br></br>
	 * Example of use:</br>
	 * {@code Matrix mat = Matrix.oDilationMatrix(2.0f, 3.0f);}
	 * </br></br>
	 * @param SX Horizontal scale.
	 * @param SY Vertical scale.
	 * 
	 * @return {@link org.s2doglm.matrix.Matrix Matrix}
	 */
	public static Matrix oDilationMatrix(float SX, float SY) {
		Matrix mat = new Matrix(new float[] {
				SX	, 0.0f	, 0.0f, 0.0f,
				0.0f, SY	, 0.0f, 0.0f,
				0.0f, 0.0f	, 1.0f, 0.0f,
				0.0f, 0.0f	, 0.0f, 1.0f
		});
		mat.transpose();
		return mat;
	}
	
	/**
	 * Translation matrix.</br></br>
	 * Example of use:</br>
	 * {@code Matrix mat = Matrix.translationMatrix(5.0f, 10.0f);}
	 * </br></br>
	 * @param X Horizontal increment/decrement.
	 * @param Y Vertical increment/decrement.
	 * 
	 * @return {@link org.s2doglm.matrix.Matrix Matrix}
	 */
	public static Matrix translationMatrix(float X, float Y) {
		Matrix mat = new Matrix(new float[] {
				1.0f, 0.0f, 0.0f, X,
				0.0f, 1.0f, 0.0f, Y,
				0.0f, 0.0f, 1.0f, 0.0f,
				0.0f, 0.0f, 0.0f, 1.0f
		});
		mat.transpose();
		return mat;
	}
	
	/**
	 * Used to get the current matrix values as a FloatBuffer.
	 * </br></br>
	 * @return {@link java.nio.FloatBuffer FloatBuffer}
	 */
	public FloatBuffer getMatrixBuffer() {
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(mat.length * Float.BYTES);
		byteBuffer.order(ByteOrder.nativeOrder());
		FloatBuffer floatBuffer = byteBuffer.asFloatBuffer();
		floatBuffer.put(mat);
		floatBuffer.flip();
		return floatBuffer;
	}
	
	/**
	 * Used to get the current matrix values as a float array.
	 */
	public float[] getMatrix() {
		return mat;
	}
	
	/**
	 * Used to simply print the matrix values to the console.
	 */
	public void printMatrix() {
		System.out.println("----------");
		for (int y = 0; y < 4; y++) {
			System.out.println(mat[0 + y * 4] + ", " + mat[1 + y * 4] + ", " + mat[2 + y * 4] + ", " + mat[3 + y * 4]);
		}
	}
	
}
