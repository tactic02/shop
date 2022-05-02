import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class ShopScreen {
    UsersWhichCurrentlyUseShop usersWhichCurrentlyUseShop;
    PropertiesAboutProductsOnScreen propertiesAboutProducts;
    OperationOnProductsFromBase operationOnProductsFromBase;
    OperationOnAllUsers operationOnAllUsers;
    LoginPanel loginPanel;
    Equpiment equpiment;
    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 100;
    private final int AMOUNT_OF_PROCUTS_IN_ROW = 5;
    private int amountOfRow = 0;
    private JFrame jFrame;
    private JPanel usersPanel;
    private JPanel settingOfWindowPanel;
    private JPanel changeSidePanel;
    private ArrayList<JPanel> listOfItems;
    private ArrayList<JLabel> labelsWithCostOfCart;
    private ArrayList<ImageIcon> linksToPhoto;
    private ArrayList<JLabel> labelsWithPhotos;
    private ArrayList<JButton> listOfButtons;
    private JLabel userMoney;
    private JLabel usersNickname;
    private JLabel FirstPartOftextWhenUserHaveNoProductsToBuy;
    private JLabel SecondPartOftextWhenUserHaveNoProductsToBuy;
    private JButton goToEq;
    private JButton logOut;
    private JButton nextPage;
    private JButton previousPage;
    private String[] optionOfSortCarts;
    private String[] amountOfCardOnScreen;
    private JComboBox JComboBoxToSortCards;
    private JComboBox JComboBoxToAmountOfCardOnSide;
    private int currentQuestionFromBase;

    private static ShopScreen shopScreen = null;

    public static ShopScreen getShopScreen() {
        if (shopScreen == null) {
            shopScreen = new ShopScreen();
        }
        return shopScreen;
    }

    public ShopScreen() {
        propertiesAboutProducts = PropertiesAboutProductsOnScreen.getAmountOfProductsOnSide();
        operationOnProductsFromBase = OperationOnProductsFromBase.getInformationAboutProductsFromBase();
        usersWhichCurrentlyUseShop = UsersWhichCurrentlyUseShop.getUsersWhichCurrentlyUseShop();
        operationOnAllUsers = OperationOnAllUsers.getInformationAboutAllUsers();
        jFrame = new JFrame();
        listOfItems = new ArrayList<>();
        labelsWithCostOfCart = new ArrayList<>();
        linksToPhoto = new ArrayList<>();
        labelsWithPhotos = new ArrayList<>();
        listOfButtons = new ArrayList<>();
        userMoney = new JLabel();
        usersNickname = new JLabel();
        FirstPartOftextWhenUserHaveNoProductsToBuy = new JLabel();
        currentQuestionFromBase = 0;
        setjFrame();
        setGoToEq();
        setLogOutButton();
        setUserMoeny();
        setUsersNickname();
        setFirstParOfTextWhenUserHaveNoProductsToBuy();
        setUsersPanel();
        setOptionOfSortCarts();
        setAmountOfCardOnScreen();
        setListOfOption();
        setListOfOptionToAmountOfCardOnSide();
        setSettingOfWindowPanel();
        setPreviousPage();
        setNextPage();
        setChangeSidePanel();
    }

    private void setjFrame() {
        jFrame.getContentPane().setBackground(new Color(195, 176, 145));
        jFrame.setLayout(new MigLayout());
        jFrame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setTitle("Shop");
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(false);
    }

    private void setUsersPanel() {
        usersPanel = new JPanel();
        usersPanel.setLayout(new MigLayout());
        usersPanel.setBackground(new Color(195, 176, 145));
        usersPanel.add(usersNickname, "gapleft 195");
        usersPanel.add(userMoney, "gapleft 50");
        usersPanel.add(goToEq, "gapleft 50");
        usersPanel.add(logOut, "gapleft 50");
    }

    private void setChangeSidePanel() {
        changeSidePanel = new JPanel();
        changeSidePanel.setBackground(new Color(195, 176, 145));
        changeSidePanel.setLayout(new MigLayout());
        changeSidePanel.add(previousPage, "gapleft 10");
        changeSidePanel.add(nextPage, "gapleft 40");
    }

    private void setSettingOfWindowPanel() {
        settingOfWindowPanel = new JPanel();
        settingOfWindowPanel.setBackground(new Color(195, 176, 145));
        settingOfWindowPanel.setLayout(new MigLayout());
        settingOfWindowPanel.add(JComboBoxToSortCards, "wrap ");
        settingOfWindowPanel.add(JComboBoxToAmountOfCardOnSide);
    }

    private void setGoToEq() {
        goToEq = new JButton("Equipment");
        goToEq.setFocusable(false);
        goToEq.addActionListener(new MyClassActionPerformed());
        goToEq.addMouseMotionListener(new MyClassMouseMotionListener());
    }

    private void setLogOutButton() {
        logOut = new JButton("Log out");
        logOut.setFocusable(false);
        logOut.addActionListener(new MyClassActionPerformed());
        logOut.addMouseMotionListener(new MyClassMouseMotionListener());
    }

    private void setPreviousPage() {
        previousPage = new JButton("<-");
        previousPage.addActionListener(new MyClassActionPerformed());
        previousPage.setFocusable(false);
        previousPage.addMouseMotionListener(new MyClassMouseMotionListener());
    }

    private void setNextPage() {
        nextPage = new JButton("Next side");
        nextPage.addActionListener(new MyClassActionPerformed());
        nextPage.setFocusable(false);
        previousPage.addMouseMotionListener(new MyClassMouseMotionListener());
    }

    private void setUserMoeny() {
        userMoney.setText(String.valueOf(usersWhichCurrentlyUseShop.getCash()));
    }

    public void setUsersNickname() {
        usersNickname.setText(usersWhichCurrentlyUseShop.getUserUserName());
    }

    public void setFirstParOfTextWhenUserHaveNoProductsToBuy() {
        FirstPartOftextWhenUserHaveNoProductsToBuy.setText("YOU BOUGHT ALL PRODUCTS");
        FirstPartOftextWhenUserHaveNoProductsToBuy.setFont(new Font("Arial", Font.PLAIN, 50));
    }

    private void changesSizeOfShopScreenWindow() {
        if (amountOfRow > 0) {
            jFrame.setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT + amountOfRow * 200));
        } else if (amountOfRow == 0) {
            jFrame.setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT + 200));
        }
        jFrame.revalidate();
        jFrame.repaint();
    }

    public void setOptionOfSortCarts() {
        optionOfSortCarts = new String[4];
        optionOfSortCarts[0] = "Sort By value Ascending";
        optionOfSortCarts[1] = "Sort By value Descending";
        optionOfSortCarts[2] = "Sort by type Ascending";
        optionOfSortCarts[3] = "Sort by type Descending";
    }

    public void setAmountOfCardOnScreen() {
        amountOfCardOnScreen = new String[3];
        amountOfCardOnScreen[0] = "10";
        amountOfCardOnScreen[1] = "15";
        amountOfCardOnScreen[2] = "20";
    }

    public void setListOfOption() {
        JComboBoxToSortCards = new JComboBox(optionOfSortCarts);
        JComboBoxToSortCards.setSelectedIndex(0);
        JComboBoxToSortCards.setFocusable(false);
        JComboBoxToSortCards.addActionListener(new MyClassActionPerformed());
    }

    public void setListOfOptionToAmountOfCardOnSide() {
        JComboBoxToAmountOfCardOnSide = new JComboBox(amountOfCardOnScreen);
        JComboBoxToAmountOfCardOnSide.setSelectedIndex(0);
        JComboBoxToAmountOfCardOnSide.setFocusable(false);
        JComboBoxToAmountOfCardOnSide.addActionListener(new MyClassActionPerformed());
    }

    public void addAllPhotosOnScreen() {
        for (int i = propertiesAboutProducts.getCurrentPage() * propertiesAboutProducts.getAmountOfProductsOnOneScreenWindow();
             (propertiesAboutProducts.getCurrentPage() + 1) * propertiesAboutProducts.getAmountOfProductsOnOneScreenWindow() > i &&
                     operationOnProductsFromBase.getLinksToPhoto().size() > i; i++) {
            linksToPhoto.add(new ImageIcon(operationOnProductsFromBase.getLinksToPhoto().get(i)));
        }
    }

    public void addItemsToList() {
        for (int i = propertiesAboutProducts.getCurrentPage() * propertiesAboutProducts.getAmountOfProductsOnOneScreenWindow();
             (propertiesAboutProducts.getCurrentPage() + 1) * propertiesAboutProducts.getAmountOfProductsOnOneScreenWindow() > i &&
                     operationOnProductsFromBase.getLinksToPhoto().size() > i; i++) {
            labelsWithPhotos.add(new JLabel(linksToPhoto.get(labelsWithPhotos.size())));
        }
    }

    public void addButtonsToList() {
        for (int i = propertiesAboutProducts.getCurrentPage() * propertiesAboutProducts.getAmountOfProductsOnOneScreenWindow();
             (propertiesAboutProducts.getCurrentPage() + 1) * propertiesAboutProducts.getAmountOfProductsOnOneScreenWindow() > i &&
                     operationOnProductsFromBase.getLinksToPhoto().size() > i; i++) {
            listOfButtons.add(new JButton("Buy"));
            listOfButtons.get(listOfButtons.size() - 1).addMouseMotionListener(new MyClassMouseMotionListener());
            listOfButtons.get(listOfButtons.size() - 1).addActionListener(new MyClassActionPerformed());
        }
    }

    public void addCostOfCardToList() {
        for (int i = propertiesAboutProducts.getCurrentPage() * propertiesAboutProducts.getAmountOfProductsOnOneScreenWindow();
             (propertiesAboutProducts.getCurrentPage() + 1) * propertiesAboutProducts.getAmountOfProductsOnOneScreenWindow() > i &&
                     operationOnProductsFromBase.getLinksToPhoto().size() > i; i++) {
            labelsWithCostOfCart.add(new JLabel(String.valueOf(operationOnProductsFromBase.getCost().get(i))));
            labelsWithCostOfCart.get(labelsWithCostOfCart.size() - 1).setForeground(Color.white);
        }
    }

    public void addProductsToPanel() {
        for (int i = 0; labelsWithPhotos.size() > i; i++) {
            listOfItems.add(new JPanel());
            listOfItems.get(i).setLayout(new MigLayout());
            listOfItems.get(i).setBackground(Color.LIGHT_GRAY);
            listOfItems.get(i).add(labelsWithPhotos.get(i), "wrap");
            listOfItems.get(i).add(labelsWithCostOfCart.get(i), "wrap");
            listOfItems.get(i).add(listOfButtons.get(i), "wrap");
        }
    }

    public void addPanelsToWindow() {
        if (amountOfRow != 0) {
            for (int i = 0; listOfItems.size() > i; i++) {
                if (i % AMOUNT_OF_PROCUTS_IN_ROW == 4) {
                    jFrame.add(listOfItems.get(i), "gapleft 10, wrap 30");
                } else if (listOfItems.size() - i == 1) {
                    jFrame.add(listOfItems.get(i), "wrap 80");
                } else {
                    jFrame.add(listOfItems.get(i), "gapleft 10");
                }
            }
        } else {
            jFrame.add(FirstPartOftextWhenUserHaveNoProductsToBuy);
        }
        jFrame.add(usersPanel, "dock north");
        jFrame.add(settingOfWindowPanel, "dock west");
        jFrame.add(changeSidePanel, "dock south");

    }

    public void setAllProductsWhenShopIsOpen() {
        addAllPhotosOnScreen();
        addItemsToList();
        addButtonsToList();
        addCostOfCardToList();
        addProductsToPanel();
        countAmontOfRow();
        addPanelsToWindow();
        setUserMoeny();
        setUsersNickname();
        jFrame.revalidate();
        jFrame.repaint();
    }

    public void resetValueFromAllProducts() {
        linksToPhoto.clear();
        labelsWithPhotos.clear();
        listOfButtons.clear();
        labelsWithCostOfCart.clear();
        listOfItems.clear();
    }

    public void countAmontOfRow() {
        double tempereryValue = (double) listOfItems.size() / AMOUNT_OF_PROCUTS_IN_ROW;
        try {
            amountOfRow = (int) Math.ceil(tempereryValue);
        } catch (Exception e) {
            System.out.println("Can't cast score to Int");
        }
        changesSizeOfShopScreenWindow();
    }

    public void inicializeShopClass() {
        jFrame.remove(usersPanel);
        jFrame.remove(settingOfWindowPanel);
        jFrame.remove(FirstPartOftextWhenUserHaveNoProductsToBuy);
        for (JPanel singlePanel : listOfItems) {
            jFrame.remove(singlePanel);
        }
        resetValueFromAllProducts();
        setAllProductsWhenShopIsOpen();
    }

    public void checkWhichRequestSendToBase() {
        switch (currentQuestionFromBase) {
            case 0 -> operationOnProductsFromBase.getProductsToBuyAscendingByCost(usersWhichCurrentlyUseShop.getUsersWhichUsingWebiste());
            case 1 -> operationOnProductsFromBase.getProductsToBuyDescendingByCost(usersWhichCurrentlyUseShop.getUsersWhichUsingWebiste());
            case 2 -> operationOnProductsFromBase.getProductsToBuyAscendingByType(usersWhichCurrentlyUseShop.getUsersWhichUsingWebiste());
            case 3 -> operationOnProductsFromBase.getProductsToBuyDescendingByType(usersWhichCurrentlyUseShop.getUsersWhichUsingWebiste());
            case 4 -> operationOnProductsFromBase.getProductsBoughtAscendingByCost(usersWhichCurrentlyUseShop.getUsersWhichUsingWebiste());
            case 5 -> operationOnProductsFromBase.getProductsBoughtDescendingByCost(usersWhichCurrentlyUseShop.getUsersWhichUsingWebiste());
            case 6 -> operationOnProductsFromBase.getProductsBoughtAscendingByType(usersWhichCurrentlyUseShop.getUsersWhichUsingWebiste());
            case 7 -> operationOnProductsFromBase.getProductsBoughtDescendingByType(usersWhichCurrentlyUseShop.getUsersWhichUsingWebiste());
        }
    }

    public void resetShopToBasicsSettings() {
        propertiesAboutProducts.setCurrentPage(0);
        propertiesAboutProducts.setAmountOfProductsOnOneScreenWindow(Integer.parseInt(
                amountOfCardOnScreen[0]));
        JComboBoxToAmountOfCardOnSide.setSelectedIndex(0);
        JComboBoxToSortCards.setSelectedIndex(0);
        currentQuestionFromBase = 0;
    }

    public JFrame getjFrame() {
        return jFrame;
    }

    class MyClassActionPerformed implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            loginPanel = LoginPanel.getLoginPanel();
            equpiment = Equpiment.getEqupiment();
            if (e.getSource().equals(logOut)) {
                jFrame.setVisible(false);
                operationOnAllUsers.takeValuesFromBaseAfterWithReset();
                loginPanel.frame.setVisible(true);
                resetShopToBasicsSettings();
            } else if (e.getSource().equals(goToEq)) {
                currentQuestionFromBase += 4;
                checkWhichRequestSendToBase();
                propertiesAboutProducts.setCurrentPage(0);
                operationOnProductsFromBase.resetValuesTakenFromBase();
                operationOnProductsFromBase.getInformationAboutProducts();
                equpiment.inicializeShopClass();
                jFrame.setVisible(false);
                equpiment.getWindowOfEqpiment().setVisible(true);
            } else if (e.getSource().equals(nextPage)) {
                checkWhichRequestSendToBase();
                propertiesAboutProducts.nextPage();
                operationOnProductsFromBase.resetValuesTakenFromBase();
                operationOnProductsFromBase.getInformationAboutProducts();
                inicializeShopClass();
            } else if (e.getSource().equals(previousPage)) {
                checkWhichRequestSendToBase();
                propertiesAboutProducts.previousPage();
                operationOnProductsFromBase.resetValuesTakenFromBase();
                operationOnProductsFromBase.getInformationAboutProducts();
                inicializeShopClass();
            } else if (e.getSource().equals(JComboBoxToSortCards)) {
                currentQuestionFromBase = JComboBoxToSortCards.getSelectedIndex();
                checkWhichRequestSendToBase();
                operationOnProductsFromBase.resetValuesTakenFromBase();
                operationOnProductsFromBase.getInformationAboutProducts();
                inicializeShopClass();
            } else if (e.getSource().equals(JComboBoxToAmountOfCardOnSide)) {
                checkWhichRequestSendToBase();
                propertiesAboutProducts.setAmountOfProductsOnOneScreenWindow(
                        Integer.parseInt(amountOfCardOnScreen[JComboBoxToAmountOfCardOnSide.getSelectedIndex()]));
                operationOnProductsFromBase.resetValuesTakenFromBase();
                operationOnProductsFromBase.getInformationAboutProducts();
                inicializeShopClass();
            }
            for (int i = 0; listOfButtons.size() > i; i++) {
                if (e.getSource().equals(listOfButtons.get(i))) {
                    if (usersWhichCurrentlyUseShop.getCash() > Integer.parseInt(labelsWithCostOfCart.get(i).getText())) {
                        usersWhichCurrentlyUseShop.setCash(usersWhichCurrentlyUseShop.getCash() - Integer.parseInt(labelsWithCostOfCart.get(i).getText()));
                        operationOnAllUsers.subtractionCash(usersWhichCurrentlyUseShop.getUsersWhichUsingWebiste(),
                                Integer.parseInt(labelsWithCostOfCart.get(i).getText()));
                        operationOnProductsFromBase.addProductsToCartsHeld(usersWhichCurrentlyUseShop.getUsersWhichUsingWebiste(),
                                operationOnProductsFromBase.getIdOfProduct().get(
                                        propertiesAboutProducts.getCurrentPage() * propertiesAboutProducts.getAmountOfProductsOnOneScreenWindow() + i));
                        checkWhichRequestSendToBase();
                        operationOnProductsFromBase.resetValuesTakenFromBase();
                        operationOnProductsFromBase.getInformationAboutProducts();
                        inicializeShopClass();
                    }
                }
            }
        }
    }

    class MyClassMouseMotionListener implements MouseMotionListener {


        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {
            goToEq.setCursor(new Cursor(Cursor.HAND_CURSOR));
            logOut.setCursor(new Cursor(Cursor.HAND_CURSOR));
            previousPage.setCursor(new Cursor(Cursor.HAND_CURSOR));
            nextPage.setCursor(new Cursor(Cursor.HAND_CURSOR));
            for (JButton singleButton : listOfButtons) {
                singleButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        }
    }
}
