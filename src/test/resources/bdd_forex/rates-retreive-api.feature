Feature: Rate Service
  Verify Rate Service Functionalities

  Scenario: Fetch the latest rate for base currency '<baseCurrency>' (EUR)
    Given API Service is started
    When I request for the latest rate for base currency '<baseCurrency>'
    Then I should receive a list of currency rates

#  Scenario Outline: Fetch the latest rate for base currency '<baseCurrency>'
#    Given API Service is started
#    When I request for the latest rate for base currency '<baseCurrency>'
#    Then I should receive a list of currency rates
#    Examples:
#      | baseCurrency |
#      | GBP |
#      | USD |

