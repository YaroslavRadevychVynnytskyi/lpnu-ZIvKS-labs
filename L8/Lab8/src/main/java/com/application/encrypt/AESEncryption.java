package com.application.encrypt;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AESEncryption extends JFrame {
    private JButton encryptButton;
    private JButton decryptButton;
    private JTextField personalInfoField;
    private JFileChooser fileChooser;

    public AESEncryption() {
        super("AES Encryption");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLayout(new FlowLayout());

        personalInfoField = new JTextField(20);
        add(new JLabel("Personal Information:"));
        add(personalInfoField);

        encryptButton = new JButton("Encrypt");
        encryptButton.addActionListener(new EncryptButtonListener());
        add(encryptButton);

        decryptButton = new JButton("Decrypt");
        decryptButton.addActionListener(new DecryptButtonListener());
        add(decryptButton);

        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    }

    private class EncryptButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int returnVal = fileChooser.showOpenDialog(AESEncryption.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                String personalInfo = personalInfoField.getText();
                try {
                    SecretKeySpec secretKey = generateKeyFromPersonalInfo(personalInfo);
                    encryptFile(file.getAbsolutePath(), secretKey);
                    JOptionPane.showMessageDialog(AESEncryption.this,
                            "File encrypted successfully.");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(AESEncryption.this,
                            "Error encrypting file: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private class DecryptButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int returnVal = fileChooser.showOpenDialog(AESEncryption.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                String personalInfo = personalInfoField.getText();
                try {
                    SecretKeySpec secretKey = generateKeyFromPersonalInfo(personalInfo);
                    decryptFile(file.getAbsolutePath(), secretKey);
                    JOptionPane.showMessageDialog(AESEncryption.this,
                            "File decrypted successfully.");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(AESEncryption.this,
                            "Error decrypting file: "
                            + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private SecretKeySpec generateKeyFromPersonalInfo(String personalInfo) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] keyBytes = md.digest(personalInfo.getBytes());
        byte[] truncatedKeyBytes = new byte[16];
        System.arraycopy(keyBytes, 0, truncatedKeyBytes, 0, Math.min(keyBytes.length,
                truncatedKeyBytes.length));
        return new SecretKeySpec(truncatedKeyBytes, "AES");
    }

    private void encryptFile(String filePath, SecretKeySpec secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        try (FileInputStream fis = new FileInputStream(filePath);
             FileOutputStream fos = new FileOutputStream(filePath + ".enc")) {
            byte[] inputBytes = new byte[4096];
            int bytesRead;
            while ((bytesRead = fis.read(inputBytes)) != -1) {
                byte[] outputBytes = cipher.update(inputBytes, 0, bytesRead);
                if (outputBytes != null) {
                    fos.write(outputBytes);
                }
            }
            byte[] outputBytes = cipher.doFinal();
            if (outputBytes != null) {
                fos.write(outputBytes);
            }
        }
    }

    private void decryptFile(String encryptedFilePath, SecretKeySpec secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        try (FileInputStream fis = new FileInputStream(encryptedFilePath);
             FileOutputStream fos = new FileOutputStream(encryptedFilePath.replace(".enc",
                     ""))) {
            byte[] inputBytes = new byte[4096];
            int bytesRead;
            while ((bytesRead = fis.read(inputBytes)) != -1) {
                byte[] outputBytes = cipher.update(inputBytes, 0, bytesRead);
                if (outputBytes != null) {
                    fos.write(outputBytes);
                }
            }
            byte[] outputBytes = cipher.doFinal();
            if (outputBytes != null) {
                fos.write(outputBytes);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AESEncryption().setVisible(true);
            }
        });
    }
}
