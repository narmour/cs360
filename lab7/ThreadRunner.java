import java.util.*;
import java.util.concurrent.*;
class ThreadRunner{



    void nonThreaded(){
        System.out.println("NON-THREADED" );
        // non threaded
        ArrayList<Move> te = new ArrayList<Move>();
        long startTime = System.currentTimeMillis();
        Board b = new Board();
        b.solve(b,te);
        Collections.reverse(b.solution);
        b.printMoves(b.solution);
       // b.applySolution(b,b.solution);
        System.out.println("Total runtime: " + (System.currentTimeMillis() - startTime) + "ms");
    }

    void threaded(){
        System.out.println("THREADED");
        //create threadgroup
        ThreadGroup tg = new ThreadGroup("solvers");
        Board start = new Board();
        start.printBoard();
        long startTime = System.currentTimeMillis();

        ArrayList<Move> childMoves = start.nextMoves(start);
        startTime = System.currentTimeMillis();
        for(Move m:childMoves){
            Board c = start.move(m,start);
            Thread t = new Thread(tg,c);
            t.run();
            c.solution.add(m);
        }
        Board b = new Board();

        System.out.println("Total runtime: " + (System.currentTimeMillis() - startTime) + "ms");
    }

    void forkJoin(){
        System.out.println("FORK/JOIN");
        // fork/join implementation
        Board s = new Board();
        long startTime = System.currentTimeMillis();
        try{
            ForkJoinPool pool = new ForkJoinPool();
            pool.invoke(s);
        }catch(CancellationException e){
        System.out.println("Total runtime: " + (System.currentTimeMillis() - startTime) + "ms");
        }
    }






    public static void main(String[] a){
        ThreadRunner t = new ThreadRunner();
        t.threaded();
        t.nonThreaded();
        t.forkJoin();
    }
}
