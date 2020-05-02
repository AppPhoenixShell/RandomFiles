package au.com.kelpie.collections;

public abstract class AbstractCircular {
	
	private int index;
	private int size;
	
	public abstract int length();
	public abstract void resize(int newsize);
	
	public int push() {
		int cindex= index;
		
		index = index + 1 >= length() ? 0: index + 1;
		size = size + 1 > length() ? length() : size + 1;
		
		return cindex;
	}
	
	public int size() {
		return size;
	}
}
