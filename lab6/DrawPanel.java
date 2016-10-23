import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
class DrawPanel extends JPanel implements ActionListener{
    private ArrayList<Rectangle> input;
    private ArrayList<ArrayList<Rectangle>> sweepRectangles;

    private ArrayList<Rectangle> anim;


    // line coords
    private int x1;private int y1;
    private int x2;private int y2;
    private int temp;


    public DrawPanel(ArrayList<Rectangle> i){
        input = i;
        setLayout(new GridLayout(10,0));
        setBackground(Color.WHITE);

        SweepLine s = new SweepLine(input);
        //generate sweep retangles
        s.computeArea();
        sweepRectangles = s.animRectangles();
        anim = animRectangles(sweepRectangles);

        //init the draw line to start at first animRectangle
        Rectangle r = anim.get(0);
        x1 = r.x - 10;
        y1 = r.y;
        x2 = maxLength(anim) +10;
        y2 = r.y;
        temp =0;
        System.out.println("FIRST RECTANGLE: " +r.x + " " + r.y + "     " + r.width  + "   " + r.height);



        //start animation timer
        Timer t = new Timer(100,(ActionListener)this);
        t.start();
        






    }

    public void actionPerformed(ActionEvent e){
        if(y1 < maxHeight(anim)){
            y1++;
            y2++;
        }

        // find out what anim rectangle the line is at
        for(int i =0;i < anim.size();i++){
            if(anim.get(i).y+anim.get(i).height > y1){
                temp = i;
                break;
            }
        }
        repaint();

    }
    //given an arrayList of rectangles, return max x cooridinate
    public int maxLength(ArrayList<Rectangle> a){
        int max =0;
        for(Rectangle r:a){
            if(r.x + r.width > max)
                max = r.x+r.width;
        }
        return max;


    }
    //given an arrayList of rectangles, return max y coordinate
    public int maxHeight(ArrayList<Rectangle>a){
        int max =0;
        for(Rectangle r:a){
            if(r.y + r.height > max)
                max = r.y + r.height;
        }
        return max;
    }
    // given an arraylist<arraylist<rectangle>> return the total sweep rectangles
    public ArrayList<Rectangle> animRectangles(ArrayList<ArrayList<Rectangle>> a){
        ArrayList<Rectangle> sweeps = new ArrayList<Rectangle>();
       for(ArrayList<Rectangle> rectangles: a){
            int minX =9999;int maxX =0;
            int minY =9999;int maxY =0;
                for(Rectangle r:rectangles){
                    /*
                    System.out.println("CREATED RECTANGLE: " +r.x+" "+r.y+" "+r.width+" "+r.height);
                    if(r.x < minX)
                        minX = r.x;

                    if(r.y<minY)
                        minY = r.y;
                    if(r.x+r.width > maxX)
                        maxX = r.x+r.width;
                    if(r.y+r.height > maxY)
                        maxY = r.y+r.height;
                    */
                    minX = (r.x < minX) ? r.x:minX;
                    minY = (r.y < minY) ? r.y:minY;
                    maxX = (r.x+r.width > maxX) ? r.x+r.width:maxX;
                    maxY = (r.y+r.height > maxY)? r.y+r.height:maxY;
                }
                //System.out.println(minX);
                Rectangle r = new Rectangle(minX,minY,maxX-minX,maxY-minY);
                // im not positive why this happens, should fix)
                if(minX!=9999)
                    sweeps.add(r);
           }
       return sweeps;
    }
    
    
    






    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        // draw the input rectangles onto the screen
        g.setColor(Color.BLUE);
        for(Rectangle r: input){
            g.drawRect(r.x,r.y,r.width,r.height);

        }
        // draw line
        g.setColor(Color.RED);
        g.drawLine(x1,y1,x2,y2);

        // draw animation rectangles
        Color current = Color.RED;
        Color other = Color.YELLOW;
        for(int i =0;i < anim.size();i++){
            Rectangle r = anim.get(i);
            if(temp == i)
                g.setColor(current);
            else
                g.setColor(other);
            g.fillRect(r.x,r.y,r.width,r.height);
        }


        /*

        t = anim.get(1);
        g.setColor(Color.RED);
        g.fillRect(t.x,t.y,t.width,t.height);

        t=anim.get(4);
        g.setColor(Color.BLACK);
        g.fillRect(t.x,t.y,t.width,t.height);

        t = anim.get(5);
        g.setColor(Color.RED);
        g.fillRect(t.x,t.y,t.width,t.height);

        t = anim.get(6);
        g.setColor(Color.RED);
        g.fillRect(t.x,t.y,t.width,t.height);
        
        t = anim.get(7);
        g.setColor(Color.BLUE);
        g.fillRect(t.x,t.y,t.width,t.height);
        */



    }

        



}
