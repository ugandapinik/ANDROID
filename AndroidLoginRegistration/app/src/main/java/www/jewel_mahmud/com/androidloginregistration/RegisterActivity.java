package www.jewel_mahmud.com.androidloginregistration;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.graphics.drawable.RippleDrawable;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private EditText Name;
    private EditText UserName;
    private EditText Email;
    private EditText Password;
    private EditText Confirmpassword;
    private Button registeruser;


    String name, username, email, password, confirmpassword;

    AlertDialog.Builder builder;

    private String serverURL = "http://192.168.0.116/android/prologreg/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //initialize
        Name = (EditText) findViewById(R.id.reg_name);
        UserName = (EditText) findViewById(R.id.reg_username);
        Email = (EditText) findViewById(R.id.reg_email_address);
        Password = (EditText) findViewById(R.id.reg_password);
        Confirmpassword = (EditText) findViewById(R.id.reg_confirm_password);
        registeruser = (Button) findViewById(R.id.btn_reg);

        builder = new AlertDialog.Builder(RegisterActivity.this);

        Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();


        registeruser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = Name.getText().toString();
                email = Email.getText().toString();
                username = UserName.getText().toString();
                password = Password.getText().toString();
                confirmpassword = Confirmpassword.getText().toString();

                if(name.equals("")
                   || email.equals("")
                   || username.equals("")
                   || password.equals("")
                   || confirmpassword.equals("")){


                    builder.setTitle("Something Went Wrong...");
                    builder.setMessage("Please fill all the fields.");
                    displayAlert("input_error");

                }else{
                    if(! password.equals(confirmpassword)){

                        builder.setTitle("Something went wrong.");
                        builder.setMessage("Password Doesnt Matched.");
                        displayAlert("input_error");

                    }else{

                        StringRequest stringRequest = new StringRequest(Request.Method.POST, serverURL, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                JSONArray jsonArray = null;
                                try {
                                    jsonArray = new JSONArray(response);
                                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                                    String code = jsonObject.getString("code");
                                    String message = jsonObject.getString("message");


                                    //set builder
                                    builder.setTitle("Server Response...");
                                    builder.setMessage(message);
                                    displayAlert(code);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        }){
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> params = new HashMap<String, String>();
                                params.put("name", name);
                                params.put("email", email);
                                params.put("username", username);
                                params.put("password", password);

                                return params;
                            }
                        };

                        SingleTon.getInstance(RegisterActivity.this).addToRequestQueue(stringRequest);
                    }
                }



            }
        });

    }


    public void displayAlert(final String code){
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(code.equals("input_error")){
                    Password.setText("");
                    Confirmpassword.setText("");
                }

                else if(code.equals("success")){
                    finish();
                }else if(code.equals("failed")){

                    Name.setText("");
                    Email.setText("");
                    Password.setText("");
                    UserName.setText("");
                    Confirmpassword.setText("");
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
