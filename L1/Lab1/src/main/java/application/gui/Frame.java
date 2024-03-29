package application.gui;

import application.encryptor.CaesarCipher;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Frame extends JFrame implements ActionListener {
    private static final String FRAME_TITLE = "Caesar Cipher";
    private static final int DIMENSION = 600;

    private CaesarCipher caesarCipher;
    private JFrame frame;
    private JLabel headLabel;
    private JLabel offsetLabel;
    private JLabel inputMessageLabel;
    private JLabel outputMessageLabel;
    private JTextField offsetTextField;
    private JTextField inputTextField;
    private JTextField outputTextField;
    private JButton encryptionButton;

    public Frame() {
        headLabel = new JLabel();
        adjustHeadLabelSettings(headLabel);

        offsetLabel = new JLabel();
        adjustOffsetLabelSettings(offsetLabel);

        offsetTextField = new JTextField();
        adjustOffsetTextFieldSettings(offsetTextField);

        inputMessageLabel = new JLabel();
        adjustInputMessageLabelSettings(inputMessageLabel);

        inputTextField = new JTextField();
        adjustInputTextFieldSettings(inputTextField);

        encryptionButton = new JButton();
        adjustEncryptButtonSettings(encryptionButton);

        outputMessageLabel = new JLabel();
        adjustOutputMessageLabelSettings(outputMessageLabel);

        outputTextField = new JTextField();
        adjustOutputTextFieldSettings(outputTextField);

        frame = new JFrame();
        adjustFrameSettings(frame);

        frame.add(headLabel);
        frame.add(offsetLabel);
        frame.add(offsetTextField);
        frame.add(inputMessageLabel);
        frame.add(inputTextField);
        frame.add(encryptionButton);
        frame.add(outputMessageLabel);
        frame.add(outputTextField);
    }

    private void adjustFrameSettings(JFrame frame) {
        frame.setTitle(FRAME_TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(DIMENSION,DIMENSION);
        frame.getContentPane().setBackground(new Color(253,245,230));
        frame.setLayout(null);
        frame.setVisible(true);
    }

    private void adjustHeadLabelSettings(JLabel headLabel) {
        headLabel.setText(FRAME_TITLE);
        headLabel.setFont(new Font("Century Gothic", Font.PLAIN, 25));
        headLabel.setVerticalAlignment(JLabel.TOP);
        headLabel.setHorizontalAlignment(JLabel.CENTER);
        headLabel.setBounds(0,0, DIMENSION,50);
    }

    private void adjustOffsetLabelSettings(JLabel offsetLabel) {
        offsetLabel.setText("Enter offset");
        offsetLabel.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        offsetLabel.setHorizontalAlignment(JLabel.CENTER);
        offsetLabel.setBounds(5, 60, 120, 50);
    }

    private void adjustOffsetTextFieldSettings(JTextField offsetTextField) {
        offsetTextField.setPreferredSize(new Dimension(250,40));
        offsetTextField.setBounds(170, 60, 50, 50);
        offsetTextField.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        offsetTextField.setHorizontalAlignment(JLabel.CENTER);
    }

    private void adjustInputMessageLabelSettings(JLabel inputMessageLabel) {
        inputMessageLabel.setText("Enter message");
        inputMessageLabel.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        inputMessageLabel.setHorizontalAlignment(JLabel.CENTER);
        inputMessageLabel.setBounds(5, 120, 150, 50);
    }

    private void adjustInputTextFieldSettings(JTextField inputTextField) {
        inputTextField.setPreferredSize(new Dimension(250,40));
        inputTextField.setBounds(170, 120, 400, 50);
        inputTextField.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        inputTextField.setHorizontalAlignment(JLabel.CENTER);
    }

    private void adjustEncryptButtonSettings(JButton encryptionButton) {
        encryptionButton.setBounds(200, 230, 200, 50);
        encryptionButton.setText("Encrypt");
        encryptionButton.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        encryptionButton.setForeground(Color.WHITE);
        encryptionButton.setFocusable(false);
        encryptionButton.setBackground(Color.black);
        encryptionButton.addActionListener(this);
    }

    private void adjustOutputMessageLabelSettings(JLabel outputMessageLabel) {
        outputMessageLabel.setText("Encrypted message");
        outputMessageLabel.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        outputMessageLabel.setHorizontalAlignment(JLabel.CENTER);
        outputMessageLabel.setBounds(5, 340, 575, 50);
    }

    private void adjustOutputTextFieldSettings(JTextField outputTextField) {
        outputTextField.setPreferredSize(new Dimension(575,50));
        outputTextField.setBounds(5, 400, 575, 50);
        outputTextField.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        outputTextField.setHorizontalAlignment(JLabel.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(encryptionButton)) {
            caesarCipher = new CaesarCipher();
            String encryptedMessage = caesarCipher.encrypt(inputTextField.getText(),
                    Integer.parseInt(offsetTextField.getText()));
            outputTextField.setText(encryptedMessage);
        }
    }
}
