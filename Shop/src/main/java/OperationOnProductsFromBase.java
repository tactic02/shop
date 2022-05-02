import java.util.ArrayList;
import java.util.List;

public class OperationOnProductsFromBase extends ConnectWithBaseProductsInShop {
    private List<Integer> idOfProduct = new ArrayList<>();
    private List<String> linksToPhoto = new ArrayList<>();
    private List<Integer> cost = new ArrayList<>();
    private List<Integer> valueOfCard = new ArrayList<>();
    private List<String> type = new ArrayList<>();
    private static OperationOnProductsFromBase operationOnProductsFromBase = null;

    public static OperationOnProductsFromBase getInformationAboutProductsFromBase(){
        if (operationOnProductsFromBase == null){
            operationOnProductsFromBase = new OperationOnProductsFromBase();
        }
        return operationOnProductsFromBase;
    }

    public OperationOnProductsFromBase(){
    }

    public void getInformationAboutProducts(){
        try {
            while (resultSet.next()){
                idOfProduct.add(resultSet.getInt(1));
                linksToPhoto.add(resultSet.getString(2));
                cost.add(resultSet.getInt(3));
                valueOfCard.add(resultSet.getInt(4));
                type.add(resultSet.getString(5));
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void resetValuesTakenFromBase(){
        idOfProduct.clear();
        linksToPhoto.clear();
        cost.clear();
        valueOfCard.clear();
        type.clear();
    }

    public List<Integer> getIdOfProduct() {
        return idOfProduct;
    }

    public List<String> getLinksToPhoto() {
        return linksToPhoto;
    }

    public List<Integer> getCost() {
        return cost;
    }

    public List<Integer> getValueOfCard() {
        return valueOfCard;
    }

    public List<String> getType() {
        return type;
    }

    public void getProductsToBuyDescendingByCost(int userWhichUsingThis){
        try {
            resultSet = stst.executeQuery("SELECT * FROM carts_to_buy WHERE id NOT IN (SELECT product_id FROM carts_held WHERE player_id = " +
                    userWhichUsingThis + ") ORDER BY cost DESC");
        } catch (Exception e){
            System.out.println("Exception dddd");
        }
    }

    public void getProductsToBuyAscendingByCost(int userWhichUsingThis){
        try {
            resultSet = stst.executeQuery("SELECT * FROM carts_to_buy WHERE id NOT IN (SELECT product_id FROM carts_held WHERE player_id = " +
                    userWhichUsingThis + ") ORDER BY cost ASC");
        } catch (Exception e){
            System.out.println("Excetpion");
        }
    }

    public void getProductsToBuyDescendingByType(int userWhichUsingThis){
        try {
            resultSet = stst.executeQuery("SELECT * FROM carts_to_buy WHERE id NOT IN (SELECT product_id FROM carts_held WHERE player_id = " +
                    userWhichUsingThis + ") ORDER BY type DESC");
        } catch (Exception e){
            System.out.println("Exception");
        }
    }

    public void getProductsToBuyAscendingByType(int userWhichUsingThis){
        try {
            resultSet = stst.executeQuery("SELECT * FROM carts_to_buy WHERE id NOT IN (SELECT product_id FROM carts_held WHERE player_id = " +
                    userWhichUsingThis + ") ORDER BY type ASC");
        } catch (Exception e){
            System.out.println("Excetpion");
        }
    }

    public void getProductsBoughtDescendingByCost(int userWhichUsingThis){
        try {
            resultSet = stst.executeQuery("SELECT * FROM carts_to_buy WHERE id IN (SELECT product_id FROM carts_held WHERE player_id = " +
                    userWhichUsingThis + ") ORDER BY cost DESC");
        } catch (Exception e){
            System.out.println("Dont information about products Bought by users");
        }
    }

    public void getProductsBoughtAscendingByCost(int userWhichUsingThis){
        try {
            resultSet = stst.executeQuery("SELECT * FROM carts_to_buy WHERE id IN (SELECT product_id FROM carts_held WHERE player_id = " +
                    userWhichUsingThis + ") ORDER BY cost ASC");
        } catch (Exception e){
            System.out.println("Don't take information about products Bought By users");
        }
    }

    public void getProductsBoughtDescendingByType(int userWhichUsingThis){
        try {
            resultSet = stst.executeQuery("SELECT * FROM carts_to_buy WHERE id IN (SELECT product_id FROM carts_held WHERE player_id = " +
                    userWhichUsingThis + ") ORDER BY type DESC");
        } catch (Exception e){
            System.out.println("Don't take information about products Bought By users");
        }
    }

    public void getProductsBoughtAscendingByType(int userWhichUsingThis){
        try {
            resultSet = stst.executeQuery("SELECT * FROM carts_to_buy WHERE id IN (SELECT product_id FROM carts_held WHERE player_id = " +
                    userWhichUsingThis + ") ORDER BY type ASC");
        } catch (Exception e){
            System.out.println("Don't take information about products Bought By users");
        }
    }

    public void addProductsToCartsHeld(int userWhichUsingThis, int idOfProduct){
        try {
            stst.executeUpdate("INSERT INTO carts_held(player_id, product_id) VALUES " +
                    "(" + userWhichUsingThis + ", " + idOfProduct + ")");
        } catch (Exception e){
            System.out.println("Don't add product to base Bought by player");
        }
    }
}
