package demo;

import java.util.ArrayList;

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
	
	public  void customerdetails(ArrayList<Totalpoint>  totalpoints,String cName,String cNumber, float total) 
	{
       float point=total/10;
      try {
		if(repeating_customer(totalpoints,cName,cNumber,point)) 
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
	public  boolean repeating_customer(ArrayList<Totalpoint>  totalpoints,String c_name,String c_no,float point)
	{
		
		for(Totalpoint t:totalpoints)
		{
			if(t.customerPhoneNo.equalsIgnoreCase(c_no))
			{
				float prev_points=t.getPoints();
				float new_points=t.setPoints(prev_points+point);
				 if(new_points>=100)
				  {
					 System.out.println("         thank you "+c_name+" for being as a valuable customer \n   we are awarding you a small gift for your constant bonding  with us\n\n");
				  }
				 return true;
			}
		}
		
		return false;
		
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
