import java.util.*;
import java.awt.*;

class SweepLine{

    // x and y bst's
    private BinarySearchTree xTree;
    private BinarySearchTree yTree;

    private ArrayList<Rectangle>inputRectangles;
    private ArrayList<Rectangle> animRectangles;
    private ArrayList<Double> animAreas; 


    public SweepLine(){
        xTree = new BinarySearchTree();
        yTree = new BinarySearchTree();

        inputRectangles = new ArrayList<Rectangle>();
        animRectangles = new ArrayList<Rectangle>();
        animAreas = new ArrayList<Double>();
    }

    public SweepLine(ArrayList<Rectangle> r){
        this();
        inputRectangles = r;
    }
        

    public ArrayList<Rectangle>rectangles(){
        return inputRectangles;
    }
    
    public ArrayList<Rectangle>animRectangles(){
        return animRectangles;
    }

    public ArrayList<Double>animAreas(){
        return animAreas;
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
        ////System.out.println("YTREE");
        //yTree.print(yTree.root);
    }
    // performs iterative in order traversal to check nodes
    public double sweepXTree(int preY,int curY){
        //System.out.println("ENTER sweepXTree");
        int counter=1;int L=0;

        TreeNode prevX = null;
        TreeNode nextX = null;

        prevX = xTree.findMin();


        while(prevX !=null){
           
            //System.out.println("prevX : " + prevX.getPrimary() + "  LOWHIGH: " + prevX.getLH());
            nextX = successor(xTree,prevX);
            if(nextX !=null){
            
                //System.out.println("nextX : " + nextX.getPrimary() + "  LOWHIGH: " + nextX.getLH());
                if(counter!=0){
                    L = L + (nextX.getPrimary() - prevX.getPrimary());
                    Rectangle r = new Rectangle(prevX.getPrimary(),preY,nextX.getPrimary()-prevX.getPrimary(),curY-preY);
                   // System.out.println("CREATED RECTANGLE: " +r.x+" "+r.y+" "+r.width+" "+r.height);
                    animRectangles.add(r);
                    //System.out.println("L = " + L);
                }
                prevX = nextX;
                /*
                if(prevX.getLH() == false)
                    counter++;
                else
                    counter--;
                    */
                counter = (prevX.getLH() == false) ? counter+1:counter-1;

                
                //System.out.println("counter: " + counter);
            }
            else{
                prevX = nextX;
            }
        }
        //System.out.println("EXIT sweepXTree");
        //System.out.println("L = " + L);

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
        //fill y tree
        fillYTree();

        double area = 0;
        int curY=0;int preY=0;

        while(!yTree.empty(yTree.root)){
            double L = 0;

            TreeNode n = new TreeNode();
            n = yTree.findMin();
            yTree.deleteMin();
            curY = n.getPrimary();
            //System.out.println("YMIN: " + n.getPrimary() + "     LOWHIGH: " + n.getLH());
            //System.out.println("PREY: " + preY + "  CURY: " + curY);

            L = sweepXTree(preY,curY);
            if(curY-preY ==0)
                area = area + L;
            else
                area = area + L*(curY-preY);
            animAreas.add(area);
            //System.out.println("AREA: " + area);
            preY= curY;
            // create and insert two xTreeNodes if low value
            if(n.getLH() == false){
                TreeNode xLow = new TreeNode(n.getLow(),n.getID(),false,n.getPrimary(),n.getPrimary() + inputRectangles.get(n.getID()).height);

                TreeNode xHigh = new TreeNode(n.getHigh(),n.getID(),true,n.getPrimary(),n.getPrimary() + inputRectangles.get(n.getID()).height);

                xTree.insert(xLow);
                xTree.insert(xHigh);
                //System.out.println("added two xnodes");
                //System.out.println("lowx : " + xLow.getPrimary());
                //System.out.println("highx : " + xHigh.getPrimary());
            }
            // else remove two xTreeNodes
            else{
                //System.out.println("removing " + n.getLow() + "   " + n.getHigh());
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
        Rectangle r2 = new Rectangle(180,164,55,24);
        
        /*
         * simple test caste
        Rectangle r0 = new Rectangle(0,0,4,4);
        Rectangle r1 = new Rectangle(2,2,1,1);
        Rectangle r2 = new Rectangle(1,1,5,5);
        */
        // add rectangles to input array
        sl.inputRectangles.add(r0);
        sl.inputRectangles.add(r1);
        sl.inputRectangles.add(r2);

        // fill ytree
        sl.fillYTree();
        sl.yTree.print(sl.yTree.root);

        //get area
        System.out.println(sl.computeArea());
        
        



    }





    }









