import javax.swing.*;
import java.awt.*;
import java.util.*;

class SweepLineFrame extends JFrame{
    private static final int FRAME_WIDTH = 300;
    private static final int FRAME_HEIGHT = 200;
    private static final int FRAME_X_ORIGIN = 150;
    private static final int FRAME_Y_ORIGIN = 250;


    private JButton areaButton;
    private AreaButtonHandler handler;


    public SweepLineFrame(){
        //set frame default properties
        setTitle("Sweep Line");
        setSize(FRAME_WIDTH,FRAME_HEIGHT);
        setLocation(FRAME_X_ORIGIN,FRAME_Y_ORIGIN);

        //set layout
        Container contentPane = getContentPane();
        contentPane.setLayout(new FlowLayout());

        //place a button
        areaButton = new JButton("Calculate Area");
        contentPane.add(areaButton);

        //add button to handler
        handler= new AreaButtonHandler();
        areaButton.addActionListener(handler);



        //close program when exiting the frame
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


    private void changeBgColor(){
        Container contentPane = getContentPane();
        contentPane.setBackground(Color.BLUE);
    }


    public static void main(String[] a){

        SweepLineFrame sweep = new SweepLineFrame();
        
        sweep.setVisible(true);

    }

}
    

