package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionSidebar extends JPanel {

    JPanel actionSidebar;
    JButton move;
    JButton synthetize;
    JButton kill;
    JButton spread;


    private class moveButtonActonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
    private class synthetizeButtonActonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
    private class killButtonActonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
    private class spreadButtonActonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    public ActionSidebar(){
        actionSidebar = new JPanel();
        move = new JButton("Move");
        synthetize = new JButton("Synthetize");
        kill = new JButton("Kill");
        spread = new JButton("Spread");

        init();
    }

    private void init(){
        actionSidebar.setLayout(new GridLayout(4,1));

        move.setPreferredSize(new Dimension(100,50));
        synthetize.setPreferredSize(new Dimension(100,50));
        kill.setPreferredSize(new Dimension(100,50));
        spread.setPreferredSize(new Dimension(100,50));

        move.addActionListener(new moveButtonActonListener());
        synthetize.addActionListener(new synthetizeButtonActonListener());
        kill.addActionListener(new killButtonActonListener());
        spread.addActionListener(new spreadButtonActonListener());



        actionSidebar.add(move);
        actionSidebar.add(synthetize);
        actionSidebar.add(kill);
        actionSidebar.add(spread);

        this.add(actionSidebar);
        this.setVisible(true);
    }
}
