package JavaRGPExample;

public class GameElement {
    public int index = 0;
    public String type = null;
    public String name = null;    
    
    //A single-element constructor
    public GameElement(int index){
        this.index = index;        
    }       
    
    //An overidden 3-argument constructor
    public GameElement(int index, String type, String name){
        this.index = index;
        this.type = type;
        this.name = name;
    }
    //An overiddent 2-arguement constructor
    public GameElement(int index, String name){
        this.index = index;
        this.name = name;
    }
    
    //Another 2 argument constructor with a different signature to distinguish
    //from the above one
    public GameElement(String type, int index){
        this.index = index;
        this.type = type;
    }       
    
}