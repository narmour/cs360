import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

class ControlPanel extends JPanel{
    private JButton areaButton;
    private JSlider slider;
    private JTextArea text;
    private AreaButtonHandler handler;

    public ControlPanel(){

        // set layout
        setLayout(new GridLayout(3,1));
        

        //place a button
        areaButton = new JButton("Calculate Area");
        add(areaButton);

        //add button to handler
        handler= new AreaButtonHandler();
        areaButton.addActionListener(handler);



        //add slider
        slider = new JSlider(JSlider.HORIZONTAL,-200,-20,-200);
        slider.addChangeListener(new TimerSlideHandler());
        add(slider);

        //place a textfield
        text = new JTextArea();
        add(text);

    }


}
