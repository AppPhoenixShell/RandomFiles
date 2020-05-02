package au.com.kelpie.collections;

public class CircularInt extends AbstractCircular
{
	private int[] array;
	
	public CircularInt(int size) {
		array = new int[size];
	}

	public void add(int value) {
		array[push()] = value;
	}
	public void add(int...array) {
		for(int i=0; i < array.length; i++)
			add(array[i]);
			
	}
	
	public void cpy(int[] dest) {
		int cpysize = Math.min(dest.length, array.length);
		System.arraycopy(array, 0, dest, 0, cpysize);
	}
	
	public int length() {
		return array.length;
	}

	@Override
	public void resize(int newsize) {
		int[] newarr = new int[newsize];
		cpy(newarr);
		this.array = newarr;
		
	}

}
