Java Swing application for working with 2D graphics. The application is based on refactoring and implementation of new functionalities in relation to the project: https://github.com/BajicMladen/Java-students-project The main functionalities implemented through design patterns are:

1.Drawing different color shapes using the JColorChooser class from the Component library; 

2.Transparency of the middle in the shape of a donut using java.awt.Graphics2D;

3.The application is realized through the MVC architectural pattern.

4.Supporting the functionality of an external class (hexagon shape) through the use of an adapter pattern.hex.jar - https://we.tl/t-sbsuGQyv6E

5.Undo and redo of any user action, but only if functionality is available(command and protorype patterns).

6.Generating and display the log of executed commands

7.Saving the log of performed user actions as well as the entire drawing (serialization) on an external memory device. Using the strategy pattern.

8.Loading a text file containing the log of executed commands and based on the content, creating the appropriate drawing, command by command in interaction with the user, loading the complete drawing.

9.Change the position of the shape along the Z axis, To Front (position by position), To Back (position by position), Bring To Front (to the highest position), Bring To Back (to the lowest position) using Command pattern.

10.Multiple shapes selection enabled.

11.All buttons are available only when their functionality on the drawing is possible - Observer pattern.
