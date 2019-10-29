package com.xebia.assessment.BillGeneration;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.xebia.assessment.BillGeneration.model.CustomerBillingData;


/**
 * @author Prashansa.shukla
 *
 * BillGenerationTests Class contains Junit test cases for all the possible scenarios to
 *         generate a bill
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BillGenerationTests {

	private static final  org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(BillGenerationTests.class);


	@Autowired
	private MockMvc mockMvc;

	@InjectMocks
	private CustomerBillingData customerBillingData;

	CustomerBillingData mockCust = new CustomerBillingData("Employee", 1, 3, "NA");

	String mockBillDataJson = "{\"userType\" : \"CUSTOMER \" ,\"userExperience\" : \"1 \" ,\"billAmt\" : \"100\",\"billType\" : \"abc \" }";

	/**
	 * @throws Exception
	 * generateBillNoDiscountTest to test the CUSTOMER  discount when userExp<2
	 */
	@Test
	public void generateBillNoDiscountTest() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/store/bill")
				.accept(MediaType.APPLICATION_JSON).content(mockBillDataJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(request).andReturn();
		MockHttpServletResponse response = result.getResponse();
		System.out.println(response.getContentAsString());
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	/**
	 * @throws Exception
	 * generateBillEmployeeDiscountTest to test the EMPLOYEE  discount when userExp<2
	 */
	@Test
	public void generateBillEmployeeDiscountTest() throws Exception {

		String mockBillDataJson1 = "{\"userType\" : \"EMPLOYEE\" ,\"userExperience\" : \"1 \" ,\"billAmt\" : \"100\",\"billType\" : \"abc \" }";

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/store/bill")
				.accept(MediaType.APPLICATION_JSON).content(mockBillDataJson1).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(request).andReturn();
		MockHttpServletResponse response = result.getResponse();
		System.out.println(response.getContentAsString());
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	/*
	 * @throws Exception
	 * generateBillForTwoYrsOldCustomerTest to test the CUSTOMER  discount when userExp>2
	 */
	@Test
	public void generateBillForTwoYrsOldCustomerTest() throws Exception {

		String mockBillDataJson1 = "{\"userType\" : \"CUSTOMER\" ,\"userExperience\" : \"2\" ,\"billAmt\" : \"100\",\"billType\" : \"abc \" }";

		CustomerBillingData mockCust = new CustomerBillingData("Employee", 1, 3, "NA");
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/store/bill")
				.accept(MediaType.APPLICATION_JSON).content(mockBillDataJson1).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(request).andReturn();
		MockHttpServletResponse response = result.getResponse();
		logger.info( "Response content: " +response.getContentAsString());
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	/**
	 * @throws Exception
	 * generateBillForDefaultDiscountTest for CUSTOMER and Bill Amount >100
	 */
	@Test
	public void generateBillForDefaultDiscountTest() throws Exception {

		String mockBillDataJson1 = "{\"userType\" : \"CUSTOMER\" ,\"userExperience\" : \"1\" ,\"billAmt\" : \"120\",\"billType\" : \"abc \" }";

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/store/bill")
				.accept(MediaType.APPLICATION_JSON).content(mockBillDataJson1).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(request).andReturn();
		MockHttpServletResponse response = result.getResponse();
		logger.info( "Response content: " +response.getContentAsString());
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	/**
	 * @throws Exception
	 * generateBillForGroceryDiscountTest when  BillType=Grocery
	 */
	@Test
	public void generateBillForGroceryDiscountTest() throws Exception {

		String mockBillDataJson1 = "{\"userType\" : \"CUSTOMER\" ,\"userExperience\" : \"1\" ,\"billAmt\" : \"120\",\"billType\" : \"GROCERY\" }";

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/store/bill")
				.accept(MediaType.APPLICATION_JSON).content(mockBillDataJson1).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(request).andReturn();
		MockHttpServletResponse response = result.getResponse();
		System.out.println(response.getContentAsString());
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	/**
	 * @throws Exception
	 * generateBillForZeroAmtPayTest when  bill Amt is 0.0
	 */
	@Test
	public void generateBillForZeroAmtPayTest() throws Exception {

		String mockBillDataJson1 = "{\"userType\" : \"CUSTOMER\" ,\"userExperience\" : \"1\" ,\"billAmt\" : \"0\",\"billType\" : \"GROCERY\" }";
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/store/bill")
				.accept(MediaType.APPLICATION_JSON).content(mockBillDataJson1).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(request).andReturn();
		MockHttpServletResponse response = result.getResponse();
		System.out.println(response.getContentAsString());
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
	}

}
