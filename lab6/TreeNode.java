abstract class TreeNode{

    // both x and y trees will need these values in their nodes
    private int primaryKey;
    private int ID;
    private boolean lowHigh;



    public TreeNode(int p,int i,boolean lh){
        primaryKey = p;
        ID = i;
        lowHigh = lh;
    }

    public int getPrimary(){
        return primaryKey;
    }

    public int getID(){
        return ID;
    }

    public boolean getLH(){
        return lowHigh;
    }

    public abstract int getLow();
    public abstract int getHigh();


  



}
