public class PrePostDemo {
	
	/*
	 * both i++ and ++i will increase i by one, but ++i
	 * increases i before evaluating it in an expression
	 * and i++ increases i after evaluating it in an
	 * expression.
	 */
	
    public static void main(String[] args){
        int i = 3;
        i++;
        // prints 4
        System.out.println(i);
        ++i;			   
        // prints 5
        System.out.println(i);
        // prints 6
        System.out.println(++i);
        // prints 6
        System.out.println(i++);
        // prints 7
        System.out.println(i);
    }
    
}