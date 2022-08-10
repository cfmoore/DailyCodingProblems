/****
Implement the myAtoi(string s) function, which converts a string to a 32-bit signed integer (similar to C/C++'s atoi function).

The algorithm for myAtoi(string s) is as follows:

1.) Read in and ignore any leading whitespace.
2.) Check if the next character (if not already at the end of the string) is '-' or '+'.
    Read this character in if it is either. This determines if the final result is negative or positive respectively. 
    Assume the result is positive if neither is present.
3.) Read in next the characters until the next non-digit character or the end of the input is reached. 
    The rest of the string is ignored.
4.) Convert these digits into an integer (i.e. "123" -> 123, "0032" -> 32).
    If no digits were read, then the integer is 0. Change the sign as necessary (from step 2).
5.) If the integer is out of the 32-bit signed integer range [-2^31, 2^31-1], 
    then clamp the integer so that it remains in the range. Specifically, integers less than -2^31 
    should be clamped to -2^31, and integers greater than 2^31 - 1 should be clamped to 2^31 - 1.
6.) Return the integer as the final result.
Note:
Only the space character ' ' is considered a whitespace character.
Do not ignore any characters other than the leading whitespace or the rest of the string after the digits.
*/
class Solution {
    /**
    *   I feel that the true spirit of this coding assignment is not to do it in the fastest way
    *   But to not cut the string down and then just convert it to a number using built in functions.
    *   I could cut the string according to the rules and then just cast the number as an int and let 
    *   Java do the work, but that is not fun.
    *
    */
    public int myAtoi(String s) {
        int answer = 0;
        boolean signed = false;
        int location = 0;
        boolean plus = false;
        int count = 0;
        boolean inNum = false;
        while(true)
        {
            if(location>=s.length())
            {
                if(signed)
                    answer *=-1;
                return answer;
            }
            else if(s.charAt(location)==' ' && answer==0 && !inNum)
                location++;
            else if (s.charAt(location)=='-' && !plus && !inNum)
            {
                signed = true;
                inNum = true;
                location++;
            }
            else if(s.charAt(location)=='+' && !signed && !inNum)
            {
                plus = true;
                inNum = true;
                location++;
            }
            else if(s.charAt(location) >=48 && s.charAt(location) <= 58)
            {
                inNum = true;                                                 
                //Avoid Integer Overflow errors assuming 32 bit integers Limit: -2147483648<answer<2147483647
                //Specs are "clamp" number which I interperet as keep to Limit
                if(s.charAt(location) == 48 && answer == 0)
                    inNum = true;
                else 
                    count++;     
                if((answer*10+(s.charAt(location)-48) < 0))
                {
                    if(signed)
                        return -2147483648;
                    else
                        return 2147483647;
                }   
                //These are not magic numbers, 9 is the amount of digits in the limits, 214748369 is the highest possible number it 
                //Could be before it starts wrapping. 
                else if(count >= 9 && answer >= 214748369)
                {
                    if(signed)
                        return -2147483648;
                    else
                        return 2147483647;
                }
                 else              
                    answer = answer*10+(s.charAt(location)-48);//48 is the ascii offset to decimal
                location++;
            }
            else
            {
                if(signed)
                    answer *=-1;
                return answer;
            }
        }
    }
}
