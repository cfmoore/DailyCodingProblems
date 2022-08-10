/****
    *Given a signed 32-bit integer x, return x with its digits reversed. 
    *If reversing x causes the value to go outside the signed 32-bit integer range [-2^31, 2^31 - 1], then return 0.
    *
    *Assume the environment does not allow you to store 64-bit integers (signed or unsigned).
*/
class Solution {
    public int reverse(int x) {
        int answer = 0;
        int temp = 0;
        int place = 0;
        //Take Care of signing
        boolean signed = false;
        if(x<0)
        {
            signed=true;
            x = x*-1;
        }                
            
        while(x > 0)
        {
            temp = x%10;
            //System.out.println(temp+"  "+x);
            //Integer Overflow
            if((answer*10+temp)<0)
                return 0;
            else if(place >= 9 && answer >= 214748369)
                return 0;
            else
            {
                answer = answer*10+temp;
                place++;
                x = (x-temp)/10;
            }
        }
        if(signed)
            answer = answer * -1;
        return answer;
    }
}
