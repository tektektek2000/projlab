package GUI;

import javax.swing.*;
import java.awt.*;

import static GUI.MapLoadView.addComponent;

public class MainWindow extends JFrame {
    MapView mapV;
    InventorySidebar invertoryV;
    ActionSidebar actionV;


    public MainWindow(){
        mapV = new MapView();
        invertoryV = new InventorySidebar();
        actionV = new ActionSidebar();
        mapV = new MapView();
        init();
    }

    private void init(){
        this.setMinimumSize(new Dimension(500,500));
        this.setLayout(new GridBagLayout());

        //jobb felos resz
        addComponent(this, invertoryV, 2,0,GridBagConstraints.NORTHEAST, GridBagConstraints.NORTHEAST,GridBagConstraints.NORTHEAST,GridBagConstraints.NORTHEAST);

        //jobb also resz
        addComponent(this, actionV, 2,1,GridBagConstraints.SOUTHEAST, GridBagConstraints.SOUTHEAST,GridBagConstraints.SOUTHEAST,GridBagConstraints.SOUTHEAST);

        //mapView mivel meg nincs mit kirajzolni így üres ez a rész
        addComponent(this, mapV, 0,0,GridBagConstraints.WEST, GridBagConstraints.WEST,GridBagConstraints.WEST,GridBagConstraints.WEST);

        this.setVisible(true);
    }



}
