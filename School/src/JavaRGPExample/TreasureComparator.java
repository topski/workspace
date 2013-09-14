package JavaRGPExample;

import java.util.Comparator;

public class TreasureComparator implements Comparator<Treasure> {
    private String attribute;
    
    public TreasureComparator(String attribute){
        //set the member variable for the attribute we will sort by
        this.attribute = attribute;
    }    
    @Override
    public int compare(Treasure t1, Treasure t2){
        int result = -1;
        //a switch statement for the member variable to handle multiple criteria in a single class
        switch (this.attribute) {            
                    case "weight":                        
                           if (t1.weight == t2.weight){
                               result = 0;
                           }else if (t1.weight > t2.weight){
                               result = 1;
                           }else{
                               result = -1;
                           }
                    break;
                    case "value":
                           if (t1.value == t2.value){
                               result = 0;
                           }else if (t1.value > t2.value){
                               result = 1;
                           }else{
                               result = -1;
                           }
                        break;  
                    default:
                        if (t1.value == t2.value){
                               result = 0;
                           }else if (t1.value > t2.value){
                               result = 1;
                           }else{
                               result = -1;
                           }
                    break;
        }
        return result;
    }
}
