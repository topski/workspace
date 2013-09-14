package JavaRGPExample;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

class Creature extends GameElement implements Runnable, ActionListener {
    public int partyIndex = 0;
    public int empathyValue;
    public int fearValue;
    public double carryingCapacity;
    public int age;
    public int weight;
    public double height;
    public ArrayList<Treasure> treasures = new ArrayList<>();
    public ArrayList<Artifact> artifacts = new ArrayList<>();
    public ArrayList<Job> jobs = new ArrayList();
    public long jobTime = 0;
    public Job currentJob = null;
    public volatile boolean runJob = false;
    public volatile boolean cancelJob = false;
    
   JButton cancelButton = new JButton("Cancel Job");
   JButton startButton = new JButton("Start Job");
    
    //A single constructor to take the data elements and assign them
    public Creature(int index, String type, String name, int partyIndex, int empathyValue, int fearValue, double carryingCapacity){
        super(index, type, name);
        this.partyIndex = partyIndex;
        this.empathyValue = empathyValue;
        this.fearValue = fearValue;
        this.carryingCapacity = carryingCapacity;
    }    
    
    public Creature(int index, String type, String name, int partyIndex, int empathyValue, int fearValue, double carryingCapacity, int age, int weight, double height){
        super(index, type, name);
        this.partyIndex = partyIndex;
        this.empathyValue = empathyValue;
        this.fearValue = fearValue;
        this.carryingCapacity = carryingCapacity;
        this.age = age;
        this.weight = weight;
        this.height = height;
    } 
    
    public void addTreature(Treasure t){
        if (t != null && t instanceof Treasure && t.creatureIndex != 0){
            this.treasures.add(t);
        }
    }
    
    public void addArtifact(Artifact a){
        if (a != null && a instanceof Artifact && a.creatureIndex != 0){
            this.artifacts.add(a);
        }
    }
    
    @Override
    public String toString(){
        //Neater output of the class data
        String output = "";
        output += "[Creature]: \n\tType = " + this.type + " \n\tName = " + this.name + " \n\tParty Index = " + Integer.toString(this.partyIndex) + " \n\tEmpathy Value = " + Integer.toString(this.empathyValue) + " \n\tFear Value = " + Integer.toString(this.fearValue) + " \n\tCarrying Capacity = " + Double.toString(this.carryingCapacity);
        output += "\n\t------Owned Items------\n";
        for (Treasure t: this.treasures) output+= "\n\t" + t.toString();
        for (Artifact a: this.artifacts) output+= "\n\t" + a.toString();
        output += "\n\t-----------------------\n";
        return output;
    }
    
    public void addJob(long time, Job j){
        this.jobTime = time;
        this.currentJob = j;
        this.jobs.add(j);
    }

    @Override
    public void run() {    
   JProgressBar progressBar = new JProgressBar ();
   progressBar.setStringPainted (true);
   cancelButton.addActionListener(this);
   startButton.addActionListener(this);
   JFrame jf = new JFrame ("Creature " + this.name + "'s Job");
   jf.add (new JLabel (" Performing Job: " + this.currentJob.name), BorderLayout.PAGE_START);
   jf.add (progressBar, BorderLayout.CENTER);
   JPanel btnPanel = new JPanel();
   btnPanel.add(startButton);
   btnPanel.add(cancelButton);
   jf.add(btnPanel, BorderLayout.SOUTH);
   jf.pack ();
   
   jf.setVisible (true);
   
   jf.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
   jf.setLocationRelativeTo (null);
   System.out.println ("Starting Job: " + this.currentJob.name);
   System.out.println ("Job Duration: " + this.jobTime);
   
   Thread jobThread = Thread.currentThread();
   while (!this.runJob){
       jobThread.yield();       
   }   
   long currentTime = System.currentTimeMillis();
   long startTime = currentTime;
   long stopTime = currentTime + 1000 * jobTime;
   System.out.println ("Stop time: " + stopTime);
   double duration = stopTime - currentTime;      
   while (currentTime < stopTime) {
     if (this.cancelJob == true && jobThread != null){
         jobThread.interrupt();
         JOptionPane.showMessageDialog(null, "Job Cancelled!");
         jf.dispose();
         break;
     }else{
     try {
       jobThread.sleep (100);
     } catch (InterruptedException e) {}
     progressBar.setValue ((int)(((currentTime - startTime) / duration) * 100));
     currentTime = System.currentTimeMillis ();
    }
   }
   if (!this.cancelJob){
    progressBar.setValue (100);
   }
   jf.dispose();
    }
  

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancelButton) {
            this.cancelJob = true;
            this.runJob = false;
        }else if (e.getSource() == startButton){
            this.runJob = true;
            this.cancelJob = false;
        }
    }
}
