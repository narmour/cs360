import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
class DrawPanel extends JPanel implements ActionListener{
    private ArrayList<Rectangle> input;
    private ArrayList<Rectangle> sweepRectangles;

    private ArrayList<Rectangle> anim;


    // line coords
    private int x1;private int y1;
    private int x2;private int y2;
    private int temp;
	private int curY;
	private ArrayList<Integer> yRange;

    private Timer t;
    private SweepLine s;


    public DrawPanel(ArrayList<Rectangle> i){
        input = i;
        setLayout(new GridLayout(10,0));
        setBackground(Color.WHITE);

        s = new SweepLine(input);
        //generate sweep retangles
        s.computeArea();
        anim = s.animRectangles();

        t = new Timer(200,(ActionListener)this);

        //init the draw line to start at first animRectangle
        Rectangle r = anim.get(0);
        x1 = 0;
        y1 = r.y;
        x2 = maxLength(anim) +10;
        y2 = r.y;
		
		// get y ranges
		yRange = yRanges(anim);
		temp =0;
		curY=yRange.get(0);
		
		System.out.println("CURY: " + curY);
        System.out.println("RANGE SIZE: " + yRange.size() + " ANIM AREA SIZE: " + s.animAreas().size());



        


    }

    public void startTimer(){
        //init the draw line to start at first animRectangle
        Rectangle r = anim.get(0);
        x1 = 0;
        y1 = r.y;
        x2 = maxLength(anim) +10;
        y2 = r.y;
        temp=0;

        //start animation timer
        t.restart();
    }

    public void changeTimer(int delay){
        //stop the timer
        if(t.isRunning()){
            t.stop();
            t = new Timer(-1*delay,(ActionListener)this);
            t.start();
        }
        else
            t = new Timer(-1*delay,(ActionListener)this);
            
    }	


    public void actionPerformed(ActionEvent e){

        //get textArea from ControlPanel
        JRootPane pane = this.getRootPane();
        JFrame frame = (JFrame)pane.getParent();
        ControlPanel c = (ControlPanel)frame.getContentPane().getComponent(1);
        JTextArea text = (JTextArea)c.getComponent(2);

	    //update prey and curY
	    if(y1 >= curY){

   	        temp++;
            if(temp < s.animAreas().size() && s.animAreas().get(temp)!=0)
                text.append(s.animAreas().get(temp) + "\n");
            if(temp < yRange.size())	
                curY = yRange.get(temp);
	    }
        if(y1 < maxHeight(anim)+1){
            y1++;
            y2++;
        }
        //else anim is finished print out result and reset values
        else{

            JOptionPane.showMessageDialog(this,s.computeArea());
            t.stop();
       	    temp=0;
	        curY=yRange.get(0);
            text.setText("");
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


	public ArrayList<Integer> yRanges(ArrayList<Rectangle> a){
		ArrayList<Integer> t = new ArrayList<Integer>();
		
		for (Rectangle r:a){
			if(!t.contains(r.y+r.height))
				t.add(r.y+r.height);
		}
		return t;
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
  //        Color current = Color.RED;
  //      Color other = Color.YELLOW;
        Color current = new Color(0,0,255,127);
        Color other = new Color(0,255,0,127);
        for(int i =0;i < anim.size();i++){
            Rectangle r = anim.get(i);
			if(r.y+r.height < y1)
				g.setColor(current);
			else
				g.setColor(other);
			if(t.isRunning())
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
