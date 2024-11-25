# Cryptique Password Validator

## Overview

The `Cryptique` class is a robust password validation tool designed to evaluate the security of passwords based on multiple criteria. It ensures that passwords meet a range of conditions, including complexity, uniqueness, and relevance, while avoiding common weaknesses like blacklisted words or insufficient length.

---

## Features

- **Length Validation**: Ensures the password is at least 8 characters long.
- **Uppercase and Lowercase Letters**: Requires both uppercase and lowercase letters.
- **Special Characters**: Checks for the inclusion of special characters (e.g., `!@#$%^&*`).
- **Numerical Criteria**: Verifies that the sum of numeric digits in the password matches a predefined random value.
- **Blacklist Check**: Prevents the use of commonly used or weak passwords, such as `password` or `123456`.
- **Band Name Inclusion**: Encourages creativity by requiring at least one name from a predefined list of legendary bands.
- **Course Name Inclusion**: Validates the inclusion of names from the course participant list (WI24A3).
- **Date Inclusion**: Verifies the presence of the current day and month in the password for a temporal element.
- **Debugging Logs**: Provides detailed logs for each validation step for better transparency and debugging.

---

## How It Works

The `validate` method performs the following steps:

1. **Input Validation**:
   - Takes the password string as input.
   - Checks each criterion using helper methods.

2. **Validation Criteria**:
   - Calls individual methods like `containsBandName`, `containsSpecialCharacter`, and others to evaluate specific rules.
   - Logs the status of each criterion to assist with debugging.

3. **Result**:
   - If all criteria are satisfied, returns a `ValidationResult` object indicating the password is valid.
   - Otherwise, returns a result stating the password is invalid, along with details about failing checks.

---

## Key Components

### `validate(String passwordtovalidate)`
Main validation method that orchestrates all checks and aggregates results.

### Criteria Methods
- **`containsBandName(String password)`**: Checks if the password includes a name from the list of bands.
- **`containsCapitalLetter(String password)`**: Ensures at least one uppercase letter is present.
- **`containsLowerCaseLetter(String password)`**: Ensures at least one lowercase letter is present.
- **`containsSpecialCharacter(String password)`**: Verifies inclusion of a special character.
- **`containsCourse(String password)`**: Checks for a name from the predefined course participant list.
- **`numberAdd(String password)`**: Validates if numeric digits sum up to the predefined random value.
- **`containsDate(String password)`**: Ensures the password contains the current day and month.
- **`isBlacklisted(String password)`**: Rejects passwords containing blacklisted words.

---



## Usage Example

```java
Cryptique validator = new Cryptique();
String password = "Nirvana2023!@";
ValidationResult result = validator.validate(password);

if (result.isValid()) {
    System.out.println("Password is valid: " + result.message());
} else {
    System.out.println("Password is invalid: " + result.message());
}
```
## Debugging

The application logs detailed messages for each validation step, which can help identify why a password failed. For example:

- "Password is too short!"
- "Password does not contain a special character!"
- "Good job! Your password contains a band name!"
- "Password does not contain a name from the course WI24A3!"

This output provides actionable insights for improving password creation.

---

## Extensibility

The `Cryptique` class is designed with modular methods, making it easy to:

- **Add new validation criteria**: For example, checks for dictionary words or additional custom rules.
- **Adjust existing checks**: Tailor specific requirements, such as changing the band list or course name inclusion criteria.

---

## Dependencies

- **`java.time.LocalDate`**: Used to retrieve the current day and month for date-related validations.
- **`com.cthiebaud.passwordvalidator.PasswordValidator`**: Serves as the base interface for the `Cryptique` class.
- **`java.util.Set`**: Used for implementing the blacklist functionality.

---

## Known Limitations

- **Date Dependency**: The current date check is tied to the local system's date and may not work as intended in environments with varied time zones.
- **Hardcoded Random Value**: The predefined random value for numerical validation (`zufallszahl`) is static and does not change dynamically unless manually modified.

---

## Notes

- Ensure the password does not unintentionally include blacklisted words, as this will result in validation failure.
- The validation criteria can be adjusted to meet specific security requirements by modifying the respective methods.

---

## License

This project is for educational purposes and demonstrates password validation techniques. Feel free to use and adapt it for your personal or professional projects.


