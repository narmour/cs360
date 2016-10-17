import java.util.*;
import java.awt.*;

class SweepLine{

    // x and y bst's
    private BinarySearchTree xTree;
    private BinarySearchTree yTree;

    private ArrayList<Rectangle>inputRectangles;


    public SweepLine(){
        xTree = new BinarySearchTree();
        yTree = new BinarySearchTree();

        inputRectangles = new ArrayList<Rectangle>();
    }

    public ArrayList<Rectangle>rectangles(){
        return inputRectangles;
    }

    public void fillYTree(){
        
        for(int i =0;i <inputRectangles.size();i++){
            // false = low 
            // true = high
            Rectangle r = inputRectangles.get(i);

            TreeNode low = new TreeNode(r.y,i,false,r.x,r.x + r.width);
            TreeNode high = new TreeNode(r.y + r.height,i,true,r.x,r.x + r.width);

            yTree.insert(low);
            yTree.insert(high);
        }
        System.out.println("YTREE");
        yTree.print(yTree.root);
    }

    
    public static void main(String[] args){
        SweepLine sl = new SweepLine();


        // test rectangles from the doc
        Rectangle r0 = new Rectangle(120,100,58,58);
        Rectangle r1 = new Rectangle(160,140,30,32);
        Rectangle r2 = new Rectangle(225,164,55,24);

        // add rectangles to input array
        sl.inputRectangles.add(r0);
        sl.inputRectangles.add(r1);
        sl.inputRectangles.add(r2);

        // fill ytree
        sl.fillYTree();



    }





    }









