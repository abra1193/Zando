TC1 - Verify user can create a profile with valid data
Given the user has enter the Zalando home page
And the user taps on the Register button
And the user enter his email to register
When the user enter all his profile data(Password, Name, Lastname)
Then the user is register on the Zalando portal

Preconditions: TC1
TC2 - Verify user can login with profile data in the database
Given the user has enter the Zalando home page
And the user taps on the log in button
When the user enters a valid email and password
Then the user will be log in on the Zalando portal

Preconditions: TC2
TC3 - Verify user can update the profile information with valid data
Given the user taps on the Profile button on the Zalando home page
And the user taps on Personal details/Adressess button
And the user taps on the Edit button
When the user modify any personal field
Then the user profile information will be updated