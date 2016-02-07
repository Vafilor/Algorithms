package com.vafilor.algorithms.genetic;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args)
    {
        if(args.length < 1)
        {
            System.out.println("Usage: <number of queens> [starting population size]");
            return;
        }

        int numberOfQueens = Integer.parseInt(args[0]);

        if(numberOfQueens < 1 || numberOfQueens == 2 || numberOfQueens == 3)
        {
            System.out.println("No solutions exist for that amount of queens");
            return;
        }

        int startingPopulationSize = 8;

        if(args.length > 1) {
            startingPopulationSize = Integer.parseInt(args[1]);
        }

        List<NQueensState> population = generateRandomNQueensStates(startingPopulationSize, numberOfQueens);
        IFitnessFunction<NQueensState> fitnessFunction = new NonAttackingQueensFunction(numberOfQueens);

        NQueensState result = GeneticAlgorithms.geneticAlgorithm(population, fitnessFunction, fitnessFunction.maxValue());

        printResults(result, fitnessFunction.value(result));
    }

    private static void printResults(NQueensState result, double fitnessScore)
    {
        System.out.format("Fitness Score: %f %n%n", fitnessScore );

        System.out.format("%-5s Columns%n", "");

        String columns = "";
        String separator = "";

        for(int i = 0; i < result.size(); i++)
        {
            columns += i + " ";
            separator += "--";
        }

        System.out.format("%-5s %s%n", "", columns);
        System.out.format("%-5s %s%n", "", separator);
        System.out.format("%5s %s%n", "Row|", result);
    }

    private static List<NQueensState> generateRandomNQueensStates(int numberOfStates, int numberOfQueens)
    {
        List<NQueensState> queens = new ArrayList<>();

        NQueensState newState = null;

        for(int i = 0; i < numberOfStates; i++)
        {
            newState = new NQueensState(numberOfQueens);
            newState.randomize();
            queens.add(newState);
        }

        return queens;
    }
}
