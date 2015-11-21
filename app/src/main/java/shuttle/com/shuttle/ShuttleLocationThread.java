package shuttle.com.shuttle;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
public class ShuttleLocationThread extends Thread{
    //query variable
    String request;
    //response from server
    String response;
    //return server response
    public String getResponse(){
        return response;
    }
    //assign query here. This query contains all registration values
    public ShuttleLocationThread(String request){
        this.request = request;
        //call to run
        start();
    }
    //start network connection
    public void run(){
        try{
            URL url = new URL(ServerURL.getURL()+"NewUser");
            URLConnection con = url.openConnection();
            con.setDoOutput(true);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(con.getOutputStream()));
            bw.write(request);
            bw.flush();
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            response = br.readLine();
        }catch(Exception e){
            //register.printError(e.getMessage());
            e.printStackTrace();
        }
    }
}



