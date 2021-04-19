package Controllers;

import Model.*;
import Model.Materials.Material;
import Model.Materials.Uranium;
import Utils.InvalidCommand;

import java.io.File;
import java.util.ArrayList;

public class GameController {
    ArrayList<PlayerShip> ps = new ArrayList<>();
    ArrayList<RobotShip> rs = new ArrayList<>();
    ArrayList<UFO> ufos = new ArrayList<>();
    ArrayList<TeleportGate> tgs = new ArrayList<>();
    ArrayList<Uranium> urans = new ArrayList<>();
    Map map=null;
    boolean controller = true;
    String CurrentWorkingDirectory = System.getProperty("user.dir");
    private PlayerShip CurrentPlayer;

    PlayerShip getCurrentPlayer(){
        if(CurrentPlayer==null){
            CurrentPlayer = ps.get(0);
        }
        return CurrentPlayer;
    }

    void setCurrentPlayer(PlayerShip p){
        CurrentPlayer = p;
    }

    void setController(boolean val){
        controller = val;
    }

    void InterpretCommand(String CommandLine) throws Exception {
        String[] parts = CommandLine.split(" ");

        if (parts[0].equals("@Fail")){
            boolean Failed = false;
            try {
                String LineWithOutFlag = parts[1] + " ";
                for(int i=2;i<parts.length;i++) {
                    LineWithOutFlag += parts[i];
                    if(i < parts.length-1)
                        LineWithOutFlag += " ";
                }
                InterpretCommand(LineWithOutFlag);
            } catch (InvalidCommand e){
                Failed = true;
            }
            if(!Failed) {
                throw (new InvalidCommand(CommandLine + "-> Line marked as Fail didn't fail"));
            }
        }
        else if (parts[0].equals("sunStorm")){
            SunStorm(Integer.parseInt(parts[1]));
        }
        else if (parts[0].equals("save")){
            FileController fc = new FileController();
            fc.Save(new File(CurrentWorkingDirectory + "\\" + parts[1]),map,this);
        }
        else if(parts[0].equals("load")){
            FileController fc = new FileController();
            map = fc.Load(new File(CurrentWorkingDirectory + "\\" + parts[1]),this);
        }
        else if(parts[0].equals("controller")){
            controller = Boolean.parseBoolean(parts[1]);
        }
        else if(parts[0].equals("endturn")){
            EndTurn();
        }
        else if(parts[0].equals("ls")){
            boolean all_attrib = false;
            if(parts.length >= 3){
                if(parts[2].equals("att")){
                    all_attrib = true;
                }
            }
            switch (parts[1]){
                case "all":
                    map.Save(System.out);
                    break;
                case "p":
                    for(PlayerShip it : ps){
                        if(all_attrib)
                            it.Save(System.out, false);
                        else
                            System.out.println("Player: " + it.GetUID());
                    }
                    break;
                case "s":
                    for(PlayerShip it : ps){
                        if(all_attrib)
                            it.Save(System.out, false);
                        else
                            System.out.println("Player: " + it.GetUID());
                    }
                    for(UFO it : ufos){
                        if(all_attrib)
                            it.Save(System.out, false);
                        else
                            System.out.println("Player: " + it.GetUID());
                    }
                case "r":
                    for(RobotShip it : rs){
                        if(all_attrib)
                            it.Save(System.out, false);
                        else
                            System.out.println("Robot: " + it.GetUID());
                    }
                    break;
                case "u":
                    for(UFO it : ufos){
                        if(all_attrib)
                            it.Save(System.out, false);
                        else
                            System.out.println("Player: " + it.GetUID());
                    }
                    break;
                case "a":
                    for(Sector s : map.getSectors()){
                        for(Field f : s.getFields()){
                            if(f.toString().equals("Asteroid")){
                                if(all_attrib)
                                    f.Save(System.out, false);
                                else
                                    System.out.println("Player: " + f.GetUID());
                            }
                        }
                    }
                    break;
                case "t":
                    for(TeleportGate it : tgs){
                        if(all_attrib)
                            it.Save(System.out, false);
                        else
                            System.out.println("Player: " + it.GetUID());
                    }
                    break;
            }
        }
        else if(parts[0].equals("compare")){
            FileController fc = new FileController();
            fc.Compare(new File(CurrentWorkingDirectory + "\\" + parts[1]),new File(CurrentWorkingDirectory + "\\" + parts[2]));
        }
        else{
            if(parts.length < 3){
                throw(new InvalidCommand(CommandLine + "-> Too few parameter for UID search or unknown command"));
            }
            char TypeFlag = parts[0].charAt(0);
            int UID = Integer.parseInt(parts[1]);
            if(controller && UID != getCurrentPlayer().GetUID()){
                throw (new InvalidCommand(CommandLine + " -> " + "The object with this identifier is not the one taking its turn."));
            }
            ArrayList<String> Args = new ArrayList<>();
            for(int i=3;i< parts.length;i++){
                Args.add(parts[i]);
            }
            switch (TypeFlag){
                case 'p':
                    PlayerDoes(UID,parts[2], Args);
                    break;
                case 'r':
                    RobotDoes(UID,parts[2], Args);
                    break;
                case 'u':
                    UFODoes(UID,parts[2], Args);
                    break;
                case 't':
                    TeleportDoes(UID,parts[2], Args);
                    break;
                default:
                    throw(new InvalidCommand(CommandLine + "-> Unknown typeflag"));
            }
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
            throw(new InvalidCommand( "p "+ UID + " " + command, Args ,"-> player UID not found"));
        if(command.equals("move")){
            ArrayList<Field> fields = current.getAsteroid().getNeighbours();
            Field target = null;
            for(Field it : fields){
                if(it.GetUID() == Integer.parseInt(Args.get(0))){
                    target = it;
                }
            }
            if(target == null)
                throw(new InvalidCommand("p "+ UID + " " + command, Args , "-> Field UID not found on move"));
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
                throw(new InvalidCommand("p "+ UID + " " + command, Args , "-> Material UID not found on put_back"));
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
                throw(new InvalidCommand("p "+ UID + " " + command, Args , "-> Teleport UID not found on put_down"));
            current.PutDown(target);
        }
        else if(command.equals("drill")){
            current.Drill();
        }
        else if(command.equals("mine")){
            current.Mine();
        }
        else if(command.equals("build_base")){
            current.BuildBase();
        }
        else{
            throw(new InvalidCommand("p "+ UID + " " + command, Args , "-> Unkown command"));
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
            throw(new InvalidCommand("r "+ UID + " " + command, Args , "-> Robot UID not found"));
        if(command.equals("move")){
            ArrayList<Field> fields = current.getAsteroid().getNeighbours();
            Field target = null;
            for(Field it : fields){
                if(it.GetUID() == Integer.parseInt(Args.get(0))){
                    target = it;
                }
            }
            if(target == null)
                throw(new InvalidCommand("r "+ UID + " " + command, Args , "-> Field UID not found on move"));
            current.Move(target);
        }
        else if(command.equals("drill")){
            current.Drill();
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
            throw(new InvalidCommand("u "+ UID + " " + command, Args , "-> UFO UID not found"));
        if(command.equals("move")){
            ArrayList<Field> fields = current.getAsteroid().getNeighbours();
            Field target = null;
            for(Field it : fields){
                if(it.GetUID() == Integer.parseInt(Args.get(0))){
                    target = it;
                }
            }
            if(target == null)
                throw(new InvalidCommand("u "+ UID + " " + command, Args , "-> Field UID not found on move"));
            current.Move(target);
        }
        else if(command.equals("mine")){
            current.Mine();
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
            throw(new InvalidCommand("t "+ UID + " " + command, Args , "-> Teleport UID not found"));
        if(command.equals("move")){
            ArrayList<Field> fields = current.getNeighbours().get(0).getNeighbours();
            Field target = null;
            for(Field it : fields){
                if(it.GetUID() == Integer.parseInt(Args.get(0))){
                    target = it;
                }
            }
            if(target == null)
                throw(new InvalidCommand("t "+ UID + " " + command, Args , "-> Field UID not found on move"));
            current.Move(target);
        }
        else if(command.equals("turnover")){
            current.TurnOver();
        }
    }

    public void UraniumDoes(int UID, String command, ArrayList<String> Args) throws InvalidCommand {
        Uranium current = null;
        for(Uranium it : urans){
            if(it.GetUID() == UID){
                current = it;
            }
        }
        if(current == null)
            throw(new InvalidCommand("U "+ UID + " " + command, Args , "-> Uranium UID not found"));
        if(command.equals("turnover")){
            current.TurnOver();
        }
    }

    public void SunStorm(int UID) throws InvalidCommand {
        ArrayList<Sector> sectors = map.getSectors();
        Sector target = null;
        for(Sector it : sectors){
            if(it.GetUID() == UID){
                target = it;
            }
        }
        if(target == null)
            throw(new InvalidCommand("sunstorm "+ UID +  "-> Robot UID not found"));
        Sun.GetInstance().SunStorm(target);
    }

    public void EndTurn(){
        for(Uranium uranium : urans){
            uranium.TurnOver();
        }
        for(TeleportGate t : tgs){
            t.TurnOver();
        }
        Sun.GetInstance().TurnOver();
    }
}
