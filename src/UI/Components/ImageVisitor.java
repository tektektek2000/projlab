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
    Base base = null;
    TeleportGate pair = null;
    static Image AsteroidImage = null;
    static Image ActiveTeleportImage = null;
    static Image InsaneTeleportImage = null;
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
    static Image BaseFoundation = null;
    static Image BaseComplete = null;

    public ImageVisitor(IVisitable v){
        filePath = new File("").getAbsolutePath();
        filePath+="\\img\\";
        v.accept(this);
    }

    public Image getImage(){
        return image;
    }
    public ArrayList<Ship> getShips(){return ships;}

    public Base getBase(){return base;}

    public TeleportGate getPair(){return pair;}

    @Override
    public void visit(TeleportGate tg){
        if(tg.getWasHitByStorm()){
            if(InsaneTeleportImage == null)
            {
                try {
                    filePath += "Insane_teleportgate.png";
                    InsaneTeleportImage = new Image(new FileInputStream(filePath));
                } catch (FileNotFoundException e){
                    System.out.println(filePath);
                    e.printStackTrace();
                }
            }
            image = InsaneTeleportImage;
        }
        else if(tg.isActive()){
            if(ActiveTeleportImage == null){
                try{
                    filePath += "Active_teleportgate.png";
                    ActiveTeleportImage = new Image(new FileInputStream(filePath));
                } catch (FileNotFoundException e){
                    System.out.println(filePath);
                    e.printStackTrace();
                }
            }
            image = ActiveTeleportImage;
        }
        else{
            if(InactiveTeleportImage == null){
                try{
                    filePath += "Not_active_teleportgate.PNG";
                    InactiveTeleportImage = new Image(new FileInputStream(filePath));
                } catch (FileNotFoundException e){
                    System.out.println(filePath);
                    e.printStackTrace();
                }
            }
            image = InactiveTeleportImage;
        }
        pair = tg.getPair();
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
        ships = a.getShips();
        base = a.GetBase();
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
                        filePath += "PlayerShip2.png";
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
                        filePath += "PlayerShip3.png";
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
                        filePath += "PlayerShip4.png";
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
                        filePath += "PlayerShip5.png";
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
        if(Robot == null){
            try{
                filePath += "Robot.png";
                Robot = new Image(new FileInputStream(filePath));
            } catch (FileNotFoundException e){
                System.out.println(filePath);
                e.printStackTrace();
            }
        }
        image = Robot;
    }

    @Override
    public void visit(UFO u) {
        if(UFO == null){
            try{
                filePath += "UFO.png";
                UFO = new Image(new FileInputStream(filePath));
            } catch (FileNotFoundException e){
                System.out.println(filePath);
                e.printStackTrace();
            }
        }
        image = UFO;
    }

    @Override
    public void visit(Uranium u) {
        if(Uranium == null){
            try{
                filePath += "Uranium.png";
                Uranium = new Image(new FileInputStream(filePath));
            } catch (FileNotFoundException e){
                System.out.println(filePath);
                e.printStackTrace();
            }
        }
        image = Uranium;
    }

    @Override
    public void visit(Iron i) {
        if(Iron == null){
            try{
                filePath += "Iron.png";
                Iron = new Image(new FileInputStream(filePath));
            } catch (FileNotFoundException e){
                System.out.println(filePath);
                e.printStackTrace();
            }
        }
        image = Iron;
    }

    @Override
    public void visit(Ice i) {
        if(Ice == null){
            try{
                filePath += "Ice.png";
                Ice = new Image(new FileInputStream(filePath));
            } catch (FileNotFoundException e){
                System.out.println(filePath);
                e.printStackTrace();
            }
        }
        image = Ice;
    }

    @Override
    public void visit(Coal tg) {
        if(Coal == null){
            try{
                filePath += "Coal.png";
                Coal = new Image(new FileInputStream(filePath));
            } catch (FileNotFoundException e){
                System.out.println(filePath);
                e.printStackTrace();
            }
        }
        image = Coal;
    }

    @Override
    public void visit(Base b) {
        if(b.CheckComplete()){
            if(BaseComplete == null) {
                try {
                    filePath += "BaseDone.png";
                    BaseComplete = new Image(new FileInputStream(filePath));
                } catch (FileNotFoundException e) {
                    System.out.println(filePath);
                    e.printStackTrace();
                }
            }
            image = BaseComplete;
        }
        else{
            if(BaseFoundation == null) {
                try {
                    filePath += "BaseFoundation.png";
                    BaseFoundation = new Image(new FileInputStream(filePath));
                } catch (FileNotFoundException e) {
                    System.out.println(filePath);
                    e.printStackTrace();
                }
            }
            image = BaseFoundation;
        }

    }
}
