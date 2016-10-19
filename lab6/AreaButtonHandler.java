import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class AreaButtonHandler implements ActionListener{
    private SweepLine sweep;
    private ArrayList<Rectangle> input;




    public AreaButtonHandler(){
        // manually creating array for now, will read it from file later.
        input = new ArrayList<Rectangle>();
        Rectangle r0 = new Rectangle(0,0,4,4);
        Rectangle r1 = new Rectangle(2,2,1,1);
        Rectangle r2 = new Rectangle(1,1,5,5);
        
        input.add(r0);
        input.add(r1);
        input.add(r2);

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
