package au.com.kelpie.collections;

public class CircularFloat extends AbstractCircular{

	private float[] array;
	
	
	public CircularFloat(int size) {
		array = new float[size];
	}
	
	public void add(float value) {
		array[push()] = value;
	}
	
	public void cpy(float[] dest) {
		int cpysize = Math.min(dest.length, array.length);
		System.arraycopy(array, 0, dest, 0, cpysize);
	}

	@Override
	public int length() {
		return array.length;
	}

	@Override
	public void resize(int newsize) {
		float[] newarr = new float[newsize];
		cpy(newarr);
		array = newarr;
		
	}
	
	
}
