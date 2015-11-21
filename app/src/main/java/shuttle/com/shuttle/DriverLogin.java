package shuttle.com.shuttle;

        import java.util.ArrayList;


        import android.app.Activity;
        import android.app.ProgressDialog;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

public class DriverLogin extends Activity{
    EditText user, pass;
    Button sign;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver_login);
        user = (EditText) findViewById(R.id.username);
        pass = (EditText) findViewById(R.id.password);
        sign=(Button) findViewById(R.id.signin);
        sign.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                try{
                    login();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
    public void login(){
        //read values from fields
        String s1 = user.getText().toString();
        String s2 = pass.getText().toString();
        if(s1.trim().length() == 0 ||s1 == null){
            Toast.makeText(DriverLogin.this, "Please enter username", Toast.LENGTH_LONG).show();
            user.requestFocus();
            return;
        }
        if(s2.trim().length() == 0 ||s2 == null){
            Toast.makeText(DriverLogin.this, "Please enter password", Toast.LENGTH_LONG).show();
            user.requestFocus();
            return;
        }
        StringBuilder request = new StringBuilder();
        request.append("t1="+s1+"&t2="+s2);
        //progress dialog
        ProgressDialog dialog = new ProgressDialog(DriverLogin.this);
        dialog.setMessage("Processing...");
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.show();
        //call thread
        DriverLoginThread register = new DriverLoginThread(request.toString());
        try{
            //wait till thread complete its execution
            register.join();
            dialog.dismiss();
            //get response from server
            String res = register.getResponse();
            //if response successfull then go to LoginActivity
            if (res.equals("success")) {
                GPSTracker gps = new GPSTracker(DriverLogin.this);
                if(gps.canGetLocation()){

                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    // \n is for new line
                    sendDriverLocation(latitude,longitude);
                }else{
                    // can't get location
                    // GPS or Network is not enabled
                    // Ask user to enable GPS/network in settings
                    gps.showSettingsAlert();
                }

            } else {
                //show login fail
                Toast.makeText(DriverLogin.this, "login failed", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void sendDriverLocation(double lat,double lon){
        StringBuilder request = new StringBuilder();
        request.append("t1=loc"+"&t2="+Double.toString(lat)+"&t3="+Double.toString(lon)+"&t4="+Double.toString(lat)+"&t5="+Double.toString(lon));
        //progress dialog
        ProgressDialog dialog = new ProgressDialog(DriverLogin.this);
        dialog.setMessage("Processing...");
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.show();
        System.out.println("laat==="+lat+" "+lon);
        //call thread
        ShuttleLocationThread register = new ShuttleLocationThread(request.toString());
        try{
            //wait till thread complete its execution
            register.join();
            dialog.dismiss();
            //get response from server
            String res = register.getResponse();
            //if response successfull then go to LoginActivity
            if (res.equals("success")) {
                Toast.makeText(DriverLogin.this, "Shuttle location sent to server", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(DriverLogin.this,MainActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(DriverLogin.this, "Error in sending driver location", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


