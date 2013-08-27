package CMSC335_Project1;

import java.util.Scanner;
 
 class StoneType
 {
   String type;
   String name;
 
   public StoneType(Scanner ss)
   {
     this.name = ss.next();
     this.type = "Stone";
   }
 }