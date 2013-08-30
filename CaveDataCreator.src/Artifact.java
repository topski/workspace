/*     */ class Artifact extends Element
/*     */ {
/*     */   String type;
/*     */ 
/*     */   public Artifact(String pt, String pn, int pi)
/*     */   {
/* 438 */     this.name = pn;
/* 439 */     this.index = pi;
/* 440 */     this.type = pt;
/*     */   }
/*     */ 
/*     */   public String toString() {
/* 444 */     return String.format("a : %5d : %7s : %5d : %s", new Object[] { Integer.valueOf(this.index), this.type, Integer.valueOf(this.parent.index), this.name });
/*     */   }
/*     */ 
/*     */   public String toAllString() {
/* 448 */     return "Artifact: " + this.name;
/*     */   }
/*     */ }

/* Location:           /home/jwatson/Downloads/CaveDataCreator.jar
 * Qualified Name:     Artifact
 * JD-Core Version:    0.6.2
 */