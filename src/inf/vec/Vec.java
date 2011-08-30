package inf.vec;

public class Vec {

    private double arr[];

    public Vec(double a, double b, double c) {
        arr = new double[3];
        arr[0] = a;
        arr[1] = b;
        arr[2] = c;
    }

    public void setElement(int i, double entry) {
        if ((i >= 0) && (i < 3)) {
            arr[i] = entry;
        }
    }

    public double getElement(int i) {
        if ((i >= 0) && (i < 3)) {
            return arr[i];
        }
        return 0;
    }

    public Vec complexConjugate() {
        Vec result = new Vec(arr[0], -arr[1], arr[2]);
        return result;
    }

    public Vec complexMultiply(Vec b) {
        double x, y, z;
        x = arr[0] * b.getElement(0) - arr[1] * b.getElement(1);
        y = arr[0] * b.getElement(1) + arr[1] * b.getElement(0);
        z = arr[2];
        Vec result = new Vec(x, y, z);
        return result;
    }

    public Vec complexDivide(Vec b) {
        double x, y, z, d;
        d = b.getElement(0)*b.getElement(0) + b.getElement(1)*b.getElement(1);

        x =  (arr[0] * b.getElement(0) + arr[1] * b.getElement(1)) / d;
        y = (-arr[0] * b.getElement(1) + arr[1] * b.getElement(0)) / d;
        z = arr[2];
        Vec result = new Vec(x, y, z);
        return result;
    }

    public void display() {
        for (int i = 0; i < 3; i++) {
            System.out.println("arr[" + i + "] is\t" + arr[i]);
        }
    }

    public Vec add(Vec b) {
        double x, y, z;
        x = arr[0] + b.getElement(0);
        y = arr[1] + b.getElement(1);
        z = arr[2] + b.getElement(2);
        Vec result = new Vec(x, y, z);
        return result;
    }

    public Vec negate() {
        Vec result = new Vec(-arr[0], -arr[1], -arr[2]);
        return result;
    }

    public Vec scale(double s) {
        Vec result = new Vec(s * arr[0], s * arr[1], s * arr[2]);
        return result;
    }

    public double dot(Vec b) {
        double x;
        x = arr[0] * b.getElement(0) +
                arr[1] * b.getElement(1) +
                arr[2] * b.getElement(2);
        return x;
    }

    public Vec cross(Vec b) {
        double x, y, z;
        x = arr[1] * b.getElement(2) -
                arr[2] * b.getElement(1);
        y = arr[2] * b.getElement(0) -
                arr[0] * b.getElement(2);
        z = arr[0] * b.getElement(1) -
                arr[1] * b.getElement(0);
        Vec result = new Vec(x, y, z);
        return result;
    }

    public double mag() {
        double x;
        x = arr[0] * arr[0] + arr[1] * arr[1] + arr[2] * arr[2];
        x = (double) Math.pow((double) x, 0.5);
        return x;
    }

    public Vec unitize() {
        double x;
        x = this.mag();
        Vec result = new Vec(arr[0] / x, arr[1] / x, arr[2] / x);
        return result;
    }

    public Vec multimat(Matrix m) {
        double x = 0, y = 0, z = 0;
        for (int j = 0; j < 3; j++) {
            x += m.getElement(0, j) * arr[j];
            y += m.getElement(1, j) * arr[j];
            z += m.getElement(2, j) * arr[j];
        }
        return new Vec(x, y, z);
    }

    public Vec convertToCartesian() {
        double x, y, z;
        x = (double) (arr[0] * Math.sin((double) arr[1]) *
                Math.cos((double) arr[2]));
        y = (double) (arr[0] * Math.sin((double) arr[1]) *
                Math.sin((double) arr[2]));
        z = (double) (arr[0] * Math.cos((double) arr[1]));
        Vec result = new Vec(x, y, z);
        return result;
    }

    public Vec convertToPolar() {
        double r, t, p;
        r = (double) Math.sqrt((double) (arr[0] * arr[0] +
                arr[1] * arr[1] + arr[2] * arr[2]));
        t = (double) (Math.acos((double) (arr[2] / r)));
        p = (double) (Math.atan2((double) arr[0], (double) arr[1]));
        p %= (double) 6.2831852;
        Vec result = new Vec(r, t, p);
        return result;
    }

    public String toString() {
        return "["+arr[0]+","+arr[1]+","+arr[2]+"]";
    }
}


