package www.jewel_mahmud.com.loginregistration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView textView;
    String serverURL = "http://192.168.0.106/android/prologreg/greetings.php";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button = (Button) findViewById(R.id.getserverinfo);
        textView = (TextView) findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

                //STRING REQUEST
                StringRequest stringRequest = new StringRequest(Request.Method.POST, serverURL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        textView.setText(response);
                        requestQueue.stop();

                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textView.setText("SOMETHING WENT WRONG.");
                        error.printStackTrace();
                        requestQueue.stop();
                    }
                }); //end String Request.


                requestQueue.add(stringRequest);
            }
        });



    }
}
