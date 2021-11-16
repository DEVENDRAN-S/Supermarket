package demo;

import java.util.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;  
public class Customer extends Totalpoint 
{
   
   private String customer_name;
   private String customer_phoneno;
   private String product_name; 
   private int purchase_qty;
   private float product_price;
   private float product_total_price;
   private int customer_bill_no;
public Customer() {
	    
   }
    Scanner sc=  new Scanner(System.in);
   
 public Customer(String customer_name,String customer_phoneno,  String product_name, int purchase_qty, float product_price,float product_total_price,int customer_bill_no) 
 {
	this.customer_name = customer_name;
	this.customer_phoneno=customer_phoneno;
	this.product_name = product_name;
	this.purchase_qty = purchase_qty;
	this.product_price = product_price;
	this.product_total_price=product_total_price;
	this.customer_bill_no= customer_bill_no;
}
public  void sellitems(ArrayList<Customer> customers,LinkedList<Groceries> groceries,ArrayList<Totalpoint>  totalpoints,String c_name,String c_no,int c_bill_no){
	   Scanner sc=new Scanner(System.in); 
	   int check=0;
	   System.out.println("enter product name");
	   String p_name=sc.next();
	 	for(Groceries g:groceries)// to check weather entered product name is available in the grocery list
		{
	 		if(g.getName().equalsIgnoreCase(p_name)) 
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
		   int  product_Quantity=sc.nextInt();
		   if( customerAlreadyContains(customers, groceries,c_bill_no,p_name,product_Quantity)) //for updating in the existing customer bill// for not repeating same product in the bill
		   {
	
			   //Main.displayitems(groceries);
		   }
		   else 
		   {
			   for(Groceries g:groceries)
				{
					if(g.getName().equalsIgnoreCase(p_name) && product_Quantity<=g.getQuantity() )//if the entered quantity is within the available in the grocery list
					{
						float pro_total_price=product_Quantity*g.getMrp();
						customers.add(new Customer(c_name,c_no,g.getName(),product_Quantity,g.getMrp(),pro_total_price,c_bill_no));
						System.out.println("#####  "+product_Quantity+" quantity of "+p_name +" is added to customer bill  #####\n");
						 int prev_Qty=g.getQuantity();
						int remaining_Qty= g.setQuantity(prev_Qty-product_Quantity);
						 System.out.println("#####  Remaining Quantity of "+p_name+" in the grocery list is:  "+remaining_Qty+"  #####\n");
						//Main.displayitems(groceries);
					}
					else if(g.getName().equalsIgnoreCase(p_name) && product_Quantity>g.getQuantity() )// if the entered quantity is not available in the grocery list
					{
						System.out.println("    we have only "+g.getQuantity() +" quantity of "+p_name);
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
	 	sellorremove(customers, groceries,totalpoints,c_name,c_no,c_bill_no);
	     
}
public  void display_customer_bill(ArrayList<Customer> customers ,LinkedList<Groceries> groceries,ArrayList<Totalpoint>  totalpoints,String c_name,String c_no,int c_bill_no)//to display customer bill
{
	float total=0;
	System.out.println("\n                          "+"BILLING");
	 DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/YYYY");
	 DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm:ss");
	   LocalDate D_now = LocalDate.now();
	   LocalTime T_now=LocalTime.now();
	System.out.println("\n      BILL NO  :"+c_bill_no);   
	System.out.println("CUSTOMER NAME  :"+c_name);
	System.out.println("                                                Date: "+df.format(D_now));//for date and time
	System.out.println("                                                Time: "+tf.format(T_now));  
	System.out.println("======================================================================");
	System.out.println("Item name"+"\t\t"+"Quantity"+"\t"+"unit cost"+"\t"+"Item Total cost");
	System.out.println("======================================================================");
	for(Customer c:customers) 
	{
		if(c.customer_bill_no==c_bill_no)
		{
		 System.out.println(c.getProduct_name()+"\t\t"+c.getPurchase_qty()+"\t\t"+c.getProduct_price()+"\t\t"+c.product_total_price);
		 total=total+(c.getProduct_price()*c.getPurchase_qty());// for total cost
		}
	}
	System.out.println("======================================================================");
    System.out.println("Total Amount   :"+total);
    System.out.println("======================================================================");
     float discount_price=0;
     float save_price=0;
     if(total>=500)// discount above 500
     {
    	 save_price=(total*10)/100;
    	  discount_price=total-save_price;
    		 System.out.println("Discount price  :"+ discount_price);
    		 System.out.println("======================================================================");
    		 System.out.println("         YOUR TODAYS SAVING IS   :"+save_price);
    		 System.out.println("======================================================================");
     }
     else if(total>=300)// discount above 300
     {
    	 save_price=(total*5)/100;
    	  discount_price=total-save_price;
    		 System.out.println("Discount price  :"+ discount_price);
    		 System.out.println("===================================================================");
    		 System.out.println("         YOUR TODAYS SAVING IS   :"+save_price);
    		 System.out.println("===================================================================");
     }
     else 
     {
    	 discount_price= total;
     }
   
	
     System.out.println("         Thank you! Have a safe and Happy day!!!\n\n");
     
     System.out.println("do you want to make any changes in the bill (yes/no)   ");//conforming bill
   try
    { String bill_change=sc.next();
     if(bill_change.equalsIgnoreCase("yes")) 
     {
    	 sellorremove(customers,groceries,totalpoints,c_name,c_no,c_bill_no);
     }
     else if(bill_change.equalsIgnoreCase("no")) 
     {
    	 System.out.println("           Your bill is confirmed!thank you\n\n\n");
    	customerdetails(totalpoints, c_name, c_no,  discount_price);//adding points to customers
     }
    }
   catch(Exception e)
       {
    	e.printStackTrace();
    	System.out.println("Enters Invalid input");
       }
     
     
    payment(discount_price); 
}
public  void removeitem(ArrayList<Customer> Customers,LinkedList<Groceries> groceries,ArrayList<Totalpoint>  totalpoints,String c_name,String c_no,int c_bill_no) {
	// for canceling items
	String p_name="";
	int check=0;
	System.out.println("enter the product name to cancel");
	p_name=sc.next();
	for(Customer c:Customers)// to check the product is in customer bill
	{
 		if(c.customer_bill_no==c_bill_no && c.product_name.equals(p_name)) 
 		{
 			check++;
 		}
	}
 	if(check==0) {
 		System.out.println("    the customer  not bought the item");
 	}
 	else 
 	{
 		
 		for(Customer c:Customers)
 		{
 			
 			if(c.customer_bill_no==c_bill_no && c.product_name.equals(p_name))
 			{ 
 			 try //to check the quantity is only integer
 			  {
 				System.out.println("Enter the quantity to cancel");
 		 		int  product_Quantity=sc.nextInt();
 				if(c.purchase_qty==product_Quantity) //if the buy quantity and canceling quantity are same
 				{
 					for(Groceries g:groceries) 
					{
						if(g.getName().equals(p_name)) 
						{
							 int prev_Qty=g.getQuantity();
							 int remaining_Qty=g.setQuantity(prev_Qty+product_Quantity);
							 System.out.println("#####  Remaining Quantity of "+p_name+" in the grocery list is:  "+remaining_Qty+"  #####\n");
							// Main.displayitems(groceries);
						}
					}
 				   Customers.remove(c);
 				  System.out.println("#####  "+p_name +" is fully removed from customer bill  #####\n");
 				   break;
 			  	
 			
 			    }
 			    else if(c.purchase_qty>product_Quantity)// if the canceling quantity is less than buy quantity
 				{
 			    	 int prev_Qty1=c.getPurchase_qty();
 			    	 int  new_Qty1=c.setPurchase_qty(prev_Qty1-product_Quantity);
 			    	 c.setProduct_total_price(c.getProduct_price()*new_Qty1);
 			    	 System.out.println("#####  "+product_Quantity+" quantity of "+p_name +" is removed from  the existing customer bill  #####\n");
 			    	    
 			    	for(Groceries g:groceries) 
 					{
 						if(g.getName().equals(p_name)) 
 						{
 							 int prev_Qty=g.getQuantity();
 							 int remaining_Qty= g.setQuantity(prev_Qty+product_Quantity);
 							 System.out.println("#####  Remaining Quantity of "+p_name+" in the grocery list is:  "+remaining_Qty+"  #####\n");
 							 //Main.displayitems(groceries);
 						}
 					} 	
 				}
 			    else 
 			    {
 			    	System.out.println("     you have bought only "+c.purchase_qty+ " quantity of "+p_name);
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
 	sellorremove(Customers, groceries,totalpoints, c_name,c_no,c_bill_no);
}
public  boolean customerAlreadyContains(ArrayList<Customer> customers,LinkedList<Groceries> groceries,int c_bill_no,String p_name,int product_Quantity ) 
{
	for(Customer c:customers) 
	{
		if(c.customer_bill_no==c_bill_no) 
		{ 
			
				if(c.product_name.equalsIgnoreCase(p_name)) 
				{
					 for(Groceries g:groceries) 
					   {
						   if(g.getName().equalsIgnoreCase(p_name) && product_Quantity<=g.getQuantity() )
							{
							
								int prev_Qty=g.getQuantity();
							    int remaining_Qty=g.setQuantity(prev_Qty-product_Quantity);
	
								int prev_Qty1=c.getPurchase_qty();
							    int new_Qty1=c.setPurchase_qty(prev_Qty1+product_Quantity);   
							    c.setProduct_total_price(c.getProduct_price()*new_Qty1); 
							    	 System.out.println("#####  "+product_Quantity+" quantity of "+p_name +" is updated in the existing customer bill  #####\n");
							    	 System.out.println("#####  Remaining Quantity of "+p_name+" in the grocery list is:  "+remaining_Qty+"  #####\n");
							}
							else if(g.getName().equalsIgnoreCase(p_name) && product_Quantity>g.getQuantity() )
							{
								System.out.println(" *   we have only "+g.getQuantity() +" quantity of "+p_name);
							}
					   }
						return true;
				}
			
		}
		
	}
	return false;
}
public  void sellorremove(ArrayList<Customer> customers,LinkedList<Groceries> groceries,ArrayList<Totalpoint>  totalpoints,String c_name,String c_no,int c_bill_no) 
{
	String choice = "";
	System.out.println("do you want to sell a new  items/remove an item (sell/remove/no)");
     choice=sc.next();
	if(choice.equalsIgnoreCase("sell"))
	{
		sellitems(customers, groceries,totalpoints,c_name,c_no,c_bill_no);
	}
	else if(choice.equalsIgnoreCase("remove"))
	{
		removeitem(customers, groceries,totalpoints,c_name,c_no,c_bill_no);
	}
	else if(choice.equalsIgnoreCase("no"))
	{
		display_customer_bill(customers,groceries,totalpoints,c_name,c_no,c_bill_no);
	}
	else 
	{
		sellorremove(customers, groceries,totalpoints,c_name,c_no,c_bill_no);
	}
}


public String getCustomer_phoneno() {
	return customer_phoneno;
}
public void setCustomer_phoneno(String customer_phoneno) {
	this.customer_phoneno = customer_phoneno;
}
public float getProduct_total_price() {
	return product_total_price;
}
public void setProduct_total_price(float product_total_price) {
	this.product_total_price = product_total_price;
}
public String getCustomer_name() {
	return customer_name;
}
public void setCustomer_name(String customer_name) {
	this.customer_name = customer_name;
}
public int getPurchase_qty() {
	return purchase_qty;
}
public int setPurchase_qty(int purchase_qty) {
	return this.purchase_qty = purchase_qty;
}

public String getProduct_name() {
	return product_name;
}


public void setProduct_name(String product_name) {
	this.product_name = product_name;
}


public float getProduct_price() {
	return product_price;
}


public void setProduct_price(float product_price) {
	this.product_price = product_price;
}

public int getCustomer_bill_no() {
	return customer_bill_no;
}
public void setCustomer_bill_no(int customer_bill_no) {
	this.customer_bill_no = customer_bill_no;
}
public  void payment(float discount_price) 
{
   System.out.println("do you want to make  online payment  (yes/no)");
   String payment_mode=sc.next();
   if(payment_mode.equalsIgnoreCase("yes"))
   {
		System.out.println("please select your bank to pay");
		System.out.println("1)axis bank");
		System.out.println("2)SBI");
		System.out.println("3)Indian bank");
		System.out.println("4)ICICI BANK");
		int b=sc.nextInt();
		switch(b) {
			case 1:
				System.out.println("Welcome to AXIS bank");
				break;
			case 2:
				System.out.println("Welcome to SBI");
				break;
			case 3:
				System.out.println("Welcome to Indian bank");
				break;
			case 4:
				System.out.println("Welcome to ICICI BANK");
				break;
		}
	
				
	try {
	System.out.println("enter  the amount to pay");
	float amountpay=sc.nextFloat();
	if(amountpay==discount_price)
	{
		System.out.println("                  your payment is successfull");
		System.out.println("         Thank you !We wish you all a great success!!!!\n");
	}
	else
	{
		System.out.println("wrong amount");
	}
	}
	catch(Exception e)
	{
		e.printStackTrace();
		System.out.println("Enter only Integer/float");
	}
   }
   else 
   {
	   System.out.println("         Thank you !We wish you all a great success!!!!\n");
   }
}


@Override
public String toString() {
	return "Customer [customer_name=" + customer_name +"billno"+customer_bill_no+ ", customer_no=" + customer_phoneno + ", product_name="
			+ product_name + ", purchase_qty=" + purchase_qty + ", product_price=" + product_price
			+ ", product_total_price=" + product_total_price + ", sc=" + sc + "]";
}




}
