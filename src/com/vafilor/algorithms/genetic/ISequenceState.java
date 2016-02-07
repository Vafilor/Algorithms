package com.vafilor.algorithms.genetic;

/**
 * Created by Andrey Melnikov on 2/5/2016.
 *
 * Represents an entity that is sequence like - a set of objects one after another. A string (of characters) is a prime example.
 * This type of entity has the ability to be split apart and joined together.
 */
public interface ISequenceState<T> {

    T subSequence(int start, int end);

    default T subsequence(int start) {
        return this.subSequence(start, this.size());
    }

    T join(T that);

    int size();
}
