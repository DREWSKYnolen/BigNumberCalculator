import java.util.*;

/**
 * Does addition and subtractions calculations
 * @author Drew
 * @version 3/7/2016
 */
public class Calculator {
	
	/**
	 * Does addition calculation
	 * @param stack1 stack of the "top row" of numbers
	 * @param stack2 stack of the "bottom row" of numbers
	 * @return string representation of answer
	 */
	public String add(Stack<Integer> stack1, Stack<Integer> stack2)
	{
		String resultString = "";
		Stack<Integer> resultStack = new Stack<Integer>();
		int temp1 = 0;
		int temp2 = 0;
		int result = 0;
		int carry = 0;
		int retDigit = 0;
		
		while(!stack1.isEmpty() && !stack2.isEmpty()){
			temp1 = stack1.pop();
			temp2 = stack2.pop();
			result = temp1 + temp2 + carry;
			retDigit = result%10;
			carry = result/10;
			resultStack.push(retDigit);
		}
		if (carry != 0)
			resultStack.push(carry);		
		
		while(!resultStack.isEmpty())
		{
			resultString += resultStack.pop();
		}				
		return resultString;
	}//add
	
	/**
	 * Does subtraction calculation
	 * @param stack1 stack of the "top row" of numbers
	 * @param stack2 stack of the "bottom row" of numbers
	 * @return string representation of answer
	 */
	public String subtract(Stack<Integer> stack1, Stack<Integer> stack2)
	{
		String resultString = "";
		Stack<Integer> resultStack = new Stack<Integer>();
		int temp1 = 0;
		int temp2 = 0;
		int result = 0;
		int carry = 0;
		int retDigit = 0;
		
		while (!stack1.isEmpty() && !stack2.isEmpty())
		{
			temp1 = stack1.pop();
			temp2 = stack2.pop();
			temp1 = temp1 -carry;
			carry = 0;
			if (temp1 < temp2)
			{
				result = (10+temp1) - temp2;
				carry = 1;
			}
			else
				result = temp1-temp2;
		retDigit = result %10;
		//carry = result /10;
		resultStack.push(retDigit);
		//System.out.println(retDigit);
		}//while
		if (carry != 0){
			resultStack.pop();
			resultStack.pop();
		}
		while(!resultStack.isEmpty())
		{
			resultString += resultStack.pop();
		}
		return resultString;
	}
}