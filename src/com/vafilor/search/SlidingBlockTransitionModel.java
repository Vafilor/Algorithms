package com.vafilor.search;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey Melnikov on 2/14/2016.
 *
 * Represents the transition model for the SlidingBlockProblem where the possible actions are up/down/left/right.
 */
public class SlidingBlockTransitionModel implements ITransitionModel<SlidingBlockState, SlidingAction> {

    /**
     * Moves the "blank" (0) spot.
     * @param action direction to move 0 spot.
     * @return new SlidingBlockState where the 0 spot has been moved.
     */
    public SlidingBlockState getResult(SlidingBlockState state, SlidingAction action)
    {
        switch(action) {
            case Up:
                return this.moveUp(state);
            case Down:
                return this.moveDown(state);
            case Left:
                return this.moveLeft(state);
            case Right:
                return this.moveRight(state);
            default:
                throw new IllegalStateException("Tried to apply illegal action to sliding block:" + action);
        }
    }

    private SlidingBlockState moveUp(SlidingBlockState state) {
        if(state.blankAtMinRow()) {
            return state;
        }

        return state.swapBlank(state.blankSpotPosition.addRow(-1) );
    }

    private SlidingBlockState moveDown(SlidingBlockState state) {
        if(state.blankAtMaxRow() ) {
            return state;
        }

        return state.swapBlank(state.blankSpotPosition.addRow(1));
    }

    private SlidingBlockState moveLeft(SlidingBlockState state) {
        if(state.blankAtMinColumn()) {
            return state;
        }

        return state.swapBlank(state.blankSpotPosition.addColumn(-1));
    }

    private SlidingBlockState moveRight(SlidingBlockState state) {
        if(state.blankAtMaxColumn() ) {
            return state;
        }

        return state.swapBlank(state.blankSpotPosition.addColumn(1));
    }

    /**
     * For this problem, we reduce possible actions by:
     * if the missing piece is at the left,top,right, or bottom, there is no need to include an action going the same way.
     * Also, an action that would undo the previous is not included. E.g. I just moved down, so moving back up will not be included.
     * @param node containing current problem state
     * @return a List of possible actions for the given state in the node.
     */
    @Override
    public List<SlidingAction> getPossibleActions(Node<SlidingBlockState, SlidingAction> node) {
        if(node == null)
        {
            throw new NullPointerException("node");
        }

        SlidingAction previousAction = SlidingAction.Null;

        if(node.getAction() != null)
        {
            previousAction = node.getAction();
        }

        List<SlidingAction> possibleActions = new ArrayList<>();

        if( !node.getState().blankAtMinRow() && previousAction != SlidingAction.Down ) {
            possibleActions.add(SlidingAction.Up);
        }

        if(!node.getState().blankAtMaxRow()  && previousAction != SlidingAction.Up ) {
            possibleActions.add(SlidingAction.Down);
        }

        if(!node.getState().blankAtMinColumn() && previousAction != SlidingAction.Right ) {
            possibleActions.add(SlidingAction.Left);
        }

        if(!node.getState().blankAtMaxColumn() && previousAction != SlidingAction.Left ) {
            possibleActions.add(SlidingAction.Right);
        }

        return possibleActions;
    }

    /**
     * For this problem, we reduce possible actions by:
     * if the missing piece is at the left,top,right, or bottom, there is no need to include an action going the same way.
     * @param state
     * @return a List of possible actions for the given state in the node.
     */
    @Override
    public List<SlidingAction> getPossibleActions(SlidingBlockState state) {
        if(state == null)
        {
            throw new NullPointerException("state");
        }

        List<SlidingAction> possibleActions = new ArrayList<>();

        if( !state.blankAtMinRow()) {
            possibleActions.add(SlidingAction.Up);
        }

        if(!state.blankAtMaxRow()) {
            possibleActions.add(SlidingAction.Down);
        }

        if(!state.blankAtMinColumn() ) {
            possibleActions.add(SlidingAction.Left);
        }

        if(!state.blankAtMaxColumn()) {
            possibleActions.add(SlidingAction.Right);
        }

        return possibleActions;
    }
}
