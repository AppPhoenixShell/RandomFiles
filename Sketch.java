package app.phoenixshell.kelpie2d.draw;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.coffeedriver.libgdx.BaseInputProcessor;
import com.coffeedriver.libgdx.VGDX;
import com.coffeedriver.math.NoiseAlgorithm;
import com.coffeedriver.math.SimplexNoise;


public abstract class Sketch implements SketchTool
{
	private ShapeRenderer shape;
	private final Color color;
	private final Color clear;
	
	private OrthographicCamera camera;
	private OrthographicCamera screenCam;
	
	private Viewport port;
	private Viewport screenViewport;
	
	private float width;
	private float height;
	
	private Random random; 
	
	private Vector3 mouse;

	private NoiseAlgorithm noiseAlgo;
	
	private Batch spriteBatch;
	private boolean mouseMoved;
	
	
	private BitmapFont font;
	
	private BaseInputProcessor input = new BaseInputProcessor() {

		@Override
		public boolean mouseMoved(int screenX, int screenY) {
			mouse.x = screenX;
			mouse.y = screenY;
			mouse.z = 0;
			
		
			//worldMosue.z = 0;
			mouseMoved = true;
			
			
			
			
			
			camera.unproject(mouse);
			return true;
		}
	};
	public boolean mouseMoved() {
		return mouseMoved;
	}
	
	@Override
	public float mouseX() {
		return mouse.x;
	}
	@Override
	public float mouseY() {
		return mouse.y;
	}
	
	
	
	public Sketch() {
		font = new BitmapFont();

		spriteBatch = new SpriteBatch();
		shape = new ShapeRenderer();
		color = new Color();
		clear = new Color(Color.WHITE);
		random = new Random();
		mouse = new Vector3();
		noiseAlgo = new SimplexNoise((int)System.currentTimeMillis());
		
		
		shape.setAutoShapeType(true);
		
		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();
		
		camera = new OrthographicCamera(width, height);
		screenCam = new OrthographicCamera(width, height);
		port = new FitViewport(width, height,camera);
		screenViewport = new FitViewport(width, height, screenCam);
		
		Gdx.input.setInputProcessor(input);
	
	}
	@Override public void overlay() {
		
	}
	@Override public abstract void draw();
	@Override public abstract void setup();
	@Override public abstract void update(float delta);
	
	@Override public float width() 	{return Gdx.graphics.getWidth();}
	@Override public float height() 	{return Gdx.graphics.getHeight();}
	
	@Override public final void clear(Color color) 		{clear.set(color);}
	@Override public final void move(float x, float y) 	{camera.translate(x, y, 0);}
	@Override public final void camera(float x, float y) 	{camera.position.set(x, y, 0);}
	@Override public final void zoomIn(float zoom) {camera.zoom /= zoom;}
	@Override public final void zoomOut(float zoom) {camera.zoom *= zoom;}
	@Override public final void zoom(float zoom) {camera.zoom = zoom;}
	@Override public final float cameraY() {return camera.position.x;}
	@Override public final float cameraX() {return camera.position.y;}
	
	@Override public float rfloat() {return random.nextFloat();}
	@Override public float rfloat(float bound) {return rfloat() * bound;}
	@Override public int rint(int bound) {return random.nextInt(bound);}
	@Override public float noise(float x, float y) {return (float) noiseAlgo.noise(x, y);}
	
	public void render() {
		mouseMoved = false;
		VGDX.clear(clear);
		//spriteBatch.setProjectionMatrix(projection);
		
		camera.update();
		shape.setProjectionMatrix(camera.combined);
		spriteBatch.setProjectionMatrix(camera.combined);
		update(Gdx.graphics.getDeltaTime());
		shape.begin();
		spriteBatch.begin();
		draw();
		shape.end();
		spriteBatch.end();
		
		screenCam.update();
		
		shape.setProjectionMatrix(screenCam.combined);
		spriteBatch.setProjectionMatrix(screenCam.combined);
		spriteBatch.begin();
		shape.begin();
		overlay();
		spriteBatch.end();
		shape.end();
	}
	@Override
	public Texture texture(String path) {
		return new Texture(Gdx.files.internal(path));
	}
	@Override
	public final void fill(Color color) {
		this.color.set(color);
		shape.setColor(this.color);
		shape.set(ShapeType.Filled);
		spriteBatch.setColor(this.color);
	}
	@Override
	public final void fill(float r, float g, float b, float a) {
		this.color.set(r,g,b,a);
		fill(this.color);
	}
	@Override
	public final void fillRandom() {
		fill(rfloat(), rfloat(), rfloat(), rfloat());
	}
	@Override
	public final void stroke(Color c) {
		this.color.set(c);
		shape.setColor(c);
		shape.set(ShapeType.Line);
	}
	@Override
	public final void circle(float x, float y, float r) {shape.circle(x, y, r);}
	@Override
	public final void circle(Vector2 pos, float r) {circle(pos.x, pos.y, r);}
	
	@Override
	public final void rect(float x, float y, float w, float h) {
		shape.rect(x, y, w,h);
	}
	@Override
	public final void rect(Rectangle r) {
		rect(r.x, r.y, r.width, r.height);
	}
	@Override
	public final void line(Vector2 p1, Vector2 p2) {
		shape.line(p1, p2);
	}
	@Override
	public final void line(float x1, float y1, float x2, float y2) {
		shape.line(x1, y1, x2, y2);
	}
	@Override
	public double distance(float x1, float y1, float x2, float y2) {
		return Vector2.dst(x1, y1, x2, y2);
	}
	@Override
	public void resize(int width, int height) {
		port.update(width, height);}
	@Override
	public void seedRandom(long seed) {
		random.setSeed(seed);}
	public void seedNoise(long seed) {
		noiseAlgo.setSeed(seed);}
	@Override
	public float random(float min, float max) {
		return min + random.nextFloat() * (max - min);
	}
	@Override
	public boolean key(int keycode) {
		return Gdx.input.isKeyPressed(keycode);
	}
	public boolean keyJust(int keycode) {
		return Gdx.input.isKeyJustPressed(keycode);
	}
	@Override
	public TextureRegion region(Texture texure, int x, int y, int w, int h) {
		return new TextureRegion(texure, x,y,w,h);
	}
	@Override
	public void tint(Color color) {
		spriteBatch.setColor(color);
	}
	@Override
	public void draw(TextureRegion texture, float x, float y) {
		spriteBatch.draw(texture, x, y);
	}
	@Override
	public void text(float x, float y, String msg) {
		font.draw(spriteBatch, msg, x, y);
	}
	
	
	
	public void onDispose() {
		
	}
	@Override
	public void autoCamera(float camSpeed, float zoomFactor) {
		if(key(Keys.W))
			move(0, camSpeed);
		if(key(Keys.S))
			move(0, -camSpeed);
		if(key(Keys.A))
			move(-camSpeed, 0);
		if(key(Keys.D))
			move(camSpeed, 0);
		
		if(key(Keys.O))
			zoomIn(zoomFactor);
		if(key(Keys.L))
			zoomOut(zoomFactor);
		camera.update();
			
	}
	
	
	


	
	

}
