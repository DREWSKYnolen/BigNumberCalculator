import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;


/**
 * GUI Class takes care of layout and visual, makes calls to calculator class
 * for calculations, converts string inputs to integers and creates
 * integer stacks to pass to calculator
 * @author Drew Nolen
 * @version 3/7/2016
 */
public class GUI extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private JButton add = new JButton("+");
	private JButton subtract = new JButton("-");
	private JTextArea type1 = new JTextArea("");
	private JTextArea type2 = new JTextArea("");
	private JTextArea resultBox = new JTextArea("");
	private JLabel t1Label = new JLabel("Type first number here");
	private JLabel t2Label = new JLabel("Type second number here");
	private JLabel rbLabel = new JLabel("Result");
	private static final int FRAMEHEIGHT = 500;
	private static final int FRAMEWIDTH = 750;
	private Stack<Integer> stack1 = new Stack<Integer>();
	private Stack<Integer> stack2 = new Stack<Integer>();
	
	/**
	 * Creates buttons, text fields, and labels for calculator
	 */
	public GUI()
	{
		setLayout(null);
		setSize(FRAMEWIDTH, FRAMEHEIGHT);
		add.setSize(50,50);
		add.setLocation(275,150);
		add.addActionListener(new ButtonClickHandler());
		add.setFont(new Font("Ariel", Font.BOLD, 25));
		add(add);
		subtract.setSize(50,50);
		subtract.setLocation(375,150);
		subtract.addActionListener(new ButtonClickHandler());
		subtract.setFont(new Font("Ariel", Font.BOLD, 30));
		add(subtract);
		t1Label.setSize(600,50);
		t1Label.setLocation(50,25);
		t1Label.setFont(new Font("Ariel", Font.BOLD, 20));
		add(t1Label);
		type1.setSize(600,50);
		type1.setLocation(50,75);
		type1.setEditable(true);
		add(type1);
		t2Label.setSize(600,50);
		t2Label.setLocation(50,200);
		t2Label.setFont(new Font("Ariel", Font.BOLD, 20));
		add(t2Label);
		type2.setSize(600,50);
		type2.setLocation(50,250);
		type2.setEditable(true);
		add(type2);
		rbLabel.setSize(600,50);
		rbLabel.setLocation(50,300);
		rbLabel.setFont(new Font("Ariel", Font.BOLD, 20));
		add(rbLabel);
		resultBox.setSize(650,50);
		resultBox.setLocation(50,350);
		resultBox.setEditable(false);
		add(resultBox);
	}
	
	/**
	 * Starts JFrame
	 */
	public void display()
	{
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setContentPane(this);
		frame.setSize(FRAMEWIDTH, FRAMEHEIGHT);
		frame.setVisible(true);		
	}	
	
	/**
	 * Takes care of button clicks and converts text input Strings to stacks to be passes to calculator class
	 * @author Drew Nolen
	 * @version 3/7/2016
	 */
	private class ButtonClickHandler implements ActionListener
	{
		Calculator calc = new Calculator();	
		
		/**
		 * Takes care of button clicks and converts text input Strings to stacks to be passes to calculator class
		 */
		public void actionPerformed(ActionEvent e){
			if (e.getSource() == add){
				String string1 = type1.getText();
				String string2 = type2.getText();
				
				if (string1.length() < string2.length()){
					while (string1.length() != string2.length())
						string1 = 0+string1;
				}//if
				if (string2.length() < string1.length()){
					while (string2.length() != string1.length())
						string2 = 0+string2;
				}//if
				
				for (int i = 0; i<string1.length(); i++){
					if (string1.charAt(i)<'0' || string1.charAt(i)>'9'){
						resultBox.setText("There is a non-integer in your 1st number");
					}
					else
						stack1.push(Character.getNumericValue(string1.charAt(i)));
				}//for
				for (int i = 0; i<string2.length(); i++){
					if (string2.charAt(i)<'0' || string2.charAt(i)>'9'){
						resultBox.setText("There is a non-integer in your 1st number");
					}
					else
						stack2.push(Character.getNumericValue(string2.charAt(i)));
				}//for
				resultBox.setText(calc.add(stack1, stack2));
				//}
			}//if	
			
			if (e.getSource() == subtract)
			{
				String string1 = type1.getText();
				String string2 = type2.getText();
				String result = "";
				
				if (string1.length() < string2.length()){
					while (string1.length() != string2.length())
						string1 = 0+string1;
				}//if
				if (string2.length() < string1.length()){
					while (string2.length() != string1.length())
						string2 = 0+string2;
				}//if				
				
				for (int i = 0; i<string1.length(); i++){
					if (string1.charAt(i)<'0' || string1.charAt(i)>'9'){
						resultBox.setText("There is a non-integer in your 1st number");
					}
					else
						stack1.push(Character.getNumericValue(string1.charAt(i)));
				}//for
				for (int i = 0; i<string2.length(); i++){
					if (string2.charAt(i)<'0' || string2.charAt(i)>'9'){
						resultBox.setText("There is a non-integer in your 1st number");
					}
					else
						stack2.push(Character.getNumericValue(string2.charAt(i)));
				}//for
				boolean neg = isNeg(string1, string2);
				//System.out.println(string1);
				//System.out.println(string2);
				if (neg){
					result = calc.subtract(stack2, stack1);
					result = "-" + result;
					//System.out.println(result);
				}
				else{
					result = calc.subtract(stack1, stack2);
					//System.out.println(result);
				}
				
				
				resultBox.setText(result);
			}
		}//actionpreformed	
		
		/**
		 * Tests the 2 inputs strings to see which is larger. If the second is larger, returns true and flips values of string 1 and 2.
		 * @param string1 string input from 1st input box
		 * @param string2 string input from 2nd input box
		 * @return true if the strings were swapped, false if not
		 */
		private boolean isNeg(String string1, String string2)
		{
			boolean isNegative = false;
			String temp = "";
			if (string1.compareTo(string2)<0){
				temp = string1;
				string1 = string2;
				string2 = temp;
				isNegative = true;
			}				
			return isNegative;
		}//isNeg
	}//bch
}
