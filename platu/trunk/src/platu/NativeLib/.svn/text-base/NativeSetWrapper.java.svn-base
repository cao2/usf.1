package platu.NativeLib;

import platu.common.SetIntTuple;

public class NativeSetWrapper extends SetIntTuple
{
	static
	{
		try
		{
			System.loadLibrary("NativeSetWrapper");
		}
		
		catch(Exception e)
		{
			
		}
		
		System.out.println();
	}	
	
	public NativeSetWrapper() {};
	
	public native int add(int[] IntArray);

	public native boolean contains(int[] IntArray);

	public native int size();

	public native String stats();

}


/*
package platu.NativeLib;

public class NativeSetWrapper 
{
	static
	{
		System.loadLibrary("NativeSetWrapper");
	}
	
	public NativeSetWrapper() {};

	public static native boolean containsArray(int[] array);
	//Returns true if array exists in the set
	//False if array is absent
	
	public static native void addArray(int[] array);
	//Returns true if the array is added successfully
	//Otherwise returns false
	
	public static native void removeArray(int[] array);
	//Returns true if the array is added successfully
	//Otherwise returns false
	
	public static native void flushSet();
	//Erases all arrays currently in the set
	
	public static native void printSet();
	//Prints the entire set of arrays
	
	public static native int getSetSize();
	//Returns the array size
	
	*//**
	public static void main(String args[])
	{	
		//STUFF GOES HERE
		int[] test1 = {1, 2, 3};
		
		addArray(test1);
		
		if(containsArray(test1))
			System.out.println("Add works, and so does contain");
		
		printSet();
		
		removeArray(test1);
		
		if(!containsArray(test1))
			System.out.println("Remove works, as does the other wing of contain");
		
		
		flushSet();
	}**//*
}
*/