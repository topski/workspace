package JavaRGPExample;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Game {
    private String fileName;
    //the "Cave" of elements for this game
    private HashMap<Integer, GameElement> caveElements = new HashMap<>();
    public ArrayList<GameElement> loners = new ArrayList<>();
    public ArrayList<Party> parties = new ArrayList<>();
    //A default no-argument constructor
    public Game(){
        
    }
    //An argument for initializing with a data file name/path
    public Game(String dataFileName) throws IOException{
        this.fileName = dataFileName;
        this.loadAndSetElementsFromFile(dataFileName);
    }
   
    //prints all elements in a neat fashion using their respective toString methods
   public String printAllElements(){
       String output = "";
       ArrayList<GameElement> printElements = new ArrayList<>();
       printElements.addAll(caveElements.values());              
            for (GameElement ge: printElements){                
                if (ge instanceof Party){
                    Party p = (Party) ge;
                    output += p.toString();
                }
            }
            output += "\n-----------------\nLoners\n-----------------\n";
            for (GameElement ge: loners){                
                output+= "\n" + ge.toString();
            }
      return output;
   }
   //searches the game elements for the input criteria
   public String searchGame(String searchInput){
       String result = "";
       ArrayList<GameElement> printElements = new ArrayList<>();
       printElements.addAll(caveElements.values());
       printElements.addAll(loners);
       for (GameElement ge : printElements){
                if (ge.index != 0 && searchInput != null && Integer.toString(ge.index).equals(searchInput)){
               result += ge.toString() + "\n";
           }else if(ge.type != null && searchInput != null && ge.type.toLowerCase().equals(searchInput.toLowerCase())){
               result += ge.toString() + "\n"; 
           }else if(ge.name != null && searchInput != null && ge.name.toLowerCase().equals(searchInput.toLowerCase())){
               result += ge.toString() + "\n";
           }  
         }
        return result;
   }
    
   //Prints the elements, sorted by the critera and the object they belong to
    public String printSortedElements(String attribute){
       String result = "";
       Set elementSet = caveElements.entrySet();
            Iterator i = elementSet.iterator();            
            while(i.hasNext()){
                Map.Entry g = (Map.Entry)i.next();
                GameElement ge = (GameElement)g.getValue();
                if (ge instanceof Party){
                   Party p = (Party)ge;                   
                   if ("empathy".equals(attribute) || "carrying".equals(attribute) || "fear".equals(attribute) || "name".equals(attribute) || "age".equals(attribute) || "height".equals(attribute) || "weight".equals(attribute)){                           
                            Collections.sort(p.creatures, new CreatureComparator(attribute)); 
                       }
                   if ("value".equals(attribute)){
                                for (Creature c : p.creatures){
                                   Collections.sort(c.treasures, new TreasureComparator(attribute));
                                }
                       }
                   result += p.toString();
                }
            }
            result += "\n---------------------\nLoners\n-------------------\n";
            for (GameElement geLoner : loners){
                 result += geLoner.toString();                 
            }

        return result;
    }
    
    //an overidden setter that loads and sets the gameElement ArrayList
    public void setGameElements(String fileName) throws IOException{
        this.loadAndSetElementsFromFile(fileName);
    }

    //a setter to set the class instance fileName variable (stored for future reference)
    public void setFileName(String fileName){
        this.fileName = fileName;
    }
    //a getter for the fileName instance variable
    public String getFileName(){
        return this.fileName;
    }     
    
    public void addJob(int index, String name, int creatureIndex, long time){
        Job j = new Job(index, name, creatureIndex, time);
        caveElements.put(index, j);
        Creature c = (Creature)caveElements.get(creatureIndex);        
        c.addJob(time, j);
        Thread t = new Thread(c);
        t.start();
    }
    
    //The method to load the data file, parse the contents, create the classes, and add to an ArrayList (gameElements)
    private void loadAndSetElementsFromFile(String fileName) throws IOException{
        try {
            Path path = Paths.get(fileName);
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            for (String line : lines){
                //remove all whitespace from the file
                line = line.replaceAll("\\s","");
                //split the line into an array of multiple parts; delimited by the colon
            if (!line.startsWith("//") && line.length() > 0){                   
                String[] lineparts = line.split(":");
                //A switch statement to keeps things neat
                //It uses the first part of the input file to determine which type of class to instantiate
                switch (lineparts[0]) {
                    case "p":
                        //Create and add Party objects from the provided data
                        Party p;
                        p = new Party(Integer.parseInt(lineparts[1]), lineparts[2]);                       
                        this.caveElements.put(p.index, p);
                        parties.add(p);
                        break;
                    case "c":
                        //Create, add, and assign Creature objects from the provided data
                        Creature c;
                        c = new Creature(Integer.parseInt(lineparts[1]), lineparts[2], lineparts[3], Integer.parseInt(lineparts[4]), Integer.parseInt(lineparts[5]), Integer.parseInt(lineparts[6]), Double.parseDouble(lineparts[7]));                        
                        //checking for an optional age input
                        if (lineparts.length > 8 && lineparts[8] != null){
                            c.age = Integer.parseInt(lineparts[8]);
                        }
                        //checking for an optional height input
                        if (lineparts.length > 9 && lineparts[9] != null){
                            c.height = Double.parseDouble(lineparts[9]);
                        }
                        //checking for an optional weight input
                        if (lineparts.length > 10 && lineparts[10] != null){
                            c.weight = Integer.parseInt(lineparts[10]);
                        }
                        Party creatureParty = (Party) caveElements.get(c.partyIndex);
                        if (creatureParty == null || c.partyIndex == 0){
                            this.loners.add(c);
                        }else{
                            creatureParty.addCreature(c);
                        }
                        this.caveElements.put(c.index, c);
                        break;
                    case "t":
                        //Create, add, and assign Treasure objects from the provided data
                        Treasure tr;
                        tr = new Treasure(Integer.parseInt(lineparts[1]), lineparts[2], Integer.parseInt(lineparts[3]), Double.parseDouble(lineparts[4]), Double.parseDouble(lineparts[5]));
                        Creature treasureCreature = (Creature) caveElements.get(tr.creatureIndex);
                        if (treasureCreature == null || tr.creatureIndex == 0){
                            this.loners.add(tr);
                        }else{
                            treasureCreature.addTreature(tr);
                        }
                        this.caveElements.put(tr.index, tr);
                        break;
                    case "a":
                        //Create, add, and assign Artifact objects from the provided data
                        Artifact ar;
                        ar = new Artifact(Integer.parseInt(lineparts[1]), lineparts[2], Integer.parseInt(lineparts[3]));
                        Creature artifactCreature = (Creature) caveElements.get(ar.creatureIndex);
                        if (artifactCreature == null || ar.creatureIndex == 0){
                            this.loners.add(ar);
                        }else if (ar.creatureIndex != 0){
                            artifactCreature.addArtifact(ar);
                        }
                        this.caveElements.put(ar.index, ar);
                        break;
                    case "j":
                        addJob(Integer.parseInt(lineparts[1]), lineparts[2], Integer.parseInt(lineparts[3]), Long.parseLong(lineparts[4]));                       
                        break;
                    default:
                        break;
                }
            }   
         }                        
      }catch (IOException | NumberFormatException e){
            e.printStackTrace();
        }
    }
}
