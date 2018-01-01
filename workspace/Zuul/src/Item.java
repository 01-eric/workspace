
public class Item {
	
	String description;
	
	public Item(String newdescription) { // reading in a description from Game
		description = newdescription;
	}
	
	public String getDescription() { // returns description when called in Game
		return description;
	}

}
