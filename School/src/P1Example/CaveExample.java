package P1Example;

//File: Cave2.java
//Date: Feb 17, 2012
//Author: Nicholas Duchon
//Semester project
//- started with Cave.java and added JProgressBar stuff.
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;

public class CaveExample {
	ArrayList<Party> parties = new ArrayList<Party>(); // the parties

	// hashmap of everything by index
	HashMap<Integer, CaveElement> hh = new HashMap<Integer, CaveElement>();

	public static void main(String args[]) {
		Scanner sf = null;
		String fileName = "/home/jwatson/workspace/School/dataZ.txt";
		try {
			sf = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + fileName);
			System.exit(1);
		}
		CaveExample cv = new CaveExample();
		cv.readFile(sf);
		System.out.println(cv);
	} // end main

	public String toString() {
		String sb = "";
		sb += toString(parties, "Party List");
		// sb += toString (hh.values (), "HashMap");
		return sb;
	} // end method dump

	String toString(Iterable<? extends CaveElement> it, String label) {
		String sb = "\n----- DUMP " + label + " -------";
		for (CaveElement c : it)
			sb += "\n" + c.toString();
		return sb;
	} // end method dump Iterable String

	void readFile(Scanner sf) {
		String inline;
		Scanner line;
		while (sf.hasNext()) {
			inline = sf.nextLine().trim();
			// System.out.printf (">%s<\n", inline);
			if (inline.length() == 0)
				continue;
			line = new Scanner(inline).useDelimiter("\\s*:\\s*"); // compress
																	// white
																	// space
																	// also,
																	// else
																	// nextInt
																	// fails
			switch (inline.charAt(0)) {
			case 'p':
				addParty(line);
				break;
			case 'c':
				addCreature(line);
				break;
			case 't':
				addTreasure(line);
				break;
			case 'a':
				addArtifact(line);
				break;
			// case 'j': addJob (line); break;
			} // end switch
		} // end while reading data file
	} // end readFile

	public void addParty(Scanner sc) {
		Party pt = new Party(sc);
		parties.add(pt);
		hh.put(pt.index, pt);
	} // end method addParty

	public void addCreature(Scanner sc) {
		Creature c = new Creature();
		int target = c.makeCreature(sc);
		Party p = (Party) (hh.get(target));
		p.addCreature(c);
		c.party = p;
		hh.put(c.index, c);
	} // end method addToParty

	public void addTreasure(Scanner sc) {
		Treasure t = new Treasure();
		int target = t.makeTreasure(sc);
		Creature c = (Creature) (hh.get(target));
		c.addTreasure(t);
		t.holder = c;
		hh.put(t.index, t);
	} // end method addTreasure

	public void addArtifact(Scanner sc) {
		Artifact a = new Artifact();
		int target = a.makeArtifact(sc);
		Creature c = (Creature) (hh.get(target));
		c.addArtifact(a);
		a.holder = c;
		hh.put(a.index, a);
	} // end method addArtifact
} // end class Cave

class CaveElement {
}

//    p:<index>:<name>
class Party extends CaveElement {
	ArrayList<Creature> members = new ArrayList<Creature>();
	int index;
	String name;

	public Party(Scanner s) {
		s.next(); // dump first field, p
		index = s.nextInt();
		name = s.next();
	} // String constructor

	public void addCreature(Creature c) {
		members.add(c);
	} // end method addCreature

	public String toString() {
		String sr = String.format("p:%6d:%s", index, name);
		for (Creature c : members)
			sr += "\n   " + c;
		return sr;
	} // end toString method
} // end class Party

//    c:<index>:<type>:<name>:<party>:<empathy>:<fear>:<carrying capacity>
class Creature extends CaveElement {
	ArrayList<Treasure> treasures = new ArrayList<Treasure>();
	ArrayList<Artifact> artifacts = new ArrayList<Artifact>();
	int index;
	String type, name;
	Party party = null;
	double empathy, fear, capacity;

	public int makeCreature(Scanner s) {
		int partyInd;
		s.next(); // dump first field, c
		index = s.nextInt();
		type = s.next();
		name = s.next();
		partyInd = s.nextInt();
		empathy = s.nextDouble();
		fear = s.nextDouble();
		capacity = s.nextDouble();
		return partyInd;
	} // Scanner Creature factory method

	public void addTreasure(Treasure t) {
		treasures.add(t);
	}

	public void addArtifact(Artifact a) {
		artifacts.add(a);
	}

	public String toString() {
		String sb = "";
		if (party == null)
			sb += String.format("c:%6d: %s : %s : %6d : %4.1f : %4.1f : %4.1f",
					index, type, name, 0, empathy, fear, capacity);
		else
			sb += String.format("c:%6d: %s : %s : %6d : %4.1f : %4.1f : %4.1f",
					index, type, name, party.index, empathy, fear, capacity);
		for (Treasure t : treasures)
			sb += "\n        " + t;
		for (Artifact a : artifacts)
			sb += "\n        " + a;
		return sb;
	} // end toString method
}

//    t:<index>:<type>:<creature>:<weight>:<value>
class Treasure extends CaveElement {
	int index;
	String type;
	double weight, value;
	Creature holder;

	public int makeTreasure(Scanner s) {
		int partyInd;
		s.next(); // dump first field, c
		index = s.nextInt();
		type = s.next();
		partyInd = s.nextInt();
		weight = s.nextDouble();
		value = s.nextDouble();
		return partyInd;
	} // Scanner Treasure factory method

	public String toString() {
		if (holder == null)
			return String.format("t:%6d: %s : %6d : %4.1f : %4.1f", index,
					type, 0, weight, value);
		return String.format("t:%6d: %s : %6d : %4.1f : %4.1f", index, type,
				holder.index, weight, value);
	} // end toString method
} // end class Treasure

//    a:<index>:<type>:<creature>[:<name>]
class Artifact extends CaveElement {
	int index;
	String type, name = "";
	Creature holder;

	public int makeArtifact(Scanner s) {
		int partyInd;
		s.next(); // dump first field, c
		index = s.nextInt();
		type = s.next();
		partyInd = s.nextInt();
		if (s.hasNext())
			name = s.next();
		return partyInd;
	} // Scanner Artifact factory method

	public String toString() {
		if (holder == null)
			return String.format("a:%6d: %s : %6d : %s", index, type, 0, name);
		return String.format("a:%6d: %s : %6d : %s", index, type, holder.index,
				name);
	} // end toString method
} // end class Artifact