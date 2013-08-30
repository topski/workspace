// File: CreateDataFile.java
// Date: May 21, 2013
// Author: Nicholas Duchon
// Purpose: create large data file for Sorcer's Cave example

   import java.util.Scanner;
   import java.util.ArrayList;
   import java.util.Random;

   import java.io.File;
   import java.io.PrintWriter;
   import java.io.IOException;

   import javax.swing.JOptionPane;
   import javax.swing.JFrame;
   import javax.swing.JPanel;
   import javax.swing.JButton;
   import javax.swing.JFileChooser;
   import javax.swing.filechooser.FileNameExtensionFilter;
   import javax.swing.JSpinner;
   import javax.swing.JLabel;
	
   import java.awt.BorderLayout;
   import java.awt.GridLayout;
   import java.awt.event.ActionListener;
   import java.awt.event.ActionEvent;

   public class CreateDataFile implements ActionListener {
      static Random rn = new Random ();
      int indexParty    = 10000;
      int indexCreature = 20000;
      int indexTreasure = 30000;
      int indexArtifact = 40000;
      int indexJob      = 50000;
   	
      JFrame jf;
      int numSpinnners = 7;
      JSpinner [] jsp = new JSpinner [numSpinnners];
   
      PrintWriter pw;
      String []             templates = new String [6];                  // headers for each section
      ArrayList <String>       namesM = new ArrayList <String>       (); // male names
      ArrayList <String>       namesF = new ArrayList <String>       (); // female names
      ArrayList <Party>        namesG = new ArrayList <Party>        (); // party names
      ArrayList <CreatureType> namesC = new ArrayList <CreatureType> (); // Creature types
      ArrayList <TreasureType> namesT = new ArrayList <TreasureType> (); // Treasure types
      ArrayList <ArtifactType> namesA = new ArrayList <ArtifactType> (); // Artifact types
      ArrayList <StoneType>    namesS = new ArrayList <StoneType>    (); // Stone types
      ArrayList <PotionType>   namesP = new ArrayList <PotionType>   (); // Potion types
      ArrayList <WandType>     namesD = new ArrayList <WandType>     (); // Wand types
      ArrayList <WeaponType>   namesW = new ArrayList <WeaponType>   (); // Weapon types
   	
      ArrayList <Party> cave = new ArrayList <Party> ();
   
      public static void main (String args[]) {
         CreateDataFile cdf = new CreateDataFile ();
      } // end main
   
      public CreateDataFile () {
         getNames ("firstNamesFemale.txt", "firstNamesMale.txt");
      // for (String s: namesF) System.out.printf ("Female: %s\n", s);
      // for (String s: namesM) System.out.printf ("Male: %s\n", s);
      //          openOutputFile ("dataZ.txt");
         getTemplates ("template.txt");
         getSpecs ();
      // for (String s: templates) System.out.printf (">%s<\n", s);
      //          System.out.printf ("The cave:\n%s", toAllString());
      //          System.out.printf ("The cave toString:\n%s", toString());
      //          pw.print (toString());
      //          pw.close ();
      // 			jf.setVisible (false);
      // 			System.exit (0);
      } // end no-arg constructor
   	
      public void actionPerformed (ActionEvent e) {
      	// createCave parameters: parties, creatures/party, treasures, stones, potions, wands, weapons per creature
         int [] specs = new int [numSpinnners];
         for (int i = 0; i < numSpinnners; i++)
            specs [i] = (Integer) (jsp[i].getValue ());
         createCave (specs);
      
         System.out.printf ("The cave:\n%s", toAllString());
         System.out.printf ("The cave toString:\n%s", toString());
      
         try {
            String fileName = "dataZ.txt";
            JFileChooser jfc = new JFileChooser (".");
				jfc.setSelectedFile (new File (fileName));
				// file name filtering does not work on a Mac!
//             FileNameExtensionFilter filter = new FileNameExtensionFilter("txt", "txt");
//             jfc.setFileFilter(filter);
            int returnVal = jfc.showSaveDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
               fileName = jfc.getSelectedFile().getName();
               pw = new PrintWriter (fileName);
               pw.print (toString());
               pw.close ();
            } // file selected
         } 
            catch (IOException eio) {
               JOptionPane.showMessageDialog(null, "Error Opening Output File Error:\n" + eio);
            } // end try/catch block opening & writing to output file
      
         System.exit (0);
      } // end method actionPerformed
   	
      void getSpecs () {
         int [] specs = {5, 10, 2, 3, 2, 2, 3}; // initial values for specifications
         jf = new JFrame ("Cave Data Creator");
         jf.setSize (472, 232);
         jf.setLocationRelativeTo (null);
      	
          // spinners
         JPanel jpSpin = new JPanel ();
         jpSpin.setLayout (new GridLayout (numSpinnners, 2));
         jf.add (jpSpin, BorderLayout.CENTER);
         jpSpin.add (new JLabel ("Number of Parties"));
         jpSpin.add (jsp[0] = new JSpinner ());
         jpSpin.add (new JLabel ("Number of creatures per party"));
         jpSpin.add (jsp[1] = new JSpinner ());
         jpSpin.add (new JLabel ("Number of treasures per creature"));
         jpSpin.add (jsp[2] = new JSpinner ());
         jpSpin.add (new JLabel ("Number of stones per creature"));
         jpSpin.add (jsp[3] = new JSpinner ());
         jpSpin.add (new JLabel ("Number of potions per creature"));
         jpSpin.add (jsp[4] = new JSpinner ());
         jpSpin.add (new JLabel ("Number of wands per creature"));
         jpSpin.add (jsp[5] = new JSpinner ());
         jpSpin.add (new JLabel ("Number of Weapons per creature"));
         jpSpin.add (jsp[6] = new JSpinner ());
      	
      	// index range: 0 to numSpinners
         jsp[0].setValue (5);
         jsp[1].setValue (10);
         jsp[2].setValue (2);
         jsp[3].setValue (3);
         jsp[4].setValue (2);
         jsp[5].setValue (2);
         jsp[6].setValue (3);
      	
         JButton jbGo = new JButton ("Go");
         jf.add (jbGo, BorderLayout.PAGE_END);
         jbGo.addActionListener (this);
      
         jf.validate ();
         jf.setVisible (true);
      } // end method getSpecs, mostly GUI
   
      void openOutputFile (String fileName) {
      } // end method openOutputFile
   
      void getTemplates (String fileName) {
         String s = "";
         try {
         // Scanner ts = new Scanner (new File (fileName));
            Scanner ts = new Scanner (getClass().getResourceAsStream(fileName));
            for (int i = 0; i < templates.length; i++) 
               for (templates[i] = "", s = ts.nextLine().trim(); 
                !s.equals (""); 
                templates [i] += s + "\n", s = ts.nextLine());
            processItems (ts);
         } 
            catch (Exception e) {
               JOptionPane.showMessageDialog(null, "Error opening template file:\n" + e);
            } // end try/catch block opening file to write
      } // end method getTemplates
   
      void getNames (String fileNameF, String fileNameM) {
         try {
         // Scanner ts = new Scanner (new File (fileNameF));
            Scanner ts = new Scanner(getClass().getResourceAsStream(fileNameF));            
            while (ts.hasNextLine ()) {namesF.add (ts.next()); ts.nextLine();}
         // ts = new Scanner (new File (fileNameM));
            ts = new Scanner (getClass().getResourceAsStream(fileNameM));
            while (ts.hasNextLine ()) {namesM.add (ts.next()); ts.nextLine();}
         } 
            catch (Exception e) {
               JOptionPane.showMessageDialog(null, "Error opening names file:\n" + e);
            } // end try/catch block opening file to write
      } // end method getTemplates
   
      void processItems (Scanner sc) {
         String line = "";
         try {
            while (sc.hasNextLine ()) {
               line = sc.nextLine().trim();
               if (line.length() == 0) 
                  continue; // skip blank lines
               if (line.charAt (0) == '/')
                  continue; // skip comments
               Scanner ss = new Scanner (line).useDelimiter ("\\s*:\\s*");
               if (!ss.hasNext()) 
                  continue;
               switch (ss.next().charAt(0)) {
                  case 'g': newGroupType (ss); 
                     break;
                  case 't': newTreasureType (ss); 
                     break;
                  case 'c': newCharacterType (ss); 
                     break;
                  case 's': newStoneType (ss); 
                     break;
                  case 'p': newPotionType (ss); 
                     break;
                  case 'd': newWandType (ss); 
                     break;
                  case 'w': newWeaponType (ss); 
                     break;
               } // end switch structure
            } // end for rest of file
         } 
            catch (Exception e) {
               System.out.printf ("Exception on the following line:\n%s\n", line);
            } // end catch Exception
      } // end 
   
      void newGroupType (Scanner ss) {
         namesG.add (new Party (ss));
      } // end method newGroup
   
      void newTreasureType (Scanner ss) {
         namesT.add (new TreasureType (ss));
      } // end method newGroup
   
      void newCharacterType (Scanner ss) {
         namesC.add (new CreatureType (ss));
      } // end method newGroup
   
      void newStoneType (Scanner ss) {
         namesS.add (new StoneType (ss));
      } // end method newGroup
   
      void newPotionType (Scanner ss) {
         namesP.add (new PotionType (ss));
      } // end method newGroup
   
      void newWandType (Scanner ss) {
         namesD.add (new WandType (ss));
      } // end method newGroup
   
      void newWeaponType (Scanner ss) {
         namesW.add (new WeaponType (ss));
      } // end method newGroup
   
   	// createCave parameters: parties-groups, creatures/party, treasures, stones, potions, wands, weapons per creature
   //    void createCave (int pg, int pc, int pt, int ps, int pp, int pd, int pw) {
      void createCave (int [] pa) {
         for (int ia = 0; ia < pa[0]; ia++) {
            Party tg = namesG.remove (rn.nextInt(namesG.size()));
            cave.add (tg);
            tg.index = indexParty ++;
            for (int ib = 0; ib < pa[1] * Math.random(); ib++) {
               CreatureType tca = namesC.get (rn.nextInt (namesC.size()));
               Creature tc = new Creature (tca, indexCreature++, namesM, namesF);
               tg.members.add (tc);
               tc.parent = tg;
               for (int ic = 0; ic < pa[2] * Math.random(); ic++) {
                  TreasureType tta = namesT.get (rn.nextInt (namesT.size()));
                  Treasure tt = new Treasure (tta, indexTreasure++);
                  tc.treasures.add (tt);
                  tt.parent = tc;
               } // adding treasures to creature
               for (int id = 0; id < pa[3] * Math.random(); id++) {
                  StoneType tta = namesS.get (rn.nextInt (namesS.size()));
                  Artifact tt = new Artifact ("Stone", tta.name, indexArtifact++);
                  tc.artifacts.add (tt);
                  tt.parent = tc;
               } // adding stones to creature
               for (int ie = 0; ie < pa[4] * Math.random(); ie++) {
                  PotionType tta = namesP.get (rn.nextInt (namesP.size()));
                  Artifact tt = new Artifact ("Potion", tta.name, indexArtifact++);
                  tc.artifacts.add (tt);
                  tt.parent = tc;
               } // adding potions
               for (int ig = 0; ig < pa[5] * Math.random(); ig++) {
                  WandType tta = namesD.get (rn.nextInt (namesD.size()));
                  Artifact tt = new Artifact ("Wand", tta.name, indexArtifact++);
                  tc.artifacts.add (tt);
                  tt.parent = tc;
               } // end adding wands
               for (int ih = 0; ih < pa[6] * Math.random(); ih++) {
                  WeaponType tta = namesW.get (rn.nextInt (namesW.size()));
                  Artifact tt = new Artifact ("Weapon", tta.name, indexArtifact++);
                  tc.artifacts.add (tt);
                  tt.parent = tc;
               } // end adding weapons
            } // adding creatures to a party
         } // end for adding each party
      } // end method createCave
   	
      public String toString () {
         int cnt = 0;
         String st = templates [cnt ++];
         st += "\n" + templates [cnt ++];
         for (Party g: cave) {
            st += g.toString () + "\n";
         } // end 
         st += "\n" + templates [cnt ++];
         for (Party g: cave)
            for (Creature c: g.members)
               st += c.toString () + "\n";
         st += "\n" + templates [cnt ++];
         for (Party g: cave)		
            for (Creature c: g.members)
               for (Treasure t: c.treasures)
                  st += t.toString () + "\n";
         st += "\n" + templates [cnt ++];
         for (Party g: cave)		
            for (Creature c: g.members)
               for (Artifact a: c.artifacts)
                  st += a.toString () + "\n";
         return st;
      } // end toString method
   
      public String toAllString () {
         String st = "";
         for (Party c: cave) {
            st += c.toAllString () + "\n";
         } // end 
         return st;
      } // end toString method
   
   } // end class CreateDateFile

   class Element {
      int index;
      String name;
      Element parent;
   } // end class Element

   class Party extends Element {
      ArrayList <Creature> members = new ArrayList <Creature> ();
   
      public Party (Scanner ss, int pi) {
         name = ss.next();
         index = pi;
      } // end constructor
   
      public Party (Scanner ss) {
         name = ss.next();
      } // end Type constructor
   	
      public String toString () {
         String st = String.format ("p : %5d : %s", index, name);
         return st;
      } // end toString 
   
      public String toAllString () {
         String st = "Party: " + name;
         for (Creature c: members)
            st += "\n   " + c.toAllString ();
         return st;
      } // end toString 
   
   } // end class Party

   class Creature extends Element {
      ArrayList <Treasure> treasures = new ArrayList <Treasure> ();
      ArrayList <Artifact> artifacts = new ArrayList <Artifact> ();
      ArrayList <Job>      jobs      = new ArrayList <Job>      ();
      String type;
      char sex;
      int empathy;
      int fear;
      int capacity;
      double age;
      double height;
      double weight;
      
      public Creature (CreatureType pt, int pi, ArrayList <String> maleNames, ArrayList <String> femaleNames) {
         type = pt.type;
         index = pi;
         double prob = 0.5;
         switch (pt.sex) {
            case 'f': prob = 1;
               break;
            case 'm': prob = 0; 
               break;
         } // end switch
         if (Math.random() < prob) {
            sex = 'f';
            name = femaleNames.get ((int) (Math.random() * femaleNames.size()));
         } 
         else {
            sex = 'm';
            name = maleNames.get ((int) (Math.random() * maleNames.size()));
         } // end assigning names
         empathy = pt.minEmpathy + (int) (pt.rangeEmpathy * Math.random());
         fear = pt.minFear + (int) (pt.rangeFear * Math.random());
         capacity = pt.minCapacity + (int) (pt.rangeCapacity * Math.random());
         age = pt.minAge + pt.rangeAge * Math.random();
         weight = pt.minWeight + pt.rangeWeight * Math.random();
         height = pt.minHeight + pt.rangeHeight * Math.random();
      } // end constructing a particular creature
   
      public String toString () {
         return String.format ("c : %5d : %10s : %10s : %5d : %3d : %3d : %3d : %7.2f : %7.2f : %7.2f", 
            index, type, name, parent.index, empathy, fear, capacity, age, height, weight);
      } // end toString 
   
      public String toAllString () {
         String st = "Creature: " + name + ": " + sex + " " + type;
         for (Treasure t: treasures)
            st += "\n      " + t.toAllString ();
         for (Artifact t: artifacts)
            st += "\n      " + t.toAllString ();
         return st;
      } // end toString 
   
   } // end class Creature

   class Treasure extends Element {
      String type;
      double weight;
      int value;
   
      public Treasure (TreasureType pt, int pi) {
         name = pt.type;
         index = pi;
         value = pt.minValue + (int) (pt.rangeValue * Math.random ());
         weight = pt.minWeight + pt.rangeWeight * Math.random ();
      } // end constructor
   
      public String toString () {
         return String.format ("t : %5d : %15s : %5d : %5.1f : %5d", index, name, parent.index, weight, value);
      } // end toString 
   
      public String toAllString () {
         return "Treasure: " + name;
      } // end toString 
   
   } // end class Treasure

   class Artifact extends Element {
      String type;
   
      public Artifact (String pt, String pn, int pi) {
         name = pn;
         index = pi;
         type = pt;
      } // end instance constructor
   
      public String toString () {
         return String.format ("a : %5d : %7s : %5d : %s", index, type, parent.index, name);
      } // end toString 
   
      public String toAllString () {
         return "Artifact: " + name;
      } // end toString 
   
   } // end class Artifact

   class Job extends Element {
      double time;
   
      public Job (Scanner ss, int pi) {
         name = ss.next();
         index = pi;
      } // end constructor
   
   } // end class Job
	
   class CreatureType {
      int    minEmpathy  =  0 , rangeEmpathy  = 100;
      int    minFear     =  0 , rangeFear     = 100;
      int    minCapacity =  0 , rangeCapacity = 300;
      double minAge      = 12 , rangeAge      = 500;
      double minHeight   = 20 , rangeHeight   = 300;
      double minWeight   =  0 , rangeWeight   = 1000;
   	
      String type;
      char sex;
   	
      public CreatureType (Scanner ss) {
         type = ss.next();
         sex = ss.next().charAt (0);
      } // end constructor
   } // end class CreatureType
	
   class ArtifactType {
      String type;
      String name;
      public ArtifactType (Scanner ss, String pn) {
         name = ss.next();
         type = pn;
      } // end constructor
   } // end class ArtifactType
	
   class TreasureType {
      int minWeight = 10;
      int rangeWeight = 300;
      int minValue = 0;
      int rangeValue = 100;
   	
      String type;
      public TreasureType (Scanner ss) {
         type = ss.next();
      } // end constructor
   } // end class TreasureType
	
   class StoneType {
      String type;
      String name;
      public StoneType (Scanner ss) {
         name = ss.next();
         type = "Stone";
      } // end constructor
   } // end class ArtifactType
	
   class PotionType {
      String type;
      String name;
      public PotionType (Scanner ss) {
         name = ss.next();
         type = "Potion";
      } // end constructor
   } // end class ArtifactType
	
   class WandType {
      String type;
      String name;
      public WandType (Scanner ss) {
         name = ss.next();
         type = "Wand";
      } // end constructor
   } // end class ArtifactType
	
   class WeaponType {
      String type;
      String name;
      public WeaponType (Scanner ss) {
         name = ss.next();
         type = "Weapon";
      } // end constructor
   } // end class ArtifactType
