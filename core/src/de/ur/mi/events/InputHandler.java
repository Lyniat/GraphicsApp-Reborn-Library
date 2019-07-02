package de.ur.mi.events;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

import java.util.ArrayList;

public class InputHandler implements InputProcessor {
    private static ArrayList<InputListener> inputListeners = new ArrayList<InputListener>();

    public InputHandler(InputListener inputListener){
        inputListeners.add(inputListener);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public boolean keyDown(int keycode) {
        KeyEvent keyEvent = new KeyEvent((char)keycode,keycode);
        for (InputListener inputListener : inputListeners
             ) {
            inputListener.keyPressed(keyEvent);
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        KeyEvent keyEvent = new KeyEvent((char)keycode,keycode);
        for (InputListener inputListener : inputListeners
        ) {
            inputListener.keyReleased(keyEvent);
        }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        MouseEvent event = new MouseEvent(3,screenX,screenY);
        for (InputListener inputListener : inputListeners
        ) {
            inputListener.mouseClicked(event);
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        MouseEvent event = new MouseEvent(2,screenX,screenY);
        for (InputListener inputListener : inputListeners
        ) {
            inputListener.mouseReleased(event);
        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
