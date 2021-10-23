package edu.uchicago.gerber._04interfaces.P9_1;

class WorldClock extends Clock {
    protected int offset;

    public WorldClock(int offset) {
        this.offset = offset;
    }

    @Override
    public int getHours() {
        return super.getHours()+offset;
    }
}
