public class PropertiesAboutProductsOnScreen {
    OperationOnProductsFromBase operationOnProductsFromBase;
    private int amountOfProductsOnOneScreenWindow;
    private int currentPage;

    private static PropertiesAboutProductsOnScreen propertiesAboutProductsOnScreen = null;

    public static PropertiesAboutProductsOnScreen getAmountOfProductsOnSide(){
        if (null == propertiesAboutProductsOnScreen){
            propertiesAboutProductsOnScreen = new PropertiesAboutProductsOnScreen();
        }
        return propertiesAboutProductsOnScreen;
    }

    public PropertiesAboutProductsOnScreen(){
        operationOnProductsFromBase = OperationOnProductsFromBase.getInformationAboutProductsFromBase();
        amountOfProductsOnOneScreenWindow = 10;
        currentPage = 0;
    }

    public void setAmountOfProductsOnOneScreenWindow(int amountOfProductsOnOneScreenWindow){
        this.amountOfProductsOnOneScreenWindow = amountOfProductsOnOneScreenWindow;
    }

    public int getAmountOfProductsOnOneScreenWindow(){
        return amountOfProductsOnOneScreenWindow;
    }

    public void nextPage(){
        if (operationOnProductsFromBase.getIdOfProduct().size() > amountOfProductsOnOneScreenWindow * (1 + currentPage)) {
            currentPage++;
        }
    }

    public void previousPage(){
        if (currentPage > 0) {
            currentPage--;
        }
    }

    public void setCurrentPage(int page){
        currentPage = page;
    }

    public int getCurrentPage(){
        return currentPage;
    }
}
