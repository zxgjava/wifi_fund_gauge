Feature: About user operation Example of a feature file

  Scenario: Get user json object
    Given user json object
    """
    {
	  "code": "001",
	  "name": "001_name"
    }
    """ 
    When I get rest user json
    Then the user should be
    """
    {
	  "code": "001",
	  "name": "001_name"
    }
    """ 