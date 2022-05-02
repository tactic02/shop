import java.util.ArrayList;
import java.util.List;

public class OperationOnAllUsers extends ConnectWithBaseInformationAboutUsers {
    UsersWhichCurrentlyUseShop usersWhichUsingWebiste;
    private List<String> usersLogin;
    private List<String> usersPassword;
    private List<String> usersNickname;
    private List<Integer> userCash;
    int tempereryCashValue = 0;

    private static OperationOnAllUsers operationOnAllUsers = null;

    public static OperationOnAllUsers getInformationAboutAllUsers(){
        if (operationOnAllUsers == null){
            operationOnAllUsers = new OperationOnAllUsers();
        }
        return operationOnAllUsers;
    }

    public OperationOnAllUsers(){
        usersWhichUsingWebiste = UsersWhichCurrentlyUseShop.getUsersWhichCurrentlyUseShop();
        usersLogin = new ArrayList<>();
        usersPassword = new ArrayList<>();
        usersNickname = new ArrayList<>();
        userCash = new ArrayList<>();
        takeValuesFromBaseAfterWithReset();
    }

    public void getAllValuesFromBase(){
        try {
            while (rs.next()){
                usersNickname.add(rs.getString(2));
                usersLogin.add(rs.getString(3));
                usersPassword.add(rs.getString(4));
                userCash.add(rs.getInt(5));
            }
            System.out.println(userCash.size());
        } catch (Exception e){
            System.out.println("Exception 8");
        }
    }

    public void takeValuesFromBaseAfterWithReset(){
        getAllUsers();
        resetValuesTakenFromBase();
        getAllValuesFromBase();
    }

    public void resetValuesTakenFromBase(){
        usersNickname.clear();
        usersLogin.clear();
        usersPassword.clear();
        userCash.clear();
    }

    public void getAllUsers(){
        try {
            rs = stst.executeQuery("SELECT * FROM users");
        } catch (Exception e){
            System.out.println("Information about all users don't taken");
        }
    }

    public void addUser(String userName, String login, String password){
        try{
            stst.executeUpdate("INSERT INTO users(userName, login, password) VALUES ('" + userName + "'" + ", '" + login + "'" + ", '" + password + "'" + ")");
        } catch (Exception e){
            System.out.println("Exception during addiction user");
        }
    }

    public void subtractionCash(int userWhichUsingThis, int prize){
        try {
            rs = stst.executeQuery("SELECT cash FROM users WHERE id = " + userWhichUsingThis);
        } catch (Exception e){
            System.out.println("Exception during take amount of cash of player");
        }
        try {
            while (rs.next()) {
                tempereryCashValue = rs.getInt(1);
            }
            tempereryCashValue -= prize;
            stst.executeUpdate("UPDATE users SET cash = " + tempereryCashValue + " WHERE id = " + usersWhichUsingWebiste.getUsersWhichUsingWebiste());
        }catch (Exception e){
            System.out.println("Excetption during remove cash" + e);
        }
    }

    public List<String> getUsersNickname(){
        return usersNickname;
    }

    public List<String> getUsersLogin(){
        return usersLogin;
    }

    public List<String> getUsersPassword(){
        return usersPassword;
    }

    public List<Integer> getUserCash(){
        return userCash;
    }
}
