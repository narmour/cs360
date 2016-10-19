import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

class ControlPanel extends JPanel{
    private JButton areaButton;
    private AreaButtonHandler handler;

    public ControlPanel(ArrayList<Rectangle> i){
        

        //place a button
        areaButton = new JButton("Calculate Area");
        add(areaButton);

        //add button to handler
        handler= new AreaButtonHandler(i);
        areaButton.addActionListener(handler);
    }


}
