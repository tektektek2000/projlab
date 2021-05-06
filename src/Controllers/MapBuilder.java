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
    // A random number
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

        // TODO: generating asteroids with coordinates (for the UI)

        // creating asteroids and adding to sectors
        Random r = new Random();
        for(Sector s : map.getSectors()){
            for (int i = 0; i < asteroidNumberMin+random.nextInt(asteroidNumberMax); i++){
                Asteroid a = new Asteroid(map.GetNewUID(), s, genRndMaterial(GC) , random.nextInt(6));
                a.setX(r.nextDouble()*2-1.0);
                a.setY(r.nextDouble()*2-1.0);
                System.out.println(a.getX()+" "+a.getY());
                asteroids.add(a);
                s.Add(a);
            }
        }

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

}
