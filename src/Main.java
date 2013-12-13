import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Main implements ActionListener{

    JFrame frame;
    JPanel panel;
    JButton btn0;
    JButton btn1;
    JLabel label;
    JTextArea textArea0;
    JTextArea textArea1;


    RSAHelper rsa;

    public Main () {
        frame = new JFrame("RSA encrypt/decrypt");
        frame.setVisible(true);
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();

        panel.setLayout(new FlowLayout());

        label = new JLabel("RSA encription and decryption");
        btn0 = new JButton("Encrypt");
        btn1 = new JButton("Decrypt");
        textArea0 = new JTextArea("", 10, 10);
        textArea1 = new JTextArea("",10, 10);
        textArea0.setLineWrap(true);
        textArea1.setLineWrap(true);



        btn0.addActionListener(this);
        btn1.addActionListener(this);

        Box textAreas = Box.createHorizontalBox();
        textAreas.add(textArea0);
        textAreas.add(textArea1);


        panel.add(label);
        panel.add(textAreas);
        panel.add(btn0);
        panel.add(btn1);


        frame.add(panel);

        rsa = new RSAHelper();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(btn0.getActionCommand())) {
            String text = textArea0.getText().toString();
            List<Long> encrypted = rsa.encrypt(text);
            String cipherText = "";
            for (Long x : encrypted) {
                cipherText += x + " ";
            }
            textArea1.setText(cipherText);
        }
        if (e.getActionCommand().equals(btn1.getActionCommand())) {
            String text = textArea0.getText();
            String plainText = rsa.decrypt(text);
            textArea1.setText(plainText);
        }
    }

    public static void main(String[] args) {
        new Main();
        System.out.println((long)Math.sqrt(Long.MAX_VALUE));
        if (true) return;
        /*Scanner in = new Scanner(System.in);

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
    */
    }

}
