package util;

import javafx.scene.media.AudioClip;

public class AudioUtil {
  public static void playSound(String fileName) {
    AudioClip sound = new AudioClip(StringUtil.getImageUrl(fileName));
    sound.play();
  }

  public static void playSound(String fileName, int volume) {
    AudioClip sound = new AudioClip(StringUtil.getImageUrl(fileName));
    sound.play(volume);
  }
}
