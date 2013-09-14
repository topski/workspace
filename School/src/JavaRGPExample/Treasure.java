package JavaRGPExample;

class Treasure extends GameElement {
    public int creatureIndex = 0;
    public double weight;
    public double value;
    //A construction to take the elements and assign them to all instance variables specific to this class
    public Treasure(int index, String type, int creatureIndex, double weight, double value){
        super(type, index);
        this.creatureIndex = creatureIndex;
        this.weight = weight;
        this.value = value;
    }
    
    @Override
    public String toString(){
        //Neater output of class data
        return "[Treasure]: \n\tType = " + this.type + " \n\tOwner Index = " + Integer.toString(this.creatureIndex) + " \n\tWeight = " + Double.toString(this.weight) + " \n\tValue = " + Double.toString(this.value);
    }
}
