package au.com.kelpie.collections;

import java.util.Iterator;

public class ObjectMatrix<T> implements ResetIterable<T>
{
	private T[][] matrix;
	private int width;
	private int height;
	
	
	@SuppressWarnings("unchecked")
	public ObjectMatrix(int width, int height) {
		matrix = (T[][])new Object[width][height];
		this.width = width;
		this.height = height;
	}
	public boolean valid(int x, int y) {
		return x >= 0 && x < width && y >= 0 && y < height;
	}
	public int index(int x, int y) {
		return x + y * width;
	}
	
	
	public int length() {
		return width * height;
	}
	
	public T get(int x, int y) {
		return matrix[x][y];
	}
	public T get(int index) {
		
		int x = index % width;
		int y = index / width;
		
		return matrix[x][y];
		
		
	}
	
	public boolean set(int x, int y, T value) {
		if(!valid(x,y))
			return false;
		//else is valid set x,y to value
		matrix[x][y] = value;
		return true;
	}
	@Override
	public Iterator<T> iterator() {
		return resetIterator();
	}
	@Override
	public ResetableIterator<T> resetIterator() {
		
		ResetableIterator<T> it = new ResetableIterator<T>() {
			int count = 0;
			int xin = 0;
			int yin = 0;
			
			@Override
			public boolean hasNext() {
				return count < length();
			}

			@Override
			public T next() {
				++count;
				T value = matrix[xin][yin];
				++xin;
				
				if(xin >= width) {
					++yin;
					xin = 0;
				}
				return value;
			}

			@Override
			public void reset() {
				count = 0;
				xin = 0;
				yin = 0;
			}
		};
		return it;
	} 
	
	
}
