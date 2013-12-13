import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println((long)Math.sqrt(Long.MAX_VALUE));
        if (true) return;
        Scanner in = new Scanner(System.in);
        RSAHelper rsa = new RSAHelper();
        System.out.println(rsa.toString());
        System.out.print("Enter message: ");
        String s = in.nextLine();
        List<Long> encrypted = rsa.encrypt(s);
        String cipherText = "";
        for (Long x : encrypted) {
            cipherText += x + " ";
        }
        System.out.println("\nPlaintext: " + Arrays.toString(s.getBytes()));
        System.out.println("Encoded: " + cipherText);
        List<Integer> decrypted = rsa.decrypt(encrypted);
        String plainText = "";
        for (int x : decrypted) {
            plainText += (char)x;
        }
        System.out.println("Decrypted: " + plainText);

    }
}
