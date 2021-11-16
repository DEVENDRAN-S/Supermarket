package demo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class Vendor {
	   private String vendorName;
	   private String vendorPhoneNo;
	   private String proName; 
	   private int proQty;
	   private float proPrice;
	   private float proTotalPrice;
	   private int vendorBillNo;
	   
	   public Vendor() 
	   {
		   
	   }
	public Vendor(String vendorName,String vendorPhoneNo, String proName, int proQty, float proPrice, float proTotalPrice,int vendorBillNo)
	{
		this.vendorName = vendorName;
		this.vendorPhoneNo = vendorPhoneNo;
		this.proName = proName;
		this.proQty = proQty;
		this.proPrice = proPrice;
		this.proTotalPrice = proTotalPrice;
		this.vendorBillNo=vendorBillNo;
	}
	 Scanner sc=new Scanner(System.in);
	public  void displayVendorBill(ArrayList<Vendor>  vendors,String vName,String vPhoneNo,int vBillNo) 
	{	
		float total=0;
	 System.out.println("\n                          "+"BILLING");
	 DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/YYYY");
	 DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm:ss");
	   LocalDate dateNow = LocalDate.now();
	   LocalTime timeNow=LocalTime.now();
    System.out.println("\n    BILL NO  :"+vBillNo);
	System.out.println("VENDOR NAME  :"+vName);                    
	System.out.println("                                              Date: "+df.format(dateNow));
	System.out.println("                                              Time: "+tf.format(timeNow));  

	System.out.println("======================================================================");
	System.out.println("Item name"+"\t\t"+"Quantity"+"\t"+"unit cost"+"\t"+"Item Total cost");
	System.out.println("======================================================================");
	for(Vendor v:vendors) {
		if(v.vendorBillNo==vBillNo) {
		 System.out.println(v.proName+"\t\t"+v.proQty+"\t\t"+v.proPrice+"\t\t"+v.proTotalPrice);
		 total=total+v.proTotalPrice;
		}
	}
	 System.out.println("======================================================================");
     System.out.println("Total Amount   :"+total);
     System.out.println("======================================================================");
     System.out.println("         Thank you! Have a safe and Happy day!!!\n\n\n");
     
	}
	
	public  void buyOrRemove(ArrayList<Vendor>  vendors,LinkedList<Groceries> groceries,String vName,String vPhoneNo,int vBillNo) 
	{
		
		String choice = "";
		System.out.println("do you want to buy a new  items/remove an item  from the vendor(buy/remove/no)");
			choice=sc.next();
		if(choice.equalsIgnoreCase("buy"))
		{
		
			Groceries g = new Groceries();
			g.buyItem(vendors, groceries,vName,vPhoneNo,vBillNo);
		}
		else if(choice.equalsIgnoreCase("remove"))
		{
			removeProduct(vendors, groceries,vName,vPhoneNo,vBillNo);
		}
		else if(choice.equalsIgnoreCase("no"))
		{
			 displayVendorBill(vendors, vName,vPhoneNo,vBillNo); 
		}
		else 
		{
			buyOrRemove(vendors, groceries,vName,vPhoneNo,vBillNo);
		}
		
	}

	
	public  void removeProduct(ArrayList<Vendor>  vendors,LinkedList<Groceries> groceries,String vName,String vPhoneNo,int vBillNo)
	{
		
		String pName="";
		int check=0;
		System.out.println("enter the product name to cancel");
		pName=sc.next();
		for(Vendor v:vendors)
		{
	 		if(   v.vendorBillNo==vBillNo && v.proName.equalsIgnoreCase(pName)) 
	 		{
	 			check++;
	 		}
		}
	 	if(check==0) {
	 		System.out.println("    we dont bought "+pName+" from this vendor ");
	 	}
	 	else 
	 	{
	 		try 
	 		{
	 		System.out.println("Enter the quantity to cancel");
	 		int  productQuantity=sc.nextInt();
	 		for(Vendor v:vendors)
	 		{
	 			
	 			if(  v.vendorBillNo==vBillNo && v.proName.equalsIgnoreCase(pName))
	 			{
	 			
	 				if(v.proQty==productQuantity) 
	 				{
	 					for(Groceries g:groceries) 
						{
							if(g.getName().equals(pName) && v.proQty==g.getQuantity()) //if you want to cancel full quantity you added as new
							{
								int id=g.getId();//for updating in the item id 
								for(Groceries g1:groceries) 
								{
									if(g1.getId()>id) 
									{
										int prevId=g1.getId();
										g1.setId(prevId-1);
									}
								} 
								groceries.remove(g);
				 				  System.out.println("#####  "+pName +" is fully removed from Groceries list  #####\n");
								break;
							}
							else if(g.getName().equals(pName) && v.proQty<g.getQuantity())//if you want to cancel full row but if already contains at intially
							{

								 int prevQty=g.getQuantity();
								 int remainingQty=g.setQuantity(prevQty-productQuantity);
								 System.out.println("#####  Remaining Quantity of "+pName+" in the grocery list is:  "+remainingQty+"  #####\n");
								 
							}
						}
	 					 //Main.displayitems(groceries);
	 	 				vendors.remove(v);
	 	 				  System.out.println("#####  "+pName +" is fully removed from Vendor bill  #####\n");
	 	 				   break;
					}
	 				else if(v.proQty>productQuantity)// if you want to cancel only few quantity from you sold
	 				{
	 					 
	 			    	 int prevQty1=v.getProQty();
	 			    	 int  newQty1=v.setProQty(prevQty1-productQuantity);
	 			    
	 			    	 v.setProTotalPrice(newQty1*v.getProPrice());
	 			    	 System.out.println("#####  "+productQuantity+" quantity of "+pName +" is removed from  the existing Vendor bill  #####\n");
	
	 			    	    
	 			    	for(Groceries g:groceries) 
	 					{
	 						if(g.getName().equals(pName)) 
	 						{
	 							 int prevQty=g.getQuantity();
	 							 int remainingQty=g.setQuantity(prevQty-productQuantity);
	 							 System.out.println("#####  Remaining Quantity of "+pName+" in the grocery list is:  "+remainingQty+"  #####\n");
	 							// Main.displayitems(groceries);
	 						}
	 					} 	
	 				}
	 			    else // if the entered quantity is more than available quantity in vendor bill
	 			    {
	 			    	System.out.println("     you have sold only "+v.proQty+ " quantity of "+pName);
	 			    }
	 				
	 			  	
	 			
	 			   }
	 			    
	 				
	 			}
	 		
	 	      }
		      catch (InputMismatchException e)
		      {
	 	       e.printStackTrace();
	 	      System.err.println("Entered value is not an integer");
	 	       }
	 		}
	 	   buyOrRemove(vendors, groceries,vName,vPhoneNo,vBillNo);
	 	}
	
	
	public String getVendorNo() {
		return vendorPhoneNo;
	}
	public void setVendorNo(String vendorPhoneNo) {
		this.vendorPhoneNo = vendorPhoneNo;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public int getProQty() {
		return proQty;
	}
	public int setProQty(int proQty) {
		return this.proQty = proQty;
	}
	public float getProPrice() {
		return proPrice;
	}
	public void setProPrice(float proPrice) {
		this.proPrice = proPrice;
	}
	public float getProTotalPrice() {
		return proTotalPrice;
	}
	public void setProTotalPrice(float proTotalPrice) {
		this.proTotalPrice = proTotalPrice;
	}
	public int getVendorBillNo() {
		return vendorBillNo;
	}
	public void setVendorBill_no(int vendorBillNo) {
		this.vendorBillNo = vendorBillNo;
	}
	@Override
	public String toString() {
		return "Vendor [vendor_name=" + vendorName + ",vendorNO="+vendorPhoneNo+", pro_name=" + proName + ", pro_qty=" + proQty + ", pro_price="
				+ proPrice + ", pro_total_price=" + proTotalPrice + "]";
	}
}
