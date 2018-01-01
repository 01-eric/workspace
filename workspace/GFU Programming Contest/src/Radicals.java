import java.util.*;

public class Radicals {
	
	ArrayList<String> outputs = new ArrayList<String>();
	
	public Radicals() {
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNextLine()) {
			double insideRad = Integer.parseInt(scanner.nextLine());
			int outsideRad = 1;
			for (int i = (int)Math.sqrt(insideRad); i >= 2; i--) {
				if (insideRad % Math.pow(i, 2) == 0) {
					outsideRad = outsideRad * i;
					insideRad = insideRad / Math.pow(i, 2);
				}
			} String output = outsideRad + "." + (int)insideRad;
			if (outsideRad == 1) output = output.substring(output.lastIndexOf("."));
			if ((int)insideRad == 1) output = output.substring(0, output.lastIndexOf("."));
			if (output.length() == 0) output = "1";
			outputs.add(output);
		} for (int i = 0; i < outputs.size(); i++) System.out.println(outputs.get(i));
		scanner.close();
	}
	
	public static void main(String[] args) {
		new Radicals();
	}

}