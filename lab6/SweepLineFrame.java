import javax.swing.*;
import java.awt.*;
import java.util.*;

class SweepLineFrame extends JFrame{
    private static final int FRAME_WIDTH = 300;
    private static final int FRAME_HEIGHT = 200;
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


    public static void main(String[] a){
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
        input.add(r0);
        input.add(r1);
        input.add(r2);

        SweepLineFrame sweep = new SweepLineFrame(input);
        
        sweep.setVisible(true);

    }

}
    

