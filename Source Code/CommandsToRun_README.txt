javac -cp .:junit-4.12.jar:hamcrest-core-1.3.jar \EdgeConvertCreateDDLTest.java
java -cp .:junit-4.12.jar:hamcrest-core-1.3.jar \org.junit.runner.JUnitCore EdgeConvertCreateDDLTest
javac -cp .:junit-4.12.jar:hamcrest-core-1.3.jar \EdgeFieldTest.java
java -cp .:junit-4.12.jar:hamcrest-core-1.3.jar \org.junit.runner.JUnitCore EdgeFieldTest