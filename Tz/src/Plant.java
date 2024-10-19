class Plant extends Species {
    public Plant(String name, int population, double growthRate, Genetics genetics) {
        super(name, population, growthRate, genetics);
    }

    @Override
    public void updatePopulation(Ecosystem ecosystem) {
        double resourceConsumption = growthRate * population;
        boolean canGrow = true;
        for (Resource resource : ecosystem.getResources()) {
            if (resource.getAvailability() < resourceConsumption) {
                canGrow = false; // Не хватает ресурсов для роста
            }
            resource.reduceAvailability(resourceConsumption);
        }

        if (canGrow) {
            population += (int) (population * growthRate);
        } else {
            population -= (int) (population * 0.1); // Уменьшение популяции, если ресурсов нет
        }
    }


    @Override
    public String getType() {
        return "Plant";
    }
}