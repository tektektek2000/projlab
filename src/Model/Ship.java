package Model;

import java.util.Scanner;

public abstract class Ship {
    Asteroid asteroid;
    public void Move(Field f){
        asteroid.Remove(this);
        Asteroid dest = f.MovedTo(this);
        setAsteroid(dest);
    }
    public abstract void Die();
    public void Hide(){
        System.out.println("You hid successfully.");
    }
    public void SunStormNow(){
        Scanner in = new Scanner(System.in);

        System.out.println("Is the asteroid drilled through? [Y/N]");

        if(in.nextLine().equals("y") || in.nextLine().equals("Y")){

            System.out.println("Is the asteroid empty? [Y/N]");

            if(in.nextLine().equals("y") || in.nextLine().equals("Y")){
                System.out.println("\tHide()");
                Hide();
            }
            else if(in.nextLine().equals("n") || in.nextLine().equals("N")){
                System.out.println("\tDie()");
                Die();
            }
        }
        else if(in.nextLine().equals("n") || in.nextLine().equals("N")){
            System.out.println("\tDie()");
            Die();
        }
    }
    public abstract void AsteroidExploding();
    public void Drill(){
        asteroid.GetDrilled();
    }
    public void setAsteroid(Asteroid a){
        asteroid = a;
    }
}
