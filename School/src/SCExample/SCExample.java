package SCExample;

//File: SorcerersCave.java
//Date: Jun 21, 2013
//Author: Nicholas Duchon
//Purpose: demonstrate the development of a project - 
// in this case, the Sorcerer's Cave project

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import java.util.ArrayList;

public class SCExample extends JFrame {
 static final long serialVersionUID = 123L;
 
 JTextArea jta = new JTextArea ();
 JComboBox jcb;
 JTextField jtf;
 Cave cave = new Cave ();

 public SCExample () {
     System.out.println ("In constructor");
     setTitle ("Sorcerer's Cave");
     setSize (600, 300);
     setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
     setVisible (true);
     
     JScrollPane jsp = new JScrollPane (jta);
     add (jsp, BorderLayout.CENTER);
     
     JButton jbr = new JButton ("Read");
     JButton jbd = new JButton ("Display");
     JButton jbs = new JButton ("Search");
     
     JLabel jls = new JLabel ("Search target");
     
     jtf = new JTextField (10);
     
     jcb = new JComboBox ();
     jcb.addItem ("Index");
     jcb.addItem ("Type");
     jcb.addItem ("Name");
     
     JPanel jp = new JPanel ();
     jp.add (jbr);
     jp.add (jbd);
     jp.add (jls);
     jp.add (jtf);
     jp.add (jcb);
     jp.add (jbs);
     
     add (jp, BorderLayout.PAGE_START);
     
     validate ();
     
     jbr.addActionListener ( new ActionListener () {
             public void actionPerformed (ActionEvent e) {
                 readFile ();
             } // end required method
         } // end local definition of inner class
     ); // the anonymous inner class
     
     jbd.addActionListener ( new ActionListener () {
             public void actionPerformed (ActionEvent e) {
                 displayCave ();
             } // end required method
         } // end local definition of inner class
     ); // the anonymous inner class
     
     jbs.addActionListener ( new ActionListener () {
             public void actionPerformed (ActionEvent e) {
                 search ((String)(jcb.getSelectedItem()), jtf.getText());
             } // end required method
         } // end local definition of inner class
     ); // the anonymous inner class
 } // end no-parameter constructor
 
 public void readFile () {
     jta.append ("Read File button pressed\n");
     
     Party p; Creature c;
     cave.parties.add (p = new Party ("party A"));
     p.members.add (c = new Creature ("Creature CA"));
     c.artifacts.add (new Artifact ("art aa"));
     c.artifacts.add (new Artifact ("art ab"));
     c.artifacts.add (new Artifact ("art ac"));
     c.treasures.add (new Treasure ("trs ta"));
 } // end method readFile
 
 public void displayCave () {
     jta.append ("Display Cav button pressed\n");
     jta.setText ("" + cave);
 } // end method readFile
 
 public void search (String type, String target) {
     jta.append (String.format ("Search button pressed, type: >%s<, target: >%s<\n", type, target));
 } // end method readFile
 
 public static void main (String [] args) {
     SCExample sc = new SCExample ();
 } // end main
} // end class SorcerersCave

class Cave {
 ArrayList <Party> parties = new ArrayList <Party> ();
 ArrayList <CaveElement> stuff = new ArrayList <CaveElement> ();
 
 public String toString () {
     String st = "Cave.toString:\nThe Parties\n";
     for (Party p: parties) 
         st += p + "\n";
     st += "\n+++++++\nThe unassociated stuff:\n";
     for (CaveElement e: stuff)
         st += e + "\n";
     return st;
 } // end toString method
} // end class Cave

class CaveElement {
 String name = ""; // make sure we have a default value here, NOT null
 int index = 0;
 
 public String toString () {
     return "CaveElement: " + name;
 } // end method toString
} // end class CaveElement

class Party extends CaveElement {
 ArrayList <Creature> members = new ArrayList <Creature> ();
 
 public Party (String n) {name = n;}
 
 public String toString () {
     String st = "    Members:\n";
     for (Creature c: members) 
         st += "    " + c + "\n";
     return st;
 } // end method toString
} // end class CaveElement

class Creature extends CaveElement {
 ArrayList <Treasure> treasures = new ArrayList <Treasure> ();
 ArrayList <Artifact> artifacts = new ArrayList <Artifact> ();

 public Creature (String n) {name = n;}
 
 public String toString () {
     String st = "    " + name + "\n       Artifacts:\n";
     for (Artifact a: artifacts) 
         st += "        " + a + "\n";
     st += "       Treasures:\n";
     for (Treasure t: treasures) 
         st += "        " + t + "\n";
     return st;
 } // end method toString
} // end class Creature

class Artifact extends CaveElement {

 public Artifact (String n) {name = n;}
 
 public String toString () {
     return "          " + name;
 } // end method toString
} // end class Artifact

class Treasure extends CaveElement {

 public Treasure (String n) {name = n;}
 
 public String toString () {
     return "          " + name;
 } // end method toString
} // end class Treasure