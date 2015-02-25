# FlexBoxOrderingSystem
FlexBox Ordering - Java Project for 2nd Year BSc Computer Science - Middlesex University, London

Introduction
------------
FlexBox is a company providing a unique service to customers who want a custom sized boxes to be used for packaging of goods.
To enable the service to the customers FlexBox Company is maintaining an Ordering System. The FlexBox Ordering System is a software product designed and implemented with the Java Programming Language. The core functionality and the Graphical User Interface is implemented in accordance to the Object Oriented Programming principles. The GUI will enable the customers to enter the box width, length and height, the required card grade, any additional colours or box reinforcements, a sealable top for their boxes as well as the required quantity of the configuration they have chosen.
The Ordering system validates the type of the box, and if the box can be supplied the customer will be able to see the total price of the order before continuing to the checkout. In the cases when the box cannot be supplied the system will display a warning message to the customer saying that the configuration needs to be changed.
The customer is able to add more boxes to the current order items list or to delete a box from the order items list, before continuing to the checkout.
When the customer decides to submit and pay for the order, the system will prompt for an order confirmation. When the customer confirms the order a receipt with unique number will be generated.
Once the order is completed the customer will have the option to continue shopping or to exit the ordering system.


FlexBox Ordering Assumptions
----------------------------
	The FlexBox customer needs to define the box Width, Height, Length in metres.
	The available input fields have to hold a valid value or have to be selected.
	The customer has to be notified with respective messages, if there is invalid input.
	The box colours need to be priced correctly depending on the selected radio button option.
	The box card grade needs to be priced correctly depending on the selected combo box grade.
	The box enhancements are optional and their checkboxes could be checked or not.
	The customer has to be able to see the list of boxes for the current order, using a JList component in a JScrollPane.
	The customer has to be able to add more boxes to the JList or to remove a box from the JList, with the respective buttons.
	The customer has to able to void the current order and create new one by clicking the New order button.
	The customer has to be able to exit the Ordering System by clicking the Exit button. 

Compile and run
---------------
1.	Running the program from the command line:
a.	In the command line prompt screen type in \>E: (or the drive letter where the CD-R with the source code is), and press Enter.
b.	Type in E:\>cd FlexBoxOrderingSystem to enter the source code folder 
c.	Type in E:\>FlexBoxOrderingSystem\java fos.UI
* If the java fos.UI doesn’t run the program, this means that the UI.java is not compiled. Type in and run the javac fos.UI.java command first to compile the java source code.

2.	Running the program from a batch file:
a.	Navigate to the source code folder in drive E: (or the drive letter where the CD-R with the source code is).
b.	Double-click the run.bat file
