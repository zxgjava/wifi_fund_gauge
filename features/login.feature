Feature: Login

  Scenario: Login with guest account
    Given Prepared user with username <"SuccessfulUsername"> and password <"12345">
    Then Open login page
    Then Fill login form with username <"SuccessfulUsername"> and password <"12345"> and click login
    Then Check we logged in with username <"SuccessfulUsername">