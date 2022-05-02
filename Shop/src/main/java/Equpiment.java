import com.mysql.cj.log.Log;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class Equpiment {
    UsersWhichCurrentlyUseShop usersWhichCurrentlyUseShop;
    PropertiesAboutProductsOnScreen propertiesAboutProducts;
    OperationOnProductsFromBase operationOnProductsFromBase;
    OperationOnAllUsers operationOnAllUsers;
    ShopScreen shopScreen;
    LoginPanel loginPanel;
    private JFrame windowOfEqpiment;
    private JPanel usersPanel;
    private JPanel settingOfWindowPanel;
    private ArrayList<JPanel> listOfUserEqipment;
    private ArrayList<JLabel> labelsWithCostOfEquipment;
    private ArrayList<ImageIcon> linksToPhotoOfEquipment;
    private ArrayList<JLabel> labelsWithPhotos;
    private JLabel userMoney;
    private JLabel usersNickname;
    private JButton goToShop;
    private JButton logOut;
    private static Equpiment equpiment = null;

    public static Equpiment getEqupiment(){
        if (equpiment == null){
            equpiment = new Equpiment();
        }
        return equpiment;
    }

    public Equpiment(){
        usersWhichCurrentlyUseShop = UsersWhichCurrentlyUseShop.getUsersWhichCurrentlyUseShop();
        propertiesAboutProducts = PropertiesAboutProductsOnScreen.getAmountOfProductsOnSide();
        operationOnProductsFromBase = OperationOnProductsFromBase.getInformationAboutProductsFromBase();
        operationOnAllUsers = OperationOnAllUsers.getInformationAboutAllUsers();
        listOfUserEqipment = new ArrayList<>();
        labelsWithCostOfEquipment = new ArrayList<>();
        linksToPhotoOfEquipment = new ArrayList<>();
        labelsWithPhotos = new ArrayList<>();
        userMoney = new JLabel();
        usersNickname = new JLabel();
        setjFrame();
        setUserMoeny();
        setUsersNickname();
        setGoToShop();
        setLogOutButton();
        setUsersPanel();
        setSettingOfWindowPanel();

    }

    public void setjFrame() {
        windowOfEqpiment = new JFrame();
        windowOfEqpiment.setLayout(new MigLayout());
        windowOfEqpiment.setSize(new Dimension(800, 550));
        windowOfEqpiment.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        windowOfEqpiment.setTitle("Shop");
        windowOfEqpiment.setLocationRelativeTo(null);
        windowOfEqpiment.setVisible(false);
    }

    public void setUsersPanel() {
        usersPanel = new JPanel();
        usersPanel.setLayout(new MigLayout());
        usersPanel.add(usersNickname, "gapleft 80");
        usersPanel.add(userMoney, "gapleft 175");
        usersPanel.add(goToShop, "gapleft 175");
        usersPanel.add(logOut, "gapleft 100");
    }

    public void setSettingOfWindowPanel(){
        settingOfWindowPanel = new JPanel();
        settingOfWindowPanel.setLayout(new MigLayout());
        settingOfWindowPanel.add(goToShop, "wrap ");
        settingOfWindowPanel.add(logOut, "wrap 50");

    }

    public void setGoToShop(){
        goToShop = new JButton("Shop");
        goToShop.setFocusable(false);
        goToShop.addActionListener(new MyActionPerformed());
        goToShop.addMouseMotionListener(new MyMouseMotion());
    }

    public void setLogOutButton() {
        logOut = new JButton("Log out");
        logOut.setFocusable(false);
        logOut.addActionListener(new MyActionPerformed());
        goToShop.addMouseMotionListener(new MyMouseMotion());
    }

    public void setUserMoeny() {
        userMoney.setText(String.valueOf(usersWhichCurrentlyUseShop.getCash()));
    }

    public void setUsersNickname() {
        usersNickname.setText(usersWhichCurrentlyUseShop.getUserUserName());
    }

    public void addAllPhotosOnScreen() {
        for (int i = propertiesAboutProducts.getCurrentPage() * propertiesAboutProducts.getAmountOfProductsOnOneScreenWindow();
             (propertiesAboutProducts.getCurrentPage() + 1) * propertiesAboutProducts.getAmountOfProductsOnOneScreenWindow() > i &&
                     operationOnProductsFromBase.getLinksToPhoto().size() > i; i++) {
            linksToPhotoOfEquipment.add(new ImageIcon(operationOnProductsFromBase.getLinksToPhoto().get(i)));
        }
    }

    public void addItemsToList() {
        for (int i = propertiesAboutProducts.getCurrentPage() * propertiesAboutProducts.getAmountOfProductsOnOneScreenWindow();
             (propertiesAboutProducts.getCurrentPage() + 1) * propertiesAboutProducts.getAmountOfProductsOnOneScreenWindow() > i &&
                     operationOnProductsFromBase.getLinksToPhoto().size() > i; i++) {
            labelsWithPhotos.add(new JLabel(linksToPhotoOfEquipment.get(labelsWithPhotos.size())));
        }
    }

    public void addCostOfCardToList() {
        for (int i = propertiesAboutProducts.getCurrentPage() * propertiesAboutProducts.getAmountOfProductsOnOneScreenWindow();
             (propertiesAboutProducts.getCurrentPage() + 1) * propertiesAboutProducts.getAmountOfProductsOnOneScreenWindow() > i &&
                     operationOnProductsFromBase.getLinksToPhoto().size() > i; i++) {
            labelsWithCostOfEquipment.add(new JLabel(String.valueOf(operationOnProductsFromBase.getCost().get(i))));
        }
    }

    public void addProductsToPanel() {
        for (int i = 0; labelsWithPhotos.size() > i; i++) {
            listOfUserEqipment.add(new JPanel());
            listOfUserEqipment.get(i).setLayout(new MigLayout());
            listOfUserEqipment.get(i).add(labelsWithPhotos.get(i), "wrap");
            listOfUserEqipment.get(i).add(labelsWithCostOfEquipment.get(i), "wrap");
        }
    }

    public void addPanelsToWindow() {
        for (int i = 0; listOfUserEqipment.size() > i; i++) {
            if (i % 5 == 4) {
                windowOfEqpiment.add(listOfUserEqipment.get(i), "gapleft 10, wrap 30");
            }
            else if (listOfUserEqipment.size() - i == 1){
                windowOfEqpiment.add(listOfUserEqipment.get(i), "wrap 80");
            }
            else {
                windowOfEqpiment.add(listOfUserEqipment.get(i), "gapleft 10");
            }
        }
        windowOfEqpiment.add(usersPanel, "dock north");
        windowOfEqpiment.add(settingOfWindowPanel, "dock east");

    }

    public void setAllProductsWhenShopIsOpen() {
        addAllPhotosOnScreen();
        addItemsToList();
        addCostOfCardToList();
        addProductsToPanel();
        addPanelsToWindow();
        setUserMoeny();
        setUsersNickname();
        windowOfEqpiment.revalidate();
        windowOfEqpiment.repaint();
    }

    public void resetValueFromAllProducts() {
        linksToPhotoOfEquipment.clear();
        labelsWithPhotos.clear();
        labelsWithCostOfEquipment.clear();
        listOfUserEqipment.clear();
        userMoney.setText("");
        usersNickname.setText("");
    }

    public void inicializeShopClass() {
        windowOfEqpiment.remove(usersPanel);
        windowOfEqpiment.remove(settingOfWindowPanel);
        for (JPanel singlePanel : listOfUserEqipment) {
            windowOfEqpiment.remove(singlePanel);
        }
        resetValueFromAllProducts();
        setAllProductsWhenShopIsOpen();
    }

    public JFrame getWindowOfEqpiment(){
        return windowOfEqpiment;
    }

    class MyActionPerformed implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            loginPanel = LoginPanel.getLoginPanel();
            shopScreen = ShopScreen.getShopScreen();
            if (e.getSource().equals(logOut)){
                windowOfEqpiment.setVisible(false);
                operationOnAllUsers.takeValuesFromBaseAfterWithReset();
                loginPanel.frame.setVisible(true);
            }
            if (e.getSource().equals(goToShop)){
                windowOfEqpiment.setVisible(false);
                shopScreen.resetShopToBasicsSettings();
                shopScreen.checkWhichRequestSendToBase();
                operationOnProductsFromBase.resetValuesTakenFromBase();
                operationOnProductsFromBase.getInformationAboutProducts();
                shopScreen.inicializeShopClass();
                shopScreen.getjFrame().setVisible(true);
            }
        }
    }



    class MyMouseMotion implements MouseMotionListener {
        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {
            goToShop.setCursor(new Cursor(Cursor.HAND_CURSOR));
            logOut.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }


}
