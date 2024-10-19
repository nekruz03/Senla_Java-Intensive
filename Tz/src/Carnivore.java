import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Carnivore extends Species {
    List<Herbivore> prey;

    public Carnivore(String name, int population, double growthRate, List<Herbivore> prey, Genetics genetics) {
        super(name, population, growthRate, genetics);
        this.prey = prey;
    }

    @Override
    public void updatePopulation(Ecosystem ecosystem) {
        double totalPreyPopulation = prey.stream().mapToDouble(Herbivore::getPopulation).sum();
        double requiredPrey = population * 0.1; // Каждый хищник требует определенное количество жертвы

        if (totalPreyPopulation < requiredPrey) {
            population -= (int) (population * 0.1); // Уменьшение на 10%, если жертв недостаточно
        } else {
            population += (int) (population * growthRate);
        }
    }

    @Override
    public String getType() {
        return "Carnivore";
    }
}

