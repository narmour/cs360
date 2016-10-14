class YTreeNode extends TreeNode{

    private int xLow;
    private int xHigh;


    public YTreeNode(int p,int i,boolean lh,int low,int high){
        super(p,i,lh);
        xLow = low;
        xHigh = high;
    }

   public int getLow(){
      return xLow;
   }

   public int getHigh(){
      return xHigh;
   } 
}
