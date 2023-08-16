#Author: pranav.dave@redspace.com
#Keywords Summary :

Feature: Validating the place API

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
 
 Scenario: Get Place
    Given Get place at "<name>"
    When user calls "<API>" with "<httpMethod>" http request
    Then the API call is success with status code <responseCode>
    
    Examples:
    |	API					|	httpMethod	|	responseCode	|	field_check	|	responseStatus	|	name			|
    |	GetPlaceAPI	|	Get					|	200						|	status			|	OK							|	TestData1 |
    |	GetPlaceAPI	|	Get					|	200						|	status			|	OK							|	TestData2 |
    
 Scenario: Delete Place
    Given Delete place at "<name>"
    When user calls "<API>" with "<httpMethod>" http request
    Then the API call is success with status code <responseCode>
    And "<field_check>" in reponse is "<responseStatus>"
    
    Examples:
    |	API							|	httpMethod	|	responseCode	|	field_check	|	responseStatus	|	name			|
    |	DeletePlaceAPI	|	Post				|	200						|	status			|	OK							|	TestData1 |
    |	DeletePlaceAPI	|	Post				|	200						|	status			|	OK							|	TestData2 |