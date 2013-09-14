package JavaRGPExample;

class Artifact extends GameElement {
    public int creatureIndex = 0;
    
    public Artifact(int index, String type, int creatureIndex){
        super(type, index);
        this.creatureIndex = creatureIndex;
    }
    
    @Override
    public String toString(){
        //Return neater output of the class data
        return "[Artifact]: \n\tOwner Index = " + Integer.toString(this.creatureIndex) + " \n\tArtifact Type = " + this.type;
    }
}