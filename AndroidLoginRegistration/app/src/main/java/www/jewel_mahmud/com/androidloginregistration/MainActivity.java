package www.jewel_mahmud.com.androidloginregistration;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class MainActivity extends AppCompatActivity {


    private TextView registerLink;
    private String email, password;
    private EditText eEmail;
    private EditText ePassword;
    private Button elogin;

    private String serverURL = "http://192.168.0.116/android/prologreg/login.php";

    AlertDialog.Builder builder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eEmail = (EditText) findViewById(R.id.log_email_address);
        ePassword = (EditText) findViewById(R.id.log_password);
        elogin = (Button) findViewById(R.id.btn_login);

        //builder alertdialog
        builder = new AlertDialog.Builder(MainActivity.this);

        registerLink = (TextView) findViewById(R.id.register_link);
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });


        elogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = eEmail.getText().toString();
                password = ePassword.getText().toString();

                if(email.equals("") || password.equals("")){
                    builder.setTitle("Something Went Wrong...");
                    displayAlert("Enter Valid Username / Password");

                }else{

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, serverURL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            JSONArray jsonArray = null;

                            try {
                                jsonArray = new JSONArray(response);
                                JSONObject jsonObject = jsonArray.getJSONObject(0);
                                String code = jsonObject.getString("code");
                                Toast.makeText(MainActivity.this, code.toString(), Toast.LENGTH_SHORT).show();


                                if (code.equals("login_failed")){
                                    //LOGIN FAILED.
                                    builder.setTitle("Server Response...");
                                    displayAlert(jsonObject.getString("message"));
                                }else{
                                    //LOGIN SUCCESSFULLY
                                    Intent intent = new Intent(MainActivity.this, LoginSuccessAcitivty.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putString("name", jsonObject.getString("name"));
                                    bundle.putString("email", jsonObject.getString("email"));
                                    bundle.putString("username", jsonObject.getString("username"));
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(MainActivity.this, "Error happens", Toast.LENGTH_SHORT).show();
                            error.printStackTrace();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("email", email);
                            params.put("pasword", password);

                            return params;
                        }
                    };

                    SingleTon.getInstance(MainActivity.this).addToRequestQueue(stringRequest);


                }
            }
        });
    }


    public void displayAlert(String message){
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                eEmail.setText("");
                ePassword.setText("");
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
