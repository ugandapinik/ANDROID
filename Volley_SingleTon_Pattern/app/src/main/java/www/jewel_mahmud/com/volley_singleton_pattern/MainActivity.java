package www.jewel_mahmud.com.volley_singleton_pattern;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

public class MainActivity extends AppCompatActivity {

    Button button;
    TextView textView;
    String serverURL = "http://192.168.0.106/android/prologreg/greetings.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textView = (TextView) findViewById(R.id.result);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StringRequest stringRequest = new StringRequest(Request.Method.POST, serverURL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        textView.setText(response);

                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textView.setText("Erro Happens.");
                        error.printStackTrace();
                    }
                });


                MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
            }
        });



    }
}
