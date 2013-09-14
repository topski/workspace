package P1_CMSC335;

/* File: SorcerersCave.java
 * Date: 08/27/2013
 * Author: James Watson
 * Purpose:
 */

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class SorcerersCave extends JFrame {
	static final long serialVersionUID = 123L;

	// hashmap of everything by index
	HashMap<Integer, CaveElement> hm = new HashMap<Integer, CaveElement>();

	JTextArea jta = new JTextArea();
	JComboBox jcb;
	JTextField jtf;
	Cave cave = new Cave();

	public SorcerersCave() {
		System.out.println("In constructor");
		setTitle("Sorcerer's Cave");
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		JScrollPane jsp = new JScrollPane(jta);
		add(jsp, BorderLayout.CENTER);

		JButton jbr = new JButton("Read");
		JButton jbd = new JButton("Display");
		JButton jbs = new JButton("Search");

		JLabel jls = new JLabel("Search target");

		jtf = new JTextField(10);

		jcb = new JComboBox();
		jcb.addItem("Index");
		jcb.addItem("Type");
		jcb.addItem("Name");

		JPanel jp = new JPanel();
		jp.add(jbr);
		jp.add(jbd);
		jp.add(jls);
		jp.add(jtf);
		jp.add(jcb);
		jp.add(jbs);

		add(jp, BorderLayout.PAGE_START);

		validate();

		jbr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				readFile(null);
			} // end required method
		} // end local definition of inner class
		); // the anonymous inner class

		jbd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayCave();
			} // end required method
		} // end local definition of inner class
		); // the anonymous inner class

		jbs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search((String) (jcb.getSelectedItem()), jtf.getText());
			} // end required method
		} // end local definition of inner class
		); // the anonymous inner class
	} // end no-parameter constructor

	void readFile(Scanner sf) {
		System.out.println("In readFile");
		String fileName = "/home/jwatson/workspace/School/dataC.txt";
		String inline;
		Scanner line;
		try {
			sf = new Scanner(new File(fileName));
		
		while (sf.hasNext()) {
			inline = sf.nextLine().trim();
			if (inline.length() == 0)
				continue;
			
			line = new Scanner(inline).useDelimiter("\\s*:\\s*"); // compress whitespace			
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
			} // end switch
		} // end while reading data file
		}// end try
		catch (FileNotFoundException e) {
			System.out.println("File not found: " + fileName);
			System.exit(1);
		}// end catch
	} // end readFile

	public void addParty(Scanner sc) {
		Party pt = new Party(sc);
		cave.parties.add(pt);
		hm.put(pt.index, pt);
	} // end method addParty

	public void addCreature(Scanner sc) {
		Creature c = new Creature();
		int target = c.makeCreature(sc);
		Party p = (Party) (hm.get(target));
		p.addCreature(c);
		c.party = p;
		hm.put(c.index, c);
	} // end method addToParty

	public void addTreasure(Scanner sc) {
		Treasure t = new Treasure();
		int target = t.makeTreasure(sc);
		Creature c = (Creature) (hm.get(target));
		c.addTreasure(t);
		t.holder = c;
		hm.put(t.index, t);
	} // end method addTreasure

	public void addArtifact(Scanner sc) {
		Artifact a = new Artifact();
		int target = a.makeArtifact(sc);
		Creature c = (Creature) (hm.get(target));
		c.addArtifact(a);
		a.holder = c;
		hm.put(a.index, a);
	} // end method addArtifact

	public void displayCave() {
		jta.append("Display Cav button pressed\n");
		jta.setText("" + cave);
	} // end method displayCave

	public void search(String type, String target) {
		for (int i = 0; i < cave.parties.size(); i++) {
            if (cave.parties.contains(target.trim())) {
                System.out.println("booyaa");
            }
        }
		//for (int i = 0; i < cave.parties.size(); i++) {
	     //   if (((List<Party>) cave.parties.get(i)).contains(target.trim()))
	     //       System.out.println(cave.parties.get(i));
	    //}
		//jta.append(String.format(
		//		"\nType: " + cave.parties.contains("20020") + "\nTarget: " + cave.parties.contains("Man") + "\n", type,
		//		target));
	} // end method search

	public static void main(String[] args) {
		
		SorcerersCave sc = new SorcerersCave();
				
	} // end main
} // end class SorcerersCave

class Cave {
	
	ArrayList<Party> parties = new ArrayList<Party>(); // the parties

	public String toString() {
		String sb = "";
		sb += toString(parties, "Party List");
		return sb;
	} // end method dump

	String toString(Iterable<? extends CaveElement> it, String label) {
		String sb = "\n----- " + label + " -------";
		for (CaveElement c : it)
			sb += "\n" + c.toString();
		return sb;
	} // end method dump Iterable String
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