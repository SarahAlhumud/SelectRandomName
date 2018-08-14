import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.*;

class SelectRandomFrame {


    private static JFrame frame;
    private static JButton startButton , stopButton ;
    private static JLabel l4 ;
    private static JPanel p1 ;
    private static ArrayList<String> res = new ArrayList <> ();
    Random rand = new Random();
    Timer timer ;
    int flag ;
    private static String[] temp;
    boolean isTimerStart = false ;



    public SelectRandomFrame() {

        frame = new JFrame();

        frame.getContentPane().setBackground(Color.white);

        p1 = new JPanel();
        p1.setLayout(new BoxLayout(p1,BoxLayout.Y_AXIS));
        p1.setBackground(Color.WHITE);
        frame.add(p1);

        //the top image
        Icon img1=new ImageIcon("bd2.png");
        JLabel l1=new JLabel("",img1,SwingConstants.LEFT);
        l1.setAlignmentX(Component.CENTER_ALIGNMENT);
        p1.add(l1);

        JLabel l2=new JLabel("أضغط زر أبدأ لبدء السحب",SwingConstants.CENTER);
        l2.setAlignmentX(Component.CENTER_ALIGNMENT);
        l2.setFont(new Font("Times",Font.BOLD,16));
        p1.add(l2);

        p1.add(Box.createRigidArea(new Dimension(10, 10)));

        startButton=new JButton("أبدأ");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        p1.add(startButton);

        p1.add(Box.createRigidArea(new Dimension(10, 10)));

        JLabel l3=new JLabel("الفائز هو   ",SwingConstants.CENTER);
        l3.setAlignmentX(Component.CENTER_ALIGNMENT);
        l3.setFont(new Font("Times",Font.BOLD,16));
        p1.add(l3);

        p1.add(Box.createRigidArea(new Dimension(10, 10)));

        l4=new JLabel("",SwingConstants.CENTER);
        l4.setAlignmentX(Component.CENTER_ALIGNMENT);
        l4.setFont(new Font("Times",Font.BOLD,20));
        p1.add(l4);

        p1.add(Box.createRigidArea(new Dimension(10, 10)));

        // Stop Button
        stopButton = new JButton("توقف") ;
        stopButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        p1.add(stopButton);

        p1.add(Box.createRigidArea(new Dimension(10, 10)));

        //the event for start and stop Buttons
        event e = new event();
        startButton.addActionListener(e);
        stopButton.addActionListener(e);

        //the bottom image the same as top image
        Icon img2=new ImageIcon("BD.png");
        JLabel l5=new JLabel("",img2,SwingConstants.CENTER);
        l5.setAlignmentX(Component.CENTER_ALIGNMENT);
        p1.add(l5);

        //read from file to ArrayList
        try
        {

            File f = new File("input.in");
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
            String line;
            line =br.readLine();
            int count=0;
            Scanner input=new Scanner(new File("input.in"));

            while(line != null)
            {
                //count++;
                //input.next();
                temp = line.split(" ");
                String name=" ";
                for(int j=0 ; j<temp.length;j++)
                {
                    name= name + temp[j]+ " ";

                }
                res.add(name);
                line=br.readLine();
            }
            /*temp=new String[count];
             Scanner input2=new Scanner(new File("input.in"));
            for(int i=0 ; i<count;i++)
            {
                temp[i]=input2.next();
            }

            for(int j=0 ; j<temp.length;j++)
            {
                res.add(temp[j]+ " "+ temp[j+1]+" "+temp[j+2]);
                j+=2;
            }*/


        }
        catch(IOException ee)
        {
            System.out.print(ee);
        }

        frame.setLayout(new FlowLayout());
        frame.setVisible(true);
        frame.setSize(360,380);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(400,200);

    }

    class event implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e)
        {

            if (e.getSource() == startButton && !isTimerStart)
            {
                shuffle(res); //shuffle array
                int counter = res.size() ;
                TimeClass tc = new TimeClass(counter);
                timer = new Timer(50 , tc); //the time between each name
                timer.start();
                isTimerStart = true ;

            }

            else if (e.getSource() == stopButton)
            {
                timer.stop();
                isTimerStart = false ;
                l4.setText(res.get(flag));
                Toolkit.getDefaultToolkit().beep();
            }
        }
    }

    public class TimeClass implements ActionListener {

        private int x ;

        public TimeClass(int a){
            x = a ;
        }

        public void actionPerformed(ActionEvent e)
        {
            x-- ;

            if(x >= 0){
                System.out.println(res.get(x));
                l4.setText(res.get(x));
                flag = x ;
                if(x == 0){
                    System.out.println("---");
                }
            }
            else {
                timer.stop();
                Toolkit.getDefaultToolkit().beep();
            }
        }
    }


    public void shuffle(ArrayList<String> array){

        for(int first = 0 ; first <array.size() ; first++){
            int second = rand.nextInt(array.size());

            String temp = array.get(first) ;
            array.set(first, array.get(second)) ;
            array.set(second, temp) ;
        }//end for
    }
}


public class SelectRandom{
    public static void main(String[] args) {
        new SelectRandomFrame();
    }
}




