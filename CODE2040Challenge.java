/*
  @author: Aracely Payan Martinez 
  @email: apayan@ucsd.edu
  @github: https://github.com/cely404/CODE2040.git
*/

import java.net.*;
import java.io.*;
import java.lang.*;
import org.json.simple.JSONObject;

public class CODE2040Challenge{
    public static void main(String[] args)
    {

        JSONObject json = new JSONObject();
        String token = "DnCvBoZG7W";
        String response; 
        String result;
        json.put("token", token);
        String url;
        HttpURLConnection connection = null;  
        //the string array representing the haystack 
        String[] haystack = new String[] {"bF481","60ddV","kBhXJ","Rr17c","dduty",
                                          "RnKMN","ZYrGZ","cvycf","4cUBo","nvGM8",
                                          "ffqHM","0Ih3W","5dPao","vLdRm","OCqNK",
                                          "VvvRe","dviKB","9ma9s","PfgUD","VxFug"};
        //string representing the needle 
        String needle = "4cUBo";
        //method call to find the needle in the haystack
        json.put("needle", findNeedle(haystack, needle));  
        String urlParameters = json.toString();
        url = "http://challenge.code2040.org/api/validateneedle";
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
    public static String reverse(String original)
    {
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

}