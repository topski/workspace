/*     */ import java.util.ArrayList;
/*     */ import java.util.Scanner;
/*     */ 
/*     */ class Party extends Element
/*     */ {
/* 332 */   ArrayList<Creature> members = new ArrayList();
/*     */ 
/*     */   public Party(Scanner ss, int pi) {
/* 335 */     this.name = ss.next();
/* 336 */     this.index = pi;
/*     */   }
/*     */ 
/*     */   public Party(Scanner ss) {
/* 340 */     this.name = ss.next();
/*     */   }
/*     */ 
/*     */   public String toString() {
/* 344 */     String st = String.format("p : %5d : %s", new Object[] { Integer.valueOf(this.index), this.name });
/* 345 */     return st;
/*     */   }
/*     */ 
/*     */   public String toAllString() {
/* 349 */     String st = "Party: " + this.name;
/* 350 */     for (Creature c : this.members)
/* 351 */       st = st + "\n   " + c.toAllString();
/* 352 */     return st;
/*     */   }
/*     */ }

/* Location:           /home/jwatson/Downloads/CaveDataCreator.jar
 * Qualified Name:     Party
 * JD-Core Version:    0.6.2
 */