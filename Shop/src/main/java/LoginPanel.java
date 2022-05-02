import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel {
    ShopScreen shopScreen;
    CreateAccount createAccount;
    UsersWhichCurrentlyUseShop usersWhichCurrentlyUseShop;
    OperationOnAllUsers operationOnAllUsers;
    OperationOnProductsFromBase operationOnProductsFromBase;
    JFrame frame;
    JPanel panel;
    JLabel sentenceAbovePlaceToWriteLogin = new JLabel("Write Email");
    JLabel sentenceAbovePlaceToWritePassword = new JLabel("Write Password");
    JTextField loginField;
    JPasswordField password;
    JButton logInButton;
    JButton registerButton;
    String passwordConvertedFromStars;

    private static LoginPanel loginPanel = null;

    public static LoginPanel getLoginPanel(){
        if (loginPanel == null){
            loginPanel = new LoginPanel();
        }
        return loginPanel;
    }

    public LoginPanel(){
        operationOnAllUsers = OperationOnAllUsers.getInformationAboutAllUsers();
        usersWhichCurrentlyUseShop = UsersWhichCurrentlyUseShop.getUsersWhichCurrentlyUseShop();
        operationOnProductsFromBase = OperationOnProductsFromBase.getInformationAboutProductsFromBase();
        setFrame();
        setPanel();
        setPassword();
        setLoginField();
        setLogInButton();
        setRegisterButton();
        addEverythingOnScreen();
        frame.revalidate();
        frame.repaint();
    }

    public void setFrame(){
        frame = new JFrame();
        frame.setSize(500, 500);
        frame.setTitle("LoginPanel");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public void setPanel(){
        panel = new JPanel();
        panel.setLayout(new MigLayout());

    }

    public void setLoginField(){
        loginField = new JTextField();
        loginField.setPreferredSize(new Dimension(200, 40));
    }

    public void setPassword(){
        password = new JPasswordField();
        password.setPreferredSize(new Dimension(200, 40));

    }

    public void setLogInButton(){
        logInButton = new JButton("Submit");
        logInButton.addActionListener(new MyActionPerformed());
    }

    public void setRegisterButton(){
        registerButton = new JButton("Register");
        registerButton.addActionListener(new MyActionPerformed());
    }

    public void addEverythingOnScreen(){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(sentenceAbovePlaceToWriteLogin, "wrap 10");
        panel.add(loginField, "wrap 20");
        panel.add(sentenceAbovePlaceToWritePassword, "wrap 10");
        panel.add(password, "wrap 20");
        panel.add(logInButton ," split 2");
        panel.add(registerButton, "gapleft 40");
        frame.add(panel, gbc);
    }

    public void convertPasswordCryptedToString(){
        passwordConvertedFromStars = "";
        for (char singleChar : password.getPassword()){
            passwordConvertedFromStars += singleChar;
        }
    }

    public void clearTextField(){
        loginField.setText("");
        password.setText("");
        passwordConvertedFromStars = "";
    }

    private void setAllPropertiesAboutUserWhichCurrentlyUseApp(int i){
        usersWhichCurrentlyUseShop.setUsersWhichUsingWebiste(i);
        usersWhichCurrentlyUseShop.setUserUserName(operationOnAllUsers.getUsersNickname().get(i));
        usersWhichCurrentlyUseShop.setCash(operationOnAllUsers.getUserCash().get(i));
    }

    private void setAllFromOperationOnProdcutsFromBase(){
        operationOnProductsFromBase.resetValuesTakenFromBase();
        operationOnProductsFromBase.getProductsToBuyAscendingByCost(usersWhichCurrentlyUseShop.getUsersWhichUsingWebiste());
        operationOnProductsFromBase.getInformationAboutProducts();
    }

    class MyActionPerformed implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            convertPasswordCryptedToString();
            if (e.getSource().equals(logInButton)) {
                for (int i = 0; operationOnAllUsers.getUsersLogin().size() > i; i++) {
                    if (loginField.getText().equals(operationOnAllUsers.getUsersLogin().get(i)) &&
                            passwordConvertedFromStars.equals(operationOnAllUsers.getUsersPassword().get(i))) {
                        setAllPropertiesAboutUserWhichCurrentlyUseApp(i);
                        setAllFromOperationOnProdcutsFromBase();
                        shopScreen = ShopScreen.getShopScreen();
                        shopScreen.inicializeShopClass();
                        clearTextField();
                        frame.setVisible(false);
                        shopScreen.getjFrame().setVisible(true);
                        return;
                    }
                }
            }
            if (e.getSource().equals(registerButton)) {
                createAccount = CreateAccount.getCreateAccount();
                createAccount.getWindowOfCreatingAccount().setVisible(true);
                frame.setVisible(false);
            }
        }
    }
}
