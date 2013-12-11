public class Utils {

    public static long generatePrime(long start) {
        for (long x = start; x < Long.MAX_VALUE; x++) {
            if (isPrime(x)) {
                return x;
            }
        }
        return 65537;
    }

    public static boolean isPrime(long n) {
        for (long i = 2; i * i <= n; i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    public static int gcd(int a, int b) {
        while (b != 0 && a != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }

    public static long gcd(long a, long b) {
        while (b != 0 && a != 0) {
            long t = b;
            b = a % b;
            a = t;
        }
        return a;
    }


    public static long modReverse(long a, long b) {
        long b0 = b;
        long x0 = 0, x1 = 1;
        while (a > 1) {
            long q = a / b;
            long t = b;
            b = a % b;
            a = t;
            t = x0;
            x0 = x1 - q * x0;
            x1 = t;
        }
        if (x1 < 0)
            x1 += b0;
        System.out.println("MODREVERSE: " + x1);
        return x1;
    }

    public static long modPow(long a, long n, long mod) {
        if (n == 0)
            return 1;
        if (n % 2 == 1) {
            return (modPow(a, n - 1, mod) * a) % mod;
        } else {
            long t = modPow(a, n / 2, mod);
            return (t * t) % mod;
        }
    }
}
