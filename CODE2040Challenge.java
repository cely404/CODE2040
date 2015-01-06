/*
  @author: Aracely Payan Martinez 
  @email: apayan@ucsd.edu
  @github: https://github.com/cely404/CODE2040.git
*/

import java.net.*;
import java.io.*;
import java.lang.*;
import org.json.simple.JSONObject;
import java.util.ArrayList; 
import java.util.List;

public class CODE2040Challenge{
    public static void main(String[] args)
    {

        JSONObject json = new JSONObject();
        String token = "DnCvBoZG7W";
        String response; 
        String[] result;
        json.put("token", token);
        String url;
        HttpURLConnection connection = null;  
       
        //String array of list of strings to be iterated through 
        String[] stringList = new String[]{"428XKkW5","547ucl8i","547beSlv",
                                           "5478VM23","42850Ylw","5478uVRM"};

        String prefix = "547";

        //result is the resulting array of words that do not contain the prefix specified 
        result = notPrefix(stringList, prefix);

        //Use a string buffer to manually create the array and dictionary entries
        StringBuffer sb = new StringBuffer(); 
        sb.append(json.toString()); 
        sb.setLength(sb.length() - 1);
        sb.append(",\"array\":"); 
        sb.append(arrayToString(result));
        sb.append("}");

        //Send string representation of String Buffer as urlParameters 
        String urlParameters = sb.toString();
        url = "http://challenge.code2040.org/api/validateprefix";
        response = postMethod(connection, url, urlParameters); 
        System.out.println(response);

        if(connection != null){
             connection.disconnect(); 
        }

    }
    /* Function takes as parameters an HttpURLConnection, a string which represents the 
       URL and another string which represent the url Parameters. sets the request method
       as POST and returns a string representing the response */
    private static String postMethod(HttpURLConnection connection, String strUrl, String urlParameters){
        try {
            //Create connection
            URL url = new URL(strUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
          
            connection.setUseCaches (false);
            connection.setDoInput(true);
            connection.setDoOutput(true);
     
            //Send request
            DataOutputStream wr = new DataOutputStream (
            connection.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            //Get Response  
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer(); 
            while((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();

            return response.toString();

        } catch (Exception e) {

            e.printStackTrace();
            return "";
        }
    }
    /* Function takes in a string and outputs the reverse of the string.  
    Creates an empty array and populates it in reverse order. */
    public static String reverse(String original){
        char[] charArray = original.toCharArray();
        int length = charArray.length;
        char[] newArray = new char[length];
        int i = 0;
        length --;

        for(char c: charArray){
            newArray[length - i]= c;
            i++;
        }
        return String.valueOf(newArray);
    }
    
    /* Function takes in an array of strings and a string to be found 
       in the array. Function returns the position of the string in the
       array or -1 if no such string is found in the array. */
    public static int findNeedle( String[] haystack, String needle){
        
        for(int i=0; i < haystack.length; i++){
        	if(needle.equals(haystack[i]))
        		return i;
        }
        //return -1 to signify the needle was not found 
        return -1;
    }

    public static String[] notPrefix(String[] stringList, String prefix){

        List<String> list = new ArrayList<String>();
        for(String word: stringList){
            if(!word.startsWith(prefix)){
            	list.add(word);
            }
        }
        return list.toArray(new String[list.size()]);
    }
    
    /* Function takes in a String array and returns the string 
       representation of the array */
    private static String arrayToString(String[] stringArray){
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        for (String word: stringArray){
        	sb.append('"');
            sb.append(word);
            sb.append('"');
            sb.append(','); 
        }
        //modify the length by one to remove trailing comma 
        sb.setLength(sb.length() - 1);
        sb.append("]");
        return sb.toString();

    }
}