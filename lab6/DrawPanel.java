import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
class DrawPanel extends JPanel{
    private ArrayList<Rectangle> input;
    private ArrayList<Rectangle> anim;

    public DrawPanel(ArrayList<Rectangle> i){
        input = i;
        setLayout(new GridLayout(10,0));
        setBackground(Color.WHITE);

        SweepLine s = new SweepLine(input);
        //generate anim retangles
        s.computeArea();
        anim = s.animRectangles();




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
        Rectangle t = anim.get(0);
        g.fillRect(t.x,t.y,t.width,t.height);

        t = anim.get(1);
        g.setColor(Color.RED);
        g.fillRect(t.x,t.y,t.width,t.height);

        t=anim.get(2);
        g.setColor(Color.BLACK);
        g.fillRect(t.x,t.y,t.width,t.height);

        t = anim.get(3);
        g.setColor(Color.YELLOW);
        g.fillRect(t.x,t.y,t.width,t.height);
        
    }

        



}
