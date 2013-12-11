import java.util.ArrayList;
import java.util.List;

public class RSAHelper {

    public long p;
    public long q;
    public long n;
    public long phin;
    public long e;
    public long d;

    public RSAHelper() {
        p = Utils.generatePrime(10000);
        q = Utils.generatePrime(p + 1);
        n = 1L * p * q;
        phin = 1L * (p - 1) * (q - 1);

        while (true) {
            e = (long) (Math.random() * phin);
            if (e > 1 && Utils.gcd(e, phin) == 1) {
                break;
            }
        }
        d = Utils.modReverse(e, phin);
        System.out.println("d*e = "  + ((d*e) % phin));
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

    public long decrypt(long b) {
        return Utils.modPow(b, d, n);
    }
    public String decrypt(List<Long> ciphers){
        String s = "";
        for (Long x : ciphers) {
            s += decrypt(x)+" ";
        }
        return s;
    }

    public String toString(){
        String s =  "p = " + p + ", q = " + q + ", n = " + n + ", phi(n) = " + phin + ", e = " + e + ", d = " + d;
        return s;
    }

}
