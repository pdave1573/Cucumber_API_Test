#Author: Pranav Dave
#Keywords Summary : Validating the place API

Feature: Validating the place API
 
 @Scenario1	
 Scenario: Add Place
    Given Add place payload
    When user calls "<API>" with "<httpMethod>" http request
    Then the API call is success with status code <responseCode>
    And "<field_check>" in reponse is "<responseStatus>"
    And verify the place_id maps to "<name>"
    
    Examples:
    |	API					|	httpMethod	|	responseCode	|	field_check	|	responseStatus	|	name			|
    |	AddPlaceAPI	|	Post				|	200						|	status			|	OK							|	TestData1	|
    |	AddPlaceAPI	|	Post				|	200						|	status			|	OK							|	TestData2	|
 
 @Scenario1
 Scenario: Get Place
    Given Get place at "<name>"
    When user calls "<API>" with "<httpMethod>" http request
    Then the API call is success with status code <responseCode>
    
    Examples:
    |	API					|	httpMethod	|	responseCode	|	name			|
    |	GetPlaceAPI	|	Get					|	200						|	TestData1 |
    |	GetPlaceAPI	|	Get					|	200						|	TestData2 |
 
 @Scenario1   
 Scenario: Delete Place
    Given Delete place at "<name>"
    When user calls "<API>" with "<httpMethod>" http request
    Then the API call is success with status code <responseCode>
    And "<field_check>" in reponse is "<responseStatus>"
    
    Examples:
    |	API							|	httpMethod	|	responseCode	|	field_check	|	responseStatus	|	name			|
    |	DeletePlaceAPI	|	Post				|	200						|	status			|	OK							|	TestData1 |
    |	DeletePlaceAPI	|	Post				|	200						|	status			|	OK							|	TestData2 |
    
 @Scenario2
 Scenario: Add Place with custome Data Set: <name>
    Given Add place payload with "<name>" "<language>" "<website>"
    When user calls "<API>" with "<httpMethod>" http request
    Then the API call is success with status code <responseCode>
    And "<field_check>" in reponse is "<responseStatus>"
    And verify the place_id maps to "<name>"
   #And this is test
    
    Examples:
    |	name					|	language	| website					|	API					|	httpMethod	|	responseCode	|	field_check	|	responseStatus	|	name1			|
    |	Toronto				|	Gujarati	|	www.google.com	|	AddPlaceAPI	|	Post				|	200						|	status			|	OK							|	TestData1	|
    |	Vancouver			|	English		|	www.test.com		|	AddPlaceAPI	|	Post				|	200						|	status			|	OK							|	TestData2	|
    #|	FailPlace			|	Tamil			|	www.tester.com	|	FailPlaceAPI|	Fail				|	200						|	status			|	OK							|	TestData3	|
 
 @Scenario2   
 Scenario: Get Place Data Set: <name>
    Given Get place at "<name>"
    When user calls "<API>" with "<httpMethod>" http request
    Then the API call is success with status code <responseCode>
    
    Examples:
    |	API					|	httpMethod	|	responseCode	|	name			|
    |	GetPlaceAPI	|	Get					|	200						|	Toronto		|
    |	GetPlaceAPI	|	Get					|	200						|	Vancouver	|
    