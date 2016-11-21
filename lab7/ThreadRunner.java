import java.util.*;
import java.util.concurrent.*;
class ThreadRunner{
    public static void main(String[] a){

/*
        // non threaded
        ArrayList<Move> te = new ArrayList<Move>();
        long startTime = System.currentTimeMillis();
        Board b = new Board(1);
        b.printBoard();
        b.solve(b,te);
        b.printMoves(b.solution);
        System.out.println("Total runtime: " + (System.currentTimeMillis() - startTime) + "ms");
        System.out.println("NON THREADED FINISH" );
*/

/*

        System.out.println("SOLUTION START" );

        int i = 0;
        ArrayList<Move> s = b.solution;
        Collections.reverse(s);
        b.printMoves(s);
        //use the solution
        for(Move m:s){
            if(b.validMove(m,b)){
                System.out.print("MOVE: " + i + "   ");m.printMove();
                b = b.move(m,b);
                b.printBoard();
            }
            else{
                System.out.print("MOVE: " + i + "   ");m.printMove();
                System.out.println("INVALID\n");
            }

            i++;
            
        }
*/

	/*

        //create threadgroup
        ThreadGroup tg = new ThreadGroup("solvers");
        Board start = new Board(69);
        start.printBoard();
        long startTime = System.currentTimeMillis();

        ArrayList<Move> childMoves = start.nextMoves(start);
        startTime = System.currentTimeMillis();
        for(Move m:childMoves){
            Board c = start.move(m,start);
            //c.solution.add(m);
            Thread t = new Thread(tg,c);
            t.run();
        }

        System.out.println("Total runtime: " + (System.currentTimeMillis() - startTime) + "ms");
	*/


        // fork/join implementation
        Board start = new Board(69);
        long startTime = System.currentTimeMillis();
        ForkJoinPool pool = new ForkJoinPool();
        //add the child boards to the pool
        ArrayList<Move> childMoves = start.nextMoves(start);
        for(Move m:childMoves){
            Board c = start.move(m,start);
            pool.invoke(c);
        }


	






    }
}
