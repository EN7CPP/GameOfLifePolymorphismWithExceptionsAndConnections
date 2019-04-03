import java.io.IOException;
import java.util.*;
public class  PackedLife extends World{

    private  long world;
   
    public PackedLife (String format)throws IOException,PatternFormatException{
        super(format);
        getPattern().initialise(this);
        int width=getPattern().getWidth();
        int height=getPattern().getHeight();
        if(height*width>=64){
            throw new IOException(""+width+" by "+height+" is too big for packed long!" );
        }
    }
    public PackedLife (Pattern pat)throws IOException,PatternFormatException{
        super(pat);
        getPattern().initialise(this);
        int width=getPattern().getWidth();
        int height=getPattern().getHeight();
        if(height*width>64){
            throw new IOException(""+width+" by "+height+" is too big for packed long!" );
        }
    }
    public boolean getCell(int col, int row) {
        if (row < 0 || row >= super.getHeight()) {
            return false;
        }
        if (col < 0 || col >= super.getWidth()) {
            return false;
        }
        if (((world >>> (row * super.getWidth() + col)) & 1L) == 1L)
            return true;
        else
            return false;
    }

 public void setCell(int col, int row, boolean value){
     if (value == true) {

         if(getCell(col,row)==false){world=world+(1L<<row*super.getWidth()+col);};
     }
     else{
         if(getCell(col,row)==true)world=world-(1L<<row*super.getWidth()+col);
     }

 }




}
