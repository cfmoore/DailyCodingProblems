
public class AlphaMap {

	public static int map(String work)
	{
		int numWays = 0;
		char[] charMap = new char[work.length()];
		while(numWays < work.length())
		{
			charMap[numWays] = work.charAt(numWays);
			numWays++;
		}
		numWays = 0;
		if(charMap[0] == 0)
			return 0;
		else
		{
			for(int i = 0; i<charMap.length; i++)
			{
				if(charMap[i]!='0')
				{
					numWays++;
				}
				else if(i != charMap.length-1)
				{
					if(((((int)charMap[i]-48)*10)+((int)charMap[i+1]-48))<26)//Check right
						numWays++;
				}
			}

		}
		return numWays;
	}
	//1 11 1
	public static void main(String[] args) {
		String map = "011";
		System.out.println(map(map));

	}

}
