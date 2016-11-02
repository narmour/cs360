import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
class TimerSlideHandler implements ChangeListener{

    public void stateChanged(ChangeEvent e){
        //get the slider
        JSlider s = (JSlider)e.getSource();

        //get the frame from the slider
        JRootPane pane = s.getRootPane();
        JFrame frame = (JFrame)pane.getParent();

        //set new time
        DrawPanel d = (DrawPanel)frame.getContentPane().getComponent(0);
        d.changeTimer(s.getValue());







    }
}



