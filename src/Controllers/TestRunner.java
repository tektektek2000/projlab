package Controllers;

import Utils.BadFileFormat;
import Utils.InvalidCommand;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class TestRunner {
    File TestDir;
    String Path;
    int DirNum;

    public TestRunner(String TestRoot) throws IOException {
        TestDir = new File(TestRoot);
        Path = TestDir.getCanonicalPath();
        if(!TestDir.isDirectory())
            throw(new FileNotFoundException());
        File[] files = TestDir.listFiles();
        DirNum = 0;
        for(File f : files){
            if(f.isDirectory())
                DirNum++;
        }
        System.out.println("Found " + files.length + " files of which " + DirNum + " are test directories.");
    }

    void Run(File Test,GameController gc) throws IOException, InvalidCommand, BadFileFormat {
        File cmd = new File(Test.getCanonicalPath() + "\\cmd.txt");
        Scanner FScanner = new Scanner(cmd);
        while (FScanner.hasNextLine()) {
            String data = FScanner.nextLine();
            System.out.println(data);
            gc.InterpretCommand(data);
        }
    }

    public void RunAllTests(GameController gc){
        File[] files = TestDir.listFiles();
        String oldCurr = gc.CurrentWorkingDirectory;
        int SuccessfulTests = 0;
        for(File f : files){
            if(f.isDirectory()) {
                try {
                    System.out.println();
                    System.out.println("Test[" + f.getName() + "]");
                    try {
                        gc.CurrentWorkingDirectory = f.getPath();
                        Run(f,gc);
                        System.out.println("Test Done");
                        SuccessfulTests++;
                    }
                    catch (InvalidCommand invalidCommand) {
                        System.out.println(invalidCommand.getMessage());
                    } catch (BadFileFormat badFileFormat) {
                        System.out.println(badFileFormat.getMessage());
                    }
                    //System.out.println("Test[" + f.getName() + "] Done");
                } catch (IOException e) {
                    System.out.println("Invalid Test Directory in Test Root. Invalid Dir: " + f.getName());
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(SuccessfulTests + " tests were successful out of " + DirNum);
        gc.CurrentWorkingDirectory = oldCurr;
    }

}
