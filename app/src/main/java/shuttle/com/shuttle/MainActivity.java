package shuttle.com.shuttle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initClickListner();
    }
    private void initClickListner()	{
        Button admin = (Button) findViewById(R.id.admin);
        admin.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AdminLogin.class);
                startActivity(intent);
            }
        });
        Button customer = (Button) findViewById(R.id.student);
        customer.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                Runnable r = new Runnable(){
                    public void run() {
                        ReadLocation rl = new ReadLocation("test");
                        try {
                            rl.join();
                            String response = rl.getResponse();
                            if (response != null) {
                                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                                intent.putExtra("lat", response);
                                startActivity(intent);
                            }else{
                                Toast.makeText(MainActivity.this, "Error in sending driver location", Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }};new Thread(r).start();

            }
        });
        Button emergency = (Button) findViewById(R.id.driverloginbutton);
        emergency.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,DriverLogin.class);
               startActivity(intent);
            }
        });


    }




}
