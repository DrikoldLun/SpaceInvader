package edu.uchicago.gerber._03objects.P8_7;

public class ComboLock {
    private int secret1;
    private int secret2;
    private int secret3;
    private int dial = 0;
    private boolean open1 = false;
    private boolean open2 = false;
    private boolean open3 = false;

    public ComboLock(int secret1, int secret2, int secret3) {
        this.secret1 = secret1;
        this.secret2 = secret2;
        this.secret3 = secret3;
    }

    public void reset() {
        dial = 0;
        open1 = false;
        open2 = false;
        open3 = false;
    }

    public void turnLeft(int ticks) {
        dial = dial - ticks;
        while (dial < 0){
            dial += 40;
        }
        dial %= 40;
        if (!open1 && secret1==dial){
            open1 = true;
        }
        else if (open1 && !open2 && secret2==dial){
            open2 = true;
        }
        else if (open1 && open2 && !open3 && secret3==dial){
            open3 = true;
        }
    }

    public void turnRight(int ticks) {
        dial = (dial + ticks)%40;
        if (!open1 && secret1==dial){
            open1 = true;
        }
        else if (open1 && !open2 && secret2==dial){
            open2 = true;
        }
        else if (open1 && open2 && !open3 && secret3==dial){
            open3 = true;
        }
    }

    public boolean open(){
        return open3;
    }
}
