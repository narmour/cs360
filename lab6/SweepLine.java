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
        //System.out.println("YTREE");
        //yTree.print(yTree.root);
    }
    // performs iterative in order traversal to check nodes
    public double sweepXTree(){
        int counter=1;int L=0;
        TreeNode prevX = null;
        TreeNode nextX = null;

        prevX = xTree.findMin();

        while(prevX !=null){
            nextX = successor(xTree,prevX);
            if(nextX == null)
                System.out.println("NULL");
            if(counter!=0)
                L = L + (nextX.getPrimary() - prevX.getPrimary());
            prevX = nextX;
            counter = (prevX.getLH() == false) ? counter++:counter--;
            System.out.println("counter: " + counter);
        }

        return L;

    }

    // given bst r and a node n, find next smalelst highest node to n
    private TreeNode successor(BinarySearchTree r, TreeNode n){
        if(n.right() !=null)
            return r.findMin(n.right());
        TreeNode succ = null;
        TreeNode root = r.root;

        while(root!=null){
            if(n.getPrimary()  < root.getPrimary()){
                succ = root;
                root = root.left();
            }
            else if(n.getPrimary() > root.getPrimary()){
                root = root.right();
            }
            else
                break;
        }
        return succ;

    }

    // returns the covered by the inputRectangles
    public double computeArea(){
        double area = 0;

        while(!yTree.empty(yTree.root)){
            int curY=0;int preY=0;
            double L = 0;

            TreeNode n = new TreeNode();
            n = yTree.findMin();
            yTree.deleteMin();
            curY = n.getPrimary();

            L = sweepXTree();

            area = area + L*(curY-preY);
            preY= curY;
            // create and insert two xTreeNodes if low value
            if(n.getLH() == false){
                TreeNode xLow = new TreeNode(n.getLow(),n.getID(),false,n.getPrimary(),n.getPrimary() + inputRectangles.get(n.getID()).height);

                TreeNode xHigh = new TreeNode(n.getHigh(),n.getID(),true,n.getPrimary(),n.getPrimary() + inputRectangles.get(n.getID()).height);

                xTree.insert(xLow);
                xTree.insert(xHigh);
                System.out.println("added two xnodes");
                System.out.println("lowx : " + xLow.getPrimary());
                System.out.println("highx : " + xHigh.getPrimary());
            }
            // else remove two xTreeNodes
            else{
                xTree.remove(n.getLow());
                xTree.remove(n.getHigh());
            }

        }
        return area;
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

        //get area
        sl.computeArea();
//        System.out.println(sl.successor(sl.yTree,sl.yTree.root).getPrimary());



    }





    }









