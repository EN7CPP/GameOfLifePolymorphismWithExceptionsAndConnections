import java.io.IOException;
import java.util.Scanner;
import java.io.*;

public class GameOfLife {
private World world;
private PatternStore store;
public GameOfLife(PatternStore patstore) {
	this.store=patstore;
}

public void play()throws IOException, PatternFormatException{
	String response = "";
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	System.out.println("Please select a pattern to play (l to list):");
	while (!response.equals("q")) {
	response = in.readLine();
	System.out.println(response);
	if (response.equals("f")) {
		
		
	if (world == null) {
	System.out.println("Please select a pattern to play (l to list):");
	
	}
	else {
	world.nextGeneration();
	print();
	}
	
	}
	else if (response.equals("l")) {
		
		
		
	Pattern[] names = store.getPatterns();
	int i = 0;
	for (Pattern p : names) {
	System.out.println(i + " " + p.getName() + " (" + p.getAuthor() + ")");
	i++;
	}
	
	
	}
	else if (response.startsWith("p")) {
	Pattern[] names = store.getPatterns();
	int numberOfPattern=Integer.parseInt(""+response.charAt(2));
	
	
	if(names[numberOfPattern].getHeight()*names[numberOfPattern].getWidth()<=64) {
		this.world=new PackedLife(names[numberOfPattern]);
	}
	else {
		this.world=new ArrayWorld(names[numberOfPattern]);
	}
	
	print();
	}
	
	
	}

}
public void print() {
	System.out.println("- "+this.world.getGenerationCount());
	for(int i=0;i<world.getHeight();i++){
		
		for(int z=0;z<world.getWidth();z++){
			if(world.getCell(z,i))System.out.print("#");
			else System.out.print("-");
		}
		
		System.out.println();
	}
}
public static void main(String[] args) throws IOException,PatternFormatException  {
	
	if (args.length != 1) {
		
		System.out.println("Usage: java GameOfLife <path/url to store>");
		return;
	}
	try {
		PatternStore ps = new PatternStore(args[0]);
		GameOfLife gol = new GameOfLife(ps);
		gol.play();
	}
	catch (IOException ioe) {
		System.out.println("Failed to load pattern store");
	}
	
	
}
}
