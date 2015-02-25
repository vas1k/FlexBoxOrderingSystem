package fos;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.*;
import java.util.*;

import javax.swing.*;
import javax.swing.Box;

public class UI extends JFrame {

	JFrame guiFrame = new JFrame();

	JPanel mainPanel = new JPanel();

	JLabel topLabel = new JLabel(),
			widthLabel = new JLabel(),
			heightLabel = new JLabel(),
			lengthLabel = new JLabel(),
			gradeLabel = new JLabel(),
			coloursLabel = new JLabel(),
			enhancementsLabel = new JLabel(),
			quantityLabel = new JLabel(),
			currencyLabel = new JLabel(),
			orderListLabel = new JLabel(),
			colourError = new JLabel(),
			gradeError = new JLabel(),
			heightError = new JLabel(),
			lengthError = new JLabel(),
			quantityError = new JLabel(),
			typeError = new JLabel(),
			widthError = new JLabel(),
			totalPriceLabel = new JLabel(),
			totalLabel = new JLabel();

	JTextField widthTF = new JTextField(4),
			heightTF = new JTextField(4),
			lengthTF = new JTextField(4),
			quantityTF = new JTextField(4);

	JComboBox gradeCombo = new JComboBox();

	ButtonGroup coloursGroup = new ButtonGroup();

	Box coloursBox = Box.createVerticalBox();

	JRadioButton noColourRB = new JRadioButton(),
			oneColourRB = new JRadioButton(),
			twoColourRB = new JRadioButton();

	JCheckBox rBottomCB = new JCheckBox(),
			rCornersCB = new JCheckBox(),
			sealTopCB = new JCheckBox();

	JList orderDetails = new JList();
	JScrollPane scrollPane = new JScrollPane();

	JButton addToButton = new JButton(),
			addMoreButton = new JButton(),
			removeButton = new JButton(),
			submitButton = new JButton(),
			exitButton = new JButton(),
			newOrderButton = new JButton(),
			infoButton = new JButton();

	private String measure = "m";
	private int grade;
	private int colour;
	private boolean reinforcedBottom = false;
	private boolean reinforcedCorners = false;
	private double boxWidth;
	private static final double minWidth = 0.1; // minimum allowed width
	private static final double maxWidth = 5.0; // maximum allowed width
	private double boxHeight;
	private static final double minHeight = 0.1; // minimum allowed height
	private static final double maxHeight = 5.0; // maximum allowed height
	private double boxLength;
	private static final double minLength = 0.1; // minimum allowed length
	private static final double maxLength = 5.0; // maximum allowed length
	private boolean sealableTop = false;
	private int quantity;
	private static final int minQuantity = 1; // minimum allowed quantity
	private static final int maxQuantity = 1000; // maximum allowed quantity
	private static ArrayList<BoxType> BoxTypes;
	private static int[] cardGrades;
	private static BoxType foundBoxType;
	private static OrderItemsList orderItemsList;
	private DefaultListModel orderList;
	
	/********* MAIN METHOD *********/
	public static void main (String[]args) {
		JOptionPane.showMessageDialog(null, "WELCOME TO THE FLEXBOX ORDERING SYSTEM", "", JOptionPane.PLAIN_MESSAGE); // display welcome message to the customer
		userInfo(); // load and display the buying guide to the customer
		UI buildUI = new UI();
		buildUI.UI(); // load the UI
	}

	// INITIALISE THE UI
	void UI() {

		guiFrame.setTitle("FlexBox Ordering System");
		mainPanel.setLayout(new GridBagLayout());

		orderList = new DefaultListModel(); // initialise the model of orderItemsList,
		orderDetails.setModel(orderList);	// which will display the ordered boxes

		boxTypes(); //initialise the types of boxes 

		// TOP LABEL
		addItem(mainPanel, topLabel, 0, 0, 2, 1, GridBagConstraints.CENTER);
		topLabel.setText("Please, enter the details of your order:");

		/********* BOX SIZE TEXT FIELDS *********/
		// WIDTH TEXT FIELD
		addItem(mainPanel, widthLabel, 0, 2, 1, 1, GridBagConstraints.EAST);
		widthLabel.setText("Box width (m):");
		addItem(mainPanel, widthTF, 1, 2, 2, 1, GridBagConstraints.WEST);

		// HEIGHT TEXT FIELD
		addItem(mainPanel, heightLabel, 0, 3, 1, 1, GridBagConstraints.EAST);
		heightLabel.setText("Box height (m):");
		addItem(mainPanel, heightTF, 1, 3, 2, 1, GridBagConstraints.WEST);

		// LENGTH TEXT FIELD
		addItem(mainPanel, lengthLabel, 0, 4, 1, 1, GridBagConstraints.EAST);
		lengthLabel.setText("Box length (m):");
		addItem(mainPanel, lengthTF, 1, 4, 2, 1, GridBagConstraints.WEST);

		// GRADE COMBOBOX
		addItem(mainPanel, gradeLabel, 0, 5, 1, 1, GridBagConstraints.EAST);
		gradeLabel.setText("Grade of card:");
		addItem(mainPanel, gradeCombo, 1, 5, 1, 1, GridBagConstraints.WEST);
		gradeCombo.setModel(new DefaultComboBoxModel(new String[] { "--select--", "1", "2", "3", "4", "5" }));

		/********* COLOUR RADIO BUTTONS GROUP *********/
		addItem(mainPanel, coloursLabel, 0, 6, 1, 1, GridBagConstraints.NORTHEAST);
		coloursLabel.setText("Box Colours:");
		coloursBox.add(noColourRB);
		coloursBox.add(oneColourRB);
		coloursBox.add(twoColourRB);

		// NO COLOUR RADIO BUTTON
		coloursGroup.add(noColourRB);
		noColourRB.setText("None");
		noColourRB.doClick();

		// ONE COLOUR RADIO BUTTON
		coloursGroup.add(oneColourRB);
		oneColourRB.setText("1");

		// TWO COLOURS RADIO BUTTON
		coloursGroup.add(twoColourRB);
		twoColourRB.setText("2");

		coloursBox.setBorder(BorderFactory.createEmptyBorder());
		addItem(mainPanel, coloursBox, 1, 6, 2, 1, GridBagConstraints.WEST);

		/********* BOX ENHANCEMENTS CHECKBOXES *********/
		addItem(mainPanel, enhancementsLabel, 0, 7, 1, 1, GridBagConstraints.NORTHEAST);
		enhancementsLabel.setText("Box enhancements:");
		Box enhancements = Box.createVerticalBox();

		// REINFORCED BOTTOM CHECKBOX
		enhancements.add(rBottomCB);
		rBottomCB.setText("Reinforced Bottom");

		// REINFORCED CORNERS CHECKBOX
		enhancements.add(rCornersCB);
		rCornersCB.setText("Reinforced Corners");

		// SEALABLE TOP CHECKBOX
		enhancements.add(sealTopCB);
		sealTopCB.setText("Sealable Tops");

		addItem(mainPanel, enhancements, 1, 7, 1, 1, GridBagConstraints.WEST);

		/********* QUANTITY TEXT FIELD *********/
		addItem(mainPanel, quantityLabel, 0, 9, 1, 1, GridBagConstraints.NORTHEAST);
		quantityLabel.setText("Quantity:");
		addItem(mainPanel, quantityTF, 1, 9, 1, 1, GridBagConstraints.WEST);

		/********* ORDER LIST SCROLL PANE *********/
		addItem(mainPanel, orderListLabel, 3, 0, 1, 1, GridBagConstraints.WEST);
		orderListLabel.setText("Order Items List:");
		addItem(mainPanel, scrollPane, 3, 1, 2, 6, GridBagConstraints.NORTHEAST);
		scrollPane.setPreferredSize(new Dimension(500, 175));
		scrollPane.setViewportView(orderDetails);

		/********* TOTAL PRICE LABELS *********/
		addItem(mainPanel, totalPriceLabel, 4, 6, 1, 1, GridBagConstraints.SOUTHEAST);
		totalPriceLabel.setText("Total Price:");
		addItem(mainPanel, currencyLabel, 5, 6, 1, 1, GridBagConstraints.SOUTHWEST);
		currencyLabel.setText("£");
		addItem(mainPanel, totalLabel, 7, 6, 1, 1, GridBagConstraints.SOUTHWEST);
		totalLabel.setText("0.0");

		/********* UI BUTTONS *********/
		// ADD TO ORDER BUTTON
		addItem(mainPanel, addToButton, 0, 11, 2, 10, GridBagConstraints.CENTER);
		addToButton.setText("Add to Order");
		addToButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				addToButtonActionPerformed(event);
			}
		});

		// ADD NEW BOX TO LIST BUTTON
		addItem(mainPanel, addMoreButton, 5, 2, 3, 1, GridBagConstraints.SOUTHWEST);
		addMoreButton.setText("Add more boxes");
		addMoreButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				addMoreButtonActionPerformed(event);
			}
		});

		// REMOVE FROM ORDER LIST BUTTON
		addItem(mainPanel, removeButton, 5, 3, 3, 1, GridBagConstraints.SOUTHWEST);
		removeButton.setText("Remove from list");
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				removeOrderActionPerformed(event);
			}
		});

		// SUBMIT ORDER BUTTON
		addItem(mainPanel, submitButton, 4, 7, 1, 1, GridBagConstraints.NORTHEAST);
		submitButton.setText("Checkout");
		submitButton.setEnabled(false);
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				submitButtonActionPerformed(event);
			}
		});

		// NEW ORDER BUTTON
		addItem(mainPanel, newOrderButton, 4, 11, 1, 1, GridBagConstraints.SOUTHEAST);
		newOrderButton.setText("New Order");
		newOrderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				newOrderButtonActionPerformed(event);
			}
		});
		
		// INFO BUTTON
		addItem(mainPanel, infoButton, 0, 11, 1, 1, GridBagConstraints.SOUTHWEST);
		infoButton.setText("?");
		infoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				infoButtonActionPerformed(event);
			}
		});

		// EXIT BUTTON
		addItem(mainPanel, exitButton, 7, 11, 1, 1, GridBagConstraints.SOUTHEAST);
		exitButton.setText("Exit");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				exitButtonActionPerformed(event);
			}
		});

		// LOAD guiFrame COMPONENTS
		guiFrame.add(mainPanel);
		guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		guiFrame.pack(); // set the size of the frame based on the size of the components
		guiFrame.setResizable(false); // restrict the resizability of the frame
		guiFrame.setLocationRelativeTo(null); // display the frame in the middle of the screen
		guiFrame.setVisible(true);
	}

	// CONSTRUCTOR FOR ADDING UI COMPONENTS TO THE mainPanel
	private void addItem(JPanel p, JComponent c, int x, int y, int width, int height, int align) {
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridx = x;
		gc.gridy = y;
		gc.gridwidth = width;
		gc.gridheight = height;
		gc.insets = new Insets(3, 5, 3, 5);
		gc.anchor = align;
		gc.fill = GridBagConstraints.NONE;
		p.add(c, gc);
	}

	// CREATE AND LOAD ALL BOX TYPES IN AN ARRAYLIST
	private void boxTypes() {

		BoxTypes = new ArrayList<>();
		cardGrades = new int[]{1,2,3};
		//create the 1st type
		BoxType boxType1 = new NormalBox(cardGrades);
		BoxTypes.add(boxType1);
		//create the 2nd type
		cardGrades = new int[]{2,3,4};
		BoxType boxType2 = new ColouredBox(cardGrades, 1);
		BoxTypes.add(boxType2);
		//create the 3rd type
		cardGrades = new int[]{2,3,4,5};
		BoxType boxType3 = new ColouredBox(cardGrades, 2);
		BoxTypes.add(boxType3);
		//create the 4th type
		cardGrades = new int[]{2,3,4,5};
		BoxType boxType4 = new ReinforcedBox(cardGrades, 2, true, false);
		BoxTypes.add(boxType4);
		//create the 5th type
		cardGrades = new int[]{3,4,5};
		BoxType boxType5 = new ReinforcedBox(cardGrades, 2, true, true);
		BoxTypes.add(boxType5);

	}

	// CHECK IF THE BOX CAN BE SUPPLIED OR NOT
	private boolean canBeSupplied() {

		boolean found = false;

		for(BoxType boxType : BoxTypes )
		{    

			if(colour == boxType.getColour() 
					&& reinforcedBottom == boxType.getReinforcedBottom() 
					&& reinforcedCorners == boxType.getReinforcedCorners()
					&& boxType.containsGrade(grade))
			{ 
				found = true;
				foundBoxType = boxType;
				foundBoxType.setCardGrade(grade);
				break;
			} 
		}
		return found;
	}

	// IF THE BOX CAN BE SUPLIED ADD IT TO THE ORDER AND DISPLAY TOTAL PRICE
	private void makeOrder() {

		// ordered box object
		fos.Box orderedBox = new fos.Box(foundBoxType, boxWidth, boxHeight, boxLength, sealableTop);

		// order container for ordered box and quantity
		Order order = new Order(orderedBox, quantity);

		// validate the availability of orderItemsList
		if(orderItemsList == null)
		{
			// create an instance of orderItemsList 
			orderItemsList = new OrderItemsList();
		}

		// add the order to orderItemsList
		orderItemsList.addOrder(order);
		displayOrderInList();
		displayTotalCost(orderItemsList.cost());

	}

	// VALIDATE WIDTH TEXTFIELD INPUT  
	private void validateWidth() {

		boolean error = false;
		widthError.setText("");

		String widthInput = widthTF.getText();
		if("".equals(widthInput))
		{
			error = true;
			widthError.setText("Box width cannot be empty!");
			JOptionPane.showMessageDialog(null, widthError, "Warning", JOptionPane.WARNING_MESSAGE);
		}
		else
		{
			try {
				boxWidth = Double.parseDouble(widthInput);
			}
			catch (Exception e) 
			{
				error = true;
				widthError.setText("Box width have to be a valid number!");
				JOptionPane.showMessageDialog(null, widthError, "Warning", JOptionPane.WARNING_MESSAGE);
			}
		}
		if(error == false)
		{
			if(boxWidth < minWidth)
			{
				widthError.setText("The Box width value must be greater than or equal to " + minWidth + " " + measure);
				JOptionPane.showMessageDialog(null, widthError, "Warning", JOptionPane.WARNING_MESSAGE);
			}
			else
				if(boxWidth > maxWidth)
				{
					widthError.setText("The Box width value must be less than or equal to " + maxWidth + " " + measure);
					JOptionPane.showMessageDialog(null, widthError, "Warning", JOptionPane.WARNING_MESSAGE);
				}
		}

	}

	// VALIDATE HEIGHT TEXT FIELD INPUT
	private void validateHeight() {

		boolean error = false;
		heightError.setText("");

		String heightInput = heightTF.getText();
		if("".equals(heightInput))
		{
			error = true;
			heightError.setText("Box height cannot be empty!");
			JOptionPane.showMessageDialog(null, heightError, "Warning", JOptionPane.WARNING_MESSAGE);
		}
		else
		{
			try {
				boxHeight = Double.parseDouble(heightInput);
			}
			catch (Exception e) 
			{
				error = true;
				heightError.setText("Box height have to be a valid number!");
				JOptionPane.showMessageDialog(null, heightError, "Warning", JOptionPane.WARNING_MESSAGE);
			}
		}
		if(error == false)
		{
			if(boxHeight < minHeight)
			{
				heightError.setText("The Box height value must be greater than or equal to " + minHeight + " " + measure);
				JOptionPane.showMessageDialog(null, heightError, "Warning", JOptionPane.WARNING_MESSAGE);
			}
			else
				if(boxHeight > maxHeight)
				{
					heightError.setText("The Box height value must be less than or equal to " + maxHeight + " " + measure);
					JOptionPane.showMessageDialog(null, heightError, "Warning", JOptionPane.WARNING_MESSAGE);
				}
		}
	}

	// VALIDATE LENGTH TEXT FIELD INPUT
	private void validateLength() {

		boolean error = false;
		lengthError.setText("");

		String lengthInput = lengthTF.getText();
		if("".equals(lengthInput))
		{
			error = true;
			lengthError.setText("Box length cannot be empty!");
			JOptionPane.showMessageDialog(null, lengthError, "Warning", JOptionPane.WARNING_MESSAGE);
		}
		else
		{
			try {
				boxLength = Double.parseDouble(lengthInput);
			}
			catch (Exception e) 
			{
				error = true;
				lengthError.setText("Box length have to be a valid number!");
				JOptionPane.showMessageDialog(null, lengthError, "Warning", JOptionPane.WARNING_MESSAGE);
			}
		}
		if(error == false)
		{
			if(boxLength < minLength)
			{
				lengthError.setText("The Box length value must be greater than or equal to " + minLength + " " + measure);
				JOptionPane.showMessageDialog(null, lengthError, "Warning", JOptionPane.WARNING_MESSAGE);
			}
			else
				if(boxLength > maxLength)
				{
					lengthError.setText("The Box length value must be less than or equal to " + maxLength + " " + measure);
					JOptionPane.showMessageDialog(null, lengthError, "Warning", JOptionPane.WARNING_MESSAGE);
				}
		}
	}

	// VALIDATE QUANTITY INPUT
	private void validateQuantity() {

		boolean error = false;
		quantityError.setText("");

		String quantityInput = quantityTF.getText();
		if("".equals(quantityInput))
		{
			error = true;
			quantityError.setText("Quantity cannot be empty!");
			JOptionPane.showMessageDialog(null, quantityError, "Warning", JOptionPane.WARNING_MESSAGE);
		}
		else
		{
			try {
				quantity = Integer.parseInt(quantityInput);
			}
			catch (Exception e) 
			{
				error = true;
				quantityError.setText("The quantity have to be a valid number!");
				JOptionPane.showMessageDialog(null, quantityError, "Warning", JOptionPane.WARNING_MESSAGE);
			}
		}
		if(error == false)
		{
			if(quantity < minQuantity)
			{
				quantityError.setText("The quantity value must be greater than or equal with " + minQuantity);
				JOptionPane.showMessageDialog(null, quantityError, "Warning", JOptionPane.WARNING_MESSAGE);
			}
			else
				if(quantity > maxQuantity)
				{
					quantityError.setText("The quantity value must be less than or equal with " + maxQuantity);
					JOptionPane.showMessageDialog(null, quantityError, "Warning", JOptionPane.WARNING_MESSAGE);
				}
		}
	}

	// VALIDATE SELECTED GRADE VALUE
	private void validateGrade() {

		gradeError.setText("");

		String gradeInput = (String) gradeCombo.getSelectedItem();
		
		try {
			grade = Integer.parseInt(gradeInput);
			
		}
		catch (Exception e) 
		{
			gradeError.setText("Card grade have to be selected!");
			JOptionPane.showMessageDialog(null, gradeError, "Warning", JOptionPane.WARNING_MESSAGE);
		}

	}


	// VALIDATE SELECTED COLOURS VALUE
	private void validateColour() {

		Enumeration<AbstractButton> colours = coloursGroup.getElements();
		boolean selected = false;
		String colouring = "";
		int i=0;
		while (colours.hasMoreElements() && i<3) {
			i++;
			AbstractButton radioButton = colours.nextElement();
			if(radioButton.isSelected())
			{
				colouring = radioButton.getText();
				if("None".equals(colouring))
				{
					colouring = "0";
				}
				selected = true;
				colour = Integer.parseInt(colouring);
				if(!"".equals(colourError.getText()))
				{
					colourError.setText("");
					JOptionPane.showMessageDialog(null, colourError, "Warning", JOptionPane.WARNING_MESSAGE);

				}
				break;
			}

		}
		if(selected == false)
		{
			JOptionPane.showMessageDialog(null, "Box colours have to be selected", "error", JOptionPane.ERROR_MESSAGE);
		}

	}

	// VALIDATE THE USER INPUT
	private void validateInputs() {
		validateWidth();
		validateHeight();
		validateLength();
		validateGrade();
		validateQuantity();
		validateColour();
		sealableTop = sealTopCB.isSelected();
		reinforcedBottom = rBottomCB.isSelected();
		reinforcedCorners = rCornersCB.isSelected();
	}

	// RESET ALL UI COMPONENTS TO THEIR INITIAL VALUES
	private void resetFields() {
		for(Component component:mainPanel.getComponents())
		{
			if(component instanceof JTextField)
			{
				((JTextField) component).setText("");
			}
			else if("errorLabel".equals(component.getName()))
			{
				((JLabel) component).setText("");
			}
		}
		noColourRB.doClick();
		gradeCombo.setSelectedIndex(0);
		sealTopCB.setSelected(false);
		rBottomCB.setSelected(false);
		rCornersCB.setSelected(false);
	}
	
	private void disableUserInput() {
		widthTF.setEnabled(false);
		heightTF.setEnabled(false);
		lengthTF.setEnabled(false);
		gradeCombo.setEnabled(false);
		noColourRB.setEnabled(false);
		oneColourRB.setEnabled(false);
		twoColourRB.setEnabled(false);
		sealTopCB.setEnabled(false);
		rBottomCB.setEnabled(false);
		rCornersCB.setEnabled(false);
		quantityTF.setEnabled(false);
		addToButton.setEnabled(false);
	}

	private void enableUserInput() {
		widthTF.setEnabled(true);
		heightTF.setEnabled(true);
		lengthTF.setEnabled(true);
		gradeCombo.setEnabled(true);
		noColourRB.setEnabled(true);
		oneColourRB.setEnabled(true);
		twoColourRB.setEnabled(true);
		sealTopCB.setEnabled(true);
		rBottomCB.setEnabled(true);
		rCornersCB.setEnabled(true);
		quantityTF.setEnabled(true);
		addToButton.setEnabled(true);
		addMoreButton.setEnabled(true);
		removeButton.setEnabled(true);
	}

	// DISPLAY THE VALID BOX ORDERS
	private void displayOrderInList() {

		String output = quantity + 
				" x Box (Width: " + boxWidth + " " + measure + 
				"; Height: " + boxHeight + " " + measure +
				"; Length: " + boxLength + " " + measure +
				"; Grade: " + grade + 
				"; Colour: " + colour +"; ";

		if(reinforcedBottom) {
			output = output + "with Reinforced bottom; ";
		}
		if(reinforcedCorners) {
			output = output + "with Reinforced corners; ";
		}
		if(sealableTop) {
			output = output + "with Sealable tops; ";
		}
		output = output + ")";
		orderList.addElement(output);
	}

	// DISPLAY THE TOTAL PRICE
	private void displayTotalCost(double cost) {
		DecimalFormat costFormat = new DecimalFormat("#.##");
		totalLabel.setText(costFormat.format(cost));
	}

	// CHECKS FOR INPUT ERRORS AND IF THE BOX CAN BE SUPPLIED
	private void checkErrors() {
		if(!"".equals(heightError.getText()) || !"".equals(widthError.getText()) || !"".equals(lengthError.getText()) ||
				!"".equals(quantityError.getText()) || !"".equals(colourError.getText()) || !"".equals(gradeError.getText()))
		{

		}
		else
		{
			//check if the type of box can be supplied
			if(canBeSupplied())
			{
				makeOrder(); // make order and display the prive
				resetFields(); // reset the input fields
				disableUserInput();
				boxTypes();
				submitButton.setEnabled(true);

			}
			else
			{
				JOptionPane.showMessageDialog(null,"The current box cannot be supplied! " + "\n" + "Please try another configuration!",
						"Box validation failed", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
	private static void userInfo() {
		JOptionPane.showMessageDialog(null, "FLEXBOX ORDERING SYSTEM - BUYING GUIDE" + "\n\n" +
											"1. Available Box sizes:" + "\n" +
												"               Width - min 0.1 sq.m. | max 5.0 sq.m." + "\n" +
												"               Height - min 0.1 sq.m. | max 5.0 sq.m." + "\n" +
												"               Lenght - min 0.1 sq.m. | max 5.0 sq.m." + "\n\n" +
											"2. Card grade prices:" + "\n" + 
												"               Grade 1 - £0.45/sq.m." + "\n" +
												"               Grade 2 - £0.59/sq.m." + "\n" +
												"               Grade 3 - £0.68/sq.m." + "\n" +
												"               Grade 4 - £0.92/sq.m." + "\n" +
												"               Grade 5 - £1.30/sq.m." + "\n\n" +
											"3. Box colour prices:" + "\n" + 
												"               1 colour - 12% extra" + "\n" +
												"               2 colours - 15% extra" + "\n\n" +
											"4. Box enhancements prices:" + "\n" +
												"               Reinforced bottom - 10% extra" + "\n" +
												"               Reinforced corners - 7% extra" + "\n" +
												"               Sealable top - 5% extra" + "\n\n\n" + 
												"* Click the [ ? ] button to view this information again" + "\n\n",												 
											"Important information", JOptionPane.PLAIN_MESSAGE);
	}

	// GENERATE UNIQUE RECEIPT NUMBER
	private String receiptNumber() {
		int n[] = new int[1000];
		int goodNumber = 1;
		Random randomGenerator = new Random();
		while (goodNumber < 9) {
			for (int i = 0; i < 8; i++) {
				n[i] = randomGenerator.nextInt(10);
				if (i > 0) {
					for (int j = 0; j < i; j++) {
						if (n[i] == n[j]) {
							goodNumber = 1;
							while (n[j] == n[i]) {
								n[i] = randomGenerator.nextInt(10);
							}
							j = 0;
						} else {
							goodNumber++;
						}
					}
				}
			}
		}
		return String.valueOf(n[0]) + String.valueOf(n[1])
				+ String.valueOf(n[2]) + String.valueOf(n[3]
				+ String.valueOf(n[4]) + String.valueOf(n[5])
				+ String.valueOf(n[6]) + String.valueOf(n[7]));
	}

	/********* ACTIONS PERFORMED SECTION *********/

	private void addMoreButtonActionPerformed(ActionEvent event) {
		//resetFields();
		enableUserInput();
		
	}
	
	// REMOVES AN BOX ORDER FROM THE ORDER ITEMS LIST
	private void removeOrderActionPerformed(ActionEvent event) {

		int selectIndex = orderDetails.getSelectedIndex();

		if(selectIndex != -1) // if order is selected do this
		{
			orderList.remove(selectIndex); // remove the order from the JList
			orderItemsList.removeOrderByID(selectIndex); // remove the order from the system
			displayTotalCost(orderItemsList.cost()); // display the total cost after the order has been removed
		}
		
		int orderListSize = orderDetails.getModel().getSize(); // get the size of the orderDetails after deleting an order
		if (orderListSize == 0){
			submitButton.setEnabled(false);
		}
		else {
			submitButton.setEnabled(true);
		}
	}

	// VALIDATES AND DISPLAYS THE CURRENT ORDER
	private void addToButtonActionPerformed(ActionEvent event) {

		validateInputs(); // validate the user inputs
		checkErrors(); //check if there are any errors and proceed with the order
	}
	
	//SUBMIT THE ORDERS AND WRITE A RECEIPT
	private void submitButtonActionPerformed(ActionEvent event) {
		if (event.getSource() == submitButton){ // confirm order
		int confirm=JOptionPane.showConfirmDialog(null,"Confirm Your Order?","Confirm",JOptionPane.OK_CANCEL_OPTION);
			
			if (JOptionPane.CANCEL_OPTION==confirm){
				this.dispose();
			}
			
			else if(JOptionPane.OK_OPTION==confirm){
			 	submitButton.setEnabled(false);
				addMoreButton.setEnabled(false);
				removeButton.setEnabled(false);
				this.dispose();

				String total = totalLabel.getText();
	
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date = new Date();
				String datetime = dateFormat.format(date);
				String receiptId = receiptNumber();
				JOptionPane.showMessageDialog(null, "Transaction Successful. \nPrinting receipt...");
				this.dispose();
	
				Receipt receipt = new Receipt(datetime,orderList,total);
				receipt.setTitle("Receipt " + receiptId);
				receipt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				receipt.setSize(250,600);
				receipt.setVisible(true);
				receipt.setResizable(false);
				receipt.setLocationRelativeTo(null);
				
	
				try { // Save the receipt to file		
	
					FileWriter FW = new FileWriter(receiptId + ".txt", true);
					PrintWriter PW = new PrintWriter(FW);
					PW.println("Receipt no: " + receiptId);
					PW.println("Date:" + datetime);
					PW.println("Your order is: " + "\n" + orderList.toString());
	
					PW.println("Total:" + total);
					PW.println("* * *");
					FW.close();
					
				}catch(IOException f){							
				}this.dispose();
			}
		}
	} 

	// CLEARS THE ORDERS ARRAYLIST AND RESETS THE UI COMPONENTS
	private void newOrderButtonActionPerformed(ActionEvent event) {
		try {
			orderItemsList.clearOrders();
		}
		catch (Exception e) {
		}
		orderList.clear();
		totalLabel.setText("0.0");
		resetFields();
		submitButton.setEnabled(false);
		enableUserInput();
	}
	
	// DISPLAYS THE USEFUL INFORMATION TO THE USER
	private void infoButtonActionPerformed(ActionEvent event) {
		userInfo();
	}

	private void exitButtonActionPerformed(ActionEvent event) {
		System.exit(0);
	}
}