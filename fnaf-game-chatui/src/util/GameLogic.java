package util;

import main.Game;

public class GameLogic {

    private static GameState gameState = GameState.NONE;

    private static int introCounter = 0;
    private static int jumpscareCounter = 0;
    private static int gameoverCounter = 0;
    private static int winCounter = 0;

    public static GameState getGameState() { return gameState; }

    public static void changeGameState(GameState gameState) {GameLogic.gameState = gameState; }

    public static boolean compareGameState(GameState comparedGameState) { return comparedGameState == gameState  || comparedGameState == GameState.ANY; }


    public static void update(Game game) {

        switch (gameState) {
            case PLAY:
                updateNight(game);
                break;
            case INTRO:
                updateIntro(game);
                break;
            case JUMPSCARE:
                updateJumpscare(game);
                break;
            case GAMEOVER:
                updateGameOver(game);
                break;
            case WIN:
                updateWin(game);
                break;
        }

    }

    public static void setUpPlay() {

        AnimatronicLogic.setUpAnimatronic();
        CameraLogic.setUpCamera();
        DoorLogic.reset();
        NightLogic.reset();
        OfficeLogic.reset();
        MessageHandler.clear();
        changeGameState(GameState.PLAY);

    }

    public static void setUpIntro() {

        introCounter = 0;
        changeGameState(GameState.INTRO);

    }

    public static void setUpJumpscare() {

        jumpscareCounter = 0;
        changeGameState(GameState.JUMPSCARE);

    }

    public static void setUpGameOver() {

        gameoverCounter = 0;
        MessageHandler.clear();
        changeGameState(GameState.GAMEOVER);

    }

    public static void setUpWin() {

        winCounter = 0;
        MessageHandler.clear();
        changeGameState(GameState.WIN);

    }

    private static void updateNight(Game game) {

        AnimatronicLogic.update();
        NightLogic.update();
        OfficeLogic.update();
        NightLogic.checkWin();

        MessageHandler.update();

//        NightLogic.speedUpCounter(game.getKeyHandler());

    }

    private static void updateIntro(Game game) {

        introCounter++;

        if (GameLogic.getIntroCounter() > Game.FPS * 4) {

            GameLogic.setUpPlay();

        }

    }

    private static void updateJumpscare(Game game) {

        jumpscareCounter++;

        if (GameLogic.getJumpscareCounter() > Game.FPS * 1) {

            GameLogic.setUpGameOver();

        }

    }

    private static void updateGameOver(Game game) {

        gameoverCounter++;

        if (GameLogic.getGameOverCounter() > Game.FPS * 8) {

            changeGameState(GameState.MENU);

        }

    }

    private static void updateWin(Game game) {

        winCounter++;

        if (GameLogic.getWinCounter() > Game.FPS * 8) {

            changeGameState(GameState.MENU);

        }

    }

    public static int getIntroCounter() {

        if (compareGameState(GameState.INTRO))
            return introCounter;
        return 0;

    }

    public static int getJumpscareCounter() {

        if (compareGameState(GameState.JUMPSCARE))
            return jumpscareCounter;
        return 0;

    }

    public static int getGameOverCounter() {

        if (compareGameState(GameState.GAMEOVER))
            return gameoverCounter;
        return 0;

    }

    public static int getWinCounter() {

        if (compareGameState(GameState.WIN))
            return winCounter;
        return 0;

    }

}
