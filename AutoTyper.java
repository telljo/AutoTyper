//Created by Josh Tell 2017

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class AutoTyper extends JPanel {
  private JButton add = new JButton("Add"); //new button for adding message
  private JButton delete = new JButton("Delete"); //new button for deleting message
  private JButton run = new JButton("Start"); //new button for running auto typer
  private JButton stop = new JButton("Stop"); //new button for stopping auto typer
  
  
  private JLabel messageRate = new JLabel("Message Rate:"); //new label for prompting user
  private JTextField mRateInput = new JTextField("1"); //new text field for input
  
  private JLabel mDelayLabel = new JLabel("Start typing after:"); //new label for prompting user
  private JTextField mDelayInput = new JTextField("1"); //new text field for input
  private JLabel mDelayLabel1 = new JLabel("second"); //new label for prompting user
  
   private JLabel numTimesLabel = new JLabel("Times to type:"); //new label for prompting user
  private JTextField numTimesInput = new JTextField("10"); //new text field for input

  
  private JLabel messageLabel = new JLabel("Message:"); //new label for prompting user
  private JTextField inputBox = new JTextField(15); //new text field for input
  
  //private JTextField messageIndexInput = new JTextField(5); //new text field for input
  private JTextField typeOrder = new JTextField(40);
  public static String message = "";
  public static float mRate = 0;
  public static float mDelay = 1;
  public static int numTimes;
  public Keyboard typer;
  public int stopCount = 0;
  public int textFieldCount = 0;
  int currentTextField = 0;
  final JPanel topGrid = new JPanel();
  public static List<JTextField> listOfTextFields = new ArrayList<JTextField>();
  //public TyperClass typer = new TyperClass(message,mRate);
  
  
  
  // private String[] messageList = {""};
  
  
  /**main method - sets up JFrame*/
  public static void main(String [] args) throws Exception{
    
    
    JFrame frame = new JFrame("Auto Typer");
    frame.setContentPane(new AutoTyper());
    frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
    
    
    
  }
  
  /** Constructor for Auto Typer */
  public AutoTyper() {
    
    //new buttonlistener
    ButtonListener listener = new ButtonListener();
    
    
    //new jpanel for bottom row conversion
    JPanel bottomGrid = new JPanel();
    
    //adds listeners to the buttons
    add.addActionListener(listener);
    delete.addActionListener(listener);
    inputBox.addActionListener(listener);
    run.addActionListener(listener);
    stop.addActionListener(listener);
    
    typeOrder.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        System.out.println("Mouse Clicked");
      }
    });
    
    
    //gives the panels sizes
    topGrid.setPreferredSize(new Dimension(500,300));
    topGrid.setBackground(Color.white);
    topGrid.removeAll();
    typeOrder.setBackground(Color.white);
    bottomGrid.setPreferredSize(new Dimension(500,120));
    
    
    //adds all the features
    topGrid.setLayout(new BorderLayout());
    
    setLayout(new BorderLayout());
    add(topGrid,BorderLayout.NORTH);
    add(bottomGrid,BorderLayout.SOUTH);
    //topGrid.setLayout(new BorderLayout());
    
    GridBagConstraints frameConstraints = new GridBagConstraints();
    frameConstraints.gridx = 0;
    frameConstraints.gridy = 0;
    topGrid.add(typeOrder,BorderLayout.NORTH);
    
    
    
    
    bottomGrid.add(messageRate);
    
    bottomGrid.add(mRateInput);
    bottomGrid.add(messageLabel);
    bottomGrid.add(inputBox);
    bottomGrid.add(add);
    bottomGrid.add(mDelayLabel);
    bottomGrid.add(mDelayInput);
    bottomGrid.add(mDelayLabel1);
    bottomGrid.add(delete);
    bottomGrid.add(run);
    bottomGrid.add(stop);
    bottomGrid.add(numTimesLabel);
    bottomGrid.add(numTimesInput);
    
    mRateInput.setColumns(3);
    mDelayInput.setColumns(3);
    typeOrder.setEditable(false);
    
    
    
    
    
//    ActionListener cbActionListener = new ActionListener() {
//      public void actionPerformed(ActionEvent a){
//        
//         if(a.getSource()==add) {
//           
//           listOfTextFields.add(new JTextField());
//           
//           System.out.println("hi");
//           String textFieldName = "typeOrder"+textFieldCount;
//           textFieldCount++;
//           topGrid.add(typeOrder,BorderLayout.NORTH);
//           
//       
//        
//      }
//        
//      }
//    };
    
    
  }
  
  private class ButtonListener implements ActionListener {
    
    
    int typerCount = 0;
    /** what to do when a button has been pressed */
    public void actionPerformed(ActionEvent aE) {
      
      if(aE.getSource()==add) {
        
        message = inputBox.getText();
        
        
        // listOfTextFields.get(currentTextField).setText(message);
        typeOrder.setText(message);
      }
      
      
      else if(aE.getSource()==delete){
        message = "";
        
        typeOrder.setText(message);
        //typeOrder.setText(message);
        
      }
      
      
      else if(aE.getSource()==run) {
        
        mDelay = Float.parseFloat(mDelayInput.getText());
        mRate = Float.parseFloat(mRateInput.getText());
        numTimes = Integer.parseInt(numTimesInput.getText());
        typer = new Keyboard(message,mRate,mDelay,numTimes);
        
        
        System.out.println(mRate);
        
        if(!(message.equals(""))){
          try{
            subject1.start();
          }catch(Exception e){
            
          }
        } 
        //t1.start();
        
      }
      else if(aE.getSource()==stop){
        
        stopCount = 0;
        while(stopCount<=1){
          typer.stop();
          interruptThread();
          subject1.interrupt();
          
          
          stopCount++;
        }
        typer = null;
      }
    }
    
    
    
    
    
    final Thread subject1 = new Thread(new Runnable() {
      
      public void run() {
        while (!Thread.interrupted()) {
          
          try{
            typer.run(message,mRate,mDelay,numTimes);
          }catch(Exception e){
            
          }
          
        }
        System.out.println("subject 1 stopped!");
      }
    });
    
    public void interruptThread(){
      
      subject1.interrupt();
      
    }
  }
  
  
  
  
}







