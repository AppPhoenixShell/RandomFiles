package au.com.kelpie.collections;

public interface ResetIterable<T> extends Iterable<T>
{
	public ResetableIterator<T> resetIterator();
}
