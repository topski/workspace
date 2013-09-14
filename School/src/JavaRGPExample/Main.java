package JavaRGPExample;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;


public class Main extends JFrame implements ActionListener {
    
    //Instantiate our Swing GUI objects
    JTextArea output = new JTextArea();
    JFileChooser inputFile = new JFileChooser(".");
    JButton openButton = new JButton("Load File");
    JTextField searchField = new JTextField(20);
    JButton showData = new JButton("Show Data");
    JButton searchButton = new JButton("Search");
    JButton sortButton = new JButton("Sort");
    String[] sortStrings = {"empathy", "carrying", "fear", "weight", "value", "age", "height"};
    JComboBox sortList = new JComboBox(sortStrings);
    JTree gameTree;
    JScrollPane midPane;
    //Instantiate the Game object by calling the no-argument constructor
    Game game = new Game();
    
    public Main(String title){
        //register our Action Listeners for the buttons
        this.openButton.addActionListener(this);
        this.showData.addActionListener(this);
        this.searchButton.addActionListener(this);
        this.sortButton.addActionListener(this);                
        
        //set up the JFrame
        setTitle(title);
        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize (800, 800);
        setVisible (true);
        //Add the scroll pane
        midPane = new JScrollPane (output);
        add (midPane, BorderLayout.CENTER);
        //create a panel, add the buttons, then add to the JFrame
        JPanel jp = new JPanel (); 
        jp.add (openButton);
        jp.add (showData);  
        jp.add(sortButton);
        jp.add(sortList);
        jp.add(searchButton);
        jp.add(searchField);        
        add (jp, BorderLayout.PAGE_END);
    }       
    
    @Override
    public void actionPerformed(ActionEvent e) {
    //Handle open button action.
    if (e.getSource() == openButton) {
        //show the choose file dialog
        int returnVal = inputFile.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            //if the user has selected a file, we get the file in a File class
            File file = inputFile.getSelectedFile();
            try { 
                //then, we set the game elements by parsing the file
                game.setGameElements(file.getAbsolutePath());
                //let the user know the data was loaded and parsed properly
                JOptionPane.showMessageDialog(null, "Data Successfully Loaded!");
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
   }else if (e.getSource() == showData){
       
       //the JTree
        DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
        renderer.setOpenIcon(null);
        renderer.setClosedIcon(null);
        renderer.setLeafIcon(null);
        final DefaultMutableTreeNode topNode = new DefaultMutableTreeNode("Game/Cave", true);        
        gameTree = new JTree(topNode);
        gameTree.setCellRenderer(renderer);        
        JScrollPane sp = new JScrollPane(gameTree);
        gameTree.addTreeSelectionListener(new TreeSelectionListener(){
           @Override
           public void valueChanged(TreeSelectionEvent e) {
               if (topNode != null){
                   DefaultMutableTreeNode partyNode = null;
                   DefaultMutableTreeNode creatureNode = null;
                   DefaultMutableTreeNode artifactNode = null;
                   DefaultMutableTreeNode treasureNode = null;
                   DefaultMutableTreeNode lonerNode = null;
                   for (Party p : game.parties){
                       partyNode = new DefaultMutableTreeNode(p.name, true);                       
                       if (!p.creatures.isEmpty()){
                           for (Creature c : p.creatures){
                                 creatureNode = new DefaultMutableTreeNode(c.name, true);
                                 if (!c.artifacts.isEmpty()){
                                     for (Artifact a : c.artifacts){
                                         artifactNode = new DefaultMutableTreeNode(a.type, false);
                                         creatureNode.add(artifactNode);
                                     }
                                 }
                                 if (!c.treasures.isEmpty()){
                                     for (Treasure t : c.treasures){
                                         treasureNode = new DefaultMutableTreeNode(t.type, false);
                                         creatureNode.add(treasureNode);
                                     }
                                 }
                            partyNode.add(creatureNode);
                           }
                       }                       
                       topNode.add(partyNode);                       
                   }
                   lonerNode = new DefaultMutableTreeNode("Loners", true);
                       for (GameElement ge : game.loners){
                          if (ge instanceof Artifact || ge instanceof Treasure){
                            DefaultMutableTreeNode geNode = new DefaultMutableTreeNode(ge.type, false);
                            lonerNode.add(geNode);
                          }else{
                            DefaultMutableTreeNode geNode = new DefaultMutableTreeNode(ge.name, false);
                            lonerNode.add(geNode);
                          }
                       }
                       topNode.add(lonerNode);
               }
           }                        
        });
        
        //add(midPane, BorderLayout.CENTER);       
        //repaint();
        JLabel lbl = new JLabel("Double-click the root node to load and view sub-nodes.");
        JFrame jf = new JFrame ("Show Data: Game Tree");
        JPanel lblPanel = new JPanel();
        lblPanel.setBorder(new EmptyBorder(10, 10, 10, 10) );
        lblPanel.add(lbl);
        jf.add(lblPanel, BorderLayout.NORTH);
        sp.setBorder(new EmptyBorder(10, 10, 10, 10) );
        jf.add(sp, BorderLayout.SOUTH);
        jf.pack();                
        jf.setVisible (true);        
        jf.setLocationRelativeTo (null);
        jf.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);               
       
       //Output all of the parsed data using each class' toString method
       //String outputText = "Game Elements: \n--------------\n";
       //outputText += game.printAllElements();
       //output.setText(outputText);
   }else if (e.getSource() == searchButton){  
       //Search for input, checking if it matches an ID, name or type field
       //converting everything lowecase to make it a more user-friendly search.
       String resultText = "Results: \n------------\n";
       String searchInput = searchField.getText();
       resultText += game.searchGame(searchInput);
       output.setText(resultText);
   }else if (e.getSource() == sortButton){
       //To sort based on input criteria from the dropdown menu
      output.setText("");
      String sortInput = sortList.getSelectedItem().toString();  
      String resultText = game.printSortedElements(sortInput);      
      output.setText(resultText);
    }
}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Start the program by calling the single-argument constructor of the main file (this one)
        Main p = new Main("New Game");        
    }
}