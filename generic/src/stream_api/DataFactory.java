package stream_api;

import java.util.ArrayList;
import java.util.List;

public class DataFactory {

    static public List<Ship> getCollection1() {
        List<Ship> res = new ArrayList<>();
        res.add(new Ship("000-001", "CargoShip1", Type.CARGO, 200, 50));
        res.add(new Ship("000-002", "CargoShip2", Type.CARGO, 250, 45));
        res.add(new Ship("000-003", "CargoShip3", Type.CARGO, 220, 60));
        res.add(new Ship("001-001", "Falcon", Type.FIGHTER, 2, 300));
        res.add(new Ship("001-002", "Hawk", Type.FIGHTER, 1, 400));
        res.add(new Ship("001-001", "Jet", Type.FIGHTER, 4, 270));
        res.add(new Ship("002-001", "Destroyer 001", Type.DESTROYER, 1000, 100));
        res.add(new Ship("002-002", "Destroyer 002", Type.DESTROYER, 2000, 85));

        return res;
    }
}
