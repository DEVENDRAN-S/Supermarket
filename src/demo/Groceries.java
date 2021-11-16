package demo;

import java.util.*;

public class Groceries extends Vendor 
{
  private int grocery_id;
  private String grocery_name="";
  private int grocery_quantity;
  private float grocery_mrp;
 Scanner sc=  new Scanner(System.in);
  public Groceries() {
	  
  }//empty constructor for  adding new object to list 
  
public Groceries(int grocery_id,String grocery_name,int grocery_quantity,float grocery_mrp) {
	this.grocery_id =grocery_id ;
	this.grocery_name = grocery_name;
	this.grocery_quantity = grocery_quantity;
	this.grocery_mrp = grocery_mrp;
}// constructor for create existing object with parameters.

public   void buyItem(ArrayList<Vendor>  Vendors,LinkedList<Groceries> groceries,String v_name,String v_no,int v_billno)
{
	
	System.out.println("enter the product name:");
	String p_name=sc.next();
	
 try 
  {
	 System.out.println("enter Quantity to add");
	   int  product_Quantity=sc.nextInt();
	if(groceryAlreadyContain(Vendors,groceries,v_name,v_no,p_name,product_Quantity,v_billno)) 
	{
		System.out.println("grocery list is updated");
	}
	else 
	{

		System.out.println("enter the MRP Price:");
		 float product_mrp=sc.nextFloat();
		 Groceries last_object= groceries.getLast();
		 int prev_id=last_object.getId();
		int  Item_no= prev_id+1;
       groceries.add(new Groceries(Item_no,p_name,product_Quantity,product_mrp));
       
       System.out.println("Enter the  vendor price for individual quantity");
         float v_item_price=sc.nextFloat();
		 float v_item_total_price=v_item_price*product_Quantity;
       Vendors.add(new Vendor(v_name,v_no,p_name,product_Quantity,v_item_price,v_item_total_price,v_billno));
   	   System.out.println("#####  "+product_Quantity+" quantity of "+p_name +" is added to Vendor bill  #####\n");
   	   System.out.println("#####  New item "+p_name+" with quantity "+product_Quantity+" is added to  the grocery list  #####\n");
       
	}
   }
     catch (InputMismatchException e)
     {
     e.printStackTrace();
     System.err.println("Entered value is not an integer");
     }
	//Main.displayitems(groceries);
      buyOrRemove(Vendors, groceries,v_name,v_no,v_billno);
	
}
public  boolean groceryAlreadyContain(ArrayList<Vendor>  vendors,LinkedList<Groceries> groceries,String v_name,String v_no,String p_name, int  product_Quantity,int v_billno)
{
	for( Groceries g:groceries) 
	{
		if(g.getName().equalsIgnoreCase(p_name))
		{
			int prev_Qty=g.getQuantity();
			int remaining_Qty=g.setQuantity(prev_Qty+product_Quantity);
			 if(vendorAlreadySells(vendors,v_billno,p_name,product_Quantity) )
			 {
				 System.out.println("#####  Remaining Quantity of "+p_name+" in the grocery list is:  "+remaining_Qty+"  #####\n");
				 System.out.println("vendor bill is updated");
			 }
			 else
			 {
				 System.out.println("Enter the  vendor price for individual quantity");
				 float v_item_price=sc.nextFloat();
				 float v_item_total_price=v_item_price*product_Quantity;
				 vendors.add(new Vendor(v_name,v_no,p_name,product_Quantity,v_item_price,v_item_total_price,v_billno));
				System.out.println("#####  "+product_Quantity+" quantity of "+p_name +" is added to Vendor bill  #####\n");
				 System.out.println("#####  Remaining Quantity of "+p_name+" in the grocery list is:  "+remaining_Qty+"  #####\n");
			 }

			return true;
		}
	}
   return false;
	}


public  boolean vendorAlreadySells(ArrayList<Vendor>  vendors,int v_billno,String p_name,int  product_Quantity) 
{
	for(Vendor v:vendors) 
	{
		if(v.getVendor_bill_no()==v_billno)
		{
			if(v.getPro_name().equalsIgnoreCase(p_name))
			{
				int prev_qty1=v.getPro_qty();
				int new_qty1=v.setPro_qty(prev_qty1+product_Quantity);
				v.setPro_total_price(v.getPro_price()*new_qty1);
		    	 System.out.println("#####  "+product_Quantity+" quantity of "+p_name +" is updated in the existing Vendor bill  #####\n");
				return true;
			}
		}
	}
	
	return false;
	
	
} 



public int getId() {
	return grocery_id;
}
public void setId(int grocery_id) {
	this.grocery_id = grocery_id;
}
public  String  getName() {
	return grocery_name;
}

public void setName(String  grocery_name) {
	this.grocery_name = grocery_name;
}
public int getQuantity() {
	return grocery_quantity;
}
public int setQuantity(int grocery_quantity) {
	return this.grocery_quantity = grocery_quantity;
}
public float getMrp() {
	return grocery_mrp;
}
public void setMrp(float grocery_mrp) {
	this.grocery_mrp = grocery_mrp;
}
@Override
public String toString() {
	return "Groceries id=" + grocery_id + ", name=" + grocery_name + ", quantity=" + grocery_quantity + ", mrp=" + grocery_mrp ;
}
}

