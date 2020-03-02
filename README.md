BackEnd Take Home Assignment
===============

Build a set of REST APIs for doing CRUD operations to create, update and
delete a Collection.  Using Spring boot is recommended, but not required.
 
A Collection is a way to group things. This can be anything – Products, Content,
etc., A collection can be created for a particular country. E.g: A collection of
products for France. The collection has attributes like name, the list of things, the
country it is for etc., The collection has to be localized for different languages.
Some countries like the US has only English. But a country like Canada will have
in English and French.

## Assumptions
Since the assignment somewhat ambiguous, here are the assumptions I made.
#### Business assumptions
We are working with products that have content (description) that needs to be presented in several languages.
A product is defined by a code, colorCode and size.
Products can be grouped into collections defined for a country. A collection id defined by a name, country and 
products that it contains. Each collection needs to be localized for 
official languages in the country it is defined for. 
We need REST services to create, read, update, and delete a collection.

**Note:**
>This differs from the given instructions by assuming that the collection groups products only. Products contain 
content, so in a way they are grouped by that collection as well. However, if the intent was to group two different
types of objects, we would create an interface with two methods: getId() and getType() and make our objects to implement
it. This way, we could have a list of objects implementing this interface. For each type of object, we would implement 
different storage.

## Programming Considerations and Solution 
### Persistence layer framework choice
Since we don't know the business requirements for the performance, I decided to choose  as simple solution as possible.
The choice of DynamoDB is tempting. However, the Java sdk v2 requires a lot of boiler plate code and there is no framework
to help with this. One would need to create their own helper classes to avoid repetition. Not appealing for a small 
project.

The data is of a relational character and fits well with a relational model. There are well developed frameworks
that help with boiler plate code and more. The Spring Data JPA is quite appealing but perhaps an overkill. The simpler
 Spring Data JDBC seems a good choice. It is not a perfect choice. Seems like some features are still not developed,
 making it harder and more awkward  to model many-to-many relationships. Despite these shortcomings, I decided to use
 it.
 
 Using Spring Data JDBC allows use of several relational database without modification of the code. I decided to use 
 in memory version of H2 for ease of use but it can be easily exchanged for a more production suited database 
 like MySQL or Postgres.
 
 I did not use any database versioning tool like Liquibase or Flyway for this small project. However, it can be 
 easily added.
 
 The tables and initial data are created on startup from sql files.
 
 ### Choice of REST framework
 I decided to use Spring Boot and its MVC model for writing REST services. I briefly considered using Spring WebFlux. 
 However, the Spring Data JDBC is not a non blocking api like Reactive MongoDB or DynamoDB async sdk version 2.
 
 ### Model design
 The entities describing data stored in database are different from DTOs that are exposed through REST services. This 
 design helps with changes to desired format for exposing data to users of our api and making database information an
 internal part of the project.
 
### Localization 
When creating a collection, you can choose a country for the collection. The choice is given through enum that
can be modified when a new country and/or language is added. There is no way to get all the countries and 
supported languages in Java, so this has to be done manually. 
The code could be extended to use Accept-Language header and map it to Java Locale class.

### Error handling
I made an effort to make exceptions more readable and informative for api consumers. 
Error handling was done using @ExceptionHandler, @ControllerAdvice and ApiError class to have a central
point for treating exceptions and wrapping them in a nice format.

## Testing and documentation
I created some example test. However, ran out of time to do full coverage. Note that test coverage tool is not
included but normally it would be.
The tests, despite being  exhaustive, try to show how the application can be tested. The controllers are tested
using @WebMvcTest(), while the services are mocked. The services are tested, while repositories are mocked. And finally
repositories are tested using H2.

## How to build and run the project
The project uses JDK 8. 
You can build the project using gradle:
```
./gradlew clean build
```
Application will be deployed at default port 8080. You can start it from Intellij or other IDE.
Alternatively, you can deploy outside of IDE using this command:
```
java -jar takehome-0.0.1-SNAPSHOT.jar
```
takehome-0.0.1-SNAPSHOT.jar can be found in 
```
build/libs/takehome-0.0.1-SNAPSHOT.jar
```
## Using Swagger to test REST services
I included Swagger UI for ease of testing. After deployment, you can access it at
```
http://<server-name>:8080/swagger-ui.html
```
It will allow testing all the endpoints.

## Final comments
I ran out of time. For me, it was hard to create a project according to the description in a given 1-3 hours.
Perhaps my interpretation of the assignment was not correct. It would have been nice to have a better stated 
business case. However, I hope that this demonstrates my ability to write rest services.
