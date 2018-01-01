import java.util.ArrayList;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0 (February 2002)
 */

/*
 * Winning Condition: Get Bluetooth Headphones and iPhone 7 to 1-20 without wired headphones in the room
 * Losing Condition: Drop Galaxy Note 7 and Charger in the same room unless it's room 1-20
 */

class Game 
{
    private Parser parser;
    private Room currentRoom;
    Room a_wing, a_hall, a_three, three_hall, auditorium, two_hall, lower_commons, cafeteria, one_hall, main_hall, d_hall, d_eight, outside, office, one_twenty, f_hall, f_one, f_seven, three_eleven;
    Item GalaxyNote7 = new Item("GalaxyNote7");
    Item BluetoothHeadphones = new Item("BluetoothHeadphones");
    Item Charger = new Item("Charger");
    Item WiredHeadphones = new Item("WiredHeadphones");
    Item iPhone7 = new Item("iPhone7");
    ArrayList<Item> inventory = new ArrayList<Item>();
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() {
        createRooms();
        parser = new Parser();
    }
    
    public static void main(String[] args) {
    	Game game = new Game();
    	game.play(); 
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
       
        // create the rooms
        outside = new Room("in the main parking lot of Sunset High School");
        office = new Room("in the Sunset High School main office");
        main_hall = new Room("in main hall");
        d_hall = new Room("in an obscure hall you didn't know existed, and you see it labeled D-Hall");
        d_eight = new Room("in D-8, Mrs. Miller's STEM Chemistry class. You see a few kids laughing and pointing at a few flasks. Interesting");
        one_hall = new Room("in one hall. Students push and shove each other out of the way to even move a single step, it's mayhem");
        one_twenty = new Room("in Room 1-20, where you see a group of freshmen in the south side of the room talking, off task. You shake your head");
        f_hall = new Room("in F-Hall");
        f_one = new Room("in F-1, and you see many students messing around with robots. Fascinating");
        f_seven = new Room("in Room F-7, where Mrs. Williams is lecturing about logarithmic identities");
        lower_commons = new Room("in the lower commons");
        cafeteria = new Room("in the cafeteria, where you see a group of students at a nearby table flipping numerous water bottles");
        a_hall = new Room("in A-Hall");
        a_wing = new Room("in A-Wing");
        a_three = new Room("in A-3. A couple of senior speech and debate students are inside laughing");
        three_hall = new Room("in 3-Hall");
        two_hall = new Room("in 2-Hall");
        auditorium = new Room("in the auditorium");
        three_eleven = new Room("in Coach Allmer's health class, room 3-11. You see a life-size cardboard cutout of Thor by is lecture stand");
        
        outside.setExit("northwest", main_hall);
        outside.setExit("southwest", f_hall);
        office.setExit("north", main_hall);
        office.setItem(GalaxyNote7);
        main_hall.setExit("northeast", outside);
        main_hall.setExit("northwest", d_hall);
        main_hall.setExit("west", one_hall);
        main_hall.setExit("south", office);
        d_hall.setExit("south", main_hall);
        d_hall.setExit("east", d_eight);
        d_eight.setExit("west", d_hall);
        one_hall.setExit("north", a_hall);
        one_hall.setExit("northeast", main_hall);
        one_hall.setExit("east", one_twenty);
        one_hall.setExit("southeast", f_hall);
        one_hall.setExit("northwest", lower_commons);
        one_hall.setExit("southwest", cafeteria);
        one_twenty.setExit("south", f_hall);
        one_twenty.setExit("west", one_hall);
        one_twenty.setItem(WiredHeadphones);
        f_hall.setExit("north", one_twenty);
        f_hall.setExit("east", outside);
        f_hall.setExit("southeast", f_seven);
        f_hall.setExit("southwest", f_one);
        f_hall.setExit("west", one_hall);
        f_one.setExit("north", f_hall);
        f_seven.setExit("north", f_hall);
        lower_commons.setExit("east", one_hall);
        lower_commons.setExit("south", cafeteria);
        lower_commons.setExit("west", two_hall);
        cafeteria.setExit("north", lower_commons);
        cafeteria.setExit("east", one_hall);
        cafeteria.setExit("west", two_hall); 
        a_hall.setExit("north", a_wing);
        a_hall.setExit("south", one_hall);
        a_hall.setExit("west", auditorium);
        a_wing.setExit("north", a_three);
        a_wing.setExit("south", a_hall);
        a_wing.setExit("west", three_hall);
        a_three.setExit("south", a_wing);
        a_three.setItem(Charger);
        a_three.setItem(BluetoothHeadphones);
        three_hall.setExit("northeast", a_wing);
        three_hall.setExit("east", auditorium);
        three_hall.setExit("southeast", two_hall);
        three_hall.setExit("west", three_eleven);
        auditorium.setExit("north", a_hall);
        auditorium.setExit("west", three_hall);
        auditorium.setExit("east", two_hall);
        two_hall.setExit("northwest", auditorium);
        two_hall.setExit("southwest", three_hall);
        two_hall.setExit("east", lower_commons);
        two_hall.setExit("southeast", cafeteria);
        three_eleven.setExit("east", three_hall);
        three_eleven.setItem(iPhone7);
        
        /* theatre = new Room("in a lecture theatre");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        
        // initialise room exits
        outside.setExit("east", theatre);
        outside.setExit("south", lab);
        outside.setExit("west", pub);

        theatre.setExit("west", outside);

        pub.setExit("east", outside);

        lab.setExit("north", outside);
        lab.setExit("east", office);

        office.setExit("west", lab);

        currentRoom = outside;  // start game outside
        inventory.add(new Item("Samsung Galaxy Note 7"));
        inventory.add(new Item("Samsung Galaxy Note 7 Charger"));
        inventory.add(new Item("Fire Extinguisher"));
        pub.setItem(new Item("Bartender")); */
        currentRoom = outside;
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to Adventure!");
        System.out.println("Adventure is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * If this command ends the game, true is returned, otherwise false is
     * returned.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        } else if (commandWord.equals("go")) {
            goRoom(command);
        } else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        } else if (commandWord.equals("inventory")) {
        	printInventory();
        } else if (commandWord.equals("get")) {
        	getItem(command);
        	wantToQuit = win() || L();
        	if (win()) {
        		System.out.println("You listen to music happily on your iPhone 7 with your wireless headphones, and the wired headphones are no where in sight. Great Work!");
        	} else if (L()) {
        		System.out.println("There is a violent explosion as you plug the charger into your Note 7. You die in the explosion. End of the game.");
        	}
        } else if (commandWord.equals("drop")) {
        	dropItem(command);
        	wantToQuit = win() || L();
        	if (win()) {
        		System.out.println("You listen to music happily on your iPhone 7 with your wireless headphones, and the wired headphones are no where in sight. Great Work!");
        	} else if (L()) {
        		System.out.println("There is a violent explosion as you plug the charger into your Note 7. You die in the explosion. End of the game.");
        	}
        }
        return wantToQuit;
    }
    
    // winning condition
    boolean win() {
    	return currentRoom.equals(one_twenty) && currentRoom.items.contains(iPhone7) && currentRoom.items.contains(BluetoothHeadphones) && !currentRoom.items.contains(WiredHeadphones);
    }
    
    // losing condition 
    boolean L() {
    	return currentRoom.items.contains(GalaxyNote7) && currentRoom.items.contains(Charger) && !currentRoom.equals(one_twenty);
    }
    
    private void getItem(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what item to pick up...
            System.out.println("Get which item?");
            return;
        }

        String item = command.getSecondWord();

        // Try to leave current room.
        Item newItem = currentRoom.getItem(item);

        if (newItem == null)
            System.out.println("That item cannot be found in this room.");
        else {
            inventory.add(newItem);
            currentRoom.removeItem(item);
            System.out.println("You picked up a " + newItem.getDescription());
        }
    }
    
    private void dropItem(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what item to drop...
            System.out.println("Drop which item?");
            return;
        }

        String item = command.getSecondWord();

        // Try to leave current room.
        Item newItem = null;
        int index = 0;
        for (int loop = 0; loop < inventory.size(); loop++) {
        	if (inventory.get(loop).getDescription().equals(item)) {
        		newItem = inventory.get(loop);
        		index = loop;
        	}
        }

        if (newItem == null)
            System.out.println("That item cannot be found in your inventory.");
        else {
            inventory.remove(index);
            currentRoom.setItem(newItem); // rather than using new Item("item")?
            System.out.println("You dropped a " + newItem.getDescription());
        }
    }

    private void printInventory() {
		String output = "";
		for (int loop = 0; loop < inventory.size(); loop++) {
			output += inventory.get(loop).getDescription() + "\n";
		} System.out.println("You are carrying: ");
		System.out.println(output); 
	}

	// implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null)
            System.out.println("There is no door!");
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game. Return true, if this command
     * quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else
            return true;  // signal that we want to quit
    }
}
