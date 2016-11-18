public class Move{
    

    int[] m;

    Move(int i, int j, int k){
        m = new int[3];
        m[0]=i;
        m[1]=j;
        m[2]=k;
    }

    void printMove(){
        System.out.println("(" + m[0] + " " + m[1] + " " +m[2] +")");
    }

} 

