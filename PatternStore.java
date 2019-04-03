import java.io.*;
import java.net.*;
import java.util.*;
public class PatternStore {
public static final int MAX_NUMBER_PATTERNS = 1000;	
private Pattern[] patterns = new Pattern[MAX_NUMBER_PATTERNS];
private int numberUsed = 0;
	
	
public PatternStore(String source) throws IOException {
if (source.startsWith("http://") || source.startsWith("https://")) {
loadFromURL(source);
}
else {
loadFromDisk(source);
}
}
public PatternStore(Reader source)throws IOException {
load(source);
}
private void load(Reader r) throws IOException {
String line="";
BufferedReader b=new BufferedReader(r);
while((line=b.readLine())!=null){
	try {
	patterns[numberUsed++]=new Pattern(line);
	}
	catch(PatternFormatException e) {
		System.out.println("Warning: "+ e.getMessage()+" For line : "+line);
	}
}
}


private void loadFromURL(String url) throws IOException {
URL link=new URL(url);
URLConnection conn=link.openConnection();
try {
Reader r=new InputStreamReader(conn.getInputStream());
load(r);
}
catch(FileNotFoundException e) {
	throw new IOException("Could not get an input stream!");
}



}


private void loadFromDisk(String filename) throws IOException {
	Reader r = new FileReader(filename);
	load(r);
	

}

public Pattern[] getPatterns() {
	Pattern[] toBeReturned=new Pattern[numberUsed];
	System.arraycopy(patterns, 0, toBeReturned, 0, numberUsed); 
	return toBeReturned;
}

public String[] getPatternAuthors() {
	String [] authors=new String [numberUsed];
	for(int i=0;i<numberUsed;i++) {
		authors[i]=new String(patterns[i].getAuthor());
	}
	Arrays.sort(authors);
	return authors;
	
}

public String[] getPatternNames() {
	String [] names=new String [numberUsed];
	for(int i=0;i<numberUsed;i++) {
		names[i]=new String(patterns[i].getName());
	}
	Arrays.sort(names);
	return names;
}


public static void main(String args[]) throws IOException {


PatternStore p = new PatternStore(args[0]);
String[] arr =p.getPatternNames();
for(int i=0;i<arr.length;i++) {
	System.out.println(arr[i]);
}

}


}
