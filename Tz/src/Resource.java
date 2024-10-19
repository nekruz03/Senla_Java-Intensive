import java.io.*;
import java.util.*;

class Resource {
    private String name;
    private double availability;

    public Resource(String name, double availability) {
        this.name = name;
        this.availability = availability;
    }

    public String getName() {
        return name;
    }

    public double getAvailability() {
        return availability;
    }

    public void reduceAvailability(double amount) {
        this.availability = Math.max(0, this.availability - amount);
    }

    @Override
    public String toString() {
        return name + "," + availability;
    }
}


