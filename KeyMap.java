package au.com.kelpie.collections;

import java.util.Arrays;
import java.util.Iterator;



/** Holds a String -> Object mapping of fixed size which can be accessed through 
 * the key or fixed index*/
public class KeyMap <T> implements Iterable<T>
{
	private String[] keys;
	private T[] values;
	
	private final float gfac= 1.5f;
	private int size;
	
	@SuppressWarnings("unchecked")
	public KeyMap(int size) {
		keys = new String[size];
		values = (T[])new Object[size];
		
	}
	private void resizeIfFull() {
		if(size < values.length)
			return;
		//must resize
		int newSize =  (int) (values.length * gfac + 1);
		this.keys = Arrays.copyOf(this.keys, newSize);
		this.values = Arrays.copyOf(this.values, newSize);
	}
	
	
	public void put(String key, T value) {
		resizeIfFull();
		values[size] = value;
		keys[size] = key;
		++size;
	}
	public <U> U getCast(int index, Class<U> clazz) {
		return clazz.cast(values[index]);
	}
	public <U> U getCast(String key, Class<U> clazz) {
		return clazz.cast(get(key));
	}
	
	public T get(String key) {
		for(int i=0; i < keys.length; i++) {
			if(keys[i] == null)
				continue;
			if(keys[i].equals(key))
				return values[i];
		}
		return null;
		
	}
	public String getKeyAt(int index) {
		return keys[index];
	}
	
	public T getValueAt(int index) {
		return values[index];
	}
	
	public int length() {
		return keys.length;
	}
	public void getPair(int index, Pair<String, T> result) {
		result.value1 = keys[index];
		result.value2 = values[index];
	}
	@Override
	public Iterator<T> iterator(){
		Iterator<T> it = new Iterator<T>()
		{
			private int index = 0;
			@Override
			public boolean hasNext() {
				return index < size;
			}

			@Override
			public T next() {
				T ob = values[index];
				++index;
				return ob;
			}
		};
		return it;
	}
	
	
	
}
