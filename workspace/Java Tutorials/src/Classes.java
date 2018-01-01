import java.util.*;

class Person {
	
	/*
	 * Classes are objects which have STATES and BEHAVIOR
	 * Classes can contain
	 * 1. Data (variables) - the object's state
	 * 2. Methods - the object's behavior (things that the object can do)
	 */
	String name;
	int years;
	
	void speak() {
		System.out.println("My name is " + name + ". I am " + years + " years old.");
	}
	
	void age(int years) { // parameters give a method flexibility by allowing values to change its behavior.
		this.years += years; // use "this" to refer to the non-local variable of the object "Person". Without "this" refers to local variable in method "age"
		System.out.println(name + " is now " + this.years + " years old.");
	}
	
	/*
	 * When the data in a class is encapsulated, other classes cannot access it, and
	 * in those cases you can't call Person.name or Person.age because that data is protected
	 * so we use a "get" method to act as a messenger to other classes with its return type.
	 * I believe this is because you can prevent other classes from changing the data such as
	 * using Person.age = someNumber by protecting the data but if you still want to get the
	 * data without modifying it you can use the "get" method.
	 */
	int getAge() {
		return years;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * When you print out an object i.e. System.out.println(instanceOfPerson)
	 * it will print out whatever the toString method returns. If there is no
	 * toString method, the system will print out the hashCode.
	 */
	
	public String toString() {
		return name;
	}
}

public class Classes {
	
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		Person Joe = new Person();
		/*
		 * Variables in the class have been initialized but contain no data.
		 * Primitive types such as ints have default values, such as 0, without being set.
		 * Objects such as Strings will throw a nullPointerException if you try to do 
		 * something with it, such as Joe.name.length() if not initialized properly.
		 */
		Joe.name = "Joe";
		Joe.years = 17;
		/*
		 * Methods allow the class to do things, perform all kinds of behavior.
		 * It can perform something (void) or have a return type.
		 * Calling methods from a different class has the syntax: Classname.methodname(parameters)
		 * Calling methods from the current class doesn't need the class name to be specified: methodname(parameters);
		 */
		Joe.speak(); // method from different class
		Person newPerson = createPerson(); // method from current class
		newPerson.speak();
		System.out.print("Enter an amount of years to pass: ");
		int yearsPassed = Integer.parseInt(scanner.nextLine());
		System.out.print(yearsPassed + " years later, ");
		newPerson.age(yearsPassed);
		Joe.age(yearsPassed);
		/*
		 * Using the toString method to print out the person objects.
		 */
		System.out.println(new StringBuilder("Demo of toString: ").append(Joe).append(", ").append(newPerson));
	}
	
	static Person createPerson() {
		System.out.print("To create a person, enter a name and an age: ");
		String[] input = scanner.nextLine().split(", ");
		Person person = new Person();
		person.name = input[0];
		person.years = Integer.parseInt(input[1]);
		return person;
	}

}
