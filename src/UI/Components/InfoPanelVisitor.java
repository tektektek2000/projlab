package UI.Components;

import Model.*;
import Model.Materials.*;

public class InfoPanelVisitor implements IVisitor {
    public boolean isAsteroid;
    public int Shell;
    public Material Core;
    public boolean isActive;
    public boolean isCrazy;

    public InfoPanelVisitor(IVisitable v){
        isAsteroid = false;
        v.accept(this);
    }

    @Override
    public void visit(TeleportGate tg) {
        isAsteroid = false;
        isActive = tg.isActive();
        isCrazy = tg.getWasHitByStorm();
    }

    @Override
    public void visit(Asteroid a) {
        isAsteroid = true;
        Shell = a.GetShell();
        Core = a.GetCore();
    }

    @Override
    public void visit(PlayerShip p) {

    }

    @Override
    public void visit(RobotShip r) {

    }

    @Override
    public void visit(UFO u) {

    }

    @Override
    public void visit(Uranium u) {

    }

    @Override
    public void visit(Iron i) {

    }

    @Override
    public void visit(Ice i) {

    }

    @Override
    public void visit(Coal tg) {

    }

    @Override
    public void visit(Base b) {

    }
}
