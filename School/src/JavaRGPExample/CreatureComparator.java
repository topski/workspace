package JavaRGPExample;

import java.util.Comparator;

/**
 *
 * @author Michael
 */
public class CreatureComparator implements Comparator<Creature>{
    
    private String attribute;
    
    public CreatureComparator(String attribute){
        //set the member variable which we will sort by
        this.attribute = attribute;
    }    
    @Override
    public int compare(Creature c1, Creature c2){
        int result = -1;
        //a switch statement for the member variable to handle multiple criteria in a single class
        switch (this.attribute) {            
                    case "empathy":                        
                           if (c1.empathyValue == c2.empathyValue){
                               result = 0;
                           }else if (c1.empathyValue > c2.empathyValue){
                               result = 1;
                           }else{
                               result = -1;
                           }
                    break;
                    case "fear":
                           if (c1.fearValue == c2.fearValue){
                               result = 0;
                           }else if (c1.fearValue > c2.fearValue){
                               result = 1;
                           }else{
                               result = -1;
                           }
                        break;
                    case "carrying":
                           if (c1.carryingCapacity == c2.carryingCapacity){
                               result = 0;
                           }else if (c1.carryingCapacity > c2.carryingCapacity){
                               result = 1;
                           }else{
                               result = -1;
                           }
                        break;
                    case "age": 
                        if (c1.age == c2.age){
                               result = 0;
                           }else if (c1.age > c2.age){
                               result = 1;
                           }else{
                               result = -1;
                           }
                        break;
                    
                    case "height":
                        if (c1.height == c2.height){
                               result = 0;
                           }else if (c1.height > c2.height){
                               result = 1;
                           }else{
                               result = -1;
                           }
                        break;
                     
                    case "weight":
                        if (c1.weight == c2.weight){
                               result = 0;
                           }else if (c1.weight > c2.weight){
                               result = 1;
                           }else{
                               result = -1;
                           }
                        break;
                    default:
                        if (c1.carryingCapacity == c2.carryingCapacity){
                               result = 0;
                           }else if (c1.carryingCapacity > c2.carryingCapacity){
                               result = 1;
                           }else{
                               result = -1;
                           }
                    break;
        }
        return result;
    }       
}

