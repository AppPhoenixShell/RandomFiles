package au.com.kelpie.collections;



public class CircularArray<T> extends AbstractCircular
{
	private T[] array;
	
	
	@SuppressWarnings("unchecked")
	public CircularArray(int size){
		array = (T[]) new Object[size];
		
	}

	public void add(T element) {
		array[push()] = element;
	}
	
	public void cpy(T[] dest) {
		int cpysize= Math.min(array.length, dest.length);
		System.arraycopy(array, 0, dest, 0, cpysize);
	}

	@Override
	public int length() {
		return array.length;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public void resize(int newsize) {
		T[] newarr = (T[]) new Object[newsize];
		cpy(newarr);
		array = newarr;
		
	}
}