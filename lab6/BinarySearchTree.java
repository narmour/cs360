class BinarySearchTree{
    // root of tree node
    public TreeNode root;

    // creates a null BST
    public BinarySearchTree(){
        root = null;
    }
    
    // create a BST with root n
    public BinarySearchTree(TreeNode n){
        root = n;
    }

    // add  n into root
    public void insert(TreeNode n){
        root = insert(root,n);
    }

    // private method used to recursively add a node
    // returns new root with n inserted into it
    private TreeNode insert(TreeNode r,TreeNode n){

        if(r ==null)
            return n;
        // go to the left
        if(r.getPrimary() < n.getPrimary())
            r.setRight(insert(r.right(),n));
        // go to the right
        else
            r.setLeft(insert(r.left(),n));
        return r;

    }


    // in order print
    public void print(TreeNode r){
        if(r == null){
            return;
        }
        print(r.left());
        System.out.println(r.getPrimary() + " ");
        print(r.right());
    }

    // public method for finding a key in the tree. returns the node with key if it exists,
    // else returns a null node.
    public TreeNode find(int key){
        return find(root,key);
    }

    private TreeNode find(TreeNode r,int key){
        while(r!=null){
            // search right subtree
            if(r.getPrimary() < key)
                r = r.right();
            // search left subtree
            else if (r.getPrimary() > key)
                r = r.left();
            // else we found it
            else
                return r;
        }
        // didnt find it
        return r;
    }



    // finds min value key, delete it and return it for root r
    void deleteMin(){

        root = deleteMin(root);

    }


    TreeNode findMin(TreeNode r){
        while(r.left()!=null)
            r = r.left();
        return r;
    }

    // recursive function that sets root with min node removed
    TreeNode deleteMin(TreeNode r){
        if(r.left()!=null){
            r.setLeft(deleteMin(r.left()));
            return r;
        }
        // if we are at the Min node, link to its right subtree, this sets
        // root as (root - minNode)
        else
            return r.right();

    }


    // return true if r is null
    boolean isEmpty(TreeNode r){
        if(r == null)
            return true;
        return false;
    }
    
    // attemps to remove key from root
    public void remove(int key){
        // only call remove if key is in root
        if(find(key)!=null)
            root = remove(root,key);
        else
            System.out.println("ERROR, key must be in root");
    }

    private TreeNode remove(TreeNode r,int key){
        //search right
        if(r.getPrimary() < key)
            r.setRight(remove(r.right(),key));
        //search left
        else if(r.getPrimary() > key)
            r.setLeft(remove(r.left(),key));

        // we are at key
        //check for double child
        else if(r.left()!=null && r.right()!=null){
           // find min val in right subtree
           r.setPrimary(findMin(r.right()).getPrimary());
           r.setRight(deleteMin(r.right()));

        }
        // else if only a right child
        else if(r.left() == null)
            r = r.right();
        // else only a left child
        else
            r = r.left();

        return r;
    }
            




        



    public static void main (String[] a){
    /*
     * Rectangles:
     * R0 (120,100) , (178,158)
     * R1 (160,140) , (190,172)
     * R2 (225,164) , (280,188)
     */


        // YTREE with test Rectangles
        BinarySearchTree ytree = new BinarySearchTree();

        // create ytree nodes
        TreeNode r0_low_y = new TreeNode(100,0,false,120,178);
        TreeNode r0_high_y = new TreeNode (158,0,true,120,178);

        TreeNode r1_low_y = new TreeNode(140,1,false,160,190);
        TreeNode r1_high_y = new TreeNode(172,1,true,160,190);

        TreeNode r2_low_y = new TreeNode(164,2,false,225,280);
        TreeNode r2_high_y = new TreeNode(280,2,true,225,280);

        //add the nodes
        ytree.insert(r0_low_y);
        ytree.insert(r0_high_y);

        ytree.insert(r1_low_y);
        ytree.insert(r1_high_y);

        ytree.insert(r2_low_y);
        ytree.insert(r2_high_y);


        //TreeNode t = new TreeNode();
        //t = ytree.find(165);
        //if(t!=null)
          //  System.out.println(t.getPrimary());

        /*
        // test if min functions are working correctly

        //print in order 
        ytree.print(ytree.root);
        System.out.print("MIN KEY: "+ ytree.findMin(ytree.root).getPrimary());
        ytree.deleteMin();
        System.out.println();
        ytree.print(ytree.root);

        */

        // test if delete is working
        ytree.print(ytree.root);
        System.out.println("DELETE 164, 100, 280");
        ytree.remove(164);
        ytree.remove(100);
        ytree.remove(280);
        ytree.print(ytree.root);

        /*
        //initialize empty xtree
        BinarySearchTree xtree = new BinarySearchTree();
        double area = 0;

        while(!ytree.isEmpty(ytree.root)){
            double currentY=0;double prevY = 0;
            // find and delete minimum node
            TreeNode t = new TreeNode();
            t = ytree.findMin(ytree.root);
            ytree.deleteMin(ytree.root);

            currentY = t.getPrimary();

        */




    }



}
