package JavaRGPExample;

import java.util.ArrayList;

public class Party extends GameElement {        
    public String location;
    public ArrayList<Creature> creatures = new ArrayList<>();
    
    public Party(int index, String name){
        super(index, name);           
    }   
    
    public void addCreature(Creature c){
        if (c != null && c instanceof Creature){
            this.creatures.add(c);
        }
    }
    
   @Override
   public String toString(){
       //Print the class data in a neater format
       String output = "";
       output += "Party: \nName = " + this.name + " \nIndex = " + Integer.toString(this.index);
       output += "\n---------Member Creatures---------\n";
       for (Creature c : this.creatures) output+= "\n\t" + c.toString();
       output += "\n----------------------------------\n";
       return output;
   }
}