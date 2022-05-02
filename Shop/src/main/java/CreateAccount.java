import net.miginfocom.layout.Grid;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateAccount {
    private ControlPassword controlPassword;
    private LoginPanel loginPanel;
    private OperationOnAllUsers operationOnAllUsers;
    private JFrame windowOfCreatingAccount;
    private JPanel panel;
    private JLabel stateOfPassword;
    private JLabel sentenceAbovePlaceToWriteNickname = new JLabel("Enter nickname");
    private JLabel sentenceAbovePlaceToWriteLogin = new JLabel("Enter login");
    private JLabel sentenceAbovePlaceToWritePassword = new JLabel("Enter Password");
    private JTextField registerNicknameField;
    private JTextField registerLoginField;
    private JPasswordField registerPasswordField;
    private JButton registerAccountButton;
    private String password = "";

    private static CreateAccount createAccount = null;

    public static CreateAccount getCreateAccount(){
        if (createAccount == null){
            createAccount = new CreateAccount();
        }
        return createAccount;
    }

    public CreateAccount(){
        operationOnAllUsers = OperationOnAllUsers.getInformationAboutAllUsers();
        controlPassword = new ControlPassword();
        stateOfPassword = new JLabel();
        setWindowOfCreatingAccount();
        setPanel();
        setRegisterNicknameField();
        setRegisterLoginField();
        setRegisterPasswordField();
        setRegisterAccountButton();
        setStateOfPassword();
        addEverythingOnScreen();
    }

    public void setWindowOfCreatingAccount(){
        windowOfCreatingAccount = new JFrame();
        windowOfCreatingAccount.setLayout(new GridBagLayout());
        windowOfCreatingAccount.setSize(500, 500);
        windowOfCreatingAccount.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        windowOfCreatingAccount.setTitle("Register");
        windowOfCreatingAccount.setVisible(false);
    }

    public void setPanel(){
        panel = new JPanel();
        panel.setLayout(new MigLayout());
    }

    public void setRegisterNicknameField(){
        registerNicknameField = new JTextField();
        registerNicknameField.setPreferredSize(new Dimension(200, 40));
    }

    public void setRegisterLoginField(){
        registerLoginField = new JTextField();
        registerLoginField.setPreferredSize(new Dimension(200, 40));
    }

    public void setRegisterPasswordField(){
        registerPasswordField = new JPasswordField();
        registerPasswordField.setPreferredSize(new Dimension(200, 40));
    }

    public void setRegisterAccountButton(){
        registerAccountButton = new JButton("Register");
        registerAccountButton.addActionListener(new MyActionPerformed());
    }

    public void setStateOfPassword(){
        stateOfPassword.setText(controlPassword.getPowerOfPassword());
        if (stateOfPassword.getText().equals("Weak")){
            stateOfPassword.setForeground(Color.red);
        }
        else if (stateOfPassword.getText().equals("Medium")){
            stateOfPassword.setForeground(Color.YELLOW);
        }
        else if (stateOfPassword.getText().equals("Strong")){
            stateOfPassword.setForeground(Color.GREEN);
        }
    }

    public void addEverythingOnScreen(){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(sentenceAbovePlaceToWriteNickname, "wrap 10");
        panel.add(registerNicknameField, "wrap 20");
        panel.add(sentenceAbovePlaceToWriteLogin, "wrap 10");
        panel.add(registerLoginField, "wrap 20");
        panel.add(sentenceAbovePlaceToWritePassword, "split 2");
        panel.add(stateOfPassword, "gapleft 50, wrap 20");
        panel.add(registerPasswordField, "wrap 20");
        panel.add(registerAccountButton);
        windowOfCreatingAccount.add(panel, gbc);
    }

    public void checkIfNewAccountIsCorrect(){
        loginPanel = LoginPanel.getLoginPanel();
        for(String singleUsersNickname : operationOnAllUsers.getUsersNickname()){
            if (registerNicknameField.getText().equals(singleUsersNickname)){
                return;
            }
        }
        for (String singleUsersLogin : operationOnAllUsers.getUsersLogin()){
            if (registerLoginField.getText().equals(singleUsersLogin)){
                return;
            }
        }
        convertPasswordTextToString();
        controlPassword.ControlPowerOfPassword(password);
        if (controlPassword.getPowerOfPassword().equals("Weak") || controlPassword.getPowerOfPassword().equals("Medium")){
            setStateOfPassword();
            resetTextField();
            refreshWindow();
            return;
        }
        operationOnAllUsers.addUser(registerNicknameField.getText(), registerLoginField.getText(), password);
        resetTextField();
        operationOnAllUsers.takeValuesFromBaseAfterWithReset();
        loginPanel.frame.setVisible(true);
        windowOfCreatingAccount.setVisible(false);
    }

    public void refreshWindow(){
        windowOfCreatingAccount.revalidate();
        windowOfCreatingAccount.repaint();
    }

    public void resetTextField(){
        password = "";
        registerNicknameField.setText("");
        registerLoginField.setText("");
        registerPasswordField.setText("");
    }

    public void convertPasswordTextToString(){
        for (char singleChar : registerPasswordField.getPassword()){
            password += singleChar;
        }
    }

    public JFrame getWindowOfCreatingAccount(){
        return windowOfCreatingAccount;
    }

    class MyActionPerformed implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(registerAccountButton)) {
                checkIfNewAccountIsCorrect();
            }
        }

    }
}
