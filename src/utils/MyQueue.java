package utils;

import java.util.Stack;

public class MyQueue {
	private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    public MyQueue() {
       // do initialization if necessary
    	stack1 = new Stack<Integer>();
    	stack2 = new Stack<Integer>();
    }
    
    public void push(int element) {
        // write your code here
    	stack1.push(element);
    }

    public int pop() {
    	 // write your code here
    	if(stack1.empty() && stack2.empty()){
    		return 0;
    	}
    	if(stack2.empty()){
    		int value;
    		while(!stack1.empty()){
    			value = stack1.pop();
    			stack2.push(value);
    		}
    		return stack2.pop();
    	}
		return stack2.pop();
    }

    public int top() {
    	 // write your code here
    	if(stack1.empty() && stack2.empty()){
    		return 0;
    	}
    	if(stack2.empty()){
    		int value;
    		while(!stack1.empty()){
    			value = stack1.pop();
    			stack2.push(value);
    		}
    		return stack2.peek();
    	}
		return stack2.peek();
    }
    
    
    
    
    
}
