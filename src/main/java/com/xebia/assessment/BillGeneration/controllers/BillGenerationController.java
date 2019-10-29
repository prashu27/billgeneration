package com.xebia.assessment.BillGeneration.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.xebia.assessment.BillGeneration.exception.InvalidBillException;
import com.xebia.assessment.BillGeneration.model.CustomerBillingData;
import com.xebia.assessment.BillGeneration.service.UserDiscountService;

/**
 * @author prashansa.shukla
 *
 *         BillGenerationController class has all the URI mapping and the
 *         business logic
 * 
 */
@RestController
public class BillGenerationController {

	@Autowired
	UserDiscountService discountService;

	private static final  Logger logger = LoggerFactory.getLogger(BillGenerationController.class);

	public BillGenerationController() {
	}

	/**
	 * this method will execute first whenever the user will call '/store/bill' URI
	 * the core application will kicks in when the below method get called
	 * 
	 * @param billData
	 * @return
	 */
	@GetMapping("/store/bill")
	public CustomerBillingData getNetPayableAmt(@RequestBody CustomerBillingData billData) {
		logger.info("request  details :" + billData);
		
		if (billData.getBillAmt() == 0) {
			throw new InvalidBillException("billAmt " + billData.getBillAmt());
		}
		
		double netbillingAmount = discountService.getApplicableDiscunt(billData);
		billData.setAmtAfterDiscount(netbillingAmount);
		return billData;
	}

}