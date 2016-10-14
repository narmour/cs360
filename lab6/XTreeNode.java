class XTreeNode extends TreeNode{

    private int yLow;
    private int yHigh;

    public XTreeNode(int p,int i,boolean lh,int low,int high){
        super(p,i,lh);
        yLow = low;
        yHigh =high;
    }

    public int getLow(){
        return yLow;
    }

    public int getHigh(){
        return yHigh;
    }



    public static void main(String[] a){

        XTreeNode x = new XTreeNode(1,2,false,10,20);


        System.out.println(x.getPrimary());

    }
}

