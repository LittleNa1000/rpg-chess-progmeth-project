package util;

import gui.TimerPane;
import javafx.application.Platform;

public class GameUtil {
  public static void startTimer(TimerPane timerPane) {
    Thread thread = new Thread(() -> {
      try {
        while (!Timer.istimeOver()) {
          Thread.sleep(20);
          Platform.runLater(new Runnable() {
            @Override
            public void run() {
              Timer.setTimer(Timer.getTimer() - 20);
              timerPane.setTime();
            }
          });
        }
        if (Timer.istimeOver()) {
          Platform.runLater(new Runnable() {
            @Override
            public void run() {
              gameOver();
            }
          });
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
    thread.start();
  }

  public static void gameOver() {
    System.out.println("Game Over");
  }
}
