package demo;

import java.util.*;


public class Totalpoint {
	private String  customerName;
	private String  customerPhoneNo;
	private float  points;
	
	public Totalpoint()
	{
		
	}
	
	public Totalpoint(String customerName, String customerPhoneNo, float points) {
		this.customerName = customerName;
		this.customerPhoneNo = customerPhoneNo;
		this.points = points;
	}
	Scanner sc=new Scanner(System.in);
	public  void customerdetails(ArrayList<Totalpoint>  totalpoints,String cName,String cNumber, float total) 
	{
       float point=total/10;
      try {
		if(repeatingCustomer(totalpoints,cName,cNumber,point)) 
		  {
			  
		  }
		  else 
		  {
			  totalpoints.add(new Totalpoint(cName,cNumber,point));
			  if(point>=100)
			  {
				  System.out.println("         thank you "+cName+" for being as a valuable customer \n   we are awarding you a small gift for your constant bonding  with us\n\n");
			  }
		  }

	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	public  boolean repeatingCustomer(ArrayList<Totalpoint>  totalpoints,String cName,String cNumber,float point)
	{
		
		for(Totalpoint t:totalpoints)
		{
			if(t.customerPhoneNo.equalsIgnoreCase(cNumber))
			{
				float prevPoints=t.getPoints();
				float newPoints=t.setPoints(prevPoints+point);
				 if(newPoints>=100)
				  {
					 System.out.println("         thank you "+cName+" for being as a valuable customer \n   we are awarding you a small gift for your constant bonding  with us\n\n");
				  }
				 return true;
			}
		}
		
		return false;
		
	}

	public  void payment(float discountPrice) 
	{
	   System.out.println("do you want to make  online payment  (yes/no)");
	   String paymentMode=sc.next();
	   if(paymentMode.equalsIgnoreCase("yes"))
	   {
			System.out.println("please select your bank to pay");
			System.out.println("1)axis bank");
			System.out.println("2)SBI");
			System.out.println("3)Indian bank");
			System.out.println("4)ICICI BANK");
			selectBank(discountPrice);
			
		
	   }
	   else 
	   {
		   System.out.println("         Thank you !We wish you all a great success!!!!\n");
	   }
	}
    public void selectBank(float discountPrice) 
    {
    	try 
      {
    	int opt=sc.nextInt();
		switch(opt) {
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
		    default:
		    	System.out.println("Please select Banks only in the list");
		    	selectBank(discountPrice);
		    	
		     }
		amountCheck(discountPrice);
      }
		catch(InputMismatchException e)
		{
			e.printStackTrace();
			System.out.println("Invalid input");
		}
		
    }
     public void amountCheck(float discountPrice) 
     {
			
		try 
	  {
		System.out.println("enter  the amount to pay");
		float amountPay=sc.nextFloat();
		if(amountPay==discountPrice)
		{
			System.out.println("                  your payment is successfull");
			System.out.println("         Thank you !We wish you all a great success!!!!\n");
		}
		else
		{
			System.out.println("wrong amount");
			amountCheck(discountPrice);
		}
	  }
		catch(InputMismatchException e)
		{
			e.printStackTrace();
			System.out.println("Entered value is not a Integer/Float");
		}
	   
     }
	
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPhoneNo() {
		return customerPhoneNo;
	}

	public void setCustomerPhoneNo(String customerPhoneNo) {
		this.customerPhoneNo = customerPhoneNo;
	}

	public float getPoints() {
		return points;
	}
	public float setPoints(float points) {
		return this.points = points;
	}

}
