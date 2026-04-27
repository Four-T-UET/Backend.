package model;

import java.util.concurrent.locks.ReentrantLock;

public class Wallet extends Entity {
    private volatile double balance;
    private double lockBalance;
    private ReentrantLock lock=new ReentrantLock();
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
    public synchronized void deposit(double amount){
        balance += amount;
    }
    public synchronized void lockWallet(double amount){
        this.balance = this.balance - (amount - this.lockBalance);
        this.lockBalance = amount;
    }

    public void releaseBalance(double amount){
        lock.lock();
        try {
            this.balance += amount;
            this.lockBalance -= amount;
        }finally {
            lock.unlock();
        }

    }

    public void deductLockBalance(double amount){
        lock.lock();
        try {
            this.balance -= amount;
            this.lockBalance -= amount;
        }finally{
            lock.unlock();
        }
    }

}