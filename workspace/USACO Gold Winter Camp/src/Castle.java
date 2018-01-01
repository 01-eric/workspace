import java.io.*;
import java.util.*;

public class Castle {
	
	public static void main(String[] args) throws IOException {
		// Scanner scanner = new Scanner(new File("maxcross.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("maxcross.out")));
		Scanner scanner = new Scanner(System.in);
		String[] input = scanner.nextLine().split(" ");
		int[][] rooms = new int[Integer.parseInt(input[1])][Integer.parseInt(input[0])], walls = new int[rooms.length][rooms[0].length];
		for (int i = 0; i < walls.length; i++) for (int j = 0; j < walls[0].length; j++) walls[i][j] = scanner.nextInt();
		scanner.close();
		
		ArrayList<Integer> unvisited = new ArrayList<Integer>(rooms.length * rooms[0].length), roomSizes = new ArrayList<Integer>();
		for (int i = 0; i < rooms.length * rooms[0].length; i++) unvisited.add(i); // keep track of all unvisited rooms
		int maxRoomSize = 0, maxCombinedSize = 0;
		for (int roomNumber = 0; unvisited.size() > 0; roomNumber++) {
			roomSizes.add(0);
			visit(unvisited.get(0), roomNumber, rooms, walls, unvisited, roomSizes); // every iteration should dfs one complete room, increment roomNumber only after this loop
			maxRoomSize = Math.max(maxRoomSize, roomSizes.get(roomNumber));
		} for (int i = 0; i < rooms.length; i++) { // walk through all squares, checking E & S neighbors for each
			for (int j = 0; j < rooms[0].length; j++) { // if neighboring squares are from different rooms, keep track of largest combined size
				if (j < rooms[0].length - 1 && rooms[i][j] != rooms[i][j + 1]) maxCombinedSize = Math.max(maxCombinedSize, roomSizes.get(rooms[i][j]) + roomSizes.get(rooms[i][j + 1])); // compare east square
				if (i < rooms.length - 1 && rooms[i][j] != rooms[i + 1][j]) maxCombinedSize = Math.max(maxCombinedSize, roomSizes.get(rooms[i][j]) + roomSizes.get(rooms[i + 1][j])); // compare south square
			}
		}
		
		System.out.println(roomSizes.size() + "\n" + maxRoomSize + "\n" + maxCombinedSize);
		out.close();
	}
	
	static void visit(int index, int roomNumber, int[][] rooms, int[][] walls, ArrayList<Integer> unvisited, ArrayList<Integer> roomSizes) { // index is square to visit, roomNumber is number to mark square part of
		rooms[index / rooms[0].length][index % rooms[0].length] = roomNumber; // set all squares within a room to same number to identify
		roomSizes.set(roomNumber, roomSizes.get(roomNumber) + 1); // increase room size by 1
		unvisited.remove((Object)new Integer(index)); // mark the square as visited
		String bin = Integer.toBinaryString(walls[index / rooms[0].length][index % rooms[0].length]); // check for exits and go to next squares for dfs
		// for west: if room is not on left exterior wall, binary last bit is 0 (no wall), and room to left is unvisited (same idea for rest of directions) recursively visit neighboring room
		if (index % rooms[0].length > 0 && bin.charAt(bin.length() - 1) == '0' && unvisited.contains(index - 1)) visit(index - 1, roomNumber, rooms, walls, unvisited, roomSizes); //west
		if (index % rooms[0].length < rooms[0].length - 1 && (bin.length() < 3 || bin.charAt(bin.length() - 3) == '0') && unvisited.contains(index + 1)) visit(index + 1, roomNumber, rooms, walls, unvisited, roomSizes); //east
		if (index / rooms[0].length > 0 && (bin.length() < 2 || bin.charAt(bin.length() - 2) == '0') && unvisited.contains(index - rooms[0].length)) visit(index - rooms[0].length, roomNumber, rooms, walls, unvisited, roomSizes); //north
		if (index / rooms[0].length < rooms.length - 1 && bin.length() < 4 && unvisited.contains(index + rooms[0].length)) visit(index + rooms[0].length, roomNumber, rooms, walls, unvisited, roomSizes); //south
	}


}

/*
7 4
11 6 11 6 3 10 6
7 9 6 13 5 15 5
1 10 12 7 13 7 5
13 11 10 8 10 12 13
 ___ ___ _____
|_  |_  |  _  |
| |_  |_| |_| |
|  ___| |_| | |
|_|_________|_|
*/