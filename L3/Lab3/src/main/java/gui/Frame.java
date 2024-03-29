package gui;

import decryptor.FrequencyAnalysisDecryptor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame implements ActionListener {
    private static final String FRAME_TITLE = "Frequency Analysis Decryptor";
    private static final int DIMENSION = 600;

    private FrequencyAnalysisDecryptor frequencyAnalysisDecryptor;
    private JFrame frame;
    private JLabel headLabel;
    private JLabel inputMessageLabel;
    private JLabel outputMessageLabel;
    private JTextField inputTextField;
    private JTextField outputTextField;
    private JButton decryptionButton;

    public Frame() {
        headLabel = new JLabel();
        adjustHeadLabelSettings(headLabel);

        inputMessageLabel = new JLabel();
        adjustInputMessageLabelSettings(inputMessageLabel);

        inputTextField = new JTextField();
        adjustInputTextFieldSettings(inputTextField);

        decryptionButton = new JButton();
        adjustDecryptButtonSettings(decryptionButton);

        outputMessageLabel = new JLabel();
        adjustOutputMessageLabelSettings(outputMessageLabel);

        outputTextField = new JTextField();
        adjustOutputTextFieldSettings(outputTextField);

        frame = new JFrame();
        adjustFrameSettings(frame);

        frame.add(headLabel);
        frame.add(inputMessageLabel);
        frame.add(inputTextField);
        frame.add(decryptionButton);
        frame.add(outputMessageLabel);
        frame.add(outputTextField);
    }

    private void adjustFrameSettings(JFrame frame) {
        frame.setTitle(FRAME_TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(DIMENSION,DIMENSION);
        frame.getContentPane().setBackground(new Color(204,204,255));
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

    private void adjustDecryptButtonSettings(JButton decryptionButton) {
        decryptionButton.setBounds(200, 230, 200, 50);
        decryptionButton.setText("Decrypt");
        decryptionButton.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        decryptionButton.setForeground(Color.WHITE);
        decryptionButton.setFocusable(false);
        decryptionButton.setBackground(Color.black);
        decryptionButton.addActionListener(this);
    }

    private void adjustOutputMessageLabelSettings(JLabel outputMessageLabel) {
        outputMessageLabel.setText("Decrypted message");
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
        if (e.getSource().equals(decryptionButton)) {
            frequencyAnalysisDecryptor = new FrequencyAnalysisDecryptor();
            String decryptedMessage = frequencyAnalysisDecryptor.frequencyAnalysis(inputTextField.getText());
            outputTextField.setText(decryptedMessage);
        }
    }
}
