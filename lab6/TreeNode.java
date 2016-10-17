class TreeNode{

    // each node has a primary value, an ID (the rectangle its value come from, a boolean if its the low or high value, 
    // and two min and max values. In an Xtree, each node will contain the min and max Y values 
    // Also a pointer to left and right subtrees.
    private int primaryKey;
    private int ID;
    private int lowKey;
    private int highKey;
    private boolean bLowHigh;

    private TreeNode leftSubtree,rightSubtree;
    


    // constructor. Takes in primary key val, rect id, if its a low or high val node, and the low and high values
    public TreeNode(int pKey,int rectID,boolean lowHigh,int low,int high){
        primaryKey = pKey;
        ID = rectID;
        bLowHigh = lowHigh;
        lowKey = low;
        highKey = high;
        leftSubtree = null;rightSubtree=null;
    }

    public TreeNode(){

    }

    public int getPrimary(){
        return primaryKey;
    }

    public int getID(){
        return ID;
    }

    public boolean getLH(){
        return bLowHigh;
    }

    public int getLow(){
        return lowKey;
    }
    public int getHigh(){
        return highKey;
    }

    public TreeNode left(){
        return leftSubtree;
    }

    public TreeNode right(){
        return rightSubtree;
    }

    public void setLeft(TreeNode n){
        leftSubtree = n;
    }

    public void setRight(TreeNode n){
        rightSubtree = n;
    }

    public void setPrimary(int key){
        primaryKey = key;
    }


  



}
