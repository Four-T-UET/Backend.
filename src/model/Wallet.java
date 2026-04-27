package model;

public class Wallet extends Entity {
    private double balance;
    private double lockBalance;
    public Wallet(){
        super();
        this.balance = 0;this.lockBalance = 0;
    }
    //Getter/Setter
    public void setBalance(double balance){this.balance = balance;}
    public void setLockBalance(double lockBalance){this.lockBalance = lockBalance;}
    public double getBalance(){return this.balance;}
    public double getLockBalance(){return this.lockBalance;}

    //Methods
    public void deposit(double amount){
        balance += amount;
    }
    public synchronized void lockWallet(double amount){
        this.balance = this.balance - (amount - this.lockBalance);
        this.lockBalance = amount;
    }

    public void releaseBalance(){
        this.balance += this.lockBalance;
        this.lockBalance = 0;
    }

    public void deductLockBalance(){
        this.balance-=this.lockBalance;
        this.lockBalance=0;
    }

}