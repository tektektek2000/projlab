package GUI;

import javax.swing.*;
import java.awt.*;

public class InventorySidebar extends JPanel {

    JPanel inventory;


    JLabel acitonTokens;
    JLabel nucleotide;
    JLabel aminoacid;
    JLabel equipments;
    JLabel learnedAgents;
    JLabel syntetizedAgents;
    JLabel appliedAgents;
    JLabel spacer;

    /// implementacional kelleni fog paraméterek az adatokhoz label parametereben + al hozza fuzve
    public InventorySidebar(){
        inventory = new JPanel();

        acitonTokens = new JLabel("ActionTokens: ");
        nucleotide = new JLabel("Nucleotide: ");
        aminoacid = new JLabel("Aminoacid: ");
        equipments = new JLabel("Equipments: ");
        learnedAgents = new JLabel("LearnedAgents: ");
        syntetizedAgents = new JLabel("SyntetizedAgents: ");
        appliedAgents = new JLabel("AppliedAgents: ");
        spacer = new JLabel();

        init();
    }


    private void init(){
        inventory.setLayout(new GridLayout(15,1));

        inventory.add(acitonTokens);
        inventory.add(new JSeparator(SwingConstants.HORIZONTAL));
        inventory.add(nucleotide);
        inventory.add(new JSeparator(SwingConstants.HORIZONTAL));
        inventory.add(aminoacid);
        inventory.add(new JSeparator(SwingConstants.HORIZONTAL));
        inventory.add(equipments);
        inventory.add(new JSeparator(SwingConstants.HORIZONTAL));
        inventory.add(learnedAgents);
        inventory.add(new JSeparator(SwingConstants.HORIZONTAL));
        inventory.add(syntetizedAgents);
        inventory.add(new JSeparator(SwingConstants.HORIZONTAL));
        inventory.add(appliedAgents);
        inventory.add(new JSeparator(SwingConstants.HORIZONTAL));
        inventory.add(spacer);

        this.add(inventory);

        this.setVisible(true);

    }

}
