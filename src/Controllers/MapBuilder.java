package Controllers;

import Model.*;
import Model.Materials.*;
import Utils.Point;

import java.util.ArrayList;
import java.util.Random;

public class MapBuilder {

    final int asteroidNumber = 64;
    final int sectorNumber = 8;
    final int playerNumber = 4;

    private static final Random random = new Random();
    Map map;

    /**
     * This method's objective is to build up a simple random generated starter map.
     * @return the map that was built up
     */
    public Map BuildMap() {

        // TODO: generating asteroid via coordinates
        map = new Map();

        ArrayList<Asteroid> asteroids = new ArrayList<>();

        // creating sectors
        for (int i = 0; i < sectorNumber; i++)
            map.AddSector(new Sector(map.GetNewUID(),map));

        /*
        ArrayList<Point> coords = new ArrayList<>();
        while(coords.size() < asteroidNumber){
            Point p = genRndPoint(-1.0,1.0);

            // TODO: distance check
            coords.add(p);
        }
        */

        // creating asteroids and adding to sectors
        for(Sector s : map.getSectors())
            for (int i = 0; i < asteroidNumber/sectorNumber; i++){
                Asteroid a = new Asteroid(map.GetNewUID(), s, genRndMaterial() , new Random().nextInt(6));
                asteroids.add(a);
                s.Add(a);
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
            Field rndFieldOuterSector = genRndSector(s).getFields().get(random.nextInt(s.getFields().size()));
            rndField.AddNeighbour(rndFieldOuterSector);
            rndFieldOuterSector.AddNeighbour(rndField);
        }

        // creating players on random asteroid
        for (int i = 0; i < playerNumber; i++) {
            asteroids.get(random.nextInt(asteroids.size())).Add(new PlayerShip(map.GetNewUID()));
        }

        // creating UFOs on random asteroid
        for (int i = 0; i < playerNumber; i++) {
            asteroids.get(random.nextInt(asteroids.size())).Add(new UFO(map.GetNewUID()));
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
    private Material genRndMaterial(){
        int rnd = random.nextInt(4);
        switch (rnd){
            case 0:
                return new Coal(map.GetNewUID());
            case 1:
                return new Ice(map.GetNewUID());
            case 2:
                return new Iron(map.GetNewUID());
            case 3:
                return new Uranium(map.GetNewUID());
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
