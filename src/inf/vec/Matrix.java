package inf.vec;

public class Matrix {

    private double[][] arr;

    public Matrix() {
        arr = new double[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                arr[i][j] = ((i == j) ? 1 : 0);
            }
        }
    }

    public Matrix(double ax, double ay, double az,
            double bx, double by, double bz,
            double cx, double cy, double cz) {
        arr[0][0] = ax;
        arr[0][1] = ay;
        arr[0][2] = az;
        arr[1][0] = bx;
        arr[1][1] = by;
        arr[1][2] = ay;
        arr[2][0] = cx;
        arr[2][1] = cy;
        arr[2][2] = ax;
    }

    public void setElement(int i, int j, double x) {
        arr[i][j] = x;
    }

    public double getElement(int i, int j) {
        return arr[i][j];
    }

    public void add(Matrix a) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                arr[i][j] += a.getElement(i, j);
            }
        }
    }

    public Vec multivec(Vec v) {
        Vec vec1 = new Vec(arr[0][0], arr[0][1], arr[0][2]);
        Vec vec2 = new Vec(arr[1][0], arr[1][1], arr[1][2]);
        Vec vec3 = new Vec(arr[2][0], arr[2][1], arr[2][2]);

        double d1 = v.dot(vec1);
        double d2 = v.dot(vec2);
        double d3 = v.dot(vec3);

        return new Vec(d1, d2, d3);
    }

    public Matrix multimat(Matrix p) {
        Matrix result = new Matrix();
        double[][] temp = new double[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                double entry = 0;
                for (int k = 0; k < 3; k++) {
                    entry += arr[i][k] * p.getElement(k, j);
                }
                temp[i][j] = entry;
            }
        }
        for (int l = 0; l < 3; l++) {
            for (int m = 0; m < 3; m++) {
                result.setElement(l, m, temp[l][m]);
            }
        }
        return result;
    }

    public void scale(double s) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                arr[i][j] *= s;
            }
        }
    }

    public void disp() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.println("El (" + i + ", " + j + " is\t" +
                        arr[i][j]);
            }
        }
    }

    static public Matrix getReflectxzMat() {
        Matrix result = new Matrix();
        result.setElement(1, 1, -1);
        return result;
    }

    static public Matrix getReflectxyMat() {
        Matrix result = new Matrix();
        result.setElement(2, 2, -1);
        return result;
    }

    static public Matrix getReflectyzMat() {
        Matrix result = new Matrix();
        result.setElement(0, 0, -1);
        return result;
    }

    static public Matrix getRotzMat(double ang) {
        Matrix result = new Matrix();
        result.setElement(0, 0, Math.cos(ang));
        result.setElement(1, 0, Math.sin(ang));
        result.setElement(0, 1, (-Math.sin(ang)));
        result.setElement(1, 1, Math.cos(ang));
        return result;
    }

    static public Matrix getRotyMat(double ang) {
        Matrix result = new Matrix();
        result.setElement(0, 0, Math.cos(ang));
        result.setElement(2, 0, Math.sin(ang));
        result.setElement(0, 2, -Math.sin(ang));
        result.setElement(2, 2, Math.cos(ang));
        return result;
    }

    static public Matrix getRotxMat(double ang) {
        Matrix result = new Matrix();
        result.setElement(1, 1, Math.cos(ang));
        result.setElement(2, 1, Math.sin(ang));
        result.setElement(1, 2, -Math.sin(ang));
        result.setElement(2, 2, Math.cos(ang));
        return result;
    }

    static public Matrix getScaleMat(double x, double y, double z) {
        Matrix result = new Matrix();
        result.setElement(0, 0, x);
        result.setElement(1, 1, y);
        result.setElement(2, 2, z);
        return result;
    }

    static public Matrix getRotationMatrix(Vec axis, double angle) {
        double zrot1angle = 0;
        if (Math.abs(axis.getElement(0)) > 0.00001) {
            zrot1angle = Math.atan(axis.getElement(1) / axis.getElement(0));
        } else {
            if (axis.getElement(1) > 0.001) {
                zrot1angle = 1.570796;
            } else if (axis.getElement(1) < -0.001) {
                zrot1angle = -1.570796;
            }
        }
        Matrix zrot1 = getRotzMat(zrot1angle);

        double yrotangle = -1 * Math.acos(axis.getElement(2) / axis.mag());
        Matrix yrot = getRotyMat(yrotangle);

        Matrix zrot2 = getRotzMat(angle);

        Matrix yrotinv = getRotyMat(-1 * yrotangle);
        Matrix zrot1inv = getRotzMat(-1 * zrot1angle);

        return zrot1.multimat(yrot).multimat(zrot2).multimat(yrotinv).multimat(zrot1inv);
    }

    public String toString() {
        return "[" + arr[0][0] + "," + arr[0][1] + "," + arr[0][2] + "]\n" +
                "[" + arr[1][0] + "," + arr[1][1] + "," + arr[1][2] + "]\n" +
                "[" + arr[2][0] + "," + arr[2][1] + "," + arr[2][2] + "]\n";
    }
}
