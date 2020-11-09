Feature: Login Feature

@login
Scenario Outline: sample datadriven scenario
Given user_enter <id> and <password>
Examples:
				|      id         |       password      |
				| "user01" | "user01" |
    			|"id_user01"|"password_user01"|
 				|"id_user01"     |"password_user01"     |

@tag01 @tag02
Scenario: Automation Scenario01
Given Automation test01

@tag02 @tag03
Scenario: Automation Scenario02
Given Automation test02

@tag03 @tag02
Scenario: Automation Scenario03
Given Automation test03

@tag01 
Scenario: Automation Scenario04
Then Automation test04

@tag02
Scenario: Automation Scenario05
Then Automation test05

@tag03
Scenario: Automation Scenario06
Then Automation test06