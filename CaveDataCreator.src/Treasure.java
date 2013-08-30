/*     */ class Treasure extends Element
/*     */ {
/*     */   String type;
/*     */   double weight;
/*     */   int value;
/*     */ 
/*     */   public Treasure(TreasureType pt, int pi)
/*     */   {
/* 418 */     this.name = pt.type;
/* 419 */     this.index = pi;
/* 420 */     this.value = (pt.minValue + (int)(pt.rangeValue * Math.random()));
/* 421 */     this.weight = (pt.minWeight + pt.rangeWeight * Math.random());
/*     */   }
/*     */ 
/*     */   public String toString() {
/* 425 */     return String.format("t : %5d : %15s : %5d : %5.1f : %5d", new Object[] { Integer.valueOf(this.index), this.name, Integer.valueOf(this.parent.index), Double.valueOf(this.weight), Integer.valueOf(this.value) });
/*     */   }
/*     */ 
/*     */   public String toAllString() {
/* 429 */     return "Treasure: " + this.name;
/*     */   }
/*     */ }

/* Location:           /home/jwatson/Downloads/CaveDataCreator.jar
 * Qualified Name:     Treasure
 * JD-Core Version:    0.6.2
 */