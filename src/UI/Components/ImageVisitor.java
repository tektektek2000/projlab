package UI.Components;

import Model.*;
import Model.Materials.Coal;
import Model.Materials.Ice;
import Model.Materials.Iron;
import Model.Materials.Uranium;
import javafx.scene.image.Image;

import javax.swing.text.html.ImageView;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ImageVisitor implements IVisitor {
    Image image = null;
    String filePath;
    ArrayList<Ship> ships = null;
    static Image AsteroidImage = null;
    static Image ActiveTeleportImage = null;
    static Image CrazyTeleportImage = null;
    static Image InactiveTeleportImage = null;
    static Image Player1 = null;
    static Image Player2 = null;
    static Image Player3 = null;
    static Image Player4 = null;
    static Image Player5 = null;
    static Image Robot = null;
    static Image UFO = null;
    static Image Uranium = null;
    static Image Iron = null;
    static Image Ice = null;
    static Image Coal = null;

    public ImageVisitor(IVisitable v){
        filePath = new File("").getAbsolutePath();
        filePath+="\\img\\";
        v.accept(this);
    }

    public Image getImage(){
        return image;
    }
    public ArrayList<Ship> getShips(){return ships;}

    @Override
    public void visit(TeleportGate tg) {
        if(tg.getWasHitByStorm())
            filePath += "Insane_teleportgate.png";
        else if(tg.isActive())
            filePath += "Active_teleportgate.png";
        else
            filePath += "Not_active_teleportgate.png";
    }

    @Override
    public void visit(Asteroid a) {
        if(AsteroidImage == null) {
            try {
                filePath += "Asteroid.png";
                AsteroidImage = new Image(new FileInputStream(filePath));
            } catch (FileNotFoundException e) {
                System.out.println(filePath);
                e.printStackTrace();
            }
        }
        image = AsteroidImage;
    }

    @Override
    public void visit(PlayerShip p) {
        switch (p.GetUID() % 5 + 1){
            case 1:
                if(Player1 == null) {
                    try {
                        filePath += "PlayerShip1.png";
                        Player1 = new Image(new FileInputStream(filePath));
                    } catch (FileNotFoundException e) {
                        System.out.println(filePath);
                        e.printStackTrace();
                    }
                }
                image = Player1;
                break;
            case 2:
                if(Player2 == null) {
                    try {
                        filePath += "PlayerShip1.png";
                        Player2 = new Image(new FileInputStream(filePath));
                    } catch (FileNotFoundException e) {
                        System.out.println(filePath);
                        e.printStackTrace();
                    }
                }
                image = Player2;
                break;
            case 3:
                if(Player3 == null) {
                    try {
                        filePath += "PlayerShip1.png";
                        Player3 = new Image(new FileInputStream(filePath));
                    } catch (FileNotFoundException e) {
                        System.out.println(filePath);
                        e.printStackTrace();
                    }
                }
                image = Player3;
                break;
            case 4:
                if(Player4 == null) {
                    try {
                        filePath += "PlayerShip1.png";
                        Player4 = new Image(new FileInputStream(filePath));
                    } catch (FileNotFoundException e) {
                        System.out.println(filePath);
                        e.printStackTrace();
                    }
                }
                image = Player4;
                break;
            case 5:
                if(Player5 == null) {
                    try {
                        filePath += "PlayerShip1.png";
                        Player5 = new Image(new FileInputStream(filePath));
                    } catch (FileNotFoundException e) {
                        System.out.println(filePath);
                        e.printStackTrace();
                    }
                }
                image = Player5;
                break;
        }
    }

    @Override
    public void visit(RobotShip r) {
        filePath += "Robot.png";
    }

    @Override
    public void visit(UFO u) {
        filePath += "UFO.png";
    }

    @Override
    public void visit(Uranium u) {
        filePath += "Uranium.png";
    }

    @Override
    public void visit(Iron i) {
        filePath += "Iron.png";
    }

    @Override
    public void visit(Ice i) {
        filePath += "Ice.png";
    }

    @Override
    public void visit(Coal tg) {
        filePath += "Coal.png";
    }
}