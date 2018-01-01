class Class extends Machine {
	
	/*
	 * Empty Class with no fields or methods
	 */
	
}

class Machine {
	
	boolean running;
	
	Machine() {
		this.startMachine();
	}
	
	Machine(boolean b) {
		running = b;
	}
	
	void startMachine() {
		System.out.println("Machine started.");
		running = true;
	}
	
	void stopMachine() {
		System.out.println("Machine stopped.");
		running = false;
	}
	
}

class Car extends Machine {
	
	Car() {
		
		/*
		 * With subclasses it is best to define
		 * constructors. Even if they are exactly
		 * the same, refer to the superclass constructor.
		 */
		
		super();
	}
	
	Car(boolean b) {
		super(b);
	}
	
	@Override
	void startMachine() {
		System.out.println("Car started.");
		running = true;
	}
	
	
	/*
	 * super keyword refers to the method contained in the superclass.
	 */
	int getHashCode() {
		return super.hashCode();
	}
	
}

public class Inheritance {

	public static void main(String[] args) {
		
		Class newClass = new Class();
		/*
		 * Class is a blank class with no methods or fields
		 * but it has already inherited all methods from class
		 * "Object" because it is its superclass so I can use
		 * all methods from Object with Class too (newClass.hashCode);
		 * Additionally, aside from Object, you can inherit more
		 * methods into a class by using the "extends" keyword.
		 * Here, Class has become a subclass of Machine and now
		 * inherits all methods from Machine too. Machine is now
		 * considered a superclass of Class.
		 */
		System.out.println(newClass.hashCode());
		newClass.startMachine(); // inherited from Machine
		Car car = new Car();
		car.startMachine(); // use @Override to override the superclass method with same name
		car.stopMachine();
		System.out.println(car.running);
		System.out.println(car.getHashCode() + " " + car.hashCode());
	}
	
}
