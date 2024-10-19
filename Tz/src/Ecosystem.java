import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Ecosystem {
    private Climate climate;
    private Resource[] resources;
    private List<Species> species;

    public Ecosystem(Climate climate, Resource[] resources) {
        this.climate = climate;
        this.resources = resources;
        this.species = new ArrayList<>();
    }

    public void addSpecies(Species s) {
        species.add(s);
    }

    public Resource[] getResources() {
        return resources;
    }

    public void simulate(int days) {
        for (int i = 0; i < days; i++) {
            for (Species s : species) {
                s.updatePopulation(this);
            }
        }
    }

    public void printStatus() {
        for (Species s : species) {
            System.out.println(s.getName() + " population: " + s.getPopulation());
        }
        for (Resource resource : resources) {
            System.out.println(resource.getName() + " availability: " + resource.getAvailability());
        }
    }

    public void saveToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Species s : species) {
                writer.write(s.toString() + "\n");
            }
            for (Resource resource : resources) {
                writer.write(resource.toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    // Species
                    String type = parts[0];
                    String name = parts[1];
                    int population = Integer.parseInt(parts[2]);
                    double growthRate = Double.parseDouble(parts[3]);
                    Genetics genetics = new Genetics(0.1, 0.1); // Пример значений
                    if (type.equals("Plant")) {
                        addSpecies(new Plant(name, population, growthRate, genetics));
                    } else if (type.equals("Herbivore")) {
                        addSpecies(new Herbivore(name, population, growthRate, new ArrayList<>(), genetics));
                    } else if (type.equals("Carnivore")) {
                        addSpecies(new Carnivore(name, population, growthRate, new ArrayList<>(), genetics));
                    }
                } else if (parts.length == 2) {
                    // Resource
                    String resourceName = parts[0];
                    double availability = Double.parseDouble(parts[1]);
                    Resource resource = new Resource(resourceName, availability);
                    resources = Arrays.copyOf(resources, resources.length + 1);
                    resources[resources.length - 1] = resource;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void predictPopulationChanges() {
        System.out.println("Population Predictions:");
        for (Species s : species) {
            double predictedPopulation = s.getPopulation();

            if (s instanceof Plant) {
                // Используем текущее состояние ресурсов из текущего экосистемы
                double futureResourceAvailability = Arrays.stream(this.getResources()).mapToDouble(Resource::getAvailability).sum();
                if (futureResourceAvailability > 0) {
                    predictedPopulation += (int) (predictedPopulation * s.growthRate); // Увеличиваем популяцию растений
                } else {
                    predictedPopulation -= (int) (predictedPopulation * 0.1); // Падение при отсутствии ресурсов
                }
            } else if (s instanceof Herbivore) {
                double totalFoodAvailability = ((Herbivore) s).foodSources.stream().mapToDouble(Plant::getPopulation).sum();
                double requiredFood = s.getPopulation() * 0.15; // Повышенные требования к пище
                if (totalFoodAvailability < requiredFood) {
                    predictedPopulation -= (int) (predictedPopulation * 0.15); // Падение на 15%, если пищи недостаточно
                }
            } else if (s instanceof Carnivore) {
                double totalPreyPopulation = ((Carnivore) s).prey.stream().mapToDouble(Herbivore::getPopulation).sum();
                double requiredPrey = s.getPopulation() * 0.1;
                if (totalPreyPopulation < requiredPrey) {
                    predictedPopulation -= (int) (predictedPopulation * 0.1);
                }
            }

            // Выводим предсказанную популяцию для каждого вида
            System.out.println(s.getName() + ": " + predictedPopulation + " (Predicted)");
        }
    }

}