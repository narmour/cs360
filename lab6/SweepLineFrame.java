import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.io.*;

class SweepLineFrame extends JFrame{
    private static final int FRAME_WIDTH = 1000;
    private static final int FRAME_HEIGHT = 1000;
    private static final int FRAME_X_ORIGIN = 150;
    private static final int FRAME_Y_ORIGIN = 250;




    public SweepLineFrame(ArrayList<Rectangle> input){
        //set frame default properties
        setTitle("Sweep Line");
        setSize(FRAME_WIDTH,FRAME_HEIGHT);
        setLocation(FRAME_X_ORIGIN,FRAME_Y_ORIGIN);

        //set layout
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout(10,2));

        //create panels
        DrawPanel draw = new DrawPanel(input);
        ControlPanel control = new ControlPanel();
        draw.setBorder(BorderFactory.createLoweredBevelBorder());
        control.setBorder(BorderFactory.createLoweredBevelBorder());
        


        //add panels to frame
        contentPane.add(draw,BorderLayout.CENTER);
        contentPane.add(control,BorderLayout.EAST);


        //close program when exiting the frame
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


    private void changeBgColor(){
        Container contentPane = getContentPane();
        contentPane.setBackground(Color.BLUE);
    }

    // reads in rectangles from file numbers.txt
    private static ArrayList<Rectangle>getInput()throws FileNotFoundException{
        ArrayList<Rectangle> temp = new ArrayList<Rectangle>();
        String line = new String();
        try{
            Scanner s = new Scanner(new File("numbers.txt"));
            //fill rectangle array
            do{
                line = s.nextLine();
                String[] rect = line.split(" ");
                temp.add(new Rectangle(Integer.parseInt(rect[0]),Integer.parseInt(rect[1]),Integer.parseInt(rect[2]),Integer.parseInt(rect[3])));

            }while(s.hasNextLine());

        }
        catch (FileNotFoundException e){
            System.out.println("error no numbers.txt");
            return temp;
        }

        return temp;

    }


    public static void main(String[] a)throws FileNotFoundException{
        ArrayList<Rectangle>input = new ArrayList<Rectangle>();
        // manually creating array for now, will read it from file later.
        /*
        Rectangle r0 = new Rectangle(0,0,4,4);
        Rectangle r1 = new Rectangle(2,2,1,1);
        Rectangle r2 = new Rectangle(1,1,5,5);
        */
        // test rectangles from the doc
        Rectangle r0 = new Rectangle(120,100,58,58);
        Rectangle r1 = new Rectangle(160,140,30,32);
        Rectangle r2 = new Rectangle(225,164,55,24);
        //input.add(r0);
        //input.add(r1);
        //input.add(r2);
        
        input =getInput();

        SweepLineFrame sweep = new SweepLineFrame(input);
        
        sweep.setVisible(true);

    }

}
    

