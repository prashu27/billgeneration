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

	@Autowired
	private MockMvc mockMvc;

	@InjectMocks
	private CustomerBillingData customerBillingData;

	CustomerBillingData mockCust = new CustomerBillingData("Employee", 1, 3, "NA");

	String mockBillDataJson = "{\"userType\" : \"CUSTOMER \" ,\"userExperience\" : \"1 \" ,\"billAmt\" : \"100\",\"billType\" : \"abc \" }";

	@Test
	public void generateBillNoDiscountTest() throws Exception {
		CustomerBillingData mockCust = new CustomerBillingData("Employee", 1, 3, "NA");
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/store/bill")
				.accept(MediaType.APPLICATION_JSON).content(mockBillDataJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(request).andReturn();
		MockHttpServletResponse response = result.getResponse();
		System.out.println(response.getContentAsString());
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test
	public void generateBillEmployeeDiscountTest() throws Exception {

		String mockBillDataJson1 = "{\"userType\" : \"EMPLOYEE\" ,\"userExperience\" : \"1 \" ,\"billAmt\" : \"100\",\"billType\" : \"abc \" }";

		CustomerBillingData mockCust = new CustomerBillingData("Employee", 1, 3, "NA");
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/store/bill")
				.accept(MediaType.APPLICATION_JSON).content(mockBillDataJson1).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(request).andReturn();
		MockHttpServletResponse response = result.getResponse();
		System.out.println(response.getContentAsString());
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test
	public void generateBillForTwoYrsOldCustomerTest() throws Exception {

		String mockBillDataJson1 = "{\"userType\" : \"CUSTOMER\" ,\"userExperience\" : \"2\" ,\"billAmt\" : \"100\",\"billType\" : \"abc \" }";

		CustomerBillingData mockCust = new CustomerBillingData("Employee", 1, 3, "NA");
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/store/bill")
				.accept(MediaType.APPLICATION_JSON).content(mockBillDataJson1).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(request).andReturn();
		MockHttpServletResponse response = result.getResponse();
		System.out.println(response.getContentAsString());
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test
	public void generateBillForDefaultDiscountTest() throws Exception {

		String mockBillDataJson1 = "{\"userType\" : \"CUSTOMER\" ,\"userExperience\" : \"1\" ,\"billAmt\" : \"120\",\"billType\" : \"abc \" }";

		CustomerBillingData mockCust = new CustomerBillingData("Employee", 1, 3, "NA");
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/store/bill")
				.accept(MediaType.APPLICATION_JSON).content(mockBillDataJson1).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(request).andReturn();
		MockHttpServletResponse response = result.getResponse();
		System.out.println(response.getContentAsString());
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test
	public void generateBillForGroceryDiscountTest() throws Exception {

		String mockBillDataJson1 = "{\"userType\" : \"CUSTOMER\" ,\"userExperience\" : \"1\" ,\"billAmt\" : \"120\",\"billType\" : \"GROCERY\" }";

		CustomerBillingData mockCust = new CustomerBillingData("Employee", 1, 3, "NA");
		// Mockito.when(Mockito.any(CustomerBillingData.class)).thenReturn(mockCust);
		// Mockito.when(Mockito.anyInt()).thenReturn(45);
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/store/bill")
				.accept(MediaType.APPLICATION_JSON).content(mockBillDataJson1).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(request).andReturn();
		MockHttpServletResponse response = result.getResponse();
		System.out.println(response.getContentAsString());
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	// User will get below response while running giving the Amt as 0.0
	/*
	 * Status = 400 Error message = null Headers =
	 * {Content-Type=[application/json;charset=UTF-8]} Content type =
	 * application/json;charset=UTF-8 Body =
	 * {"timestamp":1572294382369,"message":"billAmt 0.0","details":
	 * "uri=/store/bill"}
	 */
	@Test
	public void generateBillForZeroAmtPayTest() throws Exception {

		String mockBillDataJson1 = "{\"userType\" : \"CUSTOMER\" ,\"userExperience\" : \"1\" ,\"billAmt\" : \"0\",\"billType\" : \"GROCERY\" }";

		CustomerBillingData mockCust = new CustomerBillingData("Employee", 1, 3, "NA");
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/store/bill")
				.accept(MediaType.APPLICATION_JSON).content(mockBillDataJson1).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(request).andReturn();
		MockHttpServletResponse response = result.getResponse();
		System.out.println(response.getContentAsString());
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatus());
	}

}