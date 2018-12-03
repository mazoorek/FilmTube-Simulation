package model;

public class Distributor implements LicenceAgreement {
    private String typeOfSubject ;
    private int amount;
    private String bankAccount;
    private String name;
    private String login;
    private String password;
    public void directDeposit(){

    }
    public void procedureOfGettingMoney(){

    }
    public void renegotiateDeal(){

    }
    public void editAccount(){

    }
    public void releaseProduct(){

    }

    public String getTypeOfSubject() {
        return typeOfSubject;
    }

    public void setTypeOfSubject(String typeOfSubject) {
        this.typeOfSubject = typeOfSubject;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
