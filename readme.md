# Seisma take home test

Thank you for taking the time to review my take home coding test. 
(Describe my solution here)

### Features

### Assumptions

- Input is in JSON format
- Input is passed as POST body at /employee/payslip
- Input is well-behaved
  - Is a list/array of employees
  - Employees don't come with empty fields, fields of the wrong type, etc.

Employee format should be as follows:
```
 {
    firstName: String,
    lastName: String,
    annualSalary: int,
    paymentMonth: int,
    superRate: double
 }
```

- I assume paymentMonth mapping are as follows:
  - 0 -> January
  - 1 -> February
  - ...
  - 11 -> December