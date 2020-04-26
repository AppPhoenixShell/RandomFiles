package app.phoenixshell.kelpie2d.draw;

import com.badlogic.gdx.Game;

public abstract class SketchApp extends Game
{
	Sketch sketch;
	
	public abstract Sketch createSketch();
	public abstract void onCreate(Sketch sketch);
	
	@Override
	public final void resize(int width, int height) {
		sketch.resize(width, height);
	}
	
	
	@Override
	public final void dispose() {
		super.dispose();
		sketch.onDispose();
	}
	@Override
	public final void create() {
		sketch = createSketch();
		
		onCreate(sketch);
		sketch.setup();
	}

	@Override
	public final void render() {
		sketch.render();
		
	}

}
