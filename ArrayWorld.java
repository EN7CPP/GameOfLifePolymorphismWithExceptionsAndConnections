import java.util.*;
public class ArrayWorld extends World{
private boolean [][]world;
public ArrayWorld(String arg)throws PatternFormatException {
    super(arg);
    String [] arr=arg.split(":");
    this.world=new boolean [Integer.parseInt(arr[3])][Integer.parseInt(arr[2])];
    getPattern().initialise(this);

    
}
public ArrayWorld(Pattern pat) throws PatternFormatException {
	super(pat);
	this.world=new boolean[pat.getHeight()][pat.getWidth()];
	getPattern().initialise(this);
}
public boolean getCell(int col,int row) {
	if(row<0||row>=super.getHeight())return false;
	if(col<0||col>=super.getWidth())return false;
	return world[row][col];
	}

public void  setCell(int col,int row,boolean value) {
this.world[row][col]=value;
}



}
