# Simple 2D OpenGL Matrix (S2DOGLM)

###### Still on-going! --- Progress is stopped right now, will add more features if i find something useful needed.

## What exactly is S2DOGLM?
Simple 2D OpenGL Matrix or S2DOGLM. Created to help me to use matrix for 2D OpenGL in **Java**.
This is for my personal use only, but feel free to use it if you want to.
Anyway, i **believe** there's a better library out there somewhere. I made this because i didn't find any suitable for me, not only that but it's pretty challanging to make this.

**Updated**: Now some function has something to do with Z axis, like ortho() and Vec4()

## Contents
Here are the list of the class and functions:

1. Matrix.java
  * Constructor
    - `Matrix()` *Create a identity matrix.*
    - `Matrix(float[] f)` *Create a matrix from the given float array.*
  * Functions
    - `transpose()` *Transpose the matrix.*
    - `simpleOrtho(float t, float l, float b, float r)` *Like a normal orthogonal matrix.*
    - `simpleOrtho(float t, float l, float b, float r)` *Like a normal orthogonal matrix, but doesn't change the Z.*
    - `rotationMatrix(float X, float Y, float ang)` *Rotation relative to (X, Y).*
    - `oRotationMatrix(float ang)` *Rotation relative to (0, 0).*
    - `dilationMatrix(float X, float Y, float SX, float SY)` *Dilation with scale (SX, SY) relative to (X, Y).*
    - `oDilationMatrix(float SX, float SY)` *Dilation with scale (SX, SY) relative to (0, 0).*
    - `translationMatrix(float X, float Y)` *Translation or move the position by (X, Y).*
    - `getMatrixBuffer()` *Return a FloatBuffer containing the matrix values.*
    - `getMatrix()` *Return a float[] containing the matrix values.*
    - `printMatrix()` *Simply print the values to the console.*
2. Vec4.java
  * Constructor
    - `Vec4()` *Create a vector with (x, y, z, w) = (0, 0, 0, 1).*
    - `Vec4(float x, float y)` *Create a vector with (x, y, z, w) = (x, y, 0, 1).*
    - `Vec4(float x, float y, float z)` *Create a vector with (x, y, z, w) = (x, y, z, 1).*
  * Functions
    - `transform(Matrix mat)` *Simply multiply a matrix with the vector.*
    - `printVector()` *Simply print the values to the console.*
    - `getVector()` *Return a float[] containing the values (x, y, z, w).*

> To create a matrix you can do the followings:
>   - Matrix mat = new Matrix(...); or
>   - Matrix mat = Matrix.---Matrix(...); (Yep, static functions)

### To do list
- [x] Documentation for both classes.
- [ ] Add some more useful stuff for Matrix.java
- [ ] Add some more useful stuff for Vec4.java
- [ ] Some stuffs...