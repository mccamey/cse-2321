/**
 * @author Ian McCamey
 * @class CSE 2123
 * @version 20191204
 * 
 * A calculator program that can read expressions written in postfix 
 * notation into an expression tree, can redisplay the expression in 
 * standard (infix) notation, and display the computed result.
 */

import java.util.*;
import osu.cse2123.*;

public class ExpressionTree {
	
	/**
	 * Takes an expression written in postfix notation as a String and returns
	 * back the root node of an expression tree built from that String.
	 * 
	 * @param expr
	 * @return
	 */
	
	public static TreeNode<String> buildTreeFromString(String expr){
		String[] exprArr = expr.split("\\s+");	
		Stack<TreeNode<String>> exprStack = new Stack<TreeNode<String>>();
		
		for(String s : exprArr) {
			switch (s) {
				case "*": case "/": case "+": case "-": case "%":
					if (exprStack.size() > 1) {
						TreeNode<String> operator = new TreeNode<String>(s);
						operator.setRightChild(exprStack.pop());
						operator.setLeftChild(exprStack.pop());
						exprStack.push(operator);
					} else
						return null;
					break;
				default:
					TreeNode<String> operand = new TreeNode<String>(s);
					exprStack.push(operand);
			}
		}
		if(exprStack.size() == 1)
			return exprStack.pop();
		else
			return null;
	}

	/**
	 * 
	 * Takes the root node of an expression tree and generates a String holding 
	 * the expression represented by that tree in postfix notation.
	 * 
	 * @param expr
	 * @return
	 */
	
	public static String toPostfixString(TreeNode<String> expr) {
		if(expr == null)
			return "";
		
		String s = toPostfixString(expr.getLeftChild());
		s += toPostfixString(expr.getRightChild());
		s += expr.getData() + " ";
		return s;
	}
	
	/**
	 * Takes the root node of an expression tree and generates a String holding 
	 * the expression represented by that tree in standard (infix) notation.
	 * 
	 * @param expr
	 * @return
	 */
	
	public static String toInfixString(TreeNode<String> expr) {
		if(expr == null)
			return "";
		
		String s;
		switch(expr.getData()) {
		case "*": case "/": case "+": case "-": case "%":
			s = "(" + toInfixString(expr.getLeftChild());
			s += " " + expr.getData() + " ";
			s += toInfixString(expr.getRightChild()) + ")";
			break;
		default:
			s = toInfixString(expr.getLeftChild());
			s += expr.getData();
			s += toInfixString(expr.getRightChild());
		}
		return s;
	}
	
	/**
	 * Takes the root node of an expression tree and generates a String holding 
	 * the expression represented by that tree in prefix notation.
	 * 
	 * @param expr
	 * @return
	 */
	
	public static String toPrefixString(TreeNode<String> expr) {
		if(expr == null)
			return "";
		String s = expr.getData() + " ";
		s += toPrefixString(expr.getLeftChild());
		s += toPrefixString(expr.getRightChild());
		return s;
	}
	
	/**
	 * Evaluates the expression stored in the expression tree.
	 * 
	 * @param expr
	 * @return
	 */
	
	public static int evaluate(TreeNode<String> expr) {
		String s = expr.getData();
		int i;
		int j;
		
		switch(s) {
			case "*": 
				i = evaluate(expr.getLeftChild());
				j = evaluate(expr.getRightChild());
				return i * j;
			case "/": 
				i = evaluate(expr.getLeftChild());
				j = evaluate(expr.getRightChild());
				return i / j;
			case "+": 
				i = evaluate(expr.getLeftChild());
				j = evaluate(expr.getRightChild());
				return i + j;
			case "-": 
				i = evaluate(expr.getLeftChild());
				j = evaluate(expr.getRightChild());
				return i - j;
			case "%":
				i = evaluate(expr.getLeftChild());
				j = evaluate(expr.getRightChild());
				return i % j;
			default:
				return Integer.parseInt(s);
		}		
	}
}
