package demo;

import java.util.ArrayList;
import java.util.LinkedList;

public interface GroceryInterface {

	void buyItem(ArrayList<Vendor> vendors, LinkedList<Groceries> groceries, String vName, String vPhoneNo, int vBillNo);
	void buyOrRemove(ArrayList<Vendor> vendors, LinkedList<Groceries> groceries, String vName, String vPhoneNo, int vBillNo);
}
