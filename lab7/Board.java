import java.util.*;
import java.io.*;
class Board{

    //boolean array to represent game board
    private boolean[] brd;

    //solution move set
    private ArrayList<Move> solution; 
    
    //constructor
    public Board(){
        //init 2d array
        brd = new boolean[49];

        solution= new ArrayList<Move>();

        //set board
        brd = setBoard();

    }
    
    //copy constructor
    public Board(Board b){
        brd = new boolean[49];
        solution= new ArrayList<Move>();
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
                if(i==2)
                    System.out.println(x.brd[i+7]);
            }
        }
        return moveList;
    }

    ArrayList<Move>push(Move m, ArrayList<Move>mseq){
        ArrayList<Move>temp  =new ArrayList<Move>(mseq);
        temp.add(0,m);
        System.out.println("mseq size: " + temp.size());
        return temp;
    }


    boolean solve(Board x, ArrayList<Move> mseq){
        if(solved(x))
            return true;
        ArrayList<Move> next = nextMoves(x);
        for(Move m:next){
           Board y = move(m,x);
            if(solve(y,mseq)){
                x.printBoard();
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


    public static void main(String[] a){
        Board b = new Board();
        ArrayList<Move> moves = new ArrayList<Move>();
//        b.printBoard();
        b.solve(b,moves);
        b.printMoves(b.solution);
       //b.nextMoves(b);



    }




}
