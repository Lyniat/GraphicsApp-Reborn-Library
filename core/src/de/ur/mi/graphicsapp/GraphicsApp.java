package de.ur.mi.graphicsapp;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import de.ur.mi.events.InputHandler;
import de.ur.mi.events.InputListener;
import de.ur.mi.events.KeyEvent;
import de.ur.mi.events.MouseEvent;
import de.ur.mi.graphics.*;

import java.util.ArrayList;

public class GraphicsApp extends ApplicationAdapter implements InputListener {

    public static void main (String[] arg){
        if(arg.length == 0){
            System.out.println("No class name found in start arguments. You need to specify your main class in the start arguments. Graphics App can not be started. Exiting now!");
            return;
        }
        try { Class c = Class.forName(arg[0]);

            Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
            String[] parts = arg[0].split("\\.");
            String name = parts[parts.length-1];
            config.setTitle(name);

            new Lwjgl3Application((GraphicsApp)c.newInstance(), config);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("The class '"+arg[0]+"' was not found. Graphics App can not be started. Exiting now!");
        } catch (java.lang.ExceptionInInitializerError e){
            System.out.println("Graphics App can not be started. If you're on macOS, try to start the JVM with '-XstartOnFirstThread'. Exiting now!");
        }
    }

    private Camera camera;

    private boolean initilaized = false;

    private int zoomFactor = 3;

    public static ShapeRenderer shapeRenderer;
    public static SpriteBatch spriteBatch;
    static private boolean projectionMatrixSet;

    private Color backgroundColor = Color.WHITE;

    private ArrayList<GraphicsObject> graphicsObjects = new ArrayList<GraphicsObject>();
    private ArrayList<GraphicsObject> graphicsObjectsLastFrame = new ArrayList<GraphicsObject>();

    private InputHandler inputHandler;

    private boolean spriteBatchOpen = false;
    private boolean shapeBatchOpen = false;

    public boolean rendering = false;

    private boolean hasUpdateMethod = true;

    private boolean resizable = false;


    @Override
    public void create() {

        inputHandler = new InputHandler(this);
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        size((int) camera.viewportWidth, (int) camera.viewportHeight);
        resizable(resizable);
        shapeRenderer = new ShapeRenderer();
        spriteBatch = new SpriteBatch();
        projectionMatrixSet = false;

        GraphicsObject.app = this;
    }

    @Override
    public void render() {
        graphicsObjectsLastFrame = graphicsObjects; //keep in memory for later use if only setup() is used
        graphicsObjects = new ArrayList<GraphicsObject>();
        camera.update();
        if (!initilaized) {
            setup();
            initilaized = true;
        }
        super.render();

        //first draw to add objects to list but objects get no really drawn
        draw();

        if(!hasUpdateMethod){
            graphicsObjects = graphicsObjectsLastFrame;
        }


        Gdx.gl.glClearColor(backgroundColor.r / 255f, backgroundColor.g / 255f, backgroundColor.b / 255f, backgroundColor.a / 255f);
        Gdx.gl.glClear(Gdx.gl30.GL_COLOR_BUFFER_BIT);

        //see https://stackoverflow.com/questions/15397074/libgdx-how-to-draw-filled-rectangle-in-the-right-place-in-scene2d
        if (!projectionMatrixSet) {
            shapeRenderer.setProjectionMatrix(camera.combined);
            spriteBatch.setProjectionMatrix(camera.combined);
        }

        rendering = true;

        //second draw cycle where added objects get now really drawn

        for (GraphicsObject graphicsObject : graphicsObjects) {

            if ((graphicsObject instanceof Compound)) {
                continue;
            }

            if ((graphicsObject instanceof Image || graphicsObject instanceof Label)) {
                if(shapeBatchOpen){
                    shapeRenderer.end();
                    shapeBatchOpen = false;
                }
                if(!spriteBatchOpen){
                    spriteBatch.begin();
                    spriteBatchOpen = true;
                }
                graphicsObject.draw();
            }

            if (!(graphicsObject instanceof Image || graphicsObject instanceof Label)) {
                if(spriteBatchOpen){
                    spriteBatch.end();
                    spriteBatchOpen = false;
                }
                if(!shapeBatchOpen){
                    shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                    shapeBatchOpen = true;
                }
                graphicsObject.draw();
            }
        }

        if(spriteBatchOpen){
            spriteBatchOpen = false;
            spriteBatch.end();
        }
        if(shapeBatchOpen){
            shapeBatchOpen = false;
            shapeRenderer.end();
        }

        rendering = false;

    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
        spriteBatch.dispose();
    }

    public void addObject(GraphicsObject graphicsObject) {
        graphicsObjects.add(graphicsObject);
    }

    public void removeObject(GraphicsObject graphicsObject) {
        graphicsObjects.remove(graphicsObject);
    }

    public double getWidth() {
        return Gdx.graphics.getWidth();
    }

    public double getHeight() {
        return Gdx.graphics.getHeight();
    }

    public void background(Color color) {
        backgroundColor = color;
    }

    public void setup() {
        System.out.println("super");
    }

    //this draw is only called when child class has no own draw()
    public void draw() {
        hasUpdateMethod = false;
    }

    public void text(BitmapFont font, String text, int x, int y) {
        font.draw(spriteBatch, text, x, (int) (getHeight() - y));
    }

    @Override
    public void resize(int width, int height) {
        size(width,height);
    }

    public void size(int width, int height) {
        Gdx.graphics.setResizable(true);
        Gdx.graphics.setWindowedMode(width, height);
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        Gdx.graphics.setResizable(resizable);
    }

    public void zoom(int zoomFactor){

    }

    public void resizable(boolean resizable){
        this.resizable = resizable;
        Gdx.graphics.setResizable(resizable);
    }

    @Override
    public void keyPressed(KeyEvent event) {
    }

    @Override
    public void keyReleased(KeyEvent event) {
    }

    @Override
    public void mouseClicked(MouseEvent event) {

    }

    @Override
    public void mouseReleased(MouseEvent event) {

    }

    protected Camera getCamera(){
        return camera;
    }

}
