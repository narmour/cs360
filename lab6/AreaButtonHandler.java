import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class AreaButtonHandler implements ActionListener{
    private SweepLine sweep;
    private ArrayList<Rectangle> input;




    public AreaButtonHandler(ArrayList<Rectangle> i){
        input = i;
        sweep = new SweepLine(input);

    }


    public void actionPerformed(ActionEvent e){

        double area =0;

        JButton clicked = (JButton)e.getSource();

        JRootPane pane = clicked.getRootPane();
        Frame frame = (JFrame)pane.getParent();

        area = sweep.computeArea();


        JOptionPane.showMessageDialog(frame,area);


    }




}
