package fos;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Receipt extends JFrame implements ActionListener {

	private JTextArea receiptHeader = new JTextArea(
			"\n--------------------------------------------------------"+
					"\n               FLEXBOX INC." +
					"\n               Middlesex University," +
					"\n               Hendon Campus," +
					"\n               The Burroughs," +
					"\n               London NW4 4BT" +
					"\n               United Kingdom" +
					"\n\n             Tel: 020 8411 5000" +
			"\n--------------------------------------------------------");
	
	private JTextArea receiptTextArea = new JTextArea(50,10);
	private JButton printButton = new JButton("Print");

	public Receipt(String datetime, DefaultListModel orderList, String total)
	{
		Container receiptContainer = getContentPane();
		receiptContainer.setLayout(new BorderLayout());

		JPanel topPanel = new JPanel();
		topPanel.add(receiptHeader);
		topPanel.setBackground(Color.WHITE);
		receiptHeader.setEditable(false);
		receiptContainer.add(topPanel,BorderLayout.NORTH);

		JPanel datePanel = new JPanel();
		datePanel.add(new JLabel ("\tDate: " + datetime + "\n")).setFont(new Font("Arial", Font.ROMAN_BASELINE, 12));
		datePanel.setBackground(Color.WHITE);

		JPanel orderPanel = new JPanel();
		String html1 = "<html><body style='width: ";
        String html2 = "px'>";
		orderPanel.add(new JLabel (html1 + "175" + html2 + "Ordered boxes: \n"+ orderList + "\n")).setFont(new Font("Arial", Font.ROMAN_BASELINE, 12));
		orderPanel.setBackground(Color.WHITE);
		
		JPanel totalPanel = new JPanel();
		totalPanel.add(new JLabel ("\tTotal: £" + total));
		totalPanel.setBackground(Color.WHITE);
		
		JPanel allPanel = new JPanel();
		allPanel.setLayout(new GridLayout(3,1,1,1));
		allPanel.setBackground(Color.WHITE);
		allPanel.add(datePanel);
		allPanel.add(orderPanel);
		allPanel.add(totalPanel);
		receiptContainer.add(allPanel,BorderLayout.CENTER);  

		JPanel printPanel = new JPanel();
		printPanel.setLayout(new FlowLayout());
		printPanel.setBackground(Color.BLACK);
		printPanel.add(printButton);
		receiptContainer.add(printPanel,BorderLayout.SOUTH);

		printButton.addActionListener(this);
	}

	public void actionPerformed(ActionEvent event)
	{
		if (event.getSource() == printButton)
		{
			this.dispose();
			int confirm = JOptionPane.showConfirmDialog(null, "Your order is now completed. \nDo you wish to continue shopping?", 
												"Order completed", JOptionPane.YES_NO_OPTION);
			if (JOptionPane.YES_OPTION==confirm){
 				this.dispose();
 				
 				
 			}
 			else if(JOptionPane.NO_OPTION==confirm){
	 			JOptionPane.showMessageDialog(null, "Thank you for choosing FlexBox.\nGood bye!", "", JOptionPane.PLAIN_MESSAGE);
				System.exit(0);
			}
		}
	}
}