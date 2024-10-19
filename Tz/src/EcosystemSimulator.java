import java.util.Arrays;
import java.util.List;

public class EcosystemSimulator {
    public static void main(String[] args) {
        Resource water = new Resource("Water", 100.0);
        Resource light = new Resource("Light", 200.0);
        Resource[] resources = {water, light};

        Climate climate = new Climate(20.0, 80.0);

        Ecosystem ecosystem = new Ecosystem(climate, resources);
        Genetics plantGenetics = new Genetics(0.05, 0.9);
        Genetics herbivoreGenetics = new Genetics(0.1, 0.8);
        Genetics carnivoreGenetics = new Genetics(0.15, 0.7);
        Plant grass = new Plant("Grass", 100, 0.2, plantGenetics);
        Plant shrub = new Plant("Shrub", 50, 0.15, plantGenetics);
        ecosystem.addSpecies(grass);
        ecosystem.addSpecies(shrub);
        List<Plant> herbivoreFoodSources = Arrays.asList(grass, shrub);
        Herbivore rabbit = new Herbivore("Rabbit", 20, 0.1, herbivoreFoodSources, herbivoreGenetics);
        ecosystem.addSpecies(rabbit);

        List<Herbivore> carnivorePrey = Arrays.asList(rabbit);
        Carnivore fox = new Carnivore("Fox", 5, 0.1, carnivorePrey, carnivoreGenetics);
        ecosystem.addSpecies(fox);

        int simulationDays = 30;
        ecosystem.simulate(simulationDays);

        ecosystem.printStatus();

        ecosystem.predictPopulationChanges();

        ecosystem.saveToFile("ecosystem_state.txt");
    }
}


