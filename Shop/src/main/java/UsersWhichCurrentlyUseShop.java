public class UsersWhichCurrentlyUseShop {
    private int usersWhichUsingWebiste;
    private String userUserName;
    private int cash;


    private static UsersWhichCurrentlyUseShop usersWhichCurrentlyUseShop = null;

    public static UsersWhichCurrentlyUseShop getUsersWhichCurrentlyUseShop(){
        if (usersWhichCurrentlyUseShop == null){
            usersWhichCurrentlyUseShop = new UsersWhichCurrentlyUseShop();
        }
        return usersWhichCurrentlyUseShop;
    }

    public UsersWhichCurrentlyUseShop(){

    }

    public void setUsersWhichUsingWebiste(int usersWhichUsingWebiste){
        this.usersWhichUsingWebiste = usersWhichUsingWebiste;
    }

    public void setUserUserName(String userUserName){
        this.userUserName = userUserName;
    }

    public void setCash(int cash){
        this.cash = cash;
    }

    public String getUserUserName(){
        return userUserName;
    }

    public int getCash(){
        return cash;
    }

    public int getUsersWhichUsingWebiste(){
        return usersWhichUsingWebiste;
    }
}
