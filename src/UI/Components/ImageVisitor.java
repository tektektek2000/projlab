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

public class ImageVisitor implements IVisitor {
    Image image = null;
    String filePath;

    public ImageVisitor(IVisitable v){
        filePath = new File("").getAbsolutePath();
        filePath+="\\UI\\Components\\Resources\\";
        v.accept(this);
        try {
            image = new Image(new FileInputStream(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Image getImage(){
        return image;
    }

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
        filePath += "Asteroid.png";
    }

    @Override
    public void visit(PlayerShip p) {
        filePath += "PlayerShip" + (p.GetUID()%5 + 1) + ".png";
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
