/*     */ import java.util.ArrayList;
/*     */ 
/*     */ class Creature extends Element
/*     */ {
/* 358 */   ArrayList<Treasure> treasures = new ArrayList();
/* 359 */   ArrayList<Artifact> artifacts = new ArrayList();
/* 360 */   ArrayList<Job> jobs = new ArrayList();
/*     */   String type;
/*     */   char sex;
/*     */   int empathy;
/*     */   int fear;
/*     */   int capacity;
/*     */   double age;
/*     */   double height;
/*     */   double weight;
/*     */ 
/*     */   public Creature(CreatureType pt, int pi, ArrayList<String> maleNames, ArrayList<String> femaleNames)
/*     */   {
/* 371 */     this.type = pt.type;
/* 372 */     this.index = pi;
/* 373 */     double prob = 0.5D;
/* 374 */     switch (pt.sex) { case 'f':
/* 375 */       prob = 1.0D;
/* 376 */       break;
/*     */     case 'm':
/* 377 */       prob = 0.0D;
/*     */     }
/*     */ 
/* 380 */     if (Math.random() < prob) {
/* 381 */       this.sex = 'f';
/* 382 */       this.name = ((String)femaleNames.get((int)(Math.random() * femaleNames.size())));
/*     */     }
/*     */     else {
/* 385 */       this.sex = 'm';
/* 386 */       this.name = ((String)maleNames.get((int)(Math.random() * maleNames.size())));
/*     */     }
/* 388 */     this.empathy = (pt.minEmpathy + (int)(pt.rangeEmpathy * Math.random()));
/* 389 */     this.fear = (pt.minFear + (int)(pt.rangeFear * Math.random()));
/* 390 */     this.capacity = (pt.minCapacity + (int)(pt.rangeCapacity * Math.random()));
/* 391 */     this.age = (pt.minAge + pt.rangeAge * Math.random());
/* 392 */     this.weight = (pt.minWeight + pt.rangeWeight * Math.random());
/* 393 */     this.height = (pt.minHeight + pt.rangeHeight * Math.random());
/*     */   }
/*     */ 
/*     */   public String toString() {
/* 397 */     return String.format("c : %5d : %10s : %10s : %5d : %3d : %3d : %3d : %7.2f : %7.2f : %7.2f", new Object[] { Integer.valueOf(this.index), this.type, this.name, Integer.valueOf(this.parent.index), Integer.valueOf(this.empathy), Integer.valueOf(this.fear), Integer.valueOf(this.capacity), Double.valueOf(this.age), Double.valueOf(this.height), Double.valueOf(this.weight) });
/*     */   }
/*     */ 
/*     */   public String toAllString()
/*     */   {
/* 402 */     String st = "Creature: " + this.name + ": " + this.sex + " " + this.type;
/* 403 */     for (Treasure t : this.treasures)
/* 404 */       st = st + "\n      " + t.toAllString();
/* 405 */     for (Artifact t : this.artifacts)
/* 406 */       st = st + "\n      " + t.toAllString();
/* 407 */     return st;
/*     */   }
/*     */ }

/* Location:           /home/jwatson/Downloads/CaveDataCreator.jar
 * Qualified Name:     Creature
 * JD-Core Version:    0.6.2
 */