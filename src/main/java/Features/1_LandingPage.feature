Feature: Landing Page Feature


#Scenario Outline: Successful login and navigate to all pages and signout
#Given user_enter <id> and <password>
#Then Validation Header links in home page
#Then signout
#Examples:
#				|      id         |       password      |
#				| "Individual_id_user01" | "Individual_password_user01" |
##    			|"Ogranization_id_user01"|"Ogranization_password_user01"|
## 				|"Employe_id_user01"     |"Employe_password_user01"     |

Scenario: Validate Login with excel data
Then login from excel


