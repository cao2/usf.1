package platu.NativeLib;

import platu.common.SetIntTuple;

public class NativeBDDWrapper extends SetIntTuple
{
	static
	{
		try
		{
			System.loadLibrary("NativeBDDWrapper");
		}
		catch(Exception e)
		{
			
		}
	}
	
	public NativeBDDWrapper(int size, int[] bitsPerCol)
	{
		initBDDSet(size, bitsPerCol);
	}
	
	public NativeBDDWrapper(int size)
	{
		int[] bits = new int [size];
		java.util.Arrays.fill(bits, 0, size, 32);
		initBDDSet(size, bits);
	}
	
	public native int add(int[] arrayToAdd);
	//Only returns 0 right now
	
	public native boolean contains(int[] IntArray);
	//Returns true if the BDD Set contains the specified array
	//Returns false otherwise
	
	public native int size();
	//Returns the number of unique arrays within the BDD set
	
	public native String stats();
	//Returns a stirng containing running statistics
	
	public native void initBDDSet(int size, int[] mysizes);
	//This function MUST BE CALLED before the BDDSet is used
	//The argument size refers to the number of elements within each array
}