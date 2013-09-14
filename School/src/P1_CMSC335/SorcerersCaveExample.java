package P1_CMSC335;

import java.io.IOException;
import java.util.*;
import java.util.Scanner;
import javax.swing.JFileChooser;

public class SorcerersCaveExample {

   public static void main(String[] args) {

      ArrayList<Party> partyList = new ArrayList<Party>();
      ArrayList<Creature> creatureList = new ArrayList<Creature>();
      ArrayList<Treasure> treasureList = new ArrayList<Treasure>();
      ArrayList<Artifact> artifactList = new ArrayList<Artifact>();
      JFileChooser chooser = new JFileChooser();

      chooser.showOpenDialog(null);


      // open and read file:
      Scanner scanner = null;
      try {
         scanner = new Scanner(chooser.getSelectedFile());
      } catch (IOException e) {
         System.err.println(e);
         error();
      }
      if (scanner == null) error();




      while (scanner.hasNextLine()) {


         String line = scanner.nextLine();
         Scanner lineScan = new Scanner(line);
         lineScan.useDelimiter(" : ");
         //System.err.println("The line that was scanned: " + line);

         int index;
         String type;
         String name;

         char identifier = line.charAt(0);
         //System.err.println("identifier: " + identifier);
         lineScan.next();
                  
         if (identifier == 'p') {
            index = lineScan.nextInt();
            name = lineScan.next();
            partyList.add(new Party(index, name));

         } else if (identifier == 'c') {
            index = lineScan.nextInt();
            type = lineScan.next();
            name = lineScan.next();
            int partyC = lineScan.nextInt();
            int empathyC = lineScan.nextInt();
            int carryingCapacityC = lineScan.nextInt();
            creatureList.add(new Creature(index, type, name, partyC, empathyC, carryingCapacityC));

         } else if (identifier == 't') {
            index = lineScan.nextInt();
            type = lineScan.next();
            int creatureT = lineScan.nextInt();
            double weightT = lineScan.nextDouble();
            int valueT = lineScan.nextInt();
            treasureList.add(new Treasure(index, type, creatureT, weightT, valueT));

         } else if (identifier == 'a') {
            index = lineScan.nextInt();
            type = lineScan.next();
            int creatureA = lineScan.nextInt();
            artifactList.add(new Artifact(index, type, creatureA));

         } else {
            System.out.println("This is not a valid line of input");
        	
         }
      }
      System.out.println("party: " + partyList.toString());
   }

   private static void error() {
      System.err.println("An error has occurred: bad data");
      System.exit(0);
   }

   private static class Artifact {

      private int index, creatureA;
      private String type;

      public Artifact(int index, String type, int creatureA) {
         this.index = index;
         this.type = type;
         this.creatureA = creatureA;
      }

      public int getCreatureA() {
         return creatureA;
      }

      public void setCreatureA(int creatureA) {
         this.creatureA = creatureA;
      }

      public int getIndex() {
         return index;
      }

      public void setIndex(int index) {
         this.index = index;
      }

      public String getType() {
         return type;
      }

      public void setType(String type) {
         this.type = type;
      }
   }

   private static class Creature {

      private int index, partyC, empathyC;
      private String type, name;
      private double carryingCapacityC;

      public Creature(int index, String type, String name,
                      int partyC, int empathyC, double carryingCapacityC) {
         this.index = index;
         this.partyC = partyC;
         this.empathyC = empathyC;
         this.type = type;
         this.name = name;
         this.carryingCapacityC = carryingCapacityC;
      }

      public double getCarryingCapacityC() {
         return carryingCapacityC;
      }

      public void setCarryingCapacityC(double carryingCapacityC) {
         this.carryingCapacityC = carryingCapacityC;
      }

      public int getEmpathyC() {
         return empathyC;
      }

      public void setEmpathyC(int empathyC) {
         this.empathyC = empathyC;
      }

      public int getIndex() {
         return index;
      }

      public void setIndex(int index) {
         this.index = index;
      }

      public String getName() {
         return name;
      }

      public void setName(String name) {
         this.name = name;
      }

      public int getPartyC() {
         return partyC;
      }

      public void setPartyC(int partyC) {
         this.partyC = partyC;
      }

      public String getType() {
         return type;
      }

      public void setType(String type) {
         this.type = type;
      }
   }

   private static class Party {

      private int index;
      private String name;

      public Party(int index, String name) {
         this.index = index;
         this.name = name;
      }

      public String toString() {
         return "party: index=" + index + " name=" + name;
      }

      public int getIndex() {
         return index;
      }

      public void setIndex(int index) {
         this.index = index;
      }

      public String getName() {
         return name;
      }

      public void setName(String name) {
         this.name = name;
      }
   }

   private static class Treasure {

      private int index, creatureT, valueT;
      private String type;
      private double weightT;

      public Treasure(int index, String type, int creatureT, double weightT, int valueT) {
         this.index = index;
         this.creatureT = creatureT;
         this.valueT = valueT;
         this.type = type;
         this.weightT = weightT;
      }

      public int getCreatureT() {
         return creatureT;
      }

      public void setCreatureT(int creatureT) {
         this.creatureT = creatureT;
      }

      public int getIndex() {
         return index;
      }

      public void setIndex(int index) {
         this.index = index;
      }

      public String getType() {
         return type;
      }

      public void setType(String type) {
         this.type = type;
      }

      public int getValueT() {
         return valueT;
      }

      public void setValueT(int valueT) {
         this.valueT = valueT;
      }

      public double getWeightT() {
         return weightT;
      }

      public void setWeightT(double weightT) {
         this.weightT = weightT;
      }
   }
}