package Pandemic;


import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        int steps = 14*1000;
        int n = 10;
        Person[] agents = Functions.initializeAgents(n);
        agents[0].infect();

        for (int i = 0; i < steps; i++){
            double[][] matrix = Functions.distanceMatrix(agents);
            ArrayList<int[]> interact = Functions.interaction(agents,matrix);
            Functions.infectInteractions(agents,interact);
            Functions.move(agents);

            if (i % 1000 == 0){
                System.out.println(Functions.countInfected(agents));
            }

        }

    }




}
