package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import gui.GameInit;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import util.AudioUtil;
import util.StringUtil;

public class MainController implements Initializable {
  private String[] heading = { "Introduction", "Rules", "Units", "Attackable Units", "Debuffable Units",
      "Buffable Units", "Potions", "Credits" };
  private String[] description = {
      "Welcome to RPG Chess!\n \tThis game is a modified version of original chess. Every pieces (or units) will have their own HP and a unique action.\n \tThe objective of this game is to defeat all of the opponents's units.",
      "1. Each player starts with 9 units\n2. In each turn a player can either move a unit, attack an enemy, or use a unit's ability.\n3. A player must do an action in 90 seconds or the turn will be skipped\n4. When your unit kills an enemy, that unit will get upgraded. Enhancing its attack power or ability permanently.\n5. If a player doesn't have any units left in board, he/she loses.",
      "There are 6 types of unit.\nEach type can either attack an enemy, inflict a debuff to an enemy, or buff an ally unit.",
      "There are 3 types of unit that can attack.\n\nThe left unit is a Shooter Unit which has a long range attack.\n\nThe middle unit is a Normal Unit.\n\nThe right unit is a Flying Unit. Flying unit cannot be attacked by a Normal Unit.",
      "There are 2 types of unit that can inflict a debuff.\n\nThe left unit is a Venom Unit which can inflict poison. Poisoned unit loses HP for a certain time\n\nThe right unit is a Freezer Unit which can inflict freeze. Frozen unit cannot do anything for a certain time.",
      "There is 1 type of unit that can buff an ally. That unit is a Healer Unit which can heal an ally for a certain HP.",
      "While playing, there will be potions generated at middle row of the board. Players can consume a potion by moving a unit to that square.\nThere are 3 types of potions. Each has its own expired turn.\nThe left potion is a Healing Potion.\nThe middle potion is a Upgrade Potion, enhances a unit permanently.\nThe right potion is a Toxic Potion.",
      "This game is made by\nKittiphop Vichitkijja 6432015321\nand\nNaphan Choatchuangnapha 6432083021\n\nThank You for playing our game!" };
  private String[] image = { "/units/healer-unit-1.png", "rules.jpg", "all-units.jpg",
      "attackable-units.jpg", "debuffable-units.jpg", "buffable-unit.jpg", "all-potions.jpg",
      "units/normal-unit-1.png" };
  private int idx = 0;

  @FXML
  private Text headingInfo;
  @FXML
  private Text descriptionInfo;
  @FXML
  private Text page;
  @FXML
  private ImageView imageInfo;

  @FXML
  private Button nextButton;

  private void loadInfo() {
    imageInfo.setImage(new Image(StringUtil.getImageUrl(image[idx])));
    headingInfo.setText(heading[idx]);
    descriptionInfo.setText(description[idx]);
    page.setText("Page " + String.valueOf(idx + 1) + " of "
        + String.valueOf(heading.length));
  }

  @Override
  public void initialize(URL location, ResourceBundle resourceBundle) {
    loadInfo();
  }

  public void backInfo(ActionEvent e) {
    if (idx == 0) {
      idx = heading.length;
    }
    idx -= 1;
    loadInfo();
    AudioUtil.playSound("click.wav", 0.3);
  }

  public void nextInfo(ActionEvent e) {
    idx += 1;
    idx %= heading.length;
    loadInfo();
    AudioUtil.playSound("click.wav", 0.3);
  }

  public void exit(ActionEvent e) {
    Platform.exit();
  }

  public void newGame(ActionEvent e) throws IOException {
    GameInit.init(e);
  }

  public void mouseEntered(MouseEvent e) {
    Button btn = (Button) e.getTarget();
    btn.setOpacity(0.5);
  }

  public void mouseExited(MouseEvent e) {
    Button btn = (Button) e.getTarget();
    btn.setOpacity(1);
  }
}
