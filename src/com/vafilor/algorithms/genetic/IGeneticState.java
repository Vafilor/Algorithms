package com.vafilor.algorithms.genetic;

/**
 * Created by Andrey Melnikov on 2/5/2016.
 *
 * Represents some state that provides the ability to mutate its elements (biologically speaking),
 * wherein each element of the state has a random chance to be changed.
 */
public interface IGeneticState<T> extends ISequenceState<T> {
    void mutate(double chanceOfMutation);
    void mutate();
}
