import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public abstract class ConnectWithBaseInformationAboutUsers {
    private String url;
    private String password;
    private String login;
    protected Connection conn;
    protected Statement stst;
    protected ResultSet rs;

    public ConnectWithBaseInformationAboutUsers(){
        try {
            url = "jdbc:mysql://localhost:3306/information_about_users";
            password = "root";
            login = "root";
            conn = DriverManager.getConnection(url, login, password);
            stst = conn.createStatement();
        } catch (Exception e){
            System.out.println("Exception 2");
        }
    }
}
