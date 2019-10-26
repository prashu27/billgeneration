package com.xebia.assessment.BillGeneration.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xebia.assessment.BillGeneration.exception.InvalidBillException;
import com.xebia.assessment.BillGeneration.model.CustomerBillingData;
import com.xebia.assessment.BillGeneration.service.UserDiscountService;

/**
 * @author prashansa.shukla
 *
 * @param <netbillingAmount>
 */
@RestController

/* @RequestMapping("/store") */
public class BillGenerationController<netbillingAmount> {
	@Autowired
	UserDiscountService discountService;
	
	public BillGenerationController() {
		
	}
	

	/*
	 * @GetMapping("/store/bill") public double getNetPayableAmt(@RequestBody
	 * CustomerBillingData billData) {
	 * 
	 * System.out.println("data IS :"+ billData); double netbillingAmount
	 * =discountService.getApplicableDiscunt(billData); return netbillingAmount;
	 * 
	 * 
	 * }
	 */

	/**
	 * this method will execute  first whenever the user  will  call '/store/bill' URI
	 * @param billData
	 * @return
	 */
	@GetMapping("/store/bill")
	public CustomerBillingData getNetPayableAmt(@RequestBody CustomerBillingData  billData) {
		
		System.out.println("data IS :"+ billData);
		if(billData.getBillAmt()==0) {
			throw new InvalidBillException("billAmt "+ billData.getBillAmt());
		}
		
		double netbillingAmount =discountService.getApplicableDiscunt(billData);
		
		 billData.setAmtAfterDiscount(netbillingAmount);
			
		 ResponseEntity<CustomerBillingData> res = new ResponseEntity<>(HttpStatus.ACCEPTED);
		
		return billData;
		
		
	}
	
	
	/**
	 * @return
	 */
	@GetMapping("/hello")
	public String helloWorld() {
			System.out.println("Yess"+(150/100)*5);
			return "Hello";
			
			
		}
	
	
	
	
	
	
}

