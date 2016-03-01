package org.apache.ws.axis2;

import static org.junit.Assert.*;
import org.apache.ws.axis2.CheckOrdersStub.GetOrders;

import org.junit.Test;

public class TestCase3 {

	
	@Test
	public void CheckTest()
	{
		assertTrue(CheckWarehouseManager()==true);
	}
	
	public boolean  CheckWarehouseManager()
	{
		CheckOrdersStub.StoreToWarehouseOrder[] ar = null;
	try{	
		CheckOrdersStub obj1=new CheckOrdersStub();
		GetOrders getOrders0=new GetOrders();
		getOrders0.setWarehouseId(1);
	ar= obj1.getOrders(getOrders0).get_return();
	 System.out.println("The following products are requested by the stores <br/>");
	 
	 for (int i=0;ar[i]!=null;i++)
	 {
	 	if(ar[i]!=null)
	 	{
	 		CheckOrdersStub.StoreToWarehouseOrder x= ar[i];
	 		System.out.println("<br/> ProductID: "+x.getProductId());
	 		//out.println("<html><body><form name='form1' action='orderProduct.jsp' method='post'><a href='orderProduct.jsp'>Click here to order product this product</a></body></html>");
	 		//out.println("<html><body><a href='orderProduct.jsp?param="+x+"'>click</a><br/></body></html>");
	 	}
	 	
	 	else {}
	 }
	 System.out.println("Click here to Order all the products ");
	}
	catch(Exception e)
	{
		
	} 
	if(ar!=null)
		return true;
	else
		return false;
	}

}
