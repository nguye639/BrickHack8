package Pandemic;

import java.util.Random;

public class Person {
    Random rand = new Random();
    public double x;
    public double y;
    public boolean infected;
    public boolean masked;
    public boolean vaccinated;
    public int infectedTime;
    public double immunity;

    public Person(double x, double y, boolean infected, boolean masked, boolean vaccinated) {
        this.x = x;
        this.y = y;
        this.infected = infected;
        this.masked = masked;
        this.vaccinated = vaccinated;
        this.infectedTime = 0;
        this.immunity = .3;
    }

    public void move() {

        this.x += 60*rand.nextDouble() - 30;
        this.y += 60*rand.nextDouble() - 30;
    }

    public void timeStep(){
        int infectedLimit = 500 + rand.nextInt(700);
        double immunityDecay = 0.0001;
        move();
        if (this.infected){
            this.infectedTime++;
            if (this.infectedTime >= infectedLimit){
                this.infected = false;
                this.infectedTime = 0;
            }
        }
        if (this.vaccinated){
            if (this.immunity < 0.5){
                this.vaccinated = false;
            }
        }
        if (this.immunity > 0.3 & !this.infected){
            this.immunity -= immunityDecay;
        }

    }

    public void vacinate(){
        this.vaccinated = true;
        this.immunity = .97;
    }

    public void infect(){
        double infectRoll = rand.nextDouble();

        if (this.masked){
            infectRoll = infectRoll/2;
        }

        if (infectRoll > this.immunity){
            this.infected = true;
            this.immunity = 1;
        }
    }

    @Override
    public String toString() {
        return "" + x +
                "," + y +
                "," + infected +
                "," + masked +
                "," + vaccinated;

    }
}
