package shuttle.com.shuttle;

        import android.app.Activity;
        import android.content.ComponentName;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
public class AdminModule extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_module);
        initClickListner();
    }
    private void initClickListner()	{
        Button admin = (Button) findViewById(R.id.adddriver);
        admin.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(AdminModule.this,AddDriver.class);
                startActivity(intent);
            }
        });

        Button logout = (Button) findViewById(R.id.logout);
        logout.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(AdminModule.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }




}


