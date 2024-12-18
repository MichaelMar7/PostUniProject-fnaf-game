package gui.other;

import gui.GUI;
import util.AnimatronicLogic;
import util.GameLogic;
import util.GameState;
import util.OfficeLogic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class JumpscareGUI extends GUI {

    // images from the original game (placeholders)
    private BufferedImage bonnieJumpscareTemp;
    private BufferedImage chicaJumpscareTemp;
    private BufferedImage foxyJumpscareTemp;
    private BufferedImage freddy1JumpscareTemp;
    private BufferedImage freddy2JumpscareTemp;

    public JumpscareGUI() {

        try {

            this.bonnieJumpscareTemp = ImageIO.read(getClass().getResourceAsStream("/jumpscares/bonnie.png"));
            this.chicaJumpscareTemp = ImageIO.read(getClass().getResourceAsStream("/jumpscares/chica.png"));
            this.foxyJumpscareTemp = ImageIO.read(getClass().getResourceAsStream("/jumpscares/foxy.png"));
            this.freddy1JumpscareTemp = ImageIO.read(getClass().getResourceAsStream("/jumpscares/freddy1.png"));
            this.freddy2JumpscareTemp = ImageIO.read(getClass().getResourceAsStream("/jumpscares/freddy2_og.png"));

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    @Override
    public void render(Graphics2D g2, Rectangle camera, int scale) {

        if (GameLogic.compareGameState(GameState.JUMPSCARE)) {

            BufferedImage jumpscareImage = null;

            switch (AnimatronicLogic.getJumpscareAnimatronic()) {
                case "Bonnie":
                    jumpscareImage = bonnieJumpscareTemp;
                    break;
                case "Chica":
                    jumpscareImage = chicaJumpscareTemp;
                    break;
                case "Foxy":
                    jumpscareImage = foxyJumpscareTemp;
                    break;
                case "Freddy":
                    if (OfficeLogic.getBlackout()) {
                        jumpscareImage = freddy2JumpscareTemp;
                    } else {
                        jumpscareImage = freddy1JumpscareTemp;
                    }
                    break;
            }

            if (jumpscareImage != null) {
                g2.drawImage(jumpscareImage, 0, 0, null);
            }

        }

    }

    @Override
    public boolean handleMouseClick(Rectangle camera, Rectangle mouseRect, int scale) {
        return false;
    }

    @Override
    public boolean handleMouseHover(Rectangle camera, Rectangle mouseRect, int scale) {
        return false;
    }

}
