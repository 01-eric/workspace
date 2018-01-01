class Bicycle implements GearSystem {
	
	private int gear;
	private int speed;
	private static final int[] acceptableGears = {1, 2, 3, 4, 5, 6, 7};
	
	Bicycle(int gear, int speed) {
		this.gear = gear;
		this.speed = speed;
	}
	
	void printStates() {
		System.out.println(new StringBuilder("Gear: ").append(gear).append(", Speed: ").append(speed));
	}
	
	/*
	 * Below are the methods that must be added
	 * when GearSystem is implemented to the class.
	 */

	@Override
	public void changeGear(int newGear) {
		boolean validGear = false;
		for (int i = 0; i < acceptableGears.length; i++) validGear = validGear || newGear == acceptableGears[i];
		System.out.println("Setting gear to " + newGear + ".");
		if (validGear) gear = newGear;
		else System.out.println("Invalid gear.");
	}

	@Override
	public void changeSpeed(int increment) {
		speed += increment;
		System.out.println((increment < 0 ? "Decreasing" : "Increasing") + " speed to " + speed + ".");
	}
	
}

/*
 * An interface contains no code, only blank methods.
 * It's a contract that demands that classes that implement
 * it must contain the methods that it contains. It's useful
 * for organizing classes that have similar behavior.
 */

interface GearSystem {
	
	public void changeGear(int newGear);
	public void changeSpeed(int increment);
	
}

public class Interfaces {
	
	public static void main(String[] args) {
		Bicycle roadBike = new Bicycle(4, 20);
		roadBike.changeGear(8);
		roadBike.changeGear(2);
		roadBike.changeSpeed(-10);
		roadBike.printStates();
		
		/*
		 * can create an instance of an interface
		 * by reference of an object that implements it.
		 * the instance of the interface can be used to
		 * invoke ONLY the methods that it has (for
		 * example on GearSystem changeGear and changeSpeed)
		 * on the object it was created with.
		 */
		
		GearSystem gs = roadBike;
		gs.changeSpeed(30);
		roadBike.printStates();
	}

}
