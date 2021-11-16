package demo;

import java.util.*;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("                "+"WELCOME TO OUR SUPER MARKET");
		System.out.println("******************************************************************");
		int vendorBillNo=0;
		int customerBillNo=0;
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
			displayItems(groceries);
			System.out.println("1)Vendors Portal \n2)Customers Portal \n3)to exit");
			Groceries g=new Groceries();
			Customer c=new Customer();
			int option= sc.nextInt();
			switch(option){
			case 1:
				System.out.println("enter the vendor name");
				String vendorName=sc.next();
				try {
					if(isAlpha(vendorName)) 
					{
						System.out.println("enter the  vendor phone number");
						String venderPhoneNo= sc.next();
						

					       if(venderPhoneNo.matches("[0-9]{10}$"))
					       {
					    	   vendorBillNo=vendorBillNo+1;
					    	   g.buyItem(vendors, groceries,vendorName,venderPhoneNo,vendorBillNo);   
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
			    String customerName=sc.next();
			    try {
					if(isAlpha(customerName)) 
					{
						System.out.println("enter the  customer phone number");
						String customerPhoneNo= sc.next();
						

					       if(customerPhoneNo.matches("[0-9]{10}$"))
					       {
					    	   customerBillNo=customerBillNo+1;
					    	   c.sellItem(customers,groceries,totalpoints,customerName,customerPhoneNo,customerBillNo);	   
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

public static void displayItems(LinkedList<Groceries> groceries) {
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

