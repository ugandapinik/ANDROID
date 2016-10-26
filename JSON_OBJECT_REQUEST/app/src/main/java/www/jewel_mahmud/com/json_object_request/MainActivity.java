package www.jewel_mahmud.com.json_object_request;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private String serverURL = "http://192.168.0.116/android/prologreg/getinfo.php";
    private TextView mname;
    private TextView musername;
    private TextView mage;
    private Button getInformation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mname = (TextView) findViewById(R.id.fullname);
        musername = (TextView) findViewById(R.id.uname);
        mage = (TextView) findViewById(R.id.age);
        getInformation = (Button) findViewById(R.id.collectInformation);

        getInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, serverURL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            mname.setText(response.getString("name"));
                            musername.setText(response.getString("username"));
                            mage.setText(response.getString("age"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Error Happen", Toast.LENGTH_SHORT).show();
                    }
                });

                SingleTon.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
            }
        });

    }
}
