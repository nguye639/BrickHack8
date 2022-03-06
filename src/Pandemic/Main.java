package Pandemic;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) throws IOException {
        File file = new File("pandemic.csv");
        File file2 = new File("count.csv");
        Files.deleteIfExists(file.toPath());
        Files.deleteIfExists(file2.toPath());
        int steps = 28*100;
        int n = 1000;
        Person[] agents = Functions.specific_agents(n,.01,0,0);
        agents[0].infect();

        for (int i = 0; i < steps; i++){
            double[][] matrix = Functions.distanceMatrix(agents);
            ArrayList<int[]> interact = Functions.interaction(agents,matrix);
            Functions.infectInteractions(agents,interact);
            Functions.move(agents);
            int num = Functions.countInfected(agents);
            if (i % 100 == 0){
                System.out.println(num);
            }

            Functions.toCsv(agents);

        }


    }





}
