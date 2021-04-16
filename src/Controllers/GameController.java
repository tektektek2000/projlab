package Controllers;

import Model.*;
import Model.Materials.Material;
import Model.Materials.Uranium;
import Utils.InvalidCommand;

import java.util.ArrayList;

public class GameController {
    ArrayList<PlayerShip> ps = new ArrayList<>();
    ArrayList<RobotShip> rs = new ArrayList<>();
    ArrayList<UFO> ufos = new ArrayList<>();
    ArrayList<TeleportGate> tgs = new ArrayList<>();
    ArrayList<Uranium> urans = new ArrayList<>();

    public void PlayerDoes(int UID, String command) throws InvalidCommand {
        PlayerShip current = null;
        for(PlayerShip it : ps){
            if(it.GetUID() == UID){
                current = it;
            }
        }
        if(current == null)
            throw(new InvalidCommand());
        if(command.equals("drill")){
            current.Drill();
        }
        else if(command.equals("mine")){
            current.Mine();
        }
        else if(command.equals("build_base")){
            current.BuildBase();
        }
    }

    public void PlayerDoes(int UID, String command, ArrayList<String> Args) throws InvalidCommand {
        PlayerShip current = null;
        for(PlayerShip it : ps){
            if(it.GetUID() == UID){
                current = it;
            }
        }
        if(current == null)
            throw(new InvalidCommand());
        if(command.equals("move")){
            ArrayList<Field> fields = current.getAsteroid().getNeighbours();
            Field target = null;
            for(Field it : fields){
                if(it.GetUID() == Integer.parseInt(Args.get(0))){
                    target = it;
                }
            }
            if(target == null)
                throw(new InvalidCommand());
            current.Move(target);
        }
        else if(command.equals("craft")){
            switch (Args.get(0)) {
                case "robot":
                    current.CraftRobot();
                    break;
                case "teleports":
                    current.CraftTeleportGates();
                    break;
                case "base":
                    current.CraftBase();
                    break;
            }
        }
        else if(command.equals("put_back")){
            ArrayList<Material> materials = current.getMaterials();
            Material target = null;
            for(Material it : materials){
                if(it.GetUID() == Integer.parseInt(Args.get(0))){
                    target = it;
                }
            }
            if(target == null)
                throw(new InvalidCommand());
            current.PutBack(target);
        }
        else if(command.equals("put_down")){
            ArrayList<TeleportGate> materials = current.getTeleports();
            TeleportGate target = null;
            for(TeleportGate it : materials){
                if(it.GetUID() == Integer.parseInt(Args.get(0))){
                    target = it;
                }
            }
            if(target == null)
                throw(new InvalidCommand());
            current.PutDown(target);
        }
    }

    public void RobotDoes(int UID, String command) throws InvalidCommand {
        RobotShip current = null;
        for(RobotShip it : rs){
            if(it.GetUID() == UID){
                current = it;
            }
        }
        if(current == null)
            throw(new InvalidCommand());
        if(command.equals("drill")){
            current.Drill();
        }
    }

    public void RobotDoes(int UID, String command, ArrayList<String> Args) throws InvalidCommand {
        RobotShip current = null;
        for(RobotShip it : rs){
            if(it.GetUID() == UID){
                current = it;
            }
        }
        if(current == null)
            throw(new InvalidCommand());
        if(command.equals("move")){
            ArrayList<Field> fields = current.getAsteroid().getNeighbours();
            Field target = null;
            for(Field it : fields){
                if(it.GetUID() == Integer.parseInt(Args.get(0))){
                    target = it;
                }
            }
            if(target == null)
                throw(new InvalidCommand());
            current.Move(target);
        }
    }

    public void UFODoes(int UID, String command) throws InvalidCommand {
        UFO current = null;
        for(UFO it : ufos){
            if(it.GetUID() == UID){
                current = it;
            }
        }
        if(current == null)
            throw(new InvalidCommand());
        if(command.equals("mine")){
            current.Mine();
        }
    }

    public void UFODoes(int UID, String command, ArrayList<String> Args) throws InvalidCommand {
        UFO current = null;
        for(UFO it : ufos){
            if(it.GetUID() == UID){
                current = it;
            }
        }
        if(current == null)
            throw(new InvalidCommand());
        if(command.equals("move")){
            ArrayList<Field> fields = current.getAsteroid().getNeighbours();
            Field target = null;
            for(Field it : fields){
                if(it.GetUID() == Integer.parseInt(Args.get(0))){
                    target = it;
                }
            }
            if(target == null)
                throw(new InvalidCommand());
            current.Move(target);
        }
    }

    public void TeleportDoes(int UID, String command) throws InvalidCommand {
        TeleportGate current = null;
        for(TeleportGate it : tgs){
            if(it.GetUID() == UID){
                current = it;
            }
        }
        if(current == null)
            throw(new InvalidCommand());
        if(command.equals("turnover")){
            current.TurnOver();
        }
    }

    public void TeleportDoes(int UID, String command, ArrayList<String> Args) throws InvalidCommand {
        TeleportGate current = null;
        for(TeleportGate it : tgs){
            if(it.GetUID() == UID){
                current = it;
            }
        }
        if(current == null)
            throw(new InvalidCommand());
        if(command.equals("move")){
            ArrayList<Field> fields = current.getNeighbours();
            Field target = null;
            for(Field it : fields){
                if(it.GetUID() == Integer.parseInt(Args.get(0))){
                    target = it;
                }
            }
            if(target == null)
                throw(new InvalidCommand());
            current.Move(target);
        }
    }

    public void UraniumDoes(int UID, String command) throws InvalidCommand {
        Uranium current = null;
        for(Uranium it : urans){
            if(it.GetUID() == UID){
                current = it;
            }
        }
        if(current == null)
            throw(new InvalidCommand());
        if(command.equals("turnover")){
            current.TurnOver();
        }
        else if(command.equals("explode")){
            current.ForceExplode();
        }
    }

    public void SunStorm(){
        Sun.GetInstance().SunStorm();
    }
}
