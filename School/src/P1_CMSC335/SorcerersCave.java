package P1_CMSC335;

/* File: SorcerersCave.java
 * Date: 08/27/2013
 * Author: James Watson
 * Purpose:
 */

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import java.util.ArrayList;

public class SorcerersCave extends JFrame {
	private static final long serialVersionUID = 6410274498152162892L;

	JTextArea jta = new JTextArea();
	JComboBox jcb;
	JTextField jtf;
	Cave cave = new Cave();

	public SorcerersCave() {
		System.out.println("In Constructor");
		setTitle("Sorcerer's Cave");
		setSize(600, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		JScrollPane jsp = new JScrollPane(jta);
		add(jsp, BorderLayout.CENTER);

		JButton jbr = new JButton("Read");
		JButton jbs = new JButton("Search");
		JButton jbd = new JButton("Display");

		JLabel jls = new JLabel("Search Target");

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
			@Override
			public void actionPerformed(ActionEvent e) {
				readFile();
			}// end required method
		});//end inner class
		
		jbd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				displayCave();
			}// end required method
		});//end inner class
		
		jbs.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				search((String)(jcb.getSelectedItem()), jtf.getText());
			}// end required method
		});//end inner class
		
	}// end no-parameter constructor
	
	public void readFile() {
		jta.append("Read File Button Pressed\n");
	}//end method ReadFile
	
	public void displayCave() {
		jta.append("Display Cave Button Pressed\n");
	}//end method displayCave
	
	public void search(String type, String target) {
		jta.append(String.format("Search button pressed, type: >%s<, target: >%s<\n", type, target));
	}//end method search

	public static void main(String[] args) {
		SorcerersCave sc = new SorcerersCave();
	}// end main

}// end class SorcerersCave

class Cave {
	ArrayList <Party> parties = new ArrayList <Party> ();
	
}
