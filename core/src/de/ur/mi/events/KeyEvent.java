package de.ur.mi.events;

public class KeyEvent {
    public static final int PRESS = 1;
    public static final int RELEASE = 2;
    public static final int TYPE = 3;
    char key;
    int keyCode;

    public KeyEvent(char key, int keyCode) {
        this.key = key;
        this.keyCode = keyCode;
    }

    public char getKey() {
        return this.key;
    }

    public int getKeyCode() {
        return this.keyCode;
    }
}
