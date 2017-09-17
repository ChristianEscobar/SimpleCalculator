package org.simple.calculator;

import java.util.ArrayList;

/**
 * A simple calculator program used to calculate addition, subtraction, multiplication and division from 
 * command line arguments in the order they are provided in the arguments array.
 * The final result will be printed to the console.
 * @author Christian Escobar
 *
 */
public class SimpleCalculator {
	private ArrayList<String> history = new ArrayList<String>();

	/**
	 * Adds the specified value to the current history cache
	 * @param value
	 */
	public void addToHistory(String value) {
		this.history.add(value);
		
		return;
	}
	
	/**
	 * Checks if the specified String is a valid number
	 * @param value
	 * @return
	 */
	public boolean isNumber(String value) {
		boolean result = false;
		
		try {
			Double.parseDouble(value);
			
			return true;
		}
		catch(NumberFormatException nfe) {
			result = false;
		}
		
		return result;
	}
	
	/**
	 * Returns the final results from the history cache
	 * @return
	 */
	public double getFinalResult() {
		return Double.valueOf(this.history.get(this.history.size() - 1));
	}
	
	/**
	 * Returns true if the current operation is the first one being executed.
	 * @return
	 */
	private boolean firstOperation() {
		if(this.history.size() == 3) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Executes the specified operation and stores the result to the history cache
	 * @param operation
	 */
	public void executeOperation(String operation) {
		double[] operands = this.getOperands();
		
		double operand1 = operands[0];
		
		double operand2 = operands[1];
		
		double result = 0.0;
		
		switch(operation) {
			case "+" :
				result = operand1 + operand2;
				break;
			case "-" :
				result = operand1 - operand2;
				break;
			case "*"	 :
				result = operand1 * operand2;
				break;
			case "/" :
				result = operand1 / operand2;
				break;
			default :
				System.out.println("Operation " + operation + " not supported!");
		}	
		
		this.history.add(String.valueOf(result));
		
		return;
	}
	
	/**
	 * Extracts the current operands from the history cache
	 * @return
	 */
	private double[] getOperands() {
		double[] operands = new double[2];
		
		if(this.firstOperation()) {
			operands[0] = Double.valueOf(this.history.get(0));
			
			operands[1] = Double.valueOf(this.history.get(this.history.size() - 1));
		}
		else {
			operands[0] = Double.valueOf(this.history.get(this.history.size() - 3));
			
			operands[1] = Double.valueOf(this.history.get(this.history.size() - 1));
		}
		
		return operands;
	}
	
	public static void main(String args[]) {
		SimpleCalculator simpleCalculator = new SimpleCalculator();
		
		int operandTotal = 0;
		
		String operation = "";
		
		
		for(int i=0; i<args.length; i++) {
			String input = args[i];
			
	
			if(simpleCalculator.isNumber(input)) {
				simpleCalculator.addToHistory(input);
				
				operandTotal++;
				
				if(operandTotal == 2) {
					simpleCalculator.executeOperation(operation);
					
					operandTotal = 1;
				}
			}
			else {
				operation = input;
				
				simpleCalculator.addToHistory(operation);
			}		
			
			// Used for testing only
			//System.out.println(input);
		}

		System.out.println(simpleCalculator.getFinalResult());
	}
}
