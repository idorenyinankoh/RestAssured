Feature: Validate AddPlace API

@AddPlace 
#// uses to reference to the test incase we want to run singular tests
Scenario Outline: Validate that user can send AddPlace successfully
	Given User has API endpoint "<name>" "<language>" "<address>" "<website>"
	When user sends "AddPlaceAPI" http "post" request
	Then "status" contains in it response "OK"
	And "scope" contains in it response "APP"
	And zensure that the result of the place_id, the "<name>"  is equal to the name from "GetPlaceAPI"
	
Examples:
       | name  | language| address   |       website        | 
       | Gwen  | Ibibio  | Ikorodu   | http://gwen.com      |
       | Ankoh | Anan    | Yaba      | http://iankoh.com    |
       | Paul  | Yoruba  | Ikotun    | http://paulsure.com  |
       | Busola| Java    | Ogba      | http://bussbuss.com  |
       | Kenny | Js      | Magodo    | http://kennymoney.com|
       
@DeletePLace       
Scenario: Validate that user can Delete
	Given User has API endpoint
	When user sends "DeletePlaceAPI" http "delete" request
	Then validate that status code is ok
	And "status" is "OK"