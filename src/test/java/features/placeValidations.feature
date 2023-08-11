#Author: pranav.dave@redspace.com
#Keywords Summary :

Feature: Validating the place API

 Scenario: Add Place
    Given Add place payload
    When user calls "<API>" with post http request
    Then the API call is success with status code <responseCode>
    And "<field_check>" in reponse is "<responseStatus>"
    
    Examples:
    |	API					|	responseCode	|	field_check	|	responseStatus	|	
    |	AddPlaceAPI	|	200						|	status			|	OK							|	
    