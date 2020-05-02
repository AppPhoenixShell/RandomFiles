package au.com.kelpie.collections;

import java.util.Iterator;

public interface ResetableIterator<T> extends Iterator<T>
{
	public void reset();
}
