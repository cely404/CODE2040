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
        json.put("string", reverse("FCtMp"));
        String urlParameters = json.toString();
        String url;
        HttpURLConnection connection = null;  

        url = "http://challenge.code2040.org/api/validatestring";
        response = postMethod(connection, url, urlParameters); 
        System.out.println(response);

        if(connection != null){
          connection.disconnect(); 
        }

    }

    private static String postMethod(HttpURLConnection connection, String strUrl, String urlParameters){
      try {
        //Create connection
        URL url = new URL(strUrl);
        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("POST");
      
        connection.setUseCaches (false);
        connection.setDoInput(true);
        connection.setDoOutput(true);
 
        //Send request
        DataOutputStream wr = new DataOutputStream (
        connection.getOutputStream ());
        wr.writeBytes (urlParameters);
        wr.flush ();
        wr.close ();

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
    /*Function takes in a string and outputs the reverse of the string.  
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

}