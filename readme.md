# Seisma Take Home Test

Thank you for taking the time to review my take home coding test. 
This program takes a list of employees as json input via a POST body and returns
a json of employee payslips. It's written with Spring Boot, has CI/CD with GitHub actions,
and its docker image is hosted on AWS ECS at a public endpoint.

### Run Instructions
#### Public API endpoint
http://ec2-3-90-37-183.compute-1.amazonaws.com:8080/employee/payslip
- **Expects**: POST request with a JSON body.
  - JSON body should have a **well-behaved** list of Employees

#### Local
Note: Tested on JDK 17. Older versions of Java may not be compatible.
1) mvn test && mvn spring-boot:run

### Features
1. REST API written with Spring Boot
2. Uses GitHub Actions to automate Unit tests, End-to-End tests using Nodejs and Jest,
   building a Docker image, and pushing to ECR upon a commit on
   the Main branch.
3. Image deployed on ECS at the endpoint specified above

### Assumptions

- Input is in JSON format
- Input is passed as POST body at /employee/payslip
- Input is well-behaved
  - Body is not empty
  - Body is a list/array of employees
  - No fields are null/empty
  - Fields follow the constraints below:

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
- The Payslip output format are as follows:
```
  {
    employee: {
      firstName: String,
      lastName: String,
      annualSalary: int (0 <= annualSalary),
      paymentMonth: int (0 <= paymentMonth < 12),
      superRate: double (0 <= superRate <= 0.5)
    },
      fromDate: String (e.g. "01 February")
      toDate: String (e.g."28 February")
      grossIncome: int
      incomeTax: int
      superannuation: int
      netIncome: int
  }
```
- fromDate and toDate are based on the current year, which means that
  if it's a leap year this year, toDate for the month of February would be
  "29 February" instead of "28 February"

### Current Limitations and Possible Improvements
- No HTTPS support; should not be used with real Employee data
- Default DNS; difficult to remember endpoint
- When GitHub Actions build and push Docker images, the "latest"
  tag is always used. 
  - Bad practice; Difficult to revert to previous versions since new images
    replace the old ones with the same tag when pushed
  - Should: Set up automatic version incrementation for tagging purposes
- ECS instance does not automatically re-deploy upon new image being pushed
  to ECR.
  - Possible solution: <br>
    aws ecs update-service --cluster (cluster-name) --service (service-name)
    --force-new-deployment --region (region)
  - force-new-deployment lets us redeploy images with the same tag without having
    to create a new revision of task definitions
  - This solution is of course incompatible if we were to swap to incrimental version tagging
- Default security group was used to open EC2 port to the public
  - Should create a new security group instead specifically for that EC2 instance
    in order to avoid unintentionally opening endpoints to the public for a different
    EC2 instance that also uses default.
- Unit test and E2E test should be able to load .csv or other file types that contain testing information
  (input and expected output) and run them as test cases. Much more efficient than handwriting test cases.