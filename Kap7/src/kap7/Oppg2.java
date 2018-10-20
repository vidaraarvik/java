package kap7;

public class Oppg2 {

    public static void main(String[] args) {

        System.out.println(s(5));

    }

    // Evaluate the sum of the first n integers
    public static long s(int n) {
        if (n <= 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return s(n - 1) + n;
        }
    }

}
