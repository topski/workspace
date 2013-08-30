/*     */ import java.util.Scanner;
/*     */ 
/*     */ class CreatureType
/*     */ {
/* 464 */   int minEmpathy = 0; int rangeEmpathy = 100;
/* 465 */   int minFear = 0; int rangeFear = 100;
/* 466 */   int minCapacity = 0; int rangeCapacity = 300;
/* 467 */   double minAge = 12.0D; double rangeAge = 500.0D;
/* 468 */   double minHeight = 20.0D; double rangeHeight = 300.0D;
/* 469 */   double minWeight = 0.0D; double rangeWeight = 1000.0D;
/*     */   String type;
/*     */   char sex;
/*     */ 
/*     */   public CreatureType(Scanner ss)
/*     */   {
/* 475 */     this.type = ss.next();
/* 476 */     this.sex = ss.next().charAt(0);
/*     */   }
/*     */ }

/* Location:           /home/jwatson/Downloads/CaveDataCreator.jar
 * Qualified Name:     CreatureType
 * JD-Core Version:    0.6.2
 */