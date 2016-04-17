Feature: 测试一个简单的例子

	Scenario: A simple passing scenario
		Given the following animals: cow, horse, sheep
		Given the following animals table:
		  | cow   |
		  | horse |
		  | sheep |
		When I check the value
    	Then following animals should be: cow, horse, sheep
    	Then following animals table should be:
    	  | cow   |
		  | horse |
		  | sheep |