package www.jewel_mahmud.com.volley_request_image;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    Button button;
    String serverURL = "http://192.168.0.116/android/prologreg/item.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.image);
        button = (Button) findViewById(R.id.btn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ImageRequest imageRequest = new ImageRequest(serverURL, new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        imageView.setImageBitmap(response);
                    }
                }, 0, 0, ImageView.ScaleType.CENTER_CROP, null, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Something went Wrong", Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                });


                SingletonImage.getInstance(getApplicationContext()).addToRequestQueue(imageRequest);

            }
        });
    }
}
