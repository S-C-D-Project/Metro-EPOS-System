package main;

import Views.Frame.GifPlayer;
import Views.GUI_Manager;

public class Main {
    public static void main(String[] args) {
        Thread splashThread = new Thread(() -> {
            GifPlayer gifPlayer = new GifPlayer();
        });
        splashThread.start();

        Thread GUI = new Thread(() -> {
            GUI_Manager g = new GUI_Manager();
            try {
                splashThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            g.LogIn();
        });
        GUI.start();
    }
}