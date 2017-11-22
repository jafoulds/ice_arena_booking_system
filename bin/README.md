# project-team-6

## Running on your machine
[Install Maven](https://maven.apache.org/install.html)
on your machine.
Type `mvn spring-boot:run` into terminal from project
root to run the application.
Alternatively, you can run Application.java from your IDE.
The project should recompile when you change a java file.
To enable hot loading for the frontend, run `npm run watch` 
into a seperate terminal. This gets webpack to watch your javascript files
and rebundle the bundle.js file on a change.

###Lombok
To add IDE support for lombok in Intellij, go to Project Structure/Settings -> Modules -> Dependencies.
Then click the + in the bottom left corner select "Jars or directories" then select lombok.jar
located in the projects root directory.

###MongoDB
Install mongoDB from [docs](https://docs.mongodb.com/manual/installation/).
Start your db by typing `mongod` into terminal.
From there, Spring Boot should take care of the rest.

## Testing

Typing `mvn test` will run all tests as well as checkstyle.
Checkstyle results should be located in 
`./target/checkstyle-result.xml`.
