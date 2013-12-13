import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class RSAHelper {

    public long p;
    public long q;
    public long n;
    public long phin;
    public long e;
    private long d;

    public RSAHelper() {
        long maxLong = 3037000499L;
        p = Utils.generatePrime(10000, maxLong);
        q = Utils.generatePrime(p + 1, maxLong);
        n = p * q;
        phin = (p - 1) * (q - 1);

        while (true) {
            e = (long) (Math.random() * phin);
            if (e > 1 && Utils.gcd(e, phin) == 1) {
                break;
            }
        }
        d = Utils.modReverse(e, phin);
        System.out.println("d*e = " + ((d * e) % phin));
    }

    public long encrypt(long b) {
        return Utils.modPow(b, e, n);
    }

    public List<Long> encrypt(String message) {
        List<Long> listEncrypt = new ArrayList<Long>();
        for (byte b : message.getBytes()) {
            listEncrypt.add(encrypt(b));
        }
        return listEncrypt;
    }

    public int decrypt(long b) {
        return (int)Utils.modPow(b, d, n);
    }

    public List<Integer> decrypt(List<Long> ciphers) {
        List<Integer> plainText = new ArrayList<Integer>();
        for (Long x : ciphers) {
            plainText.add(decrypt(x));
        }
        return plainText;
    }

    public String decrypt(String ciphers) {
        List<Long> listCiphers = new ArrayList<Long>();
        StringTokenizer st = new StringTokenizer(ciphers, " ");
        while (st.hasMoreTokens()) {
            String cipher = st.nextToken();
            listCiphers.add(Long.parseLong(cipher));
        }
        List<Integer> listPlains = decrypt(listCiphers);
        String plainText = "";
        for (int c : listPlains) {
            plainText += (char)c;
        }
        return plainText;
    }

    public String toString() {
        String s = "p = " + p + ", q = " + q + ", n = " + n + ", phi(n) = " + phin + ", e = " + e + ", d = " + d;
        return s;
    }

}
