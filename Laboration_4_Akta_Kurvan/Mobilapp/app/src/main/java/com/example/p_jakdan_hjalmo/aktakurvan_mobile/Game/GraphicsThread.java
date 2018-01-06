package com.example.p_jakdan_hjalmo.aktakurvan_mobile.Game;

import android.util.Log;

class GraphicsThread extends Thread {

    private GameView view;
    private boolean running = true;
    private long start;
    private long now;
    private String print;
    private long sleepTime;

    GraphicsThread(GameView view, long sleepTime) {
        this.view = view;
        this.sleepTime = sleepTime;
        this.running = true;
        start = System.currentTimeMillis();
        print = String.valueOf(start);
    }

    protected synchronized void setRunning(boolean b) {
        running = b;
    }

    protected synchronized boolean isRunning() {
        return running;
    }

    public void run() {
        Log.i("Var jag här?", "run");
        while (running) {

            view.move();
            view.draw();
            view.detectCollision();
            view.addTail();

            try{
                Thread.sleep(sleepTime);

            }
            catch (InterruptedException ie) {}
        }
    }

    void requestExitAndWait() {
        running = false;
        try {
            this.join();
        } catch (InterruptedException ie) {
        }
    }

    void onWindowResize(int w, int h) {
        // Deal with change in surface size
    }
}