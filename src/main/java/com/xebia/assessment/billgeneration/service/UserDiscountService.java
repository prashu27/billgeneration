package com.xebia.assessment.BillGeneration.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import static com.xebia.assessment.BillGeneration.constants.Constants.EmployeeDisc;
import static com.xebia.assessment.BillGeneration.constants.Constants.AFFEmployeeDisc;
import static com.xebia.assessment.BillGeneration.constants.Constants.OtherDisc;
import  com.xebia.assessment.BillGeneration.model.CustomerBillingData;

/**
 * @author prashansa.shukla
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
				discountedAmt= (data.getBillAmt()*EmployeeDisc)/100;
				break;
				
			case "AFLT_EMPLOYEE":
				discountedAmt= (data.getBillAmt()*AFFEmployeeDisc)/100;
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

	private double getCustomerLevelDiscount(double billAmt, double exp) {
		double discountedVal;
		if(exp<2) {
			discountedVal = getDefaultDiscount(billAmt);
		}
		else {
			discountedVal = (billAmt*OtherDisc)/100;
		}
		 
		
		return discountedVal;
		
		
	}

	private double getDefaultDiscount(double billAmt) {
			
		billAmt = billAmt - (((int)billAmt/100)*5);
		
		return billAmt;
		
	}
	
}
