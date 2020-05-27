# Object Oriented Software Design Team Project

## Exciting Trivia Game

### Instructions to Build and Run Projects

Java JDK version: 13.0.1

Note: Please register Java JDK to environment PATH before building or running the application.

A. Instructions to build the application:

1. Navigate to the current directory `"./ExcitingTriviaGame/src/main/java"` in your favorite command line environment.

2. Compile the java files with the following command:

`javac exciting/*.java exciting/game/*.java exciting/system/*.java exciting/gui/*.java exciting/util/*.java`

3. (Option) To build the jar package, run the following command after Step 2. Please make sure the "MANIFEST.MF" file, which indicates the main class for the jar package, is in the current directory.

`jar cfm ExcitingGame.jar MANIFEST.MF exciting/*.class exciting/game/*.class exciting/system/*.class exciting/gui/*.class exciting/util/*.class`


B. Instruction to run the application:

1. Navigate to the current directory `"./ExcitingTriviaGame/src/main/java"` in your favorite command line environment.

2. Follow the instructions above to build the application.

3. Run the application with the following command. Please make sure "questions.txt" is in the current directory.

`java exciting.ExcitingGameTester`

4. If the jar package is built, the application could be executed with the following command. Please make sure "questions.txt" is in the current directory.

`java -jar ExcitingGame.jar`
