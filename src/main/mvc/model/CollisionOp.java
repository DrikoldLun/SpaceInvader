package model;

/**
 * Created by ag on 6/17/2015.
 */


public class CollisionOp {

    //this could also be a boolean, but we want to be explicit about what we're doing
    public enum Operation {
        ADD, REMOVE
    }

    //members
    private Sprite sprite;
    private Operation mOperation;

    //constructor
    public CollisionOp(Sprite sprite, Operation op) {
        this.sprite = sprite;
        mOperation = op;
    }


    //getters
    public Sprite getSprite() {
        return sprite;
    }

    public Operation getOperation() {
        return mOperation;
    }

}
