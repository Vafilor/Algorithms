package com.vafilor.algorithms.genetic;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args)
    {
        if(args.length != 2)
        {
            System.out.println("Expected arguments: numberOfQueens startingPopulationSize");
            return;
        }

        int numberOfQueens = Integer.parseInt(args[0]);
        int startingPopulationSize = Integer.parseInt(args[1]);

        List<NQueensState> population = generateRandomNQueensStates(startingPopulationSize, numberOfQueens);
        IFitnessFunction<NQueensState> fitnessFunction = new NonAttackingQueensFunction(numberOfQueens);

        NQueensState result = GeneticAlgorithms.geneticAlgorithm(population, fitnessFunction, fitnessFunction.maxValue());

        System.out.format("Fitness Score: %f %n", fitnessFunction.value(result) );
        System.out.println(result);
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
