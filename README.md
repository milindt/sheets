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
1. I started with a high level failing [Integration test](https://github.com/milindt/sheets/blob/master/src/test/java/com/milindt/sheets/SheetsApplicationTests.java), which would just check if application context loads or not. Note: This was later converted into a full blown integration test to verify the very rqeuirement.
2. Integration tests take a lot of time to load, hence to move towards developing the APIs, I chose [Controller tests](https://github.com/milindt/sheets/blob/master/src/test/java/com/milindt/sheets/controller/SheetsControllerTest.java), which fall in between integration and unit tests. We can test the web layer, while mocking the service layer. Add sharings API was implemented first.
3. Since service layer typically has the domain logic, I used [Junit 5 tests](https://github.com/milindt/sheets/blob/master/src/test/java/com/milindt/sheets/service/SheetsServiceTest.java) to verify the validation requirements from the layer. If you see the tests, you could see the requirement are being captured through the tests themselves and it will be a living and running proof of the requirements being met. Note: Each test case and the corresponding implementation was done in iterations, this gave me confidence that I am going in the right direction, while not breaking anything existing.
4. After the service layer we needed persistence, since we want this to be easily deployed, I went ahead with in memory database H2 with Spring Data JPA. Again I wanted to make sure the Sharing repository works, before integrating it and guess what, Spring boot has a support to test that too! Enter [Data JPA Test](https://github.com/milindt/sheets/blob/master/src/test/java/com/milindt/sheets/repository/SharingRepositoryTest.java). Note: Since all the annotations in the Sharing entity are from javax.persistance, we can easily switch the JPA implemenation in future to say Hibernate or Apache OpenJPA etc. 
5. Did a few more iterations of controller and unit tests to integrate JPA and add get all sharings API call.
6. Modified the [Integration test](https://github.com/milindt/sheets/blob/master/src/test/java/com/milindt/sheets/SheetsApplicationTests.java) to test our application end to end.

## Usage info
* Take the project checkout using `git clone https://github.com/milindt/sheets.git`
* Go to the project root and perform first time dependency download/application sanity using `mvn clean test` (Note: This requires Maven and internet connection, this step might take some time when launched for the first time)
* Launch the application using `mvn spring-boot:run`. This will launch the applicatio and print the port to be used.
* Using a HTTP client like Postman make following **POST** call: `http://localhost:8080/sheets/sharing` with this **JSON** body:
```json
{
    "selections": [
        "Assumptions!A1",
        "Assumptions!A2"
    ],
    "emailIds": [
        "test@mail.com",
        "milind.takawale@gmail.com"
    ]
}
```
This should return you the same JSON as output with HTTP code 200 OK
* Using a HTTP client like Postman make following **POST** call: `http://localhost:8080/sheets/sharing`. This should return you list of all of the sharings saved.
* Alternatively you can use [this postman collection](https://www.getpostman.com/collections/2622e6194e5a77ed5efe)!

## QnA

*If you had to generate a new document every time a sharing is added/modified, where would you put that piece of code? What changes
would have to be done to your project?*
> This would definitely make me think about my current choice of database. I would definitely want to get the data out of the memory and on to the disk, as soon as possible. We may even think about a segregated, distributed storage so that the machine hosting the application does not suffer.
> Since we have already maintained the flexibility of switching the database implementations easily this change won't be that difficult to accomodate. A new repository for Sheets will be needed though.

*How was your API design process and what was your motivation on the choice of patterns?*
> I tried to follow REST for the API endpoint developement.
> Since I followed TDD, not just as test first approach but as way to find design insigths by staying true to the requirements, I found myself refactoring the validations code a bit. Instead of hard coding the list of valid sheet names and the sheet name regex, I used strategry pattern to provide these details from outside. This means we can easily change these settings/configuration without changing much of the code. This sepration was evident when we defined a separate stratergy for test cases, which is always a good practice to follow.
