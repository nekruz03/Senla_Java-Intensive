
class Genetics {
    private double mutationRate;
    private double survivalRate;

    public Genetics(double mutationRate, double survivalRate) {
        this.mutationRate = mutationRate;
        this.survivalRate = survivalRate;
    }

    public double getMutationRate() {
        return mutationRate;
    }

    public double getSurvivalRate() {
        return survivalRate;
    }
}
