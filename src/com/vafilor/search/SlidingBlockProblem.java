package com.vafilor.search;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey Melnikov on 6/7/2015.
 */
public class SlidingBlockProblem implements ISearchProblem {

    private int length;
    private SlidingBlockState initialState;

    public SlidingBlockProblem(int length) {
        this.length = length;
        this.initialState = new SlidingBlockState(this.length);
    }

    //TODO error catching
    public SlidingBlockProblem(String sourceFile) throws FileNotFoundException {
        this.initialState = new SlidingBlockState(sourceFile);
        this.length = this.initialState.getLength();
    }

    @Override
    public Object getInitialState() {
        return this.initialState;
    }

    /**
     * For this problem, we reduce possible actions by:
     *  if the missing piece is at the left,top,right, or bottom, there is no need to include an action going the same way.
     *  an action that would undo the previous is not included. E.g. I just moved down, so moving back up will not be included.
     * @param node containing current problem state
     * @return
     */
    @Override
    public List<Object> getPossibleActions(Object node) {
        Node currentNode = (Node)node;
        SlidingBlockState currentState = (SlidingBlockState)currentNode.getState();

        SlidingAction previousAction = SlidingAction.Null;

        if(currentNode.getAction() != null) {
            previousAction = (SlidingAction)currentNode.getAction();
        }

        List<Object> possibleActions = new ArrayList<Object>();

        if( !currentState.getBlankSpotPosition().isAtMinRow()  && !previousAction.equals(SlidingAction.Down) ) {
            possibleActions.add(SlidingAction.Up);
        }

        if(!currentState.getBlankSpotPosition().isAtMaxRow()  && !previousAction.equals(SlidingAction.Up) ) {
            possibleActions.add(SlidingAction.Down);
        }

        if(!currentState.getBlankSpotPosition().isAtMinColumn() && !previousAction.equals(SlidingAction.Right) ) {
            possibleActions.add(SlidingAction.Left);
        }

        if(!currentState.getBlankSpotPosition().isAtMaxColumn() && !previousAction.equals(SlidingAction.Left) ) {
            possibleActions.add(SlidingAction.Right);
        }

        return possibleActions;
    }

    @Override
    public Object getResultingState(Object state, Object action) {

        SlidingBlockState givenState = (SlidingBlockState)state;
        SlidingAction givenAction = (SlidingAction)action;

        return givenState.applyAction(givenAction);
    }

    @Override
    public boolean isGoalState(Object state) {
        SlidingBlockState givenState = (SlidingBlockState)state;

        return givenState.isGoalState();
    }

    @Override
    public double getStepCost(Object previousState, Object action) {
        //For this problem, step cost is a sliding the block once. Path cost is total number of slides performed.
        return 1.0;
    }
}
