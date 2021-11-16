package demo;

import java.util.*;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("                "+"WELCOME TO OUR SUPER MARKET");
		System.out.println("******************************************************************");
		int vendor_bill_no=0;
		int customer_bill_no=0;
        Scanner sc= new Scanner(System.in);
		LinkedList<Groceries> groceries= new LinkedList<Groceries>();
		ArrayList<Customer>  customers= new ArrayList<Customer>(); 
		ArrayList<Vendor>  vendors= new ArrayList<Vendor>(); 
		ArrayList<Totalpoint>  totalpoints= new ArrayList<Totalpoint>(); 
		
		groceries.add(new Groceries(1,"boost(1kg)",10,100));
		groceries.add(new Groceries(2,"vim(small)",10,20));
		groceries.add(new Groceries(3,"maggi(1pkt)",10,12));
		groceries.add(new Groceries(4,"ponds(250g)",10,50));	
		groceries.add(new Groceries(5,"pepsi(500ml)",10,30));	
		groceries.add(new Groceries(6,"boost(500g)",10,50));
		groceries.add(new Groceries(7,"maida+rava(1kg)",10,130));
		while(true){
			displayitems(groceries);
			System.out.println("1)Vendors Portal \n2)Customers Portal \n3)to exit");
			Groceries g=new Groceries();
			Customer c=new Customer();
			int opt= sc.nextInt();
			switch(opt){
			case 1:
				System.out.println("enter the vendor name");
				String vendor_name=sc.next();
				try {
					if(isAlpha(vendor_name)) 
					{
						System.out.println("enter the  vendor phone number");
						String vender_phoneno= sc.next();
						

					       if(vender_phoneno.matches("[0-9]{10}$"))
					       {
					    	   vendor_bill_no=vendor_bill_no+1;
					    	   g.buyItem(vendors, groceries,vendor_name,vender_phoneno,vendor_bill_no);   
					       }
					       else
					       
					       {
					    	   System.out.println(" phone number format is wrong");
					    	
					       }
					  

					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				break;
			case 2:
			    System.out.println("enter customer name");
			    String customer_name=sc.next();
			    try {
					if(isAlpha(customer_name)) 
					{
						System.out.println("enter the  customer phone number");
						String customer_phoneno= sc.next();
						

					       if(customer_phoneno.matches("[0-9]{10}$"))
					       {
					    	   customer_bill_no=customer_bill_no+1;
					    	   c.sellitems(customers,groceries,totalpoints,customer_name,customer_phoneno,customer_bill_no);	   
					       }
					       else
					       
					       {
					    	   System.out.println(" phone number format is wrong");
					    	
					       }
					
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;              
			case 3:
				System.exit(0);
			    break;
		    case 4:
				for(Vendor v1:vendors) 
				{
					System.out.println(v1);
				}
				for(Customer c1:customers) 
				{
					System.out.println(c1);
				}
				break;
			default:
				System.out.println("    please enter a valid input\n");
			}
		}
	
	}

public static void displayitems(LinkedList<Groceries> groceries) {
		// TODO Auto-generated method stub
		System.out.println("S.NO"+"\t\t"+"NAME"+"\t\t\t"+"QUANTITY"+"\t"+"MRP RATE");
		System.out.println("******************************************************************");
		for(Groceries g:groceries) {
			 System.out.println(g.getId()+"\t\t"+g.getName()+"\t\t"+g.getQuantity()+"\t\t"+g.getMrp());
		}
		System.out.println("******************************************************************");

}
public static  boolean isAlpha(String name) {
    char[] chars = name.toCharArray();

    for (char c : chars) {
        if(!Character.isLetter(c)) {
            return false;
        }
    }

    return true;
}


}

