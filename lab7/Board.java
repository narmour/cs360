import java.util.*;
import java.io.*;
import java.util.concurrent.*;
class Board extends RecursiveAction implements Runnable{

    //boolean array to represent game board
    private boolean[] brd;

    //thread stuff
    private int threadID;

    //fork/join stuff
    public ArrayList<Board> tasks;
    private int magicNumber;

    //solution move set
    public ArrayList<Move> solution; 
    
    //constructor
    public Board(int id){
        //init 2d array
        brd = new boolean[49];

        solution= new ArrayList<Move>();

        //set board
        brd = setBoard();

        //set id
        threadID = id;

        //init tasks
        tasks = new ArrayList<Board>();



    }
    
    //copy constructor
    public Board(Board b){
        brd = new boolean[49];
        solution= new ArrayList<Move>();
        threadID = b.threadID;
        tasks = new ArrayList<Board>();
        magicNumber = b.magicNumber;
        for(int i=0;i<49;i++)
            brd[i] = b.brd[i];
    }

    boolean[] setBoard(){
        boolean[] _brd = new boolean[49];

        // read in from game.txt
        String line = new String();
        try{
            Scanner s = new Scanner(new File("game.txt"));
            line = s.nextLine();
        }catch(FileNotFoundException e){
            System.out.println("no game.txt");
            return _brd;
        }
        String[] n = line.split(" ");
        magicNumber = n.length;
        for(String x:n)
            _brd[Integer.parseInt(x)] = true;

        return _brd;
    }

    void printBoard(){
        for(int i =0;i <49;i++){
                if(brd[i])
                    System.out.print("x   ");
                else if (isPlayable(i))
                    System.out.print("_   ");
                else
                    System.out.print("    ");
                if((i+1)%7==0)
                    System.out.println();
            }
        //System.out.println("\n");
        }

    //returns true if not one of the 4 corner spots.
    boolean isPlayable(int i){
   
        if(i==0 || i==1 || i==5 || i==6 || i==7
                ||i==8 || i==12 || i==13
                || i ==35 || i==36 || i==40 || i==41
                        || i ==42 || i==43 || i==47 || i==48)
            return false;
        return true;
    }

    Board move(Move m, Board b){
        Board t =  new Board(b);

        //flip the first and last
        t.brd[m.m[0]] = true;
        t.brd[m.m[1]] = false;
        t.brd[m.m[2]] = false;
        return t;
    }


    // returns a list of all possible moves on board x
    ArrayList<Move>nextMoves(Board x){
        ArrayList<Move> moveList = new ArrayList<Move>();
        for(int i =0;i<49;i++){
            //if its a valid empty spot
            if(isPlayable(i) && x.brd[i] == false){
                //check 2 left
                if( i%7 -2 >=0 && x.brd[i-1] &&x.brd[i-2]){
               //     System.out.println("(" + (i) + " " + (i-1) + " " +(i-2) +")");
                    Move m = new Move(i,i-1,i-2);
                    moveList.add(m);
                }
                //check 2 right
                if( i%7+2<7 && x.brd[i+1] &&x.brd[i+2]){
             //       System.out.println("(" + i + " " + (i+1) + " " +( i+2) +")");
                    Move m = new Move(i,i+1,i+2);
                    moveList.add(m);
                }
                //check 2 up
                if(i-14 >=0 && x.brd[i-7] && x.brd[i-14]){
           //         System.out.println("(" + i + " " + (i-7) + " " +( i-14) +")");
                    Move m = new Move(i,i-7,i-14);
                    moveList.add(m);
                }
                //check 2 down
                if(i <35 && x.brd[i+7] && x.brd[i+14]){
         //           System.out.println("(" + i + " " + (i+7) + " " +( i+14) +")");
                    Move m = new Move(i,i+7,i+14);
                    moveList.add(m);
                }
            }
        }
        return moveList;
    }

    ArrayList<Move>push(Move m, ArrayList<Move>mseq){
        ArrayList<Move>temp  =new ArrayList<Move>(mseq);
        temp.add(0,m);
        return temp;
    }

    boolean validMove(Move m,Board b){
        if(b.brd[m.m[0]] == false && b.brd[m.m[1]] == true && b.brd[m.m[2]] == true)
            return true;
        return false;
    }


    boolean solve(Board x, ArrayList<Move> mseq){
        if(solved(x))
            return true;
        if(Thread.currentThread().isInterrupted())
            return false;
        ArrayList<Move> next = nextMoves(x);
        for(Move m:next){
           Board y = move(m,x);
            if(solve(y,mseq)){
                //x.printBoard();
                //m.printMove();
                solution.add(m);
                mseq = push(m,mseq);
                return true;
            }
        }
        return false;
    }

    // returns true if x is solved
    boolean solved(Board x){
        int n =0;
        for(int i =0;i<49;i++){
            if(x.brd[i])
                n++;
            if(n >1){
                //System.out.println("not solved");
                return false;
            }
        }
        return true;
    }

    //prints a list of moves
    void printMoves(ArrayList<Move> m){
        for(Move x : m)
            x.printMove();
    }

    void applySolution(Board b, ArrayList<Move> s){
        for(Move m :s ){
            b = b.move(m,b);
            b.printBoard();
        }
    }

    int numPegs(){
        int counter =0;
        for(int i=0;i<49;i++){
            if(brd[i])
                counter++;
        }
        return counter;
    }
       
    // overrided thread method
    public void run(){
        try{
        ArrayList<Move> moves = new ArrayList<Move>();
        solve(this,moves);
        // if it finishes, interrupt all other threads
       // System.out.println("Thread " + threadID + " finished");
       // System.out.println("Thread " + threadID + " solution size : " + solution.size());
        Thread.sleep(1);
        // if this runnable found a solution
        if(this.solution.size() >1){
            Thread.currentThread().getThreadGroup().interrupt();
            Collections.reverse(this.solution);
            printMoves(this.solution);
            //applySolution(this,this.solution);
        }

        }catch(InterruptedException exception){
            System.out.println("got interrupted");
            Thread.currentThread().interrupt();

        }

    }

    // overrided recursiveAction method
    protected void compute(){
        
        //get child tasks
        ArrayList<Move> childMoves = this.nextMoves(this);
        for(Move m: childMoves){
            Board c = this.move(m,this);
            tasks.add(c);
        }

        //invokeAll(tasks);
        
        for(Board b : tasks){
            b.fork();
            b.join();
        }
        
        solve(this,new ArrayList<Move>());
        /*
        if(solution.size() >1){
            System.out.println("solution size: " + solution.size() + "    pegs: " + numPegs() );
        }
        */
        if(numPegs() ==magicNumber){
            System.out.println("HI");
            printMoves(solution);
            cancel(true);
        }



    }


    public static void main(String[] a){
        /*
        Board b = new Board();
        ArrayList<Move> moves = new ArrayList<Move>();
        b.solve(b,moves);
        b.printMoves(b.solution);
        */
        System.out.println(Void.TYPE);



    }




}
