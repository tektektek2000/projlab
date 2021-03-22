package Model;

import java.util.Scanner;

public abstract class Ship {

    protected Asteroid asteroid;

    // moves the ship to the given field
    public void Move(Field f){
        Skeleton.AddAndPrintCallStack(this + ".Move()");
        asteroid.Remove(this);
        Asteroid dest = f.MovedTo(this);
        setAsteroid(dest);
        Skeleton.RemoveFromCallStack(this + ".Move()");
    }

    // ship dies
    public abstract void Die();

    // hides ship
    public void Hide(){
        Skeleton.AddAndPrintCallStack("Ship.Hide()");
        System.out.println("You hid successfully.");
        Skeleton.RemoveFromCallStack("Ship.Hide()");
    }

    // ship gets sun stormed
    public void SunStormNow(){
        Skeleton.AddAndPrintCallStack("Ship.SunStormNow()");
        Scanner in = new Scanner(System.in);
        boolean yes;
        yes = Skeleton.AskPlayer("Is there any layers on the asteroid?");

        // whether ship can hide in empty asteroid or dies
        if(!yes){

            yes = Skeleton.AskPlayer("Is the asteroid empty?");

            if(yes){
                Hide();
            }
            else{
                Die();
            }
        }
        else{
            Die();
        }
        Skeleton.RemoveFromCallStack("Ship.SunStormNow()");
    }

    // special action if the asteroid explodes
    public abstract void AsteroidExploding();

    // drills on the asteroid
    public void Drill(){
        Skeleton.AddAndPrintCallStack("Ship.Drill()");
        asteroid.GetDrilled();
        Skeleton.RemoveFromCallStack("Ship.Drill()");
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
