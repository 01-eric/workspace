import java.util.*;
import javax.script.*;

public class PolishNotation {
	
	public static void main(String[] args) throws ScriptException {
		Stack stack = new Stack();
		String[] elements = new Scanner(System.in).nextLine().split(" ");
		boolean prefix = false;
		if (!Character.isDigit(elements[0].charAt(0))) {
			prefix = true;
			for (int i = 0; i < elements.length / 2; i++) {
				String temp = elements[i];
				elements[i] = elements[elements.length - (i + 1)];
				elements[elements.length - (i + 1)] = temp;
			}
		} for (String s: elements) {
			if (Character.isDigit(s.charAt(0))) stack.push(s);
			else {
				String[] operands = {stack.pop(), stack.pop()};
				stack.push(Integer.toString((int)new ScriptEngineManager().getEngineByName("JavaScript").eval(operands[prefix ? 0 : 1] + s + operands[prefix ? 1 : 0])));
			}
		} System.out.println(stack.pop());
	}
	
}

class Stack {
	
	private LinkedList<String> stack = new LinkedList<String>();
	
	public void push(String s) {
		stack.add(s);
	}
	
	public String pop() {
		String s = stack.get(this.getSize() - 1);
		stack.remove(this.getSize() - 1);
		return s;
	}
	
	public int getSize() {
		return stack.size();
	}
	
}