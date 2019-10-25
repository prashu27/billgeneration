package com.xebia.assessment.BillGeneration;

import static org.junit.Assert.assertEquals;

import javax.print.attribute.standard.Media;

import org.apache.http.client.methods.RequestBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.HttpStatusCodeException;

import com.xebia.assessment.BillGeneration.controllers.BillGenerationController;
import com.xebia.assessment.BillGeneration.model.CustomerBillingData;

@RunWith(SpringRunner.class)
@WebMvcTest(value = BillGenerationController.class,secure=false)
@ContextConfiguration(classes = BillGenerationApplication.class, loader = AnnotationConfigContextLoader.class)

public class BillGenerationTests {
@Autowired
private MockMvc mockMvc;

@MockBean
private CustomerBillingData customerBillingData;

@MockBean
private BillGenerationApplication  bill;

CustomerBillingData mockCust =  new CustomerBillingData("Employee", 1, 3, "NA");
	
String mockBillDataJson = "{\"userType\" : \"CUSTOMER \" ,\"userExperience\" : \"1 \" ,\"billAmt\" : \"0 \",\"billType\" : \"abc \" }";

@Test
public void generateBillTest() throws Exception {
	CustomerBillingData mockCust =  new CustomerBillingData("Employee", 1, 3, "NA");

	Mockito.when(Mockito.any(CustomerBillingData.class)).thenReturn(mockCust);
	MockHttpServletRequestBuilder request  =MockMvcRequestBuilders.get("/store/bill").accept(org.springframework.http.MediaType.APPLICATION_JSON).content(mockBillDataJson).contentType(org.springframework.http.MediaType.APPLICATION_JSON);
	
	MvcResult result  = mockMvc.perform(request).andReturn();
	MockHttpServletResponse response = result.getResponse();
	System.out.println(response.getContentAsString());
	assertEquals(HttpStatus.CREATED.value(),response.getStatus());
}


}
