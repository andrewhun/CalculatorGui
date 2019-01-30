# CalculatorGui
This is my first JavaFx GUI application. It is a (very basic) calculator, capable of performing the four basic operations.

Here is some useful info regarding the application:
- It is a Maven project, using a basic Maven archetype for its layout
- I used IntelliJ as my IDE, however I could not get my tests to run outside the IDE (in the command line, using Maven). Thus, I decided to upload this version, which does work in such a setup. I did this in order to ensure that Travis CI can run the tests as well.
- It uses an FXML file, calculator.fxml for the graphic interface. I created it using Intellij's built-in Scene Builder.
- You can find a pretty detailed rundown of the program's design at the top of the Controller.java file. Additional details can be found in the other class-files.

Some valuable sources of information:
- Tutorial for creating a modular JavaFx project with the help of Maven: https://dzone.com/articles/javafx-on-jdk-11
- Dr Brian Fraser's YouTube channel (containing quality material on a wide range of programming topics, including Java): https://www.youtube.com/user/DrBFraser
- Margret Posch's YouTube channel (found a really clear and concise description of enumerated types from her, love her style of explanation, would definitely recommend checking her channel out): https://www.youtube.com/user/MargretPosch
- A tutorial on JavaFx applications with multiple controllers and FXML files: https://www.youtube.com/watch?v=osIRfgHTfyg&t=457s
