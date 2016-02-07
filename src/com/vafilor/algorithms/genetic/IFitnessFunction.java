package com.vafilor.algorithms.genetic;

/**
 * Created by Andrey Melnikov on 2/5/2016.
 *
 * A fitness function represents how good a particular state is. The higher the value, the better.
 * Goodness here means how close to the goal the state is. In the case of the 8 queens problem, a fitness function
 * should return the maximum value when the 8 queens are not attacking each other.
 *
 * T is the state that the function acts upon.
 */
public interface IFitnessFunction<T>
{
    double value(T state);
    double maxValue();
}
