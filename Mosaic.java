/*
NAME: JONATHAN VILLA
ASSIGNMENT: PROGRAMMING ASSIGNMENT #4
DATE : 2/9/2020
*/

import javax.swing.JFrame; 
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;
import javax.swing.Action;
import javax.swing.JButton;

import java.awt.GridLayout; 
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent; 

import java.awt.Graphics; 
import java.awt.Color;
import java.awt.Font;

import java.util.ArrayList;
import java.util.Random; 

class XAndOTile extends JPanel implements MouseListener{

    //ColorContrast
    private int red, green, blue; 

    //AlphaBet Randomize
    private String  [] alphaStrings = {"A","B","C","D","E","F","G","H","I","J","K","L",
    "M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

    //Letters to place in the shapes 
    private String letter;
    private int squareCircle; 
    private Face myFace;

    //Boolean for facedraw
    private boolean faceDraw = false;

    //DefaultConstructor
    XAndOTile(){
        super();

        //Mouse action 
        addMouseListener(this);

        //Invoke the face and provide dimensions for the constructor
        myFace = new Face(0,0,70,70);

        //Sets the random value 
        SetRandomValue();
    }

    final public void SetRandomValue(){

        //Contrast instances
        red = GetNumberBetween(0 , 255);
        green = GetNumberBetween(0, 255);
        blue = GetNumberBetween(0, 255);

        //Generate random letters
        letter = alphaStrings[GetNumberBetween(0, 25)];

        //Generate square and circle
        squareCircle = GetNumberBetween(0, 1);
}

        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            //FontSize
            final int fontSize = 20;

            //Get the panels width/height
            int panelWidth = getWidth();
            int panelHeight = getHeight(); 

            //Construct the color
            g.setColor(new Color(red,green,blue));
            
            //Generate square or circle
            if(squareCircle == 0){
                g.fillRect(0, 0, panelWidth, panelHeight);
            }else if(squareCircle == 1){
            g.fillOval(0, 0, panelWidth, panelHeight);
             
        }
            //Generate random colors
            g.setColor(new Color(GetContrastingColor(red),GetContrastingColor(green), GetContrastingColor(blue)));
            System.out.println("Letter Drawn" + letter + "\nColors: " + red + "," + blue + "," + green + "," );

            //Font size and type of font
             g.setFont(new Font("Times New Roman", Font.PLAIN, fontSize));

             //Place the font in the shapes
             int stringX = (panelWidth/2) - 6; 
             int stringY = (panelHeight/2) + 10; 
             g.drawString(letter, stringX, stringY);

            //Draw face when true return face drawn
            if(faceDraw){
                myFace.paintComponent(g);
                  return;
              }
        }

        //Draw face when mouseClicked
        public void mouseClicked (MouseEvent e){
            System.out.println("You have click an item");
            faceDraw = true;
            repaint();
        }

        //Draw face when mousePressed
        public void mousePressed(MouseEvent e){
            System.out.println("You have pressed");
            faceDraw = true;
            repaint();
        }
        
        //When mouseReleased remove face
        public void mouseReleased(MouseEvent e){
            System.out.println("You have released");
            faceDraw = false; 
            repaint();
        }

        //When mouseExited remove face
        public void mouseExited(MouseEvent e){
            faceDraw = false;
            repaint();
        }

        //Detects mouse is in frame
        public void mouseEntered(MouseEvent e){
            System.out.println("Mouse entered");
        }
    
    //This method generates min and max
    private static int GetNumberBetween(int min, int max){
        Random myRandom = new Random();
        return min + myRandom.nextInt(max - min+1);
    }

    //This Method contains the contrast
    private static int GetContrastingColor(int colorIn){
        return ((colorIn+128) %200 );
    }
}

class MosaicLiteFrame extends JFrame implements ActionListener{
    private ArrayList<XAndOTile> tileList; 

    //Constructor for the frame
   public MosaicLiteFrame(){

    //Dimensions of the frame
    setBounds(300,300,800,800);

    //Allows the user to close the application
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //Content pane for the frame 
    Container contentPane = getContentPane();
    contentPane.setLayout(new BorderLayout());

    //JPanel for the frame
    JPanel buttonPanel = new JPanel();
    contentPane.add(buttonPanel, BorderLayout.SOUTH);

    //Instance of the button and add it the frame
    JButton randomize = new JButton("Randomize");
    buttonPanel.add(randomize);
    randomize.addActionListener(this);

    //The grid panel 12 x 12
    JPanel xAndGridPanel = new JPanel();
    contentPane.add(xAndGridPanel, BorderLayout.CENTER);
    xAndGridPanel.setLayout(new GridLayout(12,12));

    //ArrayList for XAndOTile
    tileList = new ArrayList<XAndOTile>();

    //Print out the 12 x 12 grid 
    for(int i = 1; i <= 144; i++){
        XAndOTile tile = new XAndOTile(); 
        xAndGridPanel.add(tile);
        tileList.add(tile);
    }
   }

   //Randomize the shapes and colors by the Click button Listener
   public void actionPerformed(ActionEvent e) {
       for(XAndOTile tile: tileList){
           tile.SetRandomValue(); 
       }
       repaint();
   }
}

public class Mosaic{
    public static void main(String [] args){

        //Invoke the frame
        MosaicLiteFrame myMosaicLiteFrame = new MosaicLiteFrame();
        myMosaicLiteFrame.setVisible(true);  
    }

}