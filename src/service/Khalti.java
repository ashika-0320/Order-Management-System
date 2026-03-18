package service;

public class Khalti extends PaymentGateway{
    public Khalti (double amount){
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
