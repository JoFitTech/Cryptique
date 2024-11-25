Overview

The Cryptique class is a robust password validation tool designed to evaluate the security of passwords based on multiple criteria. It ensures that passwords meet a range of conditions, including complexity, uniqueness, and relevance, while avoiding common weaknesses like blacklisted words or insufficient length.

Features

Length Validation: Ensures the password is at least 8 characters long.
Uppercase and Lowercase Letters: Requires both uppercase and lowercase letters.
Special Characters: Checks for the inclusion of special characters (e.g., !@#$%^&*).
Numerical Criteria: Verifies that the sum of numeric digits in the password matches a predefined random value.
Blacklist Check: Prevents the use of commonly used or weak passwords, such as password or 123456.
Band Name Inclusion: Encourages creativity by requiring at least one name from a predefined list of legendary bands.
Course Name Inclusion: Validates the inclusion of names from the course participant list (e.g., WI24A3).
Date Inclusion: Verifies the presence of the current day and month in the password for a temporal element.
Debugging Logs: Provides detailed logs for each validation step for better transparency and debugging.
How It Works

The validate method performs the following steps:

Input Validation:
Takes the password string as input.
Checks each criterion using helper methods.
Validation Criteria:
Calls individual methods like containsBandName, containsSpecialCharacter, and others to evaluate specific rules.
Logs the status of each criterion to assist with debugging.
Result:
If all criteria are satisfied, returns a ValidationResult object indicating the password is valid.
Otherwise, returns a result stating the password is invalid, along with details about failing checks.
Key Components

validate(String passwordtovalidate)
Main validation method that orchestrates all checks and aggregates results.

Criteria Methods
containsBandName(String password): Checks if the password includes a name from the list of bands.
containsCapitalLetter(String password): Ensures at least one uppercase letter is present.
containsLowerCaseLetter(String password): Ensures at least one lowercase letter is present.
containsSpecialCharacter(String password): Verifies inclusion of a special character.
containsCourse(String password): Checks for a name from the predefined course participant list.
numberAdd(String password): Validates if numeric digits sum up to the predefined random value.
containsDate(String password): Ensures the password contains the current day and month.
isBlacklisted(String password): Rejects passwords containing blacklisted words.
