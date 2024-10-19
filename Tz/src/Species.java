abstract class Species {
    protected String name;
    protected int population;
    protected double growthRate;
    protected Genetics genetics;

    public Species(String name, int population, double growthRate, Genetics genetics) {
        this.name = name;
        this.population = population;
        this.growthRate = growthRate;
        this.genetics = genetics;
    }
    public abstract void updatePopulation(Ecosystem ecosystem);

    public abstract String getType();

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    @Override
    public String toString() {
        return getType() + "," + name + "," + population + "," + growthRate;
    }
}
