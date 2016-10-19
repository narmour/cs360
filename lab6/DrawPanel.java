import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
class DrawPanel extends JPanel{
    private ArrayList<Rectangle> input;

    public DrawPanel(ArrayList<Rectangle> i){
        input = i;
        setLayout(new GridLayout(10,0));
        setBackground(Color.WHITE);


        //paint all the rectangles
        repaint();

    }






    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        for(Rectangle r: input){
            //g.fillRect(r.x,r.y,r.width,r.height);
            g.drawRect(r.x,r.y,r.width,r.height);

        }
    }

        



}
