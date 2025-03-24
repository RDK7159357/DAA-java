package week_3;

public class Karatsuba {
    static long karatsuba(long X, long Y) {
        // Base Case
        if (X < 10 && Y < 10)
            return X * Y;
        // Write Code Here
        int size = Math.max(get_size(X), get_size(Y));
        int half = size / 2;

        long X1 = X / (long) Math.pow(10, half);
        long X2 = X % (long) Math.pow(10, half);
        long Y1 = Y / (long) Math.pow(10, half);
        long Y2 = Y % (long) Math.pow(10, half);

        long U = karatsuba(X1, Y1);
        long V = karatsuba(X2, Y2);
        long W = karatsuba(X1 + X2, Y1 + Y2);

        long Z = W - (U + V);

        return (long) (U * Math.pow(10, 2 * half) + Z * Math.pow(10, half) + V);

    }

    static int get_size(long value) {
        return Long.toString(value).length();
        // Write Code Here

    }

    public static void main(String args[]) {
        // two numbers
        long x = 145623;
        long y = 653324;
        System.out.print("The final product is: ");
        long product = karatsuba(x, y);
        System.out.println(product);
    }
}