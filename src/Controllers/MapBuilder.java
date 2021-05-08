package Controllers;

import Model.*;
import Model.Materials.*;
import Utils.Point;

import java.util.ArrayList;
import java.util.Random;

/**
 * A class for building a map.
 */
public class MapBuilder {
    // Number of sectors
    final int sectorNumber = 8;
    // Minimum number of asteroids
    final int asteroidNumberMin = 6;
    // Maximum number of asteroids
    final int asteroidNumberMax = 12;
    // Minimum number of ships
    final int shipNumberMin = 3;
    // Maximum number of ships
    final int shipNumberMax = 8;
    // the distance where asteroids doesnt overlap each other
    final double asteroidTooClose = 0.1;
    // max distance when asteroids are linked
    final double neighbourMaxDistance = 0.23;
    // A random generator
    private static final Random random = new Random();
    // The map
    Map map;

    /**
     * This method's objective is to build up a simple random generated starter map.
     * @return the map that was built up
     */
    public Map BuildMap(GameController GC) {
        GC.ps = new ArrayList<>();
        GC.rs = new ArrayList<>();
        GC.ufos = new ArrayList<>();
        GC.tgs = new ArrayList<>();
        GC.urans = new ArrayList<>();
        map = new Map();
        ArrayList<Asteroid> asteroids = new ArrayList<>();

        // creating sectors
        for (int i = 0; i < sectorNumber; i++)
            map.AddSector(new Sector(map.GetNewUID(),map));

        // creating asteroids and adding to sectors
        Random r = new Random();
        for (int i = 0; i < 80; i++){
            Asteroid a = genRndAsteroid(GC, asteroids, r);
            System.out.println(a.getX()+" "+a.getY());
            asteroids.add(a);
        }

        // linking asteroids in max distance
        for(Asteroid a1 : asteroids){
            for(Asteroid a2 : asteroids){
                if(closeEnough(a1,a2, neighbourMaxDistance)){
                    a1.AddNeighbour(a2);
                    a2.AddNeighbour(a1);
                }
            }
        }

        // linking asteroids which has 0 neighbours
        for(Asteroid a1 : asteroids){
            if(a1.getNeighbours().size()==0){
                System.out.println("NO NE");
                double minDistance = 2.0;
                Asteroid minAsteroid = null;
                for(Asteroid a2 : asteroids){
                    if(a1 != a2) {
                        if (distance(a1, a2) < minDistance) {
                            minDistance = distance(a1, a2);
                            minAsteroid = a2;
                        }
                    }
                }
                a1.AddNeighbour(minAsteroid);
            }
        }


        for(Asteroid a : asteroids) {
            Sector s = map.getSectors().get(random.nextInt(map.getSectors().size()));
            a.setSector(s);
            s.Add(a);
        }

        /*for(Sector s : map.getSectors()) {
            for (Field f : s.getFields()) {
                Field rndField = s.getFields().get(random.nextInt(s.getFields().size()));
                f.AddNeighbour(rndField);
                rndField.AddNeighbour(f);
            }
        }*/


        /*
        // linking asteroids in sectors (only 1)
        for(Sector s : map.getSectors())
            for (Field f : s.getFields()){
                Field rndField = s.getFields().get(random.nextInt(s.getFields().size()));
                f.AddNeighbour(rndField);
                rndField.AddNeighbour(f);
            }

        // linking asteroids in outside sectors (only 1)
        for(Sector s : map.getSectors()){
            Field rndField = s.getFields().get(random.nextInt(s.getFields().size()));
            Sector rndSector = genRndSector(s);
            Field rndFieldOuterSector = rndSector.getFields().get(random.nextInt(rndSector.getFields().size()));
            rndField.AddNeighbour(rndFieldOuterSector);
            rndFieldOuterSector.AddNeighbour(rndField);
        }
        */

        // creating players on random asteroid
        for (int i = 0; i < shipNumberMin+random.nextInt(shipNumberMax); i++) {
            PlayerShip p = new PlayerShip(asteroids.get(random.nextInt(asteroids.size())));
            GC.ps.add(p);
        }

        // creating UFOs on random asteroid
        for (int i = 0; i < shipNumberMin+random.nextInt(shipNumberMax); i++) {
            UFO p = new UFO(asteroids.get(random.nextInt(asteroids.size())));
            GC.ufos.add(p);
        }

        return map;
    }

    boolean tooClose(ArrayList<Asteroid> asteroids, Asteroid a){
        for(Asteroid a2: asteroids)
            if(closeEnough(a,a2,asteroidTooClose))
                return true;

        return false;
    }

    private Asteroid genRndAsteroid(GameController GC, ArrayList<Asteroid> asteroids, Random r) {
        Asteroid a = new Asteroid(map.GetNewUID(), null, genRndMaterial(GC) , random.nextInt(6));
        a.setX(r.nextDouble()*2-1.0);
        a.setY(r.nextDouble()*2-1.0);

        while(tooClose(asteroids,a)){
            a.setX(r.nextDouble()*2-1.0);
            a.setY(r.nextDouble()*2-1.0);
        }
        return a;
    }

    /**
     * Generating random Material which is not the given param
     * @return the sector which should be ignored
     */
    private Sector genRndSector(Sector s){
        while(true){
            Sector rndSector = map.getSectors().get(random.nextInt(map.getSectors().size()));
            if(s == rndSector)
                continue;
            return rndSector;
        }
    }

    /**
     * Generating random Material and setting new UID for that.
     * @return random Material instance
     */
    private Material genRndMaterial(GameController gc){
        int rnd = random.nextInt(4);
        switch (rnd){
            case 0:
                return new Coal(map.GetNewUID());
            case 1:
                return new Ice(map.GetNewUID());
            case 2:
                return new Iron(map.GetNewUID());
            case 3:
                Uranium u = new Uranium(map.GetNewUID());
                gc.urans.add(u);
                return u;
            default:
                return null;
        }
    }

    /**
     * Generating random Point in the given limits.
     * @param min value for generating
     * @param max value for generating
     * @return a pseudo random Point
     */
    private Point genRndPoint( double min, double max) {
        double x = min + random.nextDouble() * (max - min);
        double y = min + random.nextDouble() * (max - min);

        return new Point(x,y);
    }

    public double distance(Asteroid a1, Asteroid a2){
        return Math.sqrt(Math.pow(a1.getX()-a2.getX(),2) + Math.pow(a1.getY()-a2.getY(),2));
    }

    private boolean closeEnough(Asteroid a1, Asteroid a2, double maxDistance){
        if(a1 == a2){
            return false;
        }


        double d = distance(a1, a2);
        //System.out.println(d);
        return d <= maxDistance;
    }

}
