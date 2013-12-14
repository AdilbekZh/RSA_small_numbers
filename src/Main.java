import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Main implements ActionListener {

    JFrame frame;
    JPanel panel;
    JButton btn0;
    JButton btn1;
    JLabel label;
    JTextArea textArea0;
    JTextArea textArea1;


    RSAHelper rsa;

    public Main() {
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
        textArea1 = new JTextArea("", 10, 10);
        textArea0.setLineWrap(true);
        textArea1.setLineWrap(true);
        textArea0.setBorder(BorderFactory.createLineBorder(Color.black));
        textArea1.setBorder(BorderFactory.createLineBorder(Color.black));

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
        frame.repaint();
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
    }

}
