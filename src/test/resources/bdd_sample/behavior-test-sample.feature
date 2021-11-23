Feature: BDD Test Sample
  Spring Boo + Cucumber : approche BDD!

  Scenario: Alimenter liste et recuperer dimension (Exemple)
    Given api service started
    When add item to list
    Then get list size
