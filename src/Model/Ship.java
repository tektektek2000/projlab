package Model;

import java.util.Scanner;

public abstract class Ship {
    protected Asteroid asteroid;

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
        boolean yes;
        yes = Skeleton.AskPlayer("Is there any layers on the asteroid? [Y/N]");

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
    public abstract void AsteroidExploding();
    public void Drill(){
        asteroid.GetDrilled();
    }
    public Asteroid getAsteroid(){
        return asteroid;
    }
    public void setAsteroid(Asteroid a){
        asteroid = a;
    }
}
