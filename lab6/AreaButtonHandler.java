import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class AreaButtonHandler implements ActionListener{
    private ArrayList<Rectangle> input;




    public AreaButtonHandler(){

    }


    public void actionPerformed(ActionEvent e){

        double area =0;
        //get the clicked button
        JButton clicked = (JButton)e.getSource();

        //get the frame from the button
        JRootPane pane = clicked.getRootPane();
        JFrame frame = (JFrame)pane.getParent();

        //start the animation
        DrawPanel d = (DrawPanel)frame.getContentPane().getComponent(0);
        d.startTimer();


        



    }




}
