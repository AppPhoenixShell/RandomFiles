package app.phoenixshell.kelpie2d.draw;

import java.awt.Point;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import au.com.kelpie.util.Lib;

public interface SketchTool{

	/*io calls*/
	
	public float mouseX();
	public float mouseY();
	
	public void draw();
	public void setup();
	public void overlay();
	public void update(float delta);
	
	public float width();
	public float height();
	
	public void clear(Color color);
	
	public void move(float x, float y);
	
	public void camera(float x, float y);
	public void zoomIn(float zoom);
	public void zoomOut(float zoom);
	public void zoom(float zoom);
	public float cameraY() ;
	public float cameraX();
	
	/*random functions*/
	public float rfloat();
	public float rfloat(float bound);
	public int rint(int bound);
	
	public float noise(float x, float y);
	
	public Texture texture(String path);
	
	public  void fill(Color color);
	public  void fill(float r, float g, float b, float a);
	
	public void fillRandom();
	public void stroke(Color c);
	
	public void circle(float x, float y, float r);
	public void circle(Vector2 pos, float r);
	
	public void rect(float x, float y, float w, float h);
	public void rect(Rectangle r);
	public void line(Vector2 p1, Vector2 p2);
	
	public void line(float x1, float y1, float x2, float y2);
	public double distance(float x1, float y1, float x2, float y2);

	public void resize(int width, int height);
	
	public void seedRandom(long seed);
	public void seedNoise(long seed);
	
	public float random(float min, float max);
	
	public boolean key(int keycode);
	public boolean keyJust(int keycode);
	
	public TextureRegion region(Texture texure, int x, int y, int w, int h);
	
	public void tint(Color color);
	
	public void draw(TextureRegion texture, float x, float y);
	
	public void text(float x, float y, String msg);
	public void autoCamera(float camSpeed, float zoomFactor);
	
	
}
