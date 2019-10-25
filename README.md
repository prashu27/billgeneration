# billgeneration
## Table of contents
* [General info](#General info)
* [Technologies](#technologies)
* [Setup](#setup)

## General info
this project is a Retail store's bill generation project to provide discounts on certain criteria


## Prerequisites
JDK 1.8 
git setup 
Eclipse & Embedded Maven
PostMan

##Running Examples
Download the zip or clone the Git repository.
Unzip the zip file
Open Command Prompt and Change directory (cd) to folder containing pom.xml
Open Eclipse
File -> Import -> Existing Maven Project -> Navigate to the folder where you unzipped the zip
Select the right project
Choose the Spring Boot Application file (search for @SpringBootApplication)
Right Click on the file and Run as Java Application
open postman app
  run  a post  request on http://localhost:8080/store/bill
  provide below input as json body :
  {
	"userType" :	"CUSTOMER",
	"userExperience" :2,
	"billAmt" :50,
	"billType" :"NA"
}
expected Json response:
{
    "userType": "CUSTOMER",
    "userExperience": 2,
    "billAmt": 50,
    "billType": "NA",
    "amtAfterDiscount": 47.5
}

## RUN the Junits

git clone https://github.com/prashu27/billgeneration
import project to  eclipse workspace
run  maven  clean and build command
run the BillGenerationApplication
run the junit test cases

## Code Coverage  
install java code coverage eclemma 3.1.2 from  eclipse marketplace
right click on the project  explorer->coverage as ->Junit
check  the junit code coverage report
run the unit test cases and check the  code coverage

