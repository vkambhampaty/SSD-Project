package shuttle.com.shuttle;

        import java.io.ByteArrayOutputStream;
        import android.app.Activity;
        import android.content.Intent;
        import android.graphics.Bitmap;
        import android.os.Bundle;
        import android.util.Base64;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

public class AdminLogin extends Activity {
    EditText user, pass;
    Button sign;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_login_activity);
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
            Toast.makeText(AdminLogin.this, "Please enter username", Toast.LENGTH_LONG).show();
            user.requestFocus();
            return;
        }
        if(s2.trim().length() == 0 ||s2 == null){
            Toast.makeText(AdminLogin.this, "Please enter password", Toast.LENGTH_LONG).show();
            pass.requestFocus();
            return;
        }
        if (s1.equals("admin") && s2.equals("admin")) {
            Intent intent = new Intent(AdminLogin.this,AdminModule.class);
            startActivity(intent);
        } else {
            //show login fail
            Toast.makeText(AdminLogin.this, "login failed", Toast.LENGTH_LONG).show();
        }
    }
}


