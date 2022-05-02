public class ControlPassword {
    private String powerOfPassword = "";
    private int pointsOfPassword;
    private int minimalLenght;

    public ControlPassword(){
        minimalLenght = 8;
    }

    public void ControlPowerOfPassword(String passsword){
        resetValueOfPointOfPassword();
        controlLenght(passsword);
        controlNumberOfLowerLetter(passsword);
        controlNumberOfUpperLetter(passsword);
        controlNumberOfNumbers(passsword);
        controlNumberOfSpecialSigns(passsword);
        checkPowerOfPassword();
        System.out.println(pointsOfPassword);
    }

    private void controlLenght(String password){
        if (password.length() >= 8){
            addPointToPassword();
        }
    }

    private void controlNumberOfLowerLetter(String password){
        for (int singleChar : password.toCharArray()){
            if (singleChar >= 97 && 122 >= singleChar){
                addPointToPassword();
                break;
            }
        }
    }

    private void controlNumberOfUpperLetter(String password){
        for (int singleChar : password.toCharArray()){
            if (singleChar >= 65 && 90 >= singleChar){
                addPointToPassword();
                break;
            }
        }
    }

    private void controlNumberOfNumbers(String password){
        for (int singleChar : password.toCharArray()){
            if (singleChar >= 48 && 57 >= singleChar){
                addPointToPassword();
                break;
            }
        }
    }

    private void controlNumberOfSpecialSigns(String password){
        for (int singleChar : password.toCharArray()){
            if ((singleChar >= 32 && 47 >= singleChar) || (singleChar >= 58 && 64 >= singleChar) || (singleChar >= 91 && 96 >= singleChar) ||
                    (singleChar >= 123 && 126 >= singleChar)){
                addPointToPassword();
                break;
            }
        }
    }

    public void checkPowerOfPassword(){
        if (pointsOfPassword > 4){
            powerOfPassword = "Strong";
        }
        else if (pointsOfPassword > 3){
            powerOfPassword = "Medium";
        }
        else {
            powerOfPassword = "Weak";
        }
    }

    public String getPowerOfPassword(){
        return powerOfPassword;
    }

    public void resetValueOfPointOfPassword(){
        pointsOfPassword = 0;
    }

    public void addPointToPassword(){
        pointsOfPassword++;
    }
}
