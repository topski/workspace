package CMSC335_Project1;

class Treasure extends Element
 {
   String type;
   double weight;
   int value;
 
   public Treasure(TreasureType pt, int pi)
   {
     this.name = pt.type;
     this.index = pi;
     this.value = (pt.minValue + (int)(pt.rangeValue * Math.random()));
     this.weight = (pt.minWeight + pt.rangeWeight * Math.random());
   }
 
   public String toString() {
     return String.format("t : %5d : %15s : %5d : %5.1f : %5d", new Object[] { Integer.valueOf(this.index), this.name, Integer.valueOf(this.parent.index), Double.valueOf(this.weight), Integer.valueOf(this.value) });
   }
 
   public String toAllString() {
     return "Treasure: " + this.name;
   }
 }