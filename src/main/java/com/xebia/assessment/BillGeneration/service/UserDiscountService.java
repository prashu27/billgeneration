package com.xebia.assessment.BillGeneration.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import static com.xebia.assessment.BillGeneration.constants.Constants.EMPLOYEE_DISC;
import static com.xebia.assessment.BillGeneration.constants.Constants.AFF_EMPLOYEE_DISC;
import static com.xebia.assessment.BillGeneration.constants.Constants.OTH_DISC;
import  com.xebia.assessment.BillGeneration.model.CustomerBillingData;

/**
 * @author prashansa.shukla
 *
 *UserDiscountService has been create to perform business calculation related to discounts
 *
 */
@Component
@Service
public class UserDiscountService {

	public UserDiscountService() {

	}

	/**
	 * @param data
	 * @return
	 * this method contains the core logic to  calculate the discount
	 */
	public double getApplicableDiscunt(CustomerBillingData data ) {
		double discountedAmt;

		if(data.getBillType().equalsIgnoreCase("grocery"))
		{
			return data.getBillAmt();
		}
		else {
			switch(data.getUserType()) {
			case "EMPLOYEE" :
				discountedAmt= (data.getBillAmt()*EMPLOYEE_DISC)/100;
				break;

			case "AFLT_EMPLOYEE":
				discountedAmt= (data.getBillAmt()*AFF_EMPLOYEE_DISC)/100;
				break;

			case "CUSTOMER":
				discountedAmt= getCustomerLevelDiscount(data.getBillAmt(),data.getUserExperience());
				break;

			default : 
				return data.getBillAmt();
			}

		}
		return  discountedAmt;	

	}

	/**
	 * @param billAmt
	 * @param exp
	 * @return
	 *  
	 *  Logic for customer level discount has been given in  the method
	 */
	private double getCustomerLevelDiscount(double billAmt, double exp) {
		double discountedVal;
		if(exp<2) {
			discountedVal = getDefaultDiscount(billAmt);
		}
		else {
			discountedVal = (billAmt*OTH_DISC)/100;
		}
		
		return discountedVal;
	}
	
	
	/**
	 * @param billAmt
	 * @return
	 * 
	 * Logic for Default level discount has been given in  the method
	 */
	private double getDefaultDiscount(double billAmt) {

		billAmt = billAmt - (((int)billAmt/100)*5);

		return billAmt;
	}

}
