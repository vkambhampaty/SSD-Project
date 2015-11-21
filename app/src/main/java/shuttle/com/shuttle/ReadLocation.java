package shuttle.com.shuttle;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
public class ReadLocation extends Thread{
    //query variable
    String request;
    //response from server
    String response;
    //return server response
    public String getResponse(){
        return response;
    }
    //assign query here. This query contains all registration values
    public ReadLocation(String request){
        this.request = request;
        //call to run
        start();
    }
    //start network connection
    public void run(){
        try{
            URL url = new URL("https://shuttletracking-1133.appspot.com/ReadLocation");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            //con.setDoOutput(true);
            //BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(con.getOutputStream()));
            //bw.write(request);
           // bw.flush();
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            response = br.readLine();
            System.out.println("locationn >>>>>> "+response);
        }catch(Exception e){
            //register.printError(e.getMessage());
            e.printStackTrace();
        }
    }
}



