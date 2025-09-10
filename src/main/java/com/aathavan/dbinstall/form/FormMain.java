package com.aathavan.dbinstall.form;

import com.aathavan.dbinstall.common.*;
import com.aathavan.dbinstall.config.ConnectionConfig;
import com.aathavan.dbinstall.install.InstallLogic;
import com.aathavan.dbinstall.model.ServerCredentials;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Timer;

@Component
public class FormMain extends JFrame implements WindowListener, KeyListener, ActionListener, FocusListener, MouseListener {

    private JTextField txtServerIp, txtCompanyName, txtCompanyCode, txtPortNo, txtUsername;

    private JPasswordField txtPassword;
    private static JTextArea txtArea;

    private JButton btnCreate, btnClear, btnInstall, btnExit1, btnExit2;

    private JTabbedPane tabMain;

    public static JProgressBar getjProgressBar() {
        lblProgressPer.setText(jProgressBar.getValue() + "%");
        return jProgressBar;
    }

    private static JProgressBar jProgressBar;

    private static JLabel lblProgressPer;


    private static JLabel lblTimer;

    private final Logger logger = Logger.getLogger(FormMain.class);
    private ImageIcon backgroundImageIcon = null;

    @Autowired
    private InstallLogic installService;

    public FormMain() {

        ClassLoader classLoader = this.getClass().getClassLoader();

        backgroundImageIcon = new ImageIcon(Objects.requireNonNull(classLoader.getResource(CommonEnum.Image.BACKGROUND.getValue())));
        setSize(backgroundImageIcon.getIconWidth(), backgroundImageIcon.getIconHeight());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setUndecorated(true);
        addWindowListener(this);
        try {
            BufferedImage bufferedImage = null;
            if (classLoader.resources(CommonEnum.Image.LOGO_ICON.getValue()) != null && ImageIO.read(Objects.requireNonNull(classLoader.getResource(CommonEnum.Image.LOGO_ICON.getValue()))) != null) {
                bufferedImage = ImageIO.read(Objects.requireNonNull(classLoader.getResource(CommonEnum.Image.LOGO_ICON.getValue())));
            }
            if (bufferedImage != null) setIconImage(bufferedImage);

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }


    private void componentCreation() {

        JPanel panelDbInstall;


        tabMain = new JTabbedPane();
        tabMain.setBounds(-2, -25, getWidth() + 5, getHeight() + 28);
        getContentPane().add(tabMain);

        panelCompanyCreation();
        panelDbInstallCreation();

        panelDbInstall = new JPanel(null);
        panelDbInstall.setBounds(0, 0, getWidth(), getHeight());
        panelDbInstall.setBorder(BorderFactory.createLineBorder(Color.blue));
        tabMain.add(panelDbInstall);


        repaint();

    }


    private void panelCompanyCreation() {
        JPanel panelCompanyCreation;

        JLabel lblServerIp, lblUserName, lblPassword, lblPortNo, LblCompanyName, lblCompanyCode, lblCompanyCreationImg, lblDbInstallImg, lblExit, lblHeading;

        Font font = new Font("Times New Roman", Font.PLAIN, 15);

        int compWidth = getWidth() * 20 / 100;
        int compHeight = getHeight() * 4 / 100;
        int txtCompWidth = (int) (compWidth * 1.2);
        double hGap = 1.1, vGap = 7.1;
        int x, y;


        panelCompanyCreation = new JPanel(null);
        panelCompanyCreation.setBounds(0, 0, getWidth(), getHeight());
        panelCompanyCreation.setBorder(BorderFactory.createLineBorder(Color.blue));
        tabMain.add(panelCompanyCreation);

        lblCompanyCreationImg = new JLabel();
        lblCompanyCreationImg.setBounds(0, 0, panelCompanyCreation.getWidth(), panelCompanyCreation.getHeight());
        lblCompanyCreationImg.setIcon(backgroundImageIcon);
        panelCompanyCreation.add(lblCompanyCreationImg);


        lblHeading = new JLabel("COMPANY CREATION");
        lblHeading.setBounds(getWidth() * 20 / 100, getHeight() * 5 / 100, compWidth * 5, compHeight);
        lblHeading.setFont(new Font("Roboto", Font.BOLD, 40));
        lblHeading.setForeground(Color.decode("#964B00"));
        lblCompanyCreationImg.add(lblHeading);


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
        txtCompanyCode.setEnabled(false);
        lblCompanyCreationImg.add(txtCompanyCode);


        x = lblServerIp.getX() + (lblServerIp.getWidth() / 4);
        y = DbInstallCommon.verticalGap(panelCompanyCreation, txtCompanyCode, vGap / 2);


        btnClear = btnCreation("Clear", x, y, (int) (compWidth / 1.6), (int) (compHeight * 1.2), false, font);
        lblCompanyCreationImg.add(btnClear);


        x = DbInstallCommon.horizontalGap(panelCompanyCreation, btnClear, 5);

        btnCreate = btnCreation("Create", x, y, btnClear.getWidth(), btnClear.getHeight(), true, font);
        lblCompanyCreationImg.add(btnCreate);

        x = DbInstallCommon.horizontalGap(panelCompanyCreation, btnCreate, 5);

        btnExit1 = btnCreation("Exit", x, y, btnClear.getWidth(), btnClear.getHeight(), false, font);
        lblCompanyCreationImg.add(btnExit1);

    }

    private void panelDbInstallCreation() {
        JPanel panelDbInstall;
        JLabel lblBackgroundImg, lblHeading;
        JScrollPane scrPanel;

        Font font = new Font("Times New Roman", Font.PLAIN, 15);

        int compWidth = getWidth() * 20 / 100;
        int compHeight = getHeight() * 4 / 100;
        int txtCompWidth = (int) (compWidth * 1.2);
        double hGap = 1.1, vGap = 7.1;
        int x, y;


        panelDbInstall = new JPanel(null);
        panelDbInstall.setBounds(0, 0, getWidth(), getHeight());
        panelDbInstall.setBorder(BorderFactory.createLineBorder(Color.blue));
        tabMain.add(panelDbInstall);

        lblBackgroundImg = new JLabel();
        lblBackgroundImg.setBounds(0, 0, panelDbInstall.getWidth(), panelDbInstall.getHeight());
        lblBackgroundImg.setIcon(backgroundImageIcon);
        panelDbInstall.add(lblBackgroundImg);


        lblHeading = new JLabel("DATABASE INSTALL");
        lblHeading.setBounds(getWidth() * 20 / 100, getHeight() * 5 / 100, compWidth * 5, compHeight);
        lblHeading.setFont(new Font("Roboto", Font.BOLD, 40));
        lblHeading.setForeground(Color.decode("#964B00"));
        lblBackgroundImg.add(lblHeading);

        y = DbInstallCommon.verticalGap(panelDbInstall, lblHeading, vGap);

        txtArea = new JTextArea();
        txtArea.setEnabled(false);

        scrPanel = new JScrollPane(txtArea);
        scrPanel.setBounds(getWidth() * 15 / 100, y, getWidth() * 70 / 100, getHeight() * 60 / 100);
        scrPanel.setBorder(new CompoundBorder(new LineBorder(Color.BLACK), new EmptyBorder(5, 5, 5, 5)));
        lblBackgroundImg.add(scrPanel);

        y = DbInstallCommon.verticalGap(panelDbInstall, scrPanel, vGap / 2);

        jProgressBar = new JProgressBar(0, 100);
        jProgressBar.setBounds(scrPanel.getX(), y, scrPanel.getWidth() * 90 / 100, scrPanel.getHeight() * 10 / 100);
        lblBackgroundImg.add(jProgressBar);

        x = DbInstallCommon.horizontalGap(panelDbInstall, jProgressBar, hGap);

        lblTimer = new JLabel("");
        lblTimer.setBounds(x, y, compWidth, compHeight);
        lblTimer.setFont(font);
        lblBackgroundImg.add(lblTimer);

        y = DbInstallCommon.verticalGap(panelDbInstall, jProgressBar, vGap / 6);

        lblProgressPer = new JLabel("sdsf");
        lblProgressPer.setBounds((int) (jProgressBar.getWidth() / 1.5), y, compWidth / 2, compHeight / 3);
        lblProgressPer.setFont(font);
        lblBackgroundImg.add(lblProgressPer);


        y = DbInstallCommon.verticalGap(panelDbInstall, lblProgressPer, vGap / 2);


        btnInstall = btnCreation("Install", getWidth() * 40 / 100, y, (int) (compWidth / 1.6), (int) (compHeight * 1.2), false, font);
        lblBackgroundImg.add(btnInstall);


        x = DbInstallCommon.horizontalGap(panelDbInstall, btnInstall, hGap * 2);

        btnExit2 = btnCreation("Exit", x, y, btnInstall.getWidth(), btnInstall.getHeight(), false, font);
        lblBackgroundImg.add(btnExit2);

    }


    private JButton btnCreation(String name, int x, int y, int width, int height, boolean isRequireVerifire, Font font) {
        JButton jButton = new JButton(name);
        jButton.setBounds(x, y, width, (int) height);
        jButton.setForeground(Color.white);
        jButton.setFont(font);
        jButton.setBackground(Color.decode("#4b7abd"));
        jButton.setVerifyInputWhenFocusTarget(isRequireVerifire);
        jButton.setBorderPainted(false);
        return jButton;
    }


    private void fileRead() {
        try {
            File file = new File("CompanyDetail.txt");
            if (!file.exists()) {
                JOptionPane.showMessageDialog(getContentPane(), "Company Details Not Found... Create a New Company");
                tabMain.setSelectedIndex(0);
            } else {
                List<String> fileDetail = Files.readAllLines(Paths.get(file.toURI()));

                ServerCredentials serverCredentials = new ServerCredentials();
                serverCredentials.setServerip(fileDetail.getFirst());
                serverCredentials.setPortno(Secutity.decrypter(fileDetail.get(1)));
                serverCredentials.setUsername(Secutity.decrypter(fileDetail.get(2)));
                serverCredentials.setPassword(Secutity.decrypter(fileDetail.get(3)));
                serverCredentials.setCompanycode(Secutity.decrypter(fileDetail.get(4)));
                serverCredentials.setCompanyname(Secutity.decrypter(fileDetail.get(5)));

                DbInstallConstant.setServerCredentials(serverCredentials);

                if (!checkServerCredentials(serverCredentials)) {
                    tabMain.setSelectedIndex(0);
                    return;
                }

                tabMain.setSelectedIndex(1);
//                JOptionPane.showMessageDialog(getContentPane(), "Server Connected Sucessfully...");
            }
        } catch (IndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(getContentPane(), "Invalid Server File...!!");
            tabMain.setSelectedIndex(0);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(getContentPane(), e.getMessage());
            tabMain.setSelectedIndex(0);
        }
    }


    @Override
    public void windowOpened(WindowEvent e) {

        componentCreation();
        compoundListener();
        fileRead();
        if (tabMain.getSelectedIndex() == 0) txtServerIp.requestFocusInWindow();


    }

    private void compoundListener() {
        addListeners(txtServerIp, true);
        addListeners(txtPortNo, true);
        addListeners(txtCompanyName, true);
        addListeners(txtPassword, true);
        addListeners(txtUsername, true);
        addListeners(btnClear, true);
        addListeners(btnCreate, true);
        addListeners(btnInstall, true);
        addListeners(btnExit1, true);
        addListeners(btnExit2, true);


        addVerifier(txtServerIp, "Enter the Server Ip");
        addVerifier(txtPortNo, "Enter PORT");
        addVerifier(txtPassword, "Enter Password");
        addVerifier(txtUsername, "Enter UserName");
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
                    btnCreate.requestFocusInWindow();

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
            if (focusListener) ((JTextField) txtField).addFocusListener(this);
        } else {
            ((JButton) txtField).addActionListener(this);
            if (focusListener) {
                ((JButton) txtField).addMouseListener(this);
                ((JButton) txtField).addFocusListener(this);

            }
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
            if (e.getSource() == btnExit1 || e.getSource() == btnExit2) {
                System.exit(0);
            } else if (e.getSource() == btnClear) {
                clear();
            } else if (e.getSource() == btnInstall) {
                new installProgress().execute();
                btnInstall.setEnabled(false);
            } else if (e.getSource() == btnCreate) {
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

                ServerCredentials serverCredentials = new ServerCredentials();
                serverCredentials.setUsername(txtUsername.getText().trim());
                serverCredentials.setPassword(String.valueOf(txtPassword.getPassword()).trim());
                serverCredentials.setPortno(txtPortNo.getText().trim());
                serverCredentials.setServerip(txtServerIp.getText().trim());

                if (!checkServerCredentials(serverCredentials)) {
                    txtServerIp.requestFocusInWindow();
                    return;
                }

                setTxtFileTiValues();
                clear();
                JOptionPane.showMessageDialog(getContentPane(), "CompanyCreated Sucessfully...");
                tabMain.setSelectedIndex(1);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(getContentPane(), ex.getMessage());
            logger.error(ex.getMessage());
        }
    }

    private boolean checkServerCredentials(ServerCredentials serverCredentials) {
        DbInstallConstant.setServerCredentials(serverCredentials);
        try {
            ConnectionConfig connectionConfig = DbInstallConstant.getContext().getBean(ConnectionConfig.class);
            connectionConfig.checkDataSource();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }

    private void clear() {
        txtCompanyName.setText("");
        txtCompanyCode.setText("");
        txtServerIp.setText("");
        txtPortNo.setText("");
        txtUsername.setText("");
        txtPassword.setText("");
    }

    private void setTxtFileTiValues() throws Exception {
        File file = new File("CompanyDetail.txt");
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(txtServerIp.getText().trim() + "\n");
        fileWriter.write(Secutity.encrypter(txtPortNo.getText()) + "\n");
        fileWriter.write(Secutity.encrypter(txtUsername.getText()) + "\n");
        fileWriter.write(Secutity.encrypter(String.valueOf(txtPassword.getPassword())) + "\n");
        fileWriter.write(Secutity.encrypter(txtCompanyCode.getText()) + "\n");
        fileWriter.write(Secutity.encrypter(txtCompanyName.getText()) + "\n");
        fileWriter.close();

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
                    } else if (e.getComponent() == txtUsername) txtPassword.requestFocusInWindow();
                    else if (e.getComponent() == txtPassword) txtPortNo.requestFocusInWindow();
                    else if (e.getComponent() == txtPortNo) txtCompanyName.requestFocusInWindow();
                    else if (e.getComponent() == txtCompanyName) {
                        btnCreate.setEnabled(true);
                        btnCreate.requestFocusInWindow();
                    } else if (e.getComponent() == btnCreate) {
                        btnCreate.doClick();
                    } else if (e.getComponent() == btnClear) btnClear.doClick();
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

    private void setMouseEntered(JButton jButton, boolean isEntered) {
        if (isEntered) {
            jButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            jButton.setForeground(Color.BLACK);
            jButton.setBorderPainted(true);
        } else {
            jButton.setForeground(Color.WHITE);
            jButton.setBorderPainted(false);
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {

        if (e.getSource() == btnCreate) setMouseEntered(btnCreate, true);
        else if (e.getSource() == btnClear) setMouseEntered(btnClear, true);
        else if (e.getSource() == btnInstall) setMouseEntered(btnInstall, true);
        else if (e.getSource() == btnExit1) setMouseEntered(btnExit1, true);
        else if (e.getSource() == btnExit2) setMouseEntered(btnExit2, true);


    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == btnCreate) setMouseEntered(btnCreate, false);
        else if (e.getSource() == btnClear) setMouseEntered(btnClear, false);
        else if (e.getSource() == btnInstall) setMouseEntered(btnInstall, false);
        else if (e.getSource() == btnExit1) setMouseEntered(btnExit1, false);
        else if (e.getSource() == btnExit2) setMouseEntered(btnExit2, false);

    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() == btnCreate) {
            btnCreate.setForeground(Color.BLACK);
            btnCreate.setBorderPainted(true);
        }
        if (e.getSource() == btnClear) {
            btnClear.setForeground(Color.BLACK);
            btnClear.setBorderPainted(true);
        }

        if (e.getSource() == btnInstall) {
            btnInstall.setForeground(Color.BLACK);
            btnInstall.setBorderPainted(true);
        }

    }

    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource() == btnCreate) {
            btnCreate.setForeground(Color.WHITE);
            btnCreate.setBorderPainted(false);
        }
        if (e.getSource() == btnClear) {
            btnClear.setForeground(Color.WHITE);
            btnClear.setBorderPainted(false);
        }
        if (e.getSource() == btnInstall) {
            btnInstall.setForeground(Color.WHITE);
            btnInstall.setBorderPainted(false);
        }
    }

    public static void setTextArea(String txtMessage) {
        txtArea.setText(txtArea.getText() + "\n" + txtMessage);
    }

    public static JLabel getLblTimer() {
        return lblTimer;
    }


    private class installProgress extends SwingWorker<String, String> {

        @Override
        protected String doInBackground() throws Exception {

            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerCount(), 10, 1000);
            installService.installTables();
            timer.cancel();

            JOptionPane.showMessageDialog(getContentPane(), "Completed Sucessfully...");
            return "";
        }
    }

}
