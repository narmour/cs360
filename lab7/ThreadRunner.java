import java.util.*;
import java.util.concurrent.*;
class ThreadRunner{



    void nonThreaded(){
        // non threaded
        ArrayList<Move> te = new ArrayList<Move>();
        long startTime = System.currentTimeMillis();
        Board b = new Board(1);
        b.solve(b,te);
        b.printMoves(b.solution);
        System.out.println("Total runtime: " + (System.currentTimeMillis() - startTime) + "ms");
        System.out.println("NON THREADED FINISH" );
    }

    void threaded(){
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
    }





    public static void main(String[] a){
        ThreadRunner t = new ThreadRunner();
        t.nonThreaded();
        //t.threaded();

        System.out.println("FORK/JOIN");
        // fork/join implementation
        Board start = new Board(69);
        long startTime = System.currentTimeMillis();
        try{
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(start);
        }catch(CancellationException e){
        }
        System.out.println("Total runtime: " + (System.currentTimeMillis() - startTime) + "ms");
       
        for(Board b:start.tasks){
       //     b.printMoves(b.solution);
         //   System.out.println("\n");
        }
        System.out.println(Void.TYPE);
	






    }
}
