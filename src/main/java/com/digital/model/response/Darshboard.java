package com.digital.model.response;

public class Darshboard {
    private String police;
    private double cumulPaye;
    private double cumulImpaye;
    private int percent;
    public Darshboard(String police) {
        this.police = police;
    }

    public String getPolice() {
        return police;
    }

    public void setPolice(String police) {
        this.police = police;
    }

    public double getCumulPaye() {
        return cumulPaye;
    }

    public void setCumulPaye(double cumulPaye) {
        this.cumulPaye = cumulPaye;
    }

    public double getCumulImpaye() {
        return cumulImpaye;
    }

    public void setCumulImpaye(double cumulImpaye) {
        this.cumulImpaye = cumulImpaye;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent() {
        this.percent = (int)((this.cumulPaye/(this.cumulImpaye+this.cumulPaye))*100);
    }
}
