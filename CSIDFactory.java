package app.phoenixshell.api;

import java.lang.reflect.Array;


/** This factor creates an API pattern witha  common interface & validates the class
 * objects
 * */

public class CSIDFactory<C>
{
	public Class<C> commonClass;
	public Class<?>[] specArray;
	public Class<?>[] impArray;
	
	public C[] impObjects;
	
	public CSIDFactory() {
		
	}
	
	@SuppressWarnings("unused")
	public void create(Class<C> aClass, Class<?>[] specArray, Class<?>[] impArray) throws InstantiationException, IllegalAccessException {
		if(impArray.length != specArray.length)
			throw new RuntimeException("CSID implementation array must equal specification array");
		this.commonClass = aClass;
		this.impArray = impArray;
		this.specArray = specArray;
		
		impObjects	= (C[]) Array.newInstance(commonClass, specArray.length);
		//check valid dependancy
		for(int i=0; i < specArray.length; i++) {
			Class<?> spec = specArray[i];
			Class<?> imp = impArray[i];
			
			if(!spec.isInterface())
				throw new RuntimeException("Specification array must be interfaces");
			if(imp.isInterface())
				throw new RuntimeException("Implementation array must not be interfaces");
			
			//attempt to inflate implementation array and check parent classes;
			Object newImp = imp.newInstance();
			//check dependacy
			if(!spec.isInstance(newImp))
				throw new RuntimeException("imp[" + i + "] must be a subclass of spec");
			if(!commonClass.isInstance(newImp))
				throw new RuntimeException("imp[" + i + "] is not subclass of common");
			
			
			/*check if an instane of that class already exists.Only allow 1 of each for now*/
			impObjects[i] = (C) newImp;
			
		}
	}
}
