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
	
	public float[] getMatrix() {
		return mat;
	}
	
	public void printMatrix() {
		System.out.println("----------");
		for (int y = 0; y < 4; y++) {
			System.out.println(mat[0 + y * 4] + ", " + mat[1 + y * 4] + ", " + mat[2 + y * 4] + ", " + mat[3 + y * 4]);
		}
	}
	
}
