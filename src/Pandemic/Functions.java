package Pandemic;

import java.util.Arrays;
import java.util.Random;
import java.lang.Math;
import java.util.ArrayList;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Functions {


    public static Person[] initializeAgents(int numberOfAgents){
        Random rand = new Random();
        Person[] agents = new Person[numberOfAgents];
        for (int i = 0; i < numberOfAgents; i++){
            double x = 100.0 * rand.nextDouble();
            double y = 100.0 * rand.nextDouble();
            agents[i] = new Person(x,y,false,false,false);
        }
        return agents;
    }

    public static Person[] specific_agents(int numberOfAgents, double percentageInfected, double percentageMasked, double percentageVaccinated ){
        Random rand = new Random();
        Person[] agents = new Person[numberOfAgents];
        for (int i = 0; i < numberOfAgents; i++){
            double x = 100.0 * rand.nextDouble();
            double y = 100.0 * rand.nextDouble();
            agents[i] = new Person(x,y,false,false,false);
        }
        int infected = (int) (percentageInfected * agents.length);
        int masked = (int) (percentageInfected * agents.length);
        int vacinated = (int) (percentageInfected * agents.length);

        shuffleArray(agents);
        for(int i = 0; i < infected; i++){
            agents[i].infected = true;
        }

        shuffleArray(agents);
        for(int j = 0; j < masked; j++){
            agents[j].masked = true;
        }

        shuffleArray(agents);
        for(int k = 0; k < vacinated; k++){
            agents[k].vaccinated = true;
        }

        return agents;
    }

    static void shuffleArray(Person[] ar) {
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            Person a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

    public static double[][] distanceMatrix(Person[] agents){
        int length = agents.length;
        double[][] matrix = new double[length][length];
        for (int i = 0; i < length; i ++){
            double[] distance = new double[length];
            for (int j = 0; j < length; j++){
                double r = Math.sqrt((agents[i].x - agents[j].x)*(agents[i].x - agents[j].x) + (agents[i].y - agents[j].y)*(agents[i].y - agents[j].y));
                distance[j] = r;
            }
        matrix[i] = distance;
        }

        return matrix;
    }

    public static ArrayList<int[]> interaction(Person[] agents, double[][] matrix){
        int length = agents.length;
        ArrayList<int[]> interact = new ArrayList<>();
        for (int i = 0; i < length; i ++){
            for (int j = 0; j < length; j++){
                if (i != j){
                    if (matrix[i][j] < 6){
                        int[] element = {i,j};
                        interact.add(element);
                    }
                }
            }
        }

        return interact;
    }

    public static void move(Person[] agents){
        for (Person agent:agents){
            agent.move();
        }
    }

    public static void infectInteractions(Person[] agents, ArrayList<int[]> interact){
        for (int[] interaction:interact ){
            Person agentA = agents[interaction[0]];
            Person agentB = agents[interaction[1]];

            if (agentA.infected){
                agentB.infect();
            }
        }
    }

    public static int countInfected(Person[] agents){
        int count = 0;
        for (Person agent:agents){
            if (agent.infected){
                count++;
            }
        }
        return count;
    }

}
