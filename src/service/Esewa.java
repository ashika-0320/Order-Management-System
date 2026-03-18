package service;

public class Esewa extends PaymentGateway{

    public Esewa (double amount){
        this.amount = amount;
    }

    @Override
    public void pay(){
        System.out.println("Paying via esewa.....");
        try{
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("Payment Interrupted!");
        }
        super.pay();
    }
}
