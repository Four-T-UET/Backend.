package factory;

import model.Item;
import model.Vehicle;

public class VehicleFactory extends ItemFactory {
    @Override
    public Item createItem(String name, String description) {
        return new Vehicle(name, description);
    }
}
