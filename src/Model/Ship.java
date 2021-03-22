package Model;

import java.util.Scanner;

public abstract class Ship {

    protected Asteroid asteroid;

    // moves the ship to the given field
    public void Move(Field f){
        asteroid.Remove(this);
        Asteroid dest = f.MovedTo(this);
        setAsteroid(dest);
    }

    // ship dies
    public abstract void Die();

    // hides ship
    public void Hide(){
        System.out.println("You hid successfully.");
    }

    // ship gets sun stormed
    public void SunStormNow(){
        Scanner in = new Scanner(System.in);
        boolean yes;
        yes = Skeleton.AskPlayer("Is there any layers on the asteroid? [Y/N]");

        // whether ship can hide in empty asteroid or dies
        if(yes){

            yes = Skeleton.AskPlayer("Is the asteroid empty? [Y/N]");

            if(yes){
                System.out.println("\tHide()");
                Hide();
            }
            else{
                System.out.println("\tDie()");
                Die();
            }
        }
        else{
            System.out.println("\tDie()");
            Die();
        }
    }

    // special action if the asteroid explodes
    public abstract void AsteroidExploding();

    // drills on the asteroid
    public void Drill(){
        asteroid.GetDrilled();
    }

    // gives back asteroid
    public Asteroid getAsteroid(){
        return asteroid;
    }

    // sets asteroid
    public void setAsteroid(Asteroid a){
        asteroid = a;
    }
}
