package Controllers;

import Model.Field;
import Utils.BadFileFormat;
import Utils.InvalidCommand;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class MenuController {
    GameController gc = new GameController();

    public void Start(){
        System.out.println("Asteroid Miner");
        Scanner in = new Scanner(System.in);
        boolean exit = false;
        while(!exit){
            String line = in.nextLine();
            String parts[] = line.split(" ");
            if(parts[0].equals("runtests")){
                TestRunner tc = null;
                try {
                    tc = new TestRunner("teszt");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                tc.RunAllTests(gc);
            }
            else if(parts[0].equals("start")){
                gc.NewMap();
                Game();
            }
            else if(parts[0].equals("load")){
                try {
                    gc.InterpretCommand(line);
                    Game();
                }
                catch (InvalidCommand e) {
                    System.out.println(e.getMessage());
                }
                catch (BadFileFormat e) {
                    System.out.println(e.getMessage());
                }
                catch (Exception e) {
                    System.out.println("Unknown error while loading file");
                    e.printStackTrace();
                }
            }
            else if(parts[0].equals("exit")){
                exit = true;
            }
            else if(parts[0].equals("help")){
                System.out.println("Usable commands:");
                System.out.println("start -> Creates a new map and starts game");
                System.out.println("load filename -> Loads a given map and starts game");
                System.out.println("runtests -> Runs all the tests, and gives results");
                System.out.println("exit -> Exits from game");
            }
            else{
                System.out.println("Unknown command");
            }
        }
    }

    public void Game(){
        boolean exit = false;
        Scanner in = new Scanner(System.in);
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_RED = "\u001B[31m";
        while (!exit){
            PrintStream out = new PrintStream(System.out);
            gc.getCurrentPlayer().Save(out,false);
            System.out.println("Current Asteroid:");
            gc.getCurrentPlayer().getAsteroid().Save(out,false);
            String line = in.nextLine();
            String[] parts = line.split(" ");
            if(parts[0].equals("exit")){
                System.exit(0);
            }
            else if(parts[0].equals("menu")){
                exit = true;
            }
            else if(parts[0].equals("neighbours")){
                System.out.println("Neighbouring fields:");
                for(Field f : gc.getCurrentPlayer().getAsteroid().getNeighbours()){
                    f.Save(out,false);
                }
            }
            else if(parts[0].equals("help")){
                System.out.println("Usable commands:");
                System.out.println("move FieldID -> Moves current player to given asteroid");
                System.out.println("drill -> Current player drills once");
                System.out.println("mine -> Current player mines");
                System.out.println("craft robot/teleports/base -> Current player crafts the specified item/s");
                System.out.println("build -> Current player uses resources to build on the base that is on the same field.");
                System.out.println("save filename -> saves map to the given file");
                System.out.println("exit -> Exits from game");
            }
            else if(parts[0].equals("ls") || parts[0].equals("save")){
                try {
                    gc.InterpretCommand(line);
                }
                catch (InvalidCommand e) {
                    System.out.println(e.getMessage());
                }
                catch (Exception e) {
                    System.out.println("Invalid command");
                }
            }
            else{
                try {
                    gc.InterpretCommand("p " + gc.getCurrentPlayer().GetUID() + " " +line);
                }
                catch (InvalidCommand e) {
                    System.out.println(e.getMessage());
                }
                catch (Exception e) {
                    System.out.println("Invalid command");
                }
                if(NotificationManager.LastCommandSuccess){
                    String l = NotificationManager.getError();
                    while(l != null){
                        System.out.println(ANSI_RED + l + ANSI_RESET);
                        l = NotificationManager.getError();
                    }
                    System.out.println("Command failed");
                }
                else{
                    String l = NotificationManager.getMessage();
                    while(l != null){
                        System.out.println(l);
                        l = NotificationManager.getMessage();
                    }
                }
            }
        }
    }
}
