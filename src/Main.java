import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        RSAHelper rsa = new RSAHelper();
        Scanner in = new Scanner(System.in);
        System.out.println(rsa.toString());
        System.out.print("Enter message: ");
        String s = in.next();
        List<Long> encrypted = rsa.encrypt(s);
        String cipherText = "";
        for (Long x : encrypted) {
            cipherText += x + " ";
        }
        System.out.println("\nPlaintext: " + Arrays.toString(s.getBytes()));
        System.out.println("Encoded: " + cipherText);
        String decrypted = rsa.decrypt(encrypted);
        System.out.println("Decrypted: " + decrypted);
    }
}
