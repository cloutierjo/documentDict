# Document Dict

This application create dictionary files matching client and server file information.

## How to build
run `mvn clean package` or `mvn clean install` in the root folder. The jar file will be available in the "target" folder  

## How to run
run `java -jar target/documentdict-0.0.1-SNAPSHOT.jar SOURCE CLIENT_OUT SERVER_OUT`

SOURCE - The source folder for all the files
CLIENT_OUT - The destination for the client dictionary
SERVER_OUT - The destination for the server dictionary

The jar file can be moved or renamed without problems.