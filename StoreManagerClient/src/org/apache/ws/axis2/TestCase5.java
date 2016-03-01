package org.apache.ws.axis2;

import static org.junit.Assert.*;
import org.apache.ws.axis2.TrackingStub;
import org.apache.ws.axis2.TrackingStub.TrackProducts;

import org.junit.After;
import org.junit.Test;

public class TestCase5 {


	@Test
	public void TestStock()
	{
		assertTrue(TestStoreStock()==true);
	}
	boolean TestStoreStock()
	{	
		String[] ar=null;
			try{
		int sID=1;
		TrackingStub obj1=new TrackingStub();
		TrackProducts trackProducts0=new TrackProducts();
	    trackProducts0.setStoreID(sID);
	    ar= obj1.trackProducts(trackProducts0).get_return();
	    if(ar.length>0)
	    {
	    System.out.println("The following products are low on stock:");
	    for (int i=0;i<ar.length;i++)
	    {
	    	if(ar[i]!=null)
	    	{
	    		String x=ar[i];
	    		System.out.println("ProductID: "+x);
	    		//out.println("<html><body><form name='form1' action='orderProduct.jsp' method='post'><a href='orderProduct.jsp'>Click here to order product this product</a></body></html>");
	    		System.out.println("<html><body><a href='orderProduct.jsp?param="+x+"&param1="+sID+"'>click</a></body></html>");
	    	}
	    }
	    }
	    else
	    System.out.println("No product is low on stock :)");	
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