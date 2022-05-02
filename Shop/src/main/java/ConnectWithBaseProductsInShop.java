import java.sql.*;

public abstract class ConnectWithBaseProductsInShop {
    private String url;
    private String login;
    private String password;
    protected Connection conn;
    protected Statement stst;
    protected ResultSet resultSet;

    public ConnectWithBaseProductsInShop() {
        try {
            url = "jdbc:mysql://localhost:3306/products_in_shop";
            login = "root";
            password = "MYSQLBase0!";
            conn = DriverManager.getConnection(url, login, password);
            stst = conn.createStatement();
        } catch (Exception e) {
            System.out.println("E");
        }
    }
}
