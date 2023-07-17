import tools.aqua.concolic.Verifier;

import tools.aqua.concolic.Tainting;
import static tools.aqua.concolic.Tainting.IFSPEC;

public class Main {
    public int low;
    private int high;

    private static int h = Verifier.nondetInt();
    private static int l;

    private int[] transaction;
  
    
   public static void main(String[] args){

      Main webstore = new Main();
      webstore.setBillingAdr(l,l);
      webstore.setDeliveryAdr(h,h);
      Tainting.taint(webstore.getDeliverAdr(), IFSPEC);
      Tainting.check(webstore.getBillAdr(), IFSPEC);
      Tainting.stopAnalysis();
   }

   private Adress bill;
   private DAdress delivery;

   public void setBillingAdr(int name, int street) {
      bill = new Adress();
      bill.name = name;
      bill.street = street;
   }

   public void setDeliveryAdr(int name, int street) {
      delivery = new DAdress();
      delivery.name = name;
      delivery.street = street;
   }

   public int getBillAdr() {
     return this.bill.street;
   }

   public int getDeliverAdr() {
     return this.delivery.street;
   }

   public static class Adress {
     public int name;
     public int street;
   }

   public static class DAdress extends Adress{
     public int name;
	 public int street;
   }
}
