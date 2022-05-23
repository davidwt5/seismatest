const fetch = require("node-fetch");
const url =
  "http://ec2-3-84-66-55.compute-1.amazonaws.com:8080/employee/payslip";

test("testing endpoint", async () => {
  let body = [
    {
      firstName: "David",
      lastName: "Rudd",
      annualSalary: 60050,
      paymentMonth: 1,
      superRate: 0.09,
    },
    {
      firstName: "Ryan",
      lastName: "Chen",
      annualSalary: 120000,
      paymentMonth: 1,
      superRate: 0.1,
    },
  ];
  let expectedResponse = [
    {
      employee: {
        firstName: "David",
        lastName: "Rudd",
        annualSalary: 60050,
        paymentMonth: 1,
        superRate: 0.09,
      },
      fromDate: "01 February",
      toDate: "28 February",
      grossIncome: 5004,
      incomeTax: 922,
      superannuation: 450,
      netIncome: 4082,
    },
    {
      employee: {
        firstName: "Ryan",
        lastName: "Chen",
        annualSalary: 120000,
        paymentMonth: 1,
        superRate: 0.1,
      },
      fromDate: "01 February",
      toDate: "28 February",
      grossIncome: 10000,
      incomeTax: 2669,
      superannuation: 1000,
      netIncome: 7331,
    },
  ];

  const response = await fetch(url, {
    method: "post",
    body: JSON.stringify(body),
    headers: { "Content-Type": "application/json" },
  });
  const data = await response.json();
  expect(JSON.stringify(data)).toBe(JSON.stringify(expectedResponse));
});
