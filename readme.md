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
    annualSalary: int (0 <= annualSalary),
    paymentMonth: int (0 <= paymentMonth < 12),
    superRate: double (0 <= superRate <= 0.5)
 }
```

- I assume paymentMonth mapping are as follows:
  - 0: January
  - 1: February
  - ...
  - 11: December
- fromDate and toDate are based on the current year, implying that if it's a leap year this year then
February would have 29 days instead of 28.

build: docker build -t sheowyy/seismatest .
test: docker run -it --rm --name springboot-test sheowyy/seismatest ./mvnw test
tag: docker tag sheowyy/seismatest:latest sheowyy/seismatest:latest
push: docker push sheowyy/seismatest:latest
run: docker run sheowyy/seismatest:latest