package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * NINCS még készen bocsi meghalok  de nem tudtam ma este(május 8.) megcsinalni holnap befejezem napkozben (május 9.)en
 */

public class MapLoadView extends JFrame{
    JPanel mainMenu;
    JComboBox configList;
    JButton loadGame;


    //a configList action listenere katintasra betolti a kivalasztott map configot
    private class configListButtonActonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    //A loadGame button action listenere alinditja a kivalasztott map configot
    private class loadGameButtonActonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }


    public MapLoadView(){
        mainMenu = new JPanel();
        configList = new JComboBox();
        loadGame = new JButton("Load Game");

        // ez csak minta bocsi faradt voltam hogy hogyan toltok dolgokat a JComboBox ba
        ArrayList<Integer> teszt_list =new ArrayList<>();
        Integer cnt_of_maps = 10;

        for(int i =0; i<cnt_of_maps; i++) {
            teszt_list.add(i);
            configList.addItem(teszt_list.get(i));
        }
        // idaig tart


        init();
    }

    public void init(){
        this.setMinimumSize(new Dimension(500,500));

        configList.addActionListener(new configListButtonActonListener());
        loadGame.addActionListener(new loadGameButtonActonListener());

        configList.setPreferredSize(new Dimension(100,25));
        loadGame.setPreferredSize(new Dimension(100,50));

        mainMenu.setLayout(new GridBagLayout());
        addComponent(mainMenu, configList, 2,0,GridBagConstraints.CENTER, GridBagConstraints.BOTH,GridBagConstraints.CENTER,GridBagConstraints.NONE);

        addComponent(mainMenu, loadGame, 2,1,GridBagConstraints.CENTER, GridBagConstraints.BOTH,GridBagConstraints.CENTER,GridBagConstraints.NONE);

        this.add(mainMenu);

        this.setVisible(true);


    }

    public static void addComponent(Container container, Component component, int gridx, int gridy, int gridwidth, int gridheight, int anchor, int fill) {
        Insets insets = new Insets(0,0,0,0);
        GridBagConstraints gbc = new GridBagConstraints(gridx, gridy, gridwidth, gridheight, 1.0, 1.0, anchor, fill,insets, 0, 0);

        container.add(component, gbc);
    }
}
