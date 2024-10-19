import java.util.List;

class Herbivore extends Species {
    public List<Plant> foodSources;

    public Herbivore(String name, int population, double growthRate, List<Plant> foodSources, Genetics genetics) {
        super(name, population, growthRate, genetics);
        this.foodSources = foodSources;
    }

    @Override
    public void updatePopulation(Ecosystem ecosystem) {
        double requiredFood = population * 0.15; // Каждый травоядный требует больше пищи
        double totalFoodAvailability = foodSources.stream().mapToDouble(Plant::getPopulation).sum();
        // Если пищи недостаточно, популяция падает
        if (totalFoodAvailability < requiredFood) {
            population -= (int) (population * 0.15); // Уменьшаем популяцию на 15%
        } else {
            // Пищи достаточно, популяция растет
            population += (int) (population * growthRate);
        }
    }
    @Override
    public String getType() {
        return "Herbivore";
    }
}
