package CMSC335_Project1;

import java.util.ArrayList;

class Creature extends Element {
	ArrayList<Treasure> treasures = new ArrayList();
	ArrayList<Artifact> artifacts = new ArrayList();
	ArrayList<Job> jobs = new ArrayList();
	String type;
	char sex;
	int empathy;
	int fear;
	int capacity;
	double age;
	double height;
	double weight;

	public Creature(CreatureType pt, int pi, ArrayList<String> maleNames,
			ArrayList<String> femaleNames) {
		this.type = pt.type;
		this.index = pi;
		double prob = 0.5D;
		switch (pt.sex) {
		case 'f':
			prob = 1.0D;
			break;
		case 'm':
			prob = 0.0D;
		}

		if (Math.random() < prob) {
			this.sex = 'f';
			this.name = ((String) femaleNames
					.get((int) (Math.random() * femaleNames.size())));
		} else {
			this.sex = 'm';
			this.name = ((String) maleNames
					.get((int) (Math.random() * maleNames.size())));
		}
		this.empathy = (pt.minEmpathy + (int) (pt.rangeEmpathy * Math.random()));
		this.fear = (pt.minFear + (int) (pt.rangeFear * Math.random()));
		this.capacity = (pt.minCapacity + (int) (pt.rangeCapacity * Math
				.random()));
		this.age = (pt.minAge + pt.rangeAge * Math.random());
		this.weight = (pt.minWeight + pt.rangeWeight * Math.random());
		this.height = (pt.minHeight + pt.rangeHeight * Math.random());
	}

	public String toString() {
		return String
				.format("c : %5d : %10s : %10s : %5d : %3d : %3d : %3d : %7.2f : %7.2f : %7.2f",
						new Object[] { Integer.valueOf(this.index), this.type,
								this.name, Integer.valueOf(this.parent.index),
								Integer.valueOf(this.empathy),
								Integer.valueOf(this.fear),
								Integer.valueOf(this.capacity),
								Double.valueOf(this.age),
								Double.valueOf(this.height),
								Double.valueOf(this.weight) });
	}

	public String toAllString() {
		String st = "Creature: " + this.name + ": " + this.sex + " "
				+ this.type;
		for (Treasure t : this.treasures)
			st = st + "\n      " + t.toAllString();
		for (Artifact t : this.artifacts)
			st = st + "\n      " + t.toAllString();
		return st;
	}
}