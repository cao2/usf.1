package platu.NativeLib;

import platu.common.SetIntTuple;

public class NativeHybridMDDWrapper extends SetIntTuple
{
	static
	{
		try
		{
			System.loadLibrary("NativeHybridMDDWrapper");
		}
		
		catch(Exception e)
		{
			
		}
		
		System.out.println();
	}
	
	public NativeHybridMDDWrapper(int size, boolean isHybrid, int[] bitsPerCol)
	{
		init(size, isHybrid, bitsPerCol);
	}
	
	public NativeHybridMDDWrapper(int size, boolean isHybrid)
	{
		int[] bitsPerCol = new int[size];
		java.util.Arrays.fill(bitsPerCol, 0, size, 32);
		init(size, isHybrid, bitsPerCol);
	}
	
	public native int add(int[] IntArray);

	public native boolean contains(int[] IntArray);

	public native int size();

	public native String stats();

	public native void init(int elements, boolean isHybrid, int[] bitsPerCol);

}