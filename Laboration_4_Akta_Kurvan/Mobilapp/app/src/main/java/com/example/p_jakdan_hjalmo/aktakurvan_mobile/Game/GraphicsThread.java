package com.example.p_jakdan_hjalmo.aktakurvan_mobile.Game;

import android.util.Log;

class GraphicsThread extends Thread {

    private GameView view;
    private boolean running = true;
    private long sleepTime;
    private long timeStamp;


    GraphicsThread(GameView view, long sleepTime) {
        this.view = view;
        this.sleepTime = sleepTime;
        this.running = true;
        timeStamp = System.currentTimeMillis();
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

            long now = System.currentTimeMillis();
            view.detectCollision();

            view.move();
            view.draw();

          if(now > timeStamp + 30){
            view.addTail();

                try{
                    Thread.sleep(sleepTime);

                }
                catch (InterruptedException ie) {}
                timeStamp = now;
            }
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