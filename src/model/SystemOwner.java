package model;

public class SystemOwner {
    private String typeOfSubject;
    private int amount;
    private String bankAccount;
    private String login;
    void directDeposit(){

    }
    void procedureOfGettingMoney(){

    }
    void showMoneyBalance(){

    }
    void endSimulation(){

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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
