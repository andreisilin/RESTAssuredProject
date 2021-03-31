Feature: Validating Place API's

  @AddPlace @Regression
  Scenario Outline: Verify is Place being added using AddPlace API
    Given addPlace payload with "<name>" "<address>" "<language>"
    When user calls "AddPlaceResource" API with "POST" request
    Then response is successful with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_id is created maps to "<name>" using "GetPlaceRecourse" API

  Examples:
      |name  |address    |language  |
     # |Andrei|Sozhskaya 5|Russian-BY|
      |Leshka|Viliams 18  |Russian-EN |

    @DeletePlace @Regression
    Scenario: Verify delete functionality
      Given deletePlace payload
      When user calls "DeletePlaceResources" API with "POST" request
      Then response is successful with status code 200
      And "status" in response body is "OK"