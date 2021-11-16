package demo;

import java.util.*;

public  class Groceries extends Vendor
{
  private int groceryId;
  private String groceryName="";
  private int groceryQuantity;
  private float groceryMrp;
  Scanner sc=  new Scanner(System.in);
  public Groceries() {
	  
  }//empty constructor for  adding new object to list 
  
public Groceries(int groceryId,String groceryName,int groceryQuantity,float groceryMrp) {
	this.groceryId =groceryId ;
	this.groceryName = groceryName;
	this.groceryQuantity = groceryQuantity;
	this.groceryMrp = groceryMrp;
}// constructor for create existing object with parameters.

public   void buyItem(ArrayList<Vendor>  vendors,LinkedList<Groceries> groceries,String vName,String vPhoneNo,int vBillNo)
{
	
	System.out.println("enter the product name:");
	String pName=sc.next();
	
 try 
  {
	 System.out.println("enter Quantity to add");
	   int  productQuantity=sc.nextInt();
	if(groceryAlreadyContain(vendors,groceries,vName,vPhoneNo,pName,productQuantity,vBillNo)) 
	{
		System.out.println("grocery list is updated");
	}
	else 
	{

		System.out.println("enter the MRP Price:");
		 float productMrp=sc.nextFloat();
		 Groceries lastObject= groceries.getLast();
		 int prevId=lastObject.getId();
		int  ItemNo= prevId+1;
       groceries.add(new Groceries(ItemNo,pName,productQuantity,productMrp));
       
       System.out.println("Enter the  vendor price for individual quantity");
         float vItemPrice=sc.nextFloat();
		 float vItemTotalPrice=vItemPrice*productQuantity;
       vendors.add(new Vendor(vName,vPhoneNo,pName,productQuantity,vItemPrice,vItemTotalPrice,vBillNo));
   	   System.out.println("#####  "+productQuantity+" quantity of "+pName +" is added to Vendor bill  #####\n");
   	   System.out.println("#####  New item "+pName+" with quantity "+productQuantity+" is added to  the grocery list  #####\n");
       
	}
   }
     catch (InputMismatchException e)
     {
     e.printStackTrace();
     System.err.println("Entered value is not an integer");
     }
	//Main.displayitems(groceries);
     buyOrRemove(vendors, groceries,vName,vPhoneNo,vBillNo);
	
}
public  boolean groceryAlreadyContain(ArrayList<Vendor>  vendors,LinkedList<Groceries> groceries,String vName,String vPhoneNo,String pName, int  productQuantity,int vBillNo)
{
	for( Groceries g:groceries) 
	{
		if(g.getName().equalsIgnoreCase(pName))
		{
			 int prevQty=g.getQuantity();
			 int remainingQty=g.setQuantity(prevQty+productQuantity);
			 if(vendorAlreadySells(vendors,vBillNo,pName,productQuantity) )
			 {
				 System.out.println("#####  Quantity of "+pName+" in the grocery list is updated to:  "+remainingQty+"  #####\n");
				 System.out.println("vendor bill is updated");
			 }
			 else
			 {
				 System.out.println("Enter the  vendor price for individual quantity");
				 float vItemPrice=sc.nextFloat();
				 float vItemTotalPrice=vItemPrice*productQuantity;
				 vendors.add(new Vendor(vName,vPhoneNo,pName,productQuantity,vItemPrice,vItemTotalPrice,vBillNo));
				 System.out.println("#####  "+productQuantity+" quantity of "+pName +" is added to Vendor bill  #####\n");
				 System.out.println("#####  Quantity of "+pName+" in the grocery list is updated to:  "+remainingQty+"  #####\n");
			 }

			return true;
		}
	}
   return false;
	}


public  boolean vendorAlreadySells(ArrayList<Vendor>  vendors,int vBillNo,String pName,int  productQuantity) 
{
	for(Vendor v:vendors) 
	{
		if(v.getVendorBillNo()==vBillNo)
		{
			if(v.getProName().equalsIgnoreCase(pName))
			{
				int prevQty1=v.getProQty();
				int newQty1=v.setProQty(prevQty1+productQuantity);
				v.setProTotalPrice(v.getProPrice()*newQty1);
		    	 System.out.println("#####  "+productQuantity+" quantity of "+pName +" is updated in the existing Vendor bill  #####\n");
				return true;
			}
		}
	}
	
	return false;
	
	
} 



public int getId() {
	return groceryId;
}
public void setId(int groceryId) {
	this.groceryId = groceryId;
}
public  String  getName() {
	return groceryName;
}

public void setName(String  groceryName) {
	this.groceryName = groceryName;
}
public int getQuantity() {
	return groceryQuantity;
}
public int setQuantity(int groceryQuantity) {
	return this.groceryQuantity = groceryQuantity;
}
public float getMrp() {
	return groceryMrp;
}
public void setMrp(float groceryMrp) {
	this.groceryMrp = groceryMrp;
}
@Override
public String toString() {
	return "Groceries id=" + groceryId + ", name=" + groceryName + ", quantity=" + groceryQuantity + ", mrp=" + groceryMrp ;
}

}

