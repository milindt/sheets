# sheets
An API service which lets you share your Sheets selections with recepients

## Technology stack
* Java 1.8
* Spring Boot 2.1.5
* Junit 5.4.2
* Mockito 2.23.4
* Maven 3.5.3

## Notes
* I wanted to demonstrate how I typically like to work, hence I used Test Driven Developement to drive the entire project
* Spring boot was a clear choice because API developement is very easy and fast, testing support is incredible and since it comes with Tomcat 8.5, we can easily deploy the application locally as well(more on that later)

## Approach
1. I started with a high level failing [integration test](https://github.com/milindt/sheets/blob/master/src/test/java/com/milindt/sheets/SheetsApplicationTests.java), which would just check if application context loads or not. Note: This was later converted into a full blown integration test to verify the very rqeuirement.
2. Integration tests take a lot of time to load, hence to move towards developing the APIs, I chose [controller tests](https://github.com/milindt/sheets/blob/master/src/test/java/com/milindt/sheets/controller/SheetsControllerTest.java), which fall in between integration and unit tests. We can test the web layer, while mocking the service layer. Add sharings API was implemented first.
3. Since service layer typically has the domain logic, I used [Junit 5 tests](https://github.com/milindt/sheets/blob/master/src/test/java/com/milindt/sheets/service/SheetsServiceTest.java) to verify the validation requirements from the layer. If you see the tests, you could see the requirement are being captured through the tests themselves and it will be a living and running proof of the requirements being met.
