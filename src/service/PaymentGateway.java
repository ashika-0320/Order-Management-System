package service;

public class PaymentGateway {
    protected double amount;

    public void pay(){
        System.out.println("==============Transaction Successfull===============");
        System.out.println("Amount"+ amount + " deducted from your account.");
    }
}

