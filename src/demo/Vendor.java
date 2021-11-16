package demo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class Vendor{
	   private String vendorName;
	   private String vendor_phoneno;
	   private String pro_name; 
	   private int pro_qty;
	   private float pro_price;
	   private float pro_total_price;
	   private int vendor_bill_no;
	   
	   public Vendor() 
	   {
		   
	   }
	public Vendor(String vendor_name,String vendor_phoneno, String pro_name, int pro_qty, float pro_price, float pro_total_price,int vendor_bill_no)
	{
		this.vendorName = vendor_name;
		this.vendor_phoneno = vendor_phoneno;
		this.pro_name = pro_name;
		this.pro_qty = pro_qty;
		this.pro_price = pro_price;
		this.pro_total_price = pro_total_price;
		this.vendor_bill_no=vendor_bill_no;
	}
	 Scanner sc=new Scanner(System.in);
	public  void displayVendorBill(ArrayList<Vendor>  vendors,String v_name,String v_no,int v_bill_no) 
	{	
		float total=0;
	 System.out.println("\n                          "+"BILLING");
	 DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/YYYY");
	 DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm:ss");
	   LocalDate D_now = LocalDate.now();
	   LocalTime T_now=LocalTime.now();
    System.out.println("\n    BILL NO  :"+v_bill_no);
	System.out.println("VENDOR NAME  :"+v_name);                    
	System.out.println("                                              Date: "+df.format(D_now));
	System.out.println("                                              Time: "+tf.format(T_now));  

	System.out.println("======================================================================");
	System.out.println("Item name"+"\t\t"+"Quantity"+"\t"+"unit cost"+"\t"+"Item Total cost");
	System.out.println("======================================================================");
	for(Vendor v:vendors) {
		if(v.vendor_bill_no==v_bill_no) {
		 System.out.println(v.pro_name+"\t\t"+v.pro_qty+"\t\t"+v.pro_price+"\t\t"+v.pro_total_price);
		 total=total+v.pro_total_price;
		}
	}
	 System.out.println("======================================================================");
     System.out.println("Total Amount   :"+total);
     System.out.println("======================================================================");
     System.out.println("         Thank you! Have a safe and Happy day!!!\n\n\n");
     
	}
	
	public  void buyOrRemove(ArrayList<Vendor>  vendors,LinkedList<Groceries> groceries,String v_name,String v_no,int v_bill_no) 
	{
		
		String choice = "";
		System.out.println("do you want to buy a new  items/remove an item  from the vendor(buy/remove/no)");
			choice=sc.next();
		if(choice.equalsIgnoreCase("buy"))
		{
		
			Groceries g = new Groceries();
			g.buyItem(vendors, groceries,v_name,v_no,v_bill_no);
		}
		else if(choice.equalsIgnoreCase("remove"))
		{
			removeProduct(vendors, groceries,v_name,v_no,v_bill_no);
		}
		else if(choice.equalsIgnoreCase("no"))
		{
			 displayVendorBill(vendors, v_name,v_no,v_bill_no); 
		}
		else 
		{
			buyOrRemove(vendors, groceries,v_name,v_no,v_bill_no);
		}
		
	}

	
	public  void removeProduct(ArrayList<Vendor>  vendors,LinkedList<Groceries> groceries,String v_name,String v_no,int v_bill_no)
	{
		
		String p_name="";
		int check=0;
		System.out.println("enter the product name to cancel");
		p_name=sc.next();
		for(Vendor v:vendors)
		{
	 		if(   v.vendor_bill_no==v_bill_no && v.pro_name.equalsIgnoreCase(p_name)) 
	 		{
	 			check++;
	 		}
		}
	 	if(check==0) {
	 		System.out.println("    we dont bought "+p_name+" from this vendor ");
	 	}
	 	else 
	 	{
	 		try 
	 		{
	 		System.out.println("Enter the quantity to cancel");
	 		int  product_Quantity=sc.nextInt();
	 		for(Vendor v:vendors)
	 		{
	 			
	 			if(  v.vendor_bill_no==v_bill_no && v.pro_name.equalsIgnoreCase(p_name))
	 			{
	 			
	 				if(v.pro_qty==product_Quantity) 
	 				{
	 					for(Groceries g:groceries) 
						{
							if(g.getName().equals(p_name) && v.pro_qty==g.getQuantity()) //if you want to cancel full quantity you added as new
							{
								int id=g.getId();//for updating in the item id 
								for(Groceries g1:groceries) 
								{
									if(g1.getId()>id) 
									{
										int prev_id=g1.getId();
										g1.setId(prev_id-1);
									}
								} 
								groceries.remove(g);
				 				  System.out.println("#####  "+p_name +" is fully removed from Groceries list  #####\n");
								break;
							}
							else if(g.getName().equals(p_name) && v.pro_qty<g.getQuantity())//if you want to cancel full row but if already contains at intially
							{

								 int prev_Qty=g.getQuantity();
								 int remaining_Qty=g.setQuantity(prev_Qty-product_Quantity);
								 System.out.println("#####  Remaining Quantity of "+p_name+" in the grocery list is:  "+remaining_Qty+"  #####\n");
								 
							}
						}
	 					 //Main.displayitems(groceries);
	 	 				vendors.remove(v);
	 	 				  System.out.println("#####  "+p_name +" is fully removed from Vendor bill  #####\n");
	 	 				   break;
					}
	 				else if(v.pro_qty>product_Quantity)// if you want to cancel only few quantity from you sold
	 				{
	 					 
	 			    	 int prev_Qty1=v.getPro_qty();
	 			    	 int  new_Qty1=v.setPro_qty(prev_Qty1-product_Quantity);
	 			    
	 			    	 v.setPro_total_price(new_Qty1*v.getPro_price());
	 			    	 System.out.println("#####  "+product_Quantity+" quantity of "+p_name +" is removed from  the existing Vendor bill  #####\n");
	
	 			    	    
	 			    	for(Groceries g:groceries) 
	 					{
	 						if(g.getName().equals(p_name)) 
	 						{
	 							 int prev_Qty=g.getQuantity();
	 							 int remaining_Qty=g.setQuantity(prev_Qty-product_Quantity);
	 							 System.out.println("#####  Remaining Quantity of "+p_name+" in the grocery list is:  "+remaining_Qty+"  #####\n");
	 							// Main.displayitems(groceries);
	 						}
	 					} 	
	 				}
	 			    else // if the entered quantity is more than available quantity in vendor bill
	 			    {
	 			    	System.out.println("     you have sold only "+v.pro_qty+ " quantity of "+p_name);
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
	 	   buyOrRemove(vendors, groceries,v_name,v_no,v_bill_no);
	 	}
	
	
	public String getVendor_no() {
		return vendor_phoneno;
	}
	public void setVendor_no(String vendor_no) {
		this.vendor_phoneno = vendor_no;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendor_name(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getPro_name() {
		return pro_name;
	}
	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}
	public int getPro_qty() {
		return pro_qty;
	}
	public int setPro_qty(int pro_qty) {
		return this.pro_qty = pro_qty;
	}
	public float getPro_price() {
		return pro_price;
	}
	public void setPro_price(float pro_price) {
		this.pro_price = pro_price;
	}
	public float getPro_total_price() {
		return pro_total_price;
	}
	public void setPro_total_price(float pro_total_price) {
		this.pro_total_price = pro_total_price;
	}
	public int getVendor_bill_no() {
		return vendor_bill_no;
	}
	public void setVendor_bill_no(int vendor_bill_no) {
		this.vendor_bill_no = vendor_bill_no;
	}
	@Override
	public String toString() {
		return "Vendor [vendor_name=" + vendorName + ",vendorname="+vendor_phoneno+", pro_name=" + pro_name + ", pro_qty=" + pro_qty + ", pro_price="
				+ pro_price + ", pro_total_price=" + pro_total_price + "]";
	}
}
