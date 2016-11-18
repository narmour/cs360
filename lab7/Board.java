import java.util.*;
class Board{

    //boolean array to represent game board
    private boolean[] brd;
    
    //constructor
    public Board(){
        //init 2d array
        brd = new boolean[49];

        //set board
        brd = setBoard();

    }

    boolean[] setBoard(){
        boolean[] _brd = new boolean[49];
        // hard code start for now
        for(int i =0;i < 49;i++){
                if(i==0 || i==1 || i==5 || i==6 || i==7
                        ||i==8 || i==12 || i==13|| i==24
                        || i ==35 || i==36 || i==40 || i==41
                        || i ==42 || i==43 || i==47 || i==48)

                    _brd[i] = false;
                else
                    _brd[i]= true;
            }
        return _brd;
    }

    void printBoard(){
        for(int i =0;i <49;i++){
                if(brd[i])
                    System.out.print("x   ");
                else
                    System.out.print("    ");
                if((i+1)%7==0)
                    System.out.println();
            }
        }

    //returns true if not one of the 4 corner spots.
    boolean isPlayable(int i){
   
        if(i==0 || i==1 || i==5 || i==6 || i==7
                ||i==8 || i==12 || i==13|| i==2
                || i ==35 || i==36 || i==40 || i==41
                        || i ==42 || i==43 || i==47 || i==48)
            return false;
        return true;
    }

    Board move(Move m, Board b){
        //flip the first and last
        b.brd[m.m[0]] = true;
        b.brd[m.m[2]] = false;
        return b;
    }



    ArrayList<Move>nextMoves(Board x){
        ArrayList<Move> moveList = new ArrayList<Move>();
        for(int i =0;i<49;i++){
            //if its a valid empty spot
            if(isPlayable(i) && brd[i] == false){
                //check 2 left
                if( i%7 -2 >=0 && brd[i-1] &&brd[i-2]){
                    System.out.println("(" + (i) + " " + (i-1) + " " +(i-2) +")");
                    Move m = new Move(i,i-1,i-2);
                    moveList.add(m);
                }
                //check 2 right
                if( i%7+2<7 && brd[i+1] &&brd[i+2]){
                    System.out.println("(" + i + " " + (i+1) + " " +( i+2) +")");
                    Move m = new Move(i,i+1,i+2);
                    moveList.add(m);
                }
                //check 2 up
                if(i-14 >=0 && brd[i-7] && brd[i-14]){
                    System.out.println("(" + i + " " + (i-7) + " " +( i-14) +")");
                    Move m = new Move(i,i-7,i-14);
                    moveList.add(m);
                }
                //check 2 down
                if(i <35 && brd[i+7] && brd[i+14]){
                    System.out.println("(" + i + " " + (i+7) + " " +( i+14) +")");
                    Move m = new Move(i,i+7,i+14);
                    moveList.add(m);
                }
            }
        }
        return moveList;
    }
                



    public static void main(String[] a){
        Board b = new Board();
        b.printBoard();
        ArrayList<Move> moveList = b.nextMoves(b);

        Board x = b.move(moveList.get(0),b);
        x.printBoard();
    }




}
