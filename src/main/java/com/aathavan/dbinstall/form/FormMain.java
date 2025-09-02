package com.aathavan.dbinstall.form;

import com.aathavan.dbinstall.common.DbInstallCommon;
import com.aathavan.dbinstall.common.ImagesPath;
import com.aathavan.dbinstall.common.Secutity;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Objects;

@Component
public class FormMain extends JFrame implements WindowListener, KeyListener, ActionListener, FocusListener, MouseListener {

    private JTextField txtServerIp, txtCompanyName, txtCompanyCode, txtPortNo, txtUsername;

    private JPasswordField txtPassword;

    private JButton btnCreate, btnClear;

    private ClassLoader classLoader = this.getClass().getClassLoader();

    private final org.apache.log4j.Logger logger = Logger.getLogger(FormMain.class);
    private ImageIcon backgroundImageIcon = null;

    public FormMain() {

        backgroundImageIcon = new ImageIcon(Objects.requireNonNull(classLoader.getResource(ImagesPath.Image.BACKGROUND.getValue())));
        setSize(backgroundImageIcon.getIconWidth(), backgroundImageIcon.getIconHeight());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setUndecorated(true);
        addWindowListener(this);
        try {
            BufferedImage bufferedImage = null;
            if (classLoader.resources(ImagesPath.Image.LOGO_ICON.getValue()) != null
                    && ImageIO.read(Objects.requireNonNull(classLoader.getResource(ImagesPath.Image.LOGO_ICON.getValue()))) != null) {
                bufferedImage = ImageIO.read(Objects.requireNonNull(classLoader.getResource(ImagesPath.Image.LOGO_ICON.getValue())));
            }
            if (bufferedImage != null)
                setIconImage(bufferedImage);

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }


    private void componentCreation() {

        JPanel panelCompanyCreation;
        JTabbedPane tabMain;
        JLabel lblServerIp, lblUserName, lblPassword, lblPortNo, LblCompanyName, lblCompanyCode, lblCompanyCreationImg,
                lblExit;

        Font font = new Font("Times New Roman", Font.PLAIN, 15);

        int compWidth = getWidth() * 20 / 100;
        int compHeight = getHeight() * 4 / 100;
        int txtCompWidth = (int) (compWidth * 1.2);
        double hGap = 1.1, vGap = 7.1;
        int x, y;


        tabMain = new JTabbedPane();
        tabMain.setBounds(-2, -25, getWidth() + 5, getHeight() + 28);
        getContentPane().add(tabMain);


        panelCompanyCreation = new JPanel(null);
        panelCompanyCreation.setBounds(0, 0, getWidth(), getHeight());
        panelCompanyCreation.setBorder(BorderFactory.createLineBorder(Color.blue));
        tabMain.add(panelCompanyCreation);

        lblCompanyCreationImg = new JLabel();
        lblCompanyCreationImg.setBounds(0, 0, panelCompanyCreation.getWidth(), panelCompanyCreation.getHeight());
        lblCompanyCreationImg.setIcon(backgroundImageIcon);
        panelCompanyCreation.add(lblCompanyCreationImg);


//        lblExit=new JLabel();
//        lblExit.setBounds();
//        lblExit.set


        lblServerIp = new JLabel("Server Ip");
        lblServerIp.setBounds(getWidth() * 20 / 100, getHeight() * 20 / 100, compWidth, compHeight);
        lblServerIp.setFont(font);
        lblCompanyCreationImg.add(lblServerIp);

        x = DbInstallCommon.horizontalGap(panelCompanyCreation, lblServerIp, hGap);
        y = lblServerIp.getY();

        txtServerIp = new JTextField();
        txtServerIp.setBounds(x, y, txtCompWidth, compHeight);
        txtServerIp.setToolTipText("Enter The Server Ip..");
        lblCompanyCreationImg.add(txtServerIp);

        x = lblServerIp.getX();
        y = DbInstallCommon.verticalGap(panelCompanyCreation, lblServerIp, vGap);

        lblUserName = new JLabel("UserName");
        lblUserName.setBounds(x, y, compWidth, compHeight);
        lblUserName.setFont(font);
        lblCompanyCreationImg.add(lblUserName);

        x = DbInstallCommon.horizontalGap(panelCompanyCreation, lblUserName, hGap);

        txtUsername = new JTextField();
        txtUsername.setBounds(x, y, txtCompWidth, compHeight);
        txtUsername.setToolTipText("Enter The UserName..");
        lblCompanyCreationImg.add(txtUsername);


        x = lblServerIp.getX();
        y = DbInstallCommon.verticalGap(panelCompanyCreation, lblUserName, vGap);

        lblPassword = new JLabel("Password");
        lblPassword.setBounds(x, y, compWidth, compHeight);
        lblPassword.setFont(font);
        lblCompanyCreationImg.add(lblPassword);

        x = DbInstallCommon.horizontalGap(panelCompanyCreation, lblPassword, hGap);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(x, y, txtCompWidth, compHeight);
        txtPassword.setToolTipText("Enter The Password");
        lblCompanyCreationImg.add(txtPassword);

        x = lblServerIp.getX();
        y = DbInstallCommon.verticalGap(panelCompanyCreation, lblPassword, vGap);

        lblPortNo = new JLabel("PortNo");
        lblPortNo.setBounds(x, y, compWidth, compHeight);
        lblPortNo.setFont(font);
        lblCompanyCreationImg.add(lblPortNo);

        x = DbInstallCommon.horizontalGap(panelCompanyCreation, lblPortNo, hGap);

        txtPortNo = new JTextField();
        txtPortNo.setBounds(x, y, txtCompWidth, compHeight);
        txtPortNo.setToolTipText("Enter The PortNo");
        lblCompanyCreationImg.add(txtPortNo);


        x = lblServerIp.getX();
        y = DbInstallCommon.verticalGap(panelCompanyCreation, lblPortNo, vGap);

        LblCompanyName = new JLabel("CompanyName");
        LblCompanyName.setBounds(x, y, compWidth, compHeight);
        LblCompanyName.setFont(font);
        lblCompanyCreationImg.add(LblCompanyName);

        x = DbInstallCommon.horizontalGap(panelCompanyCreation, LblCompanyName, hGap);

        txtCompanyName = new JTextField();
        txtCompanyName.setBounds(x, y, txtCompWidth, compHeight);
        txtCompanyName.setToolTipText("User CompanyName..");
        lblCompanyCreationImg.add(txtCompanyName);


        x = lblServerIp.getX();
        y = DbInstallCommon.verticalGap(panelCompanyCreation, LblCompanyName, vGap);

        lblCompanyCode = new JLabel("CompanyCode");
        lblCompanyCode.setBounds(x, y, compWidth, compHeight);
        lblCompanyCode.setFont(font);
        lblCompanyCreationImg.add(lblCompanyCode);

        x = DbInstallCommon.horizontalGap(panelCompanyCreation, lblCompanyCode, hGap);

        txtCompanyCode = new JTextField();
        txtCompanyCode.setBounds(x, y, txtCompWidth, compHeight);
        txtCompanyCode.setEditable(false);
        lblCompanyCreationImg.add(txtCompanyCode);


        x = lblServerIp.getX() + (lblServerIp.getWidth() / 2);
        y = DbInstallCommon.verticalGap(panelCompanyCreation, txtCompanyCode, vGap / 2);


        btnClear = new JButton("Clear");
        btnClear.setBounds(x, y, (int) (compWidth / 1.4), (int) (compHeight * 1.2));
        btnClear.setForeground(Color.white);
        btnClear.setFont(font);
        btnClear.setBackground(Color.decode("#4b7abd"));
        btnClear.setBorderPainted(false);
        btnClear.setVerifyInputWhenFocusTarget(false);
        lblCompanyCreationImg.add(btnClear);


        x = DbInstallCommon.horizontalGap(panelCompanyCreation, btnClear, 5);

        btnCreate = new JButton("Create");
        btnCreate.setBounds(x, y, (int) (compWidth / 1.4), (int) (compHeight * 1.2));
        btnCreate.setForeground(Color.white);
        btnCreate.setFont(font);
        btnCreate.setBackground(Color.decode("#4b7abd"));
        btnCreate.setBorderPainted(false);
        lblCompanyCreationImg.add(btnCreate);

        repaint();

    }


    private void fileRead() {
        try {
            File file = new File("compdetails.company");
            if (!file.exists()) {
//                JOptionPane.showMessageDialog(getContentPane(), "Company Details Not Found... Create a New Company");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(getContentPane(), e.getMessage());
        }
    }


    @Override
    public void windowOpened(WindowEvent e) {

        componentCreation();
        compoundListener();
        fileRead();


    }

    private void compoundListener() {
        addListeners(txtServerIp, true);
        addListeners(txtPortNo, false);
        addListeners(txtCompanyName, false);
        addListeners(txtPassword, false);
        addListeners(txtUsername, false);
        addListeners(btnClear, true);
        addListeners(btnCreate, true);


        addVerifier(txtServerIp, "Enter the Server Ip");
        addVerifier(txtPortNo, "Enter PORT");
        addVerifier(txtCompanyName, "Enter Company Name");
        addVerifier(txtPassword, "Enter Password");
        addVerifier(txtUsername, "Enter UserName");
        txtServerIp.requestFocusInWindow();
        txtCompanyName.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                try {
                    if (txtCompanyName.getText().isEmpty()) {
                        throw new Exception("Enter the CompanyName");
                    }
                    if (txtCompanyName.getText().length() < 3)
                        throw new Exception("CompanyName Should Have Minimum of 3 Characters");

                    txtCompanyCode.setText(txtCompanyName.getText().toLowerCase().substring(0, 3));


                    return true;
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(getContentPane(), e.getMessage());
                    return false;
                }
            }
        });

    }

    private void addVerifier(JTextField txtBox, String message) {
        txtBox.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                try {
                    if (txtBox.isEnabled()) {
                        if (txtBox.getText().trim().isEmpty()) {
                            throw new Exception(message);
                        }
                    }
                    return true;
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(getContentPane(), e.getMessage());
                    return false;
                }
            }
        });
    }

    private void addListeners(JComponent txtField, boolean focusListener) {

        if (txtField instanceof JTextField) {
            ((JTextField) txtField).addKeyListener(this);
            if (focusListener)
                ((JTextField) txtField).addFocusListener(this);
        } else {
            ((JButton) txtField).addActionListener(this);
            if (focusListener)
                ((JButton) txtField).addMouseListener(this);

        }
    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == btnCreate) {
                if (txtServerIp.getText().isEmpty()) {
                    txtServerIp.requestFocusInWindow();
                    throw new Exception("Enter Server Ip");
                }
                if (txtCompanyName.getText().isEmpty()) {
                    txtCompanyName.requestFocusInWindow();
                    throw new Exception("Enter CompanyName");
                }
                if (txtPortNo.getText().isEmpty()) {
                    txtPortNo.requestFocusInWindow();
                    throw new Exception("Enter PortNo");
                }
                if (String.valueOf(txtPassword.getPassword()).isEmpty()) {
                    txtPassword.requestFocusInWindow();
                    throw new Exception("Enter Password");
                }
                if (txtUsername.getText().isEmpty()) {
                    txtServerIp.requestFocusInWindow();
                    throw new Exception("Enter UsernName");
                }
                setTxtFileTiValues();

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(getContentPane(), ex.getMessage());
        }
    }

    private void setTxtFileTiValues() {
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getComponent() == btnCreate)
            btnCreate.setBackground(Color.BLUE);

    }

    @Override
    public void focusLost(FocusEvent e) {

        if (e.getComponent() == btnCreate)
            btnCreate.setBackground(Color.white);

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }


    @Override
    public void keyPressed(KeyEvent e) {
        try {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_ENTER -> {
                    if (e.getComponent() == txtServerIp) {
                        txtUsername.requestFocusInWindow();
                    } else if (e.getComponent() == txtUsername)
                        txtPassword.requestFocusInWindow();
                    else if (e.getComponent() == txtPassword)
                        txtPortNo.requestFocusInWindow();
                    else if (e.getComponent() == txtPortNo)
                        txtCompanyName.requestFocusInWindow();
                    else if (e.getComponent() == txtCompanyName)
                        btnCreate.requestFocusInWindow();
                    else if (e.getComponent() == btnCreate)
                        btnCreate.doClick();
                    else if (e.getComponent() == btnClear)
                        btnClear.doClick();
                }
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

        if (e.getSource() == btnCreate) {
            btnCreate.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btnCreate.setForeground(Color.BLACK);
            btnCreate.setBorderPainted(true);
        }
        if (e.getSource() == btnClear) {
            btnClear.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btnClear.setForeground(Color.BLACK);
            btnClear.setBorderPainted(true);
        }

    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == btnCreate) {
            btnCreate.setForeground(Color.WHITE);
            btnCreate.setBorderPainted(false);
        }
        if (e.getSource() == btnClear) {
            btnClear.setForeground(Color.WHITE);
            btnClear.setBorderPainted(false);
        }

    }
}
