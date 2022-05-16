package util;

public class Timer {
  private static int ms;

  public static boolean isTimeOver() {
    return ms <= 0;
  }

  public static void setTimer(int time) {
    if (time <= 0) {
      ms = 0;
      return;
    }
    ms = time;
  }

  public static int getTimer() {
    return ms;
  }

  public static String getString() {
    return String.format("%02d:%02d:%03d", (ms / 60000), (ms / 1000) % 60, ms % 1000);
  }
}
