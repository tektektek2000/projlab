package Controllers;

import Utils.InvalidCommand;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class MenuController {
    GameController gc = new GameController();

    public void Start(){
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
            }
            else if(parts[0].equals("load")){
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
        }
    }

    public void Game(){
        boolean exit = false;
        Scanner in = new Scanner(System.in);
        while (!exit){
            gc.getCurrentPlayer().Save(new PrintStream(System.out),false);
            String line = in.nextLine();
            String[] parts = line.split(" ");
            if(parts[0].equals("exit")){
                System.exit(0);
            }
            else if(parts[0].equals("menu")){
                exit = true;
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
            }
        }
    }
}
