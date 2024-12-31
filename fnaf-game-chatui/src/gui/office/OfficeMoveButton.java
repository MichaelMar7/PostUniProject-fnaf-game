package gui.office;

import gui.GUIButton;
import util.GameLogic;
import util.GameState;

import java.awt.*;

public class OfficeMoveButton extends GUIButton {

    private boolean moveCamera = true;

    public OfficeMoveButton(Rectangle area) {

        super(null, area, false);

    }

    @Override
    public void render(Graphics2D g2, Rectangle camera, int scale) {

    }

    @Override
    public boolean handleMouseClick(Rectangle camera, Rectangle mouseRect, int scale) {
        return false;
    }

    @Override
    public boolean handleMouseHover(Rectangle camera, Rectangle mouseRect, int scale) {

        if (mouseRect.intersects(area) && GameLogic.compareGameState(GameState.PLAY))
            moveCamera = true;
        else
            moveCamera = false;

        return moveCamera;

    }

    public boolean getMoveCamera() { return moveCamera; }

}
