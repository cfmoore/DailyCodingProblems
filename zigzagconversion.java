/**
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: 
(you may want to display this pattern in a fixed font for better legibility)
P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"
Write the code that will take a string and make this conversion given a number of rows:
*/
class Solution {
    public String convert(String s, int numRows) {
        String answer = "";
        String[] rows = new String[numRows];
        int row = 0;
        boolean zig = false;
        int loc = 0;
        while(loc < s.length())
        {
            //work backwards
            if(zig)
            {
                rows[row] = rows[row]+s.charAt(loc);
                loc++;
                row--;
                if(row<0)
                {
                    zig = false;
                    row = 1;
                }
            }
            //build forward
            else
            {
                if(rows[row]==null)
                    rows[row] = ""+s.charAt(loc);
                else
                    rows[row] = rows[row]+s.charAt(loc);
                row++;
                loc++;
                if(row >= numRows)
                {
                    if(numRows==1)
                    {
                        row = 0;
                        zig = false;
                    }
                    else
                    {
                        zig = true;
                        row = row-2;
                    }
                }
            }
        }
        for(int i = 0; i<rows.length; i++)
        {
            if(rows[i]!=null)
                answer = answer+rows[i];
        }
        return answer;
    }
}
