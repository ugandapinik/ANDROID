package www.jewel_mahmud.com.senddataremotedatabase;

import android.content.DialogInterface;
import android.preference.DialogPreference;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText eName;
    private EditText eUsername;
    private EditText eAge;
    private Button eSubmit;
    String serverURL = "http://192.168.0.116/android/prologreg/updateinfo.php";

    AlertDialog.Builder builder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eName = (EditText) findViewById(R.id.editTextFullName);
        eUsername = (EditText) findViewById(R.id.editTextUsername);
        eAge = (EditText) findViewById(R.id.editTextAge);

        builder = new AlertDialog.Builder(getApplicationContext());

        eSubmit = (Button) findViewById(R.id.submit);
        eSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name, username, age;

                name = eName.getText().toString();
                username = eUsername.getText().toString();
                age = eAge.getText().toString();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, serverURL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        builder.setTitle("Server Response");
                        builder.setMessage("Response: " + response);
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                eName.setText("");
                                eUsername.setText("");
                                eAge.setText("");
                            }
                        });


                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Error Happening...", Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("name", name);
                        params.put("usernname", username);
                        params.put("age", age);

                        return params;
                    }
                };


                SingleTon.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);


            }
        });
    }
}
