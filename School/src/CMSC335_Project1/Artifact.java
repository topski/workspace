package CMSC335_Project1;

class Artifact extends Element {
	String type;

	public Artifact(String pt, String pn, int pi) {
		this.name = pn;
		this.index = pi;
		this.type = pt;
	}

	public String toString() {
		return String.format(
				"a : %5d : %7s : %5d : %s",
				new Object[] { Integer.valueOf(this.index), this.type,
						Integer.valueOf(this.parent.index), this.name });
	}

	public String toAllString() {
		return "Artifact: " + this.name;
	}
}