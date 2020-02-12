
public class ArraySum {

	public static int sumArray(int[] array)
	{
		int sum = 0;
		for(int i = 0; i<array.length; i++)
			sum += array[i];
		return sum;
	}
	
	public static int selectSum(int[] array)
	{
		int sum = 0;
		int previous = 0, next = 0, im1 = 0, ip1 = 0;
		for(int i = 1; i<array.length; i++)
		{
			//Need to get the three in a row.
			previous = array[i-1];//prev = 1
			if(i<array.length-1)
				next = array[i+1];//next = 3
			if(previous > array[i] && next>array[i])
			{
				if(i+2 < array.length-1)
			}
			
		}
		
		return sum;
	}
	public static void main(String[] args) {
		/**For example, [2, 4, 6, 2, 5] should
		return 13, since we pick 2, 6, and 5. 
			[5, 1, 1, 5] should return 10, since we pick 5 and 5.*/
		int[] array = {1,2,3};
		System.out.println(sumArray(array));
		System.out.println(selectSum(array));

	}

}
