import java.math.BigInteger;
import java.util.Random;

public class Utils {
    /**
     * Generate prime number, bigger than start, if not exist return -1
     * If number smaller than 1e8 than sqrt algorithm will be used,
     * otherwise will be used Miller Rabin Primality Test
     *
     * @param start starting point
     * @param end   end point
     * @return prime number if found, else -1
     */
    public static long generatePrime(long start, long end) {
        for (long x = start; x <= end; x++) {
            if (x < 10000000) {
                if (isPrime(x)) {
                    return x;
                }
            } else if (MillerRabinTest(x, 15)) {
                return x;
            }
        }
        return -1;
    }

    /**
     * Sqrt algorithm to find prime number
     * Work time O(sqrt(n))
     *
     * @param n number to check
     * @return true, false
     */

    public static boolean isPrime(long n) {
        for (long i = 2; i * i <= n; i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    /**
     * Miller Rabin Primality Test
     * probability will be (1/4)^round
     * Wikipedia information:
     * Input: n > 3, an odd integer to be tested for primality;
     * Input: k, a parameter that determines the accuracy of the test
     * Output: composite if n is composite, otherwise probably prime
     * write n − 1 as 2s·d with d odd by factoring powers of 2 from n − 1
     * WitnessLoop: repeat k times:
     * pick a random integer a in the range [2, n − 2]
     * n ← ad mod n
     * if n = 1 or n = n − 1 then do next WitnessLoop
     * repeat s − 1 times:
     * n ← x2 mod n
     * if n = 1 then return composite
     * if n = n − 1 then do next WitnessLoop
     * return composite
     * return probably prime
     *
     * @param n     number to check
     * @param round iterations of algorithm
     * @return true, false
     */
    public static boolean MillerRabinTest(long n, int round) {
        new BigInteger(12, 15, new Random());
        if (n < 2 || n % 2 == 0) return false;
        if (n == 2 || n == 3) return true;
        long s = 0;
        long t = n - 1;
        while (t % 2 == 0) {
            s++;
            t /= 2;
        }
        for (; round > 0; round--) {
            long a = (long) (Math.random() * (n - 2)) + 1;
            a = modPow(a, t, n);
            if (a == 1 || a == n - 1) continue;
            for (int k = 0; k < s - 1; k++) {
                a = modPow(a, 2, n);
                if (a == n - 1)
                    break;
            }
            if (a == n - 1) continue;
            return false;
        }
        return true;
    }

    /**
     * Greatest common divisor using Euclid algorithm
     * Worktime O(log(n))
     *
     * @param a first number
     * @param b second number
     * @return gcd of a and b
     */
    public static long gcd(long a, long b) {
        while (b != 0 && a != 0) {
            long t = b;
            b = a % b;
            a = t;
        }
        return a;
    }

    /**
     * module reverse using extended euclid algorithm
     * @param a first number
     * @param mod module
     * @return reverse number of a (a^-1)
     */
    public static long modReverse(long a, long mod) {
        long b0 = mod;
        long x0 = 0, x1 = 1;
        while (a > 1) {
            long q = a / mod;
            long t = mod;
            mod = a % mod;
            a = t;
            t = x0;
            x0 = x1 - q * x0;
            x1 = t;
        }
        if (x1 < 0)
            x1 += b0;
        return x1;
    }

    /**
     * Binary exponentiation by module
     *
     * @param a   base
     * @param n   exponent
     * @param mod module
     * @return
     */

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
