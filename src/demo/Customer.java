package demo;

import java.util.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;  
public class Customer extends Totalpoint 
{
   
   private String customerName;
   private String customerPhoneNo;
   private String productName; 
   private int purchaseQty;
   private float productPrice;
   private float productTotalPrice;
   private int customerBillNo;
public Customer() {
	    
   }
    Scanner sc=  new Scanner(System.in);
   
 public Customer(String customerName,String customerPhoneNo,  String productName, int purchaseQty, float productPrice,float productTotalPrice,int customerBillNo) 
 {
	this.customerName = customerName;
	this.customerPhoneNo=customerPhoneNo;
	this.productName = productName;
	this.purchaseQty = purchaseQty;
	this.productPrice = productPrice;
	this.productTotalPrice=productTotalPrice;
	this.customerBillNo= customerBillNo;
}
public  void sellItem(ArrayList<Customer> customers,LinkedList<Groceries> groceries,ArrayList<Totalpoint>  totalpoints,String cName,String cPhoneNo,int cBillNo){
	   Scanner sc=new Scanner(System.in); 
	   int check=0;
	   System.out.println("enter product name");
	   String pName=sc.next();
	 	for(Groceries g:groceries)// to check weather entered product name is available in the grocery list
		{
	 		if(g.getName().equalsIgnoreCase(pName)) 
	 		{
	 			check++;
	 		}
		}
	 	if(check==0) {
	 		System.out.println("    product the customer needs is not in the grocery list");
	 	}
	 	else 
	 	{
	 	  try
	 	{
	 	   System.out.println("enter Quantity");
		   int  productQuantity=sc.nextInt();
		   if( customerAlreadyContains(customers, groceries,cBillNo,pName,productQuantity)) //for updating in the existing customer bill// for not repeating same product in the bill
		   {
	
			   //Main.displayitems(groceries);
		   }
		   else 
		   {
			   for(Groceries g:groceries)
				{
					if(g.getName().equalsIgnoreCase(pName) && productQuantity<=g.getQuantity() )//if the entered quantity is within the available in the grocery list
					{
						float proTotalPrice=productQuantity*g.getMrp();
						customers.add(new Customer(cName,cPhoneNo,g.getName(),productQuantity,g.getMrp(),proTotalPrice,cBillNo));
						System.out.println("#####  "+productQuantity+" quantity of "+pName +" is added to customer bill  #####\n");
						int prevQty=g.getQuantity();
						int remaining_Qty= g.setQuantity(prevQty-productQuantity);
						System.out.println("#####  Remaining Quantity of "+pName+" in the grocery list is:  "+remaining_Qty+"  #####\n");
						//Main.displayitems(groceries);
					}
					else if(g.getName().equalsIgnoreCase(pName) && productQuantity>g.getQuantity() )// if the entered quantity is not available in the grocery list
					{
						System.out.println("    we have only "+g.getQuantity() +" quantity of "+pName);
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
	 	sellOrRemove(customers, groceries,totalpoints,cName,cPhoneNo,cBillNo);
	     
}
public  void displayCustomerBill(ArrayList<Customer> customers ,LinkedList<Groceries> groceries,ArrayList<Totalpoint>  totalpoints,String cName,String cPhoneNo,int cBillNo)//to display customer bill
{
	float total=0;
	System.out.println("\n                          "+"BILLING");
	DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/YYYY");
	DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm:ss");
    LocalDate dateNow = LocalDate.now();
	LocalTime timeNow=LocalTime.now();
	System.out.println("\n      BILL NO  :"+cBillNo);   
	System.out.println("CUSTOMER NAME  :"+cName);
	System.out.println("                                                Date: "+df.format(dateNow));//for date and time
	System.out.println("                                                Time: "+tf.format(timeNow));  
	System.out.println("======================================================================");
	System.out.println("Item name"+"\t\t"+"Quantity"+"\t"+"unit cost"+"\t"+"Item Total cost");
	System.out.println("======================================================================");
	for(Customer c:customers) 
	{
		if(c.customerBillNo==cBillNo)
		{
		 System.out.println(c.productName+"\t\t"+c.purchaseQty+"\t\t"+c.productPrice+"\t\t"+c.productTotalPrice);
		 total=total+(c.productPrice*c.purchaseQty);// for total cost
		}
	}
	System.out.println("======================================================================");
    System.out.println("Total Amount   :"+total);
    System.out.println("======================================================================");
     float discountPrice=0;
     float savePrice=0;
     if(total>=500)// discount above 500
     {
    	 savePrice=(total*10)/100;
    	  discountPrice=total-savePrice;
    		 System.out.println("Discount price  :"+ discountPrice);
    		 System.out.println("======================================================================");
    		 System.out.println("         YOUR TODAYS SAVING IS   :"+savePrice);
    		 System.out.println("======================================================================");
     }
     else if(total>=300)// discount above 300
     {
    	 savePrice=(total*5)/100;
    	  discountPrice=total-savePrice;
    		 System.out.println("Discount price  :"+ discountPrice);
    		 System.out.println("===================================================================");
    		 System.out.println("         YOUR TODAYS SAVING IS   :"+savePrice);
    		 System.out.println("===================================================================");
     }
     else 
     {
    	 discountPrice= total;
     }
   
	
     System.out.println("         Thank you! Have a safe and Happy day!!!\n\n");
     
     System.out.println("do you want to make any changes in the bill (yes/no)   ");//conforming bill
   try
    { 
	   String billChange=sc.next();
     if(billChange.equalsIgnoreCase("yes")) 
     {
    	 sellOrRemove(customers,groceries,totalpoints,cName,cPhoneNo,cBillNo);
     }
     else if(billChange.equalsIgnoreCase("no")) 
     {
    	 System.out.println("           Your bill is confirmed!thank you\n\n\n");
    	customerdetails(totalpoints, cName, cPhoneNo,  discountPrice);//adding points to customers
     }
    }
   catch(Exception e)
       {
    	e.printStackTrace();
    	System.out.println(" Invalid input");
       }
     
     
    //payment(discountPrice); 
}
public  void removeItem(ArrayList<Customer> customers,LinkedList<Groceries> groceries,ArrayList<Totalpoint>  totalpoints,String cName,String cPhoneNo,int cBillNo) {
	// for canceling items
	String pName="";
	int check=0;
	System.out.println("enter the product name to cancel");
	pName=sc.next();
	for(Customer c:customers)// to check the product is in customer bill
	{
 		if(c.customerBillNo==cBillNo && c.productName.equals(pName)) 
 		{
 			check++;
 		}
	}
 	if(check==0) {
 		System.out.println("    the customer  not bought the item");
 	}
 	else 
 	{
 		
 		for(Customer c:customers)
 		{
 			
 			if(c.customerBillNo==cBillNo && c.productName.equals(pName))
 			{ 
 			 try //to check the quantity is only integer
 			  {
 				System.out.println("Enter the quantity to cancel");
 		 		int  productQuantity=sc.nextInt();
 				if(c.purchaseQty==productQuantity) //if the buy quantity and canceling quantity are same
 				{
 					for(Groceries g:groceries) 
					{
						if(g.getName().equals(pName)) 
						{
							 int prevQty=g.getQuantity();
							 int remainingQty=g.setQuantity(prevQty+productQuantity);
							 System.out.println("#####  Remaining Quantity of "+pName+" in the grocery list is:  "+remainingQty+"  #####\n");
							// Main.displayitems(groceries);
						}
					}
 				   customers.remove(c);
 				  System.out.println("#####  "+pName +" is fully removed from customer bill  #####\n");
 				   break;
 			  	
 			
 			    }
 			    else if(c.purchaseQty>productQuantity)// if the canceling quantity is less than buy quantity
 				{
 			    	 int prevQty1=c.getPurchaseQty();
 			    	 int  newQty1=c.setPurchaseQty(prevQty1-productQuantity);
 			    	 c.setProductTotalPrice(c.getProductPrice()*newQty1);
 			    	 System.out.println("#####  "+productQuantity+" quantity of "+pName +" is removed from  the existing customer bill  #####\n");
 			    	    
 			    	for(Groceries g:groceries) 
 					{
 						if(g.getName().equals(pName)) 
 						{
 							 int prevQty=g.getQuantity();
 							 int remainingQty= g.setQuantity(prevQty+productQuantity);
 							 System.out.println("#####  Remaining Quantity of "+pName+" in the grocery list is:  "+remainingQty+"  #####\n");
 							 //Main.displayitems(groceries);
 						}
 					} 	
 				}
 			    else 
 			    {
 			    	System.out.println("     you have bought only "+c.purchaseQty+ " quantity of "+pName);
 			    }
 			
 			 }
 			   catch (InputMismatchException e)
 				{
 		 	  e.printStackTrace();
 		 	  System.err.println("Entered value is not an integer");
 		 	    }
 				
 			}
 		}	
 	}
 	sellOrRemove(customers, groceries,totalpoints, cName,cPhoneNo,cBillNo);
}
public  boolean customerAlreadyContains(ArrayList<Customer> customers,LinkedList<Groceries> groceries,int cBillNo,String pName,int productQuantity ) 
{
	for(Customer c:customers) 
	{
		if(c.customerBillNo==cBillNo) 
		{ 
			
				if(c.productName.equalsIgnoreCase(pName)) 
				{
					 for(Groceries g:groceries) 
					   {
						   if(g.getName().equalsIgnoreCase(pName) && productQuantity<=g.getQuantity() )
							{
							
								int prevQty=g.getQuantity();
							    int remainingQty=g.setQuantity(prevQty-productQuantity);
	
								int prevQty1=c.getPurchaseQty();
							    int newQty1=c.setPurchaseQty(prevQty1+productQuantity);   
							    c.setProductTotalPrice(c.getProductPrice()*newQty1); 
							    	 System.out.println("#####  "+productQuantity+" quantity of "+pName +" is updated in the existing customer bill  #####\n");
							    	 System.out.println("#####  Remaining Quantity of "+pName+" in the grocery list is:  "+remainingQty+"  #####\n");
							}
							else if(g.getName().equalsIgnoreCase(pName) && productQuantity>g.getQuantity() )
							{
								System.out.println(" *   we have only "+g.getQuantity() +" quantity of "+pName);
							}
					   }
						return true;
				}
			
		}
		
	}
	return false;
}
public  void sellOrRemove(ArrayList<Customer> customers,LinkedList<Groceries> groceries,ArrayList<Totalpoint>  totalpoints,String cName,String cPhoneNo,int cBillNo) 
{
	String choice = "";
	System.out.println("do you want to sell a new  items/remove an item (sell/remove/no)");
     choice=sc.next();
	if(choice.equalsIgnoreCase("sell"))
	{
		sellItem(customers, groceries,totalpoints,cName,cPhoneNo,cBillNo);
	}
	else if(choice.equalsIgnoreCase("remove"))
	{
		removeItem(customers, groceries,totalpoints,cName,cPhoneNo,cBillNo);
	}
	else if(choice.equalsIgnoreCase("no"))
	{
		displayCustomerBill(customers,groceries,totalpoints,cName,cPhoneNo,cBillNo);
	}
	else 
	{
		sellOrRemove(customers, groceries,totalpoints,cName,cPhoneNo,cBillNo);
	}
}


public String getCustomerPhoneNo() {
	return customerPhoneNo;
}
public void setCustomerPhoneNo(String customerPhoneNo) {
	this.customerPhoneNo = customerPhoneNo;
}
public float getProductTotalPrice() {
	return productTotalPrice;
}
public void setProductTotalPrice(float productTotalPrice) {
	this.productTotalPrice = productTotalPrice;
}
public String getCustomerName() {
	return customerName;
}
public void setCustomerName(String customerName) {
	this.customerName = customerName;
}
public int getPurchaseQty() {
	return purchaseQty;
}
public int setPurchaseQty(int purchaseQty) {
	return this.purchaseQty = purchaseQty;
}

public String getProductName() {
	return productName;
}


public void setProductName(String productName) {
	this.productName = productName;
}


public float getProductPrice() {
	return productPrice;
}


public void setProductPrice(float productPrice) {
	this.productPrice = productPrice;
}

public int getCustomerBillNo() {
	return customerBillNo;
}
public void setCustomerBillNo(int customerBillNo) {
	this.customerBillNo = customerBillNo;
}

@Override
public String toString() {
	return "Customer [customer_name=" + customerName +"billno"+customerBillNo+ ", customer_no=" + customerPhoneNo + ", product_name="
			+ productName + ", purchase_qty=" + purchaseQty + ", product_price=" + productPrice
			+ ", product_total_price=" + productTotalPrice + "]";
}




}
