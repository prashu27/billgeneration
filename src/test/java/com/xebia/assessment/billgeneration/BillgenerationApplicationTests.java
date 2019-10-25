package com.xebia.assessment.BillGeneration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.apache.catalina.connector.ClientAbortException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import com.xebia.assessment.BillGeneration.model.CustomerBillingData;
import com.xebia.assessment.BillGeneration.service.UserDiscountService;

/**
 * @author prashansa.shukla
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BillGenerationApplicationTests {
	
	public BillGenerationApplicationTests() throws Exception {
		super();
	
		//UserDiscountService discountService = new UserDiscountService();
	}
	
//	@Autowired
//	UserDiscountService discountService;
	 
 private UserDiscountService discountService;




@Before(value = "")
public void setUp() throws Exception {
	UserDiscountService discountService= new UserDiscountService();

}

	/*
	 * @Before(value = "init") public void init() { UserDiscountService
	 * discountService= new UserDiscountService(); }
	 */
	
	@org.junit.Test
	public void contextLoads() {
	}
	
	@Test
	public void getDiscountAmtForCustomerTest()
	  throws Exception {
	  
		CustomerBillingData billData = new CustomerBillingData("customer", 1, 150, "abc");
		setUp();
			double netbillingAmount = discountService.getApplicableDiscunt(billData);
		billData.setAmtAfterDiscount(145.00);
	    assertEquals(145.00, billData.getAmtAfterDiscount(), 0.02);
	}

	/**
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	@Test
	public void getDiscountAmtForEmployeeTest()
	  throws ClientProtocolException, IOException {
	  
		CustomerBillingData billData = new CustomerBillingData("Employee", 1, 150, "abc");
		double netbillingAmount = discountService.getApplicableDiscunt(billData);
		billData.setAmtAfterDiscount(145.00);
	    assertEquals(145.00, billData.getAmtAfterDiscount(), 0.02);
	}
	/**
	 * 
	 */
	@Test 
	public void testAutowired()
	{CustomerBillingData billData = new CustomerBillingData("Employee", 1, 150, "abc");
	double netbillingAmount = discountService.getApplicableDiscunt(billData);
	billData.setAmtAfterDiscount(145.00);
    assertEquals(145.00, billData.getAmtAfterDiscount(), 0.02);
		UserDiscountService u = new UserDiscountService();
		u.getApplicableDiscunt(billData);
		
	}
	
	
	
	
}
