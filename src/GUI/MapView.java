package GUI;

import javax.swing.*;
import javax.swing.border.Border;

public class MapView extends JPanel {

    // ez lesz a jövöhét zenéje
    JPanel Map;

    public MapView(){
        Map = new JPanel();

        init();
    }


    private void init(){
        Map.setSize(400,400);
        Map.setVisible(true);
        Border border = BorderFactory.createTitledBorder("Map");
        Map.setBorder(border);
    }

}
