#Author: pranav.dave@redspace.com
#Keywords Summary :

Feature: Validating the place API

 Scenario: Add Place
    Given Add place payload
    When user calls "<API>" with post http request
    Then the API calls is success with status code <responseCode>
    And "<field_check>" in reponse is "<responseStatus>"
    
    Examples:
    |	API					|	responseCode	|	responseStatus	|	field_check	|
    |	AddPlaceAPI	|	200						|	OK							|	status			|