package com.vafilor.search;

import java.util.List;

/**
 * Created by Andrey Melnikov on 6/7/2015.
 */
public class SlidingBlockProblem<U> implements ISearchProblem<SlidingBlockState, U> {

    private SlidingBlockState initialState;
    private ITransitionModel<SlidingBlockState, U> transitionModel;
    protected ISlidingBlockGoal goalChecker;

    //TODO - null checks
    public SlidingBlockProblem(ITransitionModel<SlidingBlockState, U> transitionModel, SlidingBlockState state, ISlidingBlockGoal goalChecker)
    {
        this.transitionModel = transitionModel;
        this.initialState = state;
        this.goalChecker = goalChecker;
    }

    public SlidingBlockProblem(ITransitionModel<SlidingBlockState, U> transitionModel, int length)
    {
        this(transitionModel, new SlidingBlockState(length), new ZeroFirstSlidingBlockGoal());
    }

    @Override
    public SlidingBlockState getInitialState() {
        return this.initialState;
    }


    @Override
    public SlidingBlockState getResultState(SlidingBlockState state, U action) {
        return this.transitionModel.getResult(state, action);
    }

    @Override
    public boolean isGoal(SlidingBlockState state) {
        return this.goalChecker.isGoal(state);
    }

    /**
     * The step cost is the same for every move.
     * @param state a state of the problem.
     * @param action an action applicable to the state of the problem.
     * @return 1.0
     */
    @Override
    public double getStepCost(SlidingBlockState state, U action) {
        return 1.0;
    }

    @Override
    public List<U> getPossibleActions(SlidingBlockState state) {
        return this.transitionModel.getPossibleActions(state);
    }

    @Override
    public List<U> getPossibleActions(Node<SlidingBlockState, U> node) {
        return this.transitionModel.getPossibleActions(node);
    }
}
