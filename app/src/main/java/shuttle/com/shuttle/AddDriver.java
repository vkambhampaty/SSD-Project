package shuttle.com.shuttle;
        import android.app.Activity;
        import android.app.ProgressDialog;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;
public class AddDriver extends Activity {
    EditText user,pass,place,email,contact;
    Button signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_driver);
        user = (EditText) findViewById(R.id.username);
        pass = (EditText) findViewById(R.id.password);
        place = (EditText) findViewById(R.id.address);
        email = (EditText) findViewById(R.id.mail);
        contact = (EditText) findViewById(R.id.contact);
        signup=(Button) findViewById(R.id.signup);
        signup.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                try{
                    register();
                }catch(Exception e){
                    Toast.makeText(AddDriver.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });
    }
    public void register(){
        //read values from fields
        String s1 = user.getText().toString();
        String s2 = pass.getText().toString();
        String s3 = place.getText().toString();
        String s4 = email.getText().toString();
        String s5 = contact.getText().toString();
        //check values should not be empty
        if(s1.trim().length() == 0 ||s1 == null){
            Toast.makeText(AddDriver.this, "Please enter username", Toast.LENGTH_LONG).show();
            user.requestFocus();
            return;
        }
        if(s2.trim().length() == 0 ||s2 == null){
            Toast.makeText(AddDriver.this, "Please enter password", Toast.LENGTH_LONG).show();
            pass.requestFocus();
            return;
        }
        if(s3.trim().length() == 0 ||s3 == null){
            Toast.makeText(AddDriver.this, "Please enter place name", Toast.LENGTH_LONG).show();
            place.requestFocus();
            return;
        }
        if(s4.trim().length() == 0 ||s4 == null){
            Toast.makeText(AddDriver.this, "Please enter emailid", Toast.LENGTH_LONG).show();
            email.requestFocus();
            return;
        }
        if(!CheckMail.checkMail(s4)){
            Toast.makeText(AddDriver.this, "Please enter valid email id", Toast.LENGTH_LONG).show();
            email.requestFocus();
            return;
        }
        if(s5.trim().length() == 0 || s5 == null){
            Toast.makeText(AddDriver.this, "Please enter contact no", Toast.LENGTH_LONG).show();
            contact.requestFocus();
            return;
        }
        StringBuilder request = new StringBuilder();
        request.append("t1="+s1+"&t2="+s2+"&t3="+s3+"&t4="+s4+"&t5="+s5);
        //progress dialog
        ProgressDialog dialog = new ProgressDialog(AddDriver.this);
        dialog.setMessage("Processing...");
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.show();
        //call thread
        AddDriverThread register = new AddDriverThread(request.toString());
        try{
            //wait till thread complete its execution
            register.join();
            dialog.dismiss();
            //get response from server
            String res = register.getResponse();
            //if response successfull then go to LoginActivity
            if (res.equals("success")) {
                Intent in1 = new Intent(AddDriver.this,AdminModule.class);
                startActivity(in1);
            } else {
                //show login fail
                Toast.makeText(AddDriver.this, "failed to register", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(AddDriver.this, e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
    public void printError(String msg){
        user.setText(msg);
        Toast.makeText(AddDriver.this, msg, Toast.LENGTH_LONG).show();
    }
}


