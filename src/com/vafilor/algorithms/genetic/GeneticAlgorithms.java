package com.vafilor.algorithms.genetic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Andrey Melnikov on 2/5/2016.
 */
public class GeneticAlgorithms
{
    private static Random random;

    //Cache Random object so we don't create it over and over.
    static {
        random = new Random();
    }

    /**
     * An algorithm that mimics that natural selection/mutation process of nature.
     *
     * Based on the pseudo-code outlined in: Artificial Intelligence: A Modern Approach (3rd Edition) 3rd Edition
     * by Stuart Russell, Peter Norvig
     *
     * @param population the initial starting population of individuals.
     * @param fitnessFunction a function that takes an individual and evaluates how "good" they are. The higher the better.
     * @param fitnessThreshold a value to aim for - the algorithm stops once an individual has at least this value as evaluated by fitnessFunction.
     * @param <T> a representation of a state, must implement IGeneticState<T>. E.g. a positioning of Queens in the N-Queens problem.
     * @return An individual who has a fitnessThreshold (as determined by fitness function) greater than or equal to the fitnessThreshold passed in.
     */
    public static <T extends IGeneticState<T>> T geneticAlgorithm(List<T> population, IFitnessFunction<T> fitnessFunction, double fitnessThreshold)
    {
        int generations = 0;
        List<T> newPopulation = null;
        T best;

        while(true)
        {
            //Get the best child and return him if above threshold
            best = max(population, fitnessFunction);

            generations++;

            if( fitnessFunction.value(best) >= fitnessThreshold )
            {
                System.out.format("Took %d generations%n" , generations);
                return best;
            }

            newPopulation = new ArrayList<>();

            for(int i = 0; i < population.size(); i++)
            {
                //TO-CONSIDER is it a problem that the two below could be the same entity?
                T chosenStateA = randomSelection(population, fitnessFunction);
                T chosenStateB = randomSelection(population, fitnessFunction);

                T child = mate(chosenStateA, chosenStateB);

                //TO-CONSIDER - should a mutation trigger have its own chance? Currently each element in sequence has its own mutation chance.
                child.mutate();

                newPopulation.add(child);
            }

            population = newPopulation;
        }
    }

    /**
     * Goes through the population, evaluating each individual according to fitnessFunction and returns the one with largest value.
     * @param population
     * @param fitnessFunction
     * @param <T> anything that can be evaluated by an associated fitness function, usually a problem state.
     * @return The individual with the largest fitnessFunction value in the population.
     */
    private static <T> T max(List<T> population, IFitnessFunction<T> fitnessFunction)
    {
        T maxElement = population.get(0);

        double currentValue = fitnessFunction.value(maxElement);
        double newValue = 0;

        for(T element : population)
        {
            newValue = fitnessFunction.value(element);

            if( newValue > currentValue)
            {
                maxElement = element;
                currentValue = newValue;
            }
        }

        return maxElement;
    }

    /**
     * Picks a random individual from the population for reproduction. The individual is more likely to be chosen
     * the larger his fitnessFunction value is. See shouldChooseForReproduction for determining chance of selection.
     * @param population
     * @param fitnessFunction
     * @param <T>
     * @return
     */
    private static <T> T randomSelection(List<T> population, IFitnessFunction<T> fitnessFunction)
    {
        T chosen = null;
        T potential = null;

        while( chosen == null )
        {
            potential = population.get(random.nextInt(population.size()));

            if( shouldChooseForReproduction(potential, fitnessFunction) )
            {
                chosen = potential;
            }
        }

        return chosen;
    }

    /**
     * Determines if an individual should be chosen for reproduction. The individual has a chance to be chosen
     * based on his fitnessFunction value with the higher value resulting in higher chance of selection.
     *
     * @param individual
     * @param fitnessFunction
     * @param <T>
     * @return true if individual should be chosen, false otherwise.
     */
    private static <T> boolean shouldChooseForReproduction(T individual, IFitnessFunction<T> fitnessFunction)
    {
        double chance = fitnessFunction.value(individual) / fitnessFunction.maxValue();

        return random.nextDouble() <= chance;
    }


    /**
     * Takes two individuals that are IGeneticState and splits them randomly along their sequence representation.
     * A child is then formed by taking the subsequence of A and the subsequence of B and joining them.
     *
     * @param a
     * @param b
     * @param <T>
     * @return
     */
    private static <T extends IGeneticState<T>> T mate(T a, T b)
    {
        int crossOverPoint = random.nextInt(a.size());

        //TO-CONSIDER subSequenceA can be empty? Should I allow this?
        T subsequenceA = a.subSequence(0, crossOverPoint);
        T subSequenceB = b.subsequence(crossOverPoint);

        return subsequenceA.join(subSequenceB);
    }
}
