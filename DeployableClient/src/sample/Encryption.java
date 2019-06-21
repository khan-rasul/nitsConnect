package sample;

public class Encryption {
    public static String cipher(String s)
    {
        String result = new String("");
        for(int i = 0 ; i < s.length() ; i++)
        {
            char ch = (char) (( (int)s.charAt(i) + 3) % 256);
            result = result + ch;
        }
        return result;
    }
    public static String decipher(String s)
    {
        String result = new String("");
        for(int i = 0 ; i < s.length() ; i++)
        {
            char ch = (char) (( (int)s.charAt(i) - 3 + 256) % 256);
            result = result + ch;
        }
        return result;
    }
}
