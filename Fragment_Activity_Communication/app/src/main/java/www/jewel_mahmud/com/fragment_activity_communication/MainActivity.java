package www.jewel_mahmud.com.fragment_activity_communication;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ColorFragment.OnColorChangeListener {

    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout = (LinearLayout) findViewById(R.id.activity_main);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ColorFragment colorFragment = new ColorFragment();

        fragmentTransaction.add(R.id.fragment_container, colorFragment);
        fragmentTransaction.commit();


    }

    @Override
    public void colorChange(String color_name) {

        if(color_name.equals("RED")){

            linearLayout.setBackgroundColor(Color.RED);

        }else if(color_name.equals("BLUE")){

            linearLayout.setBackgroundColor(Color.BLUE);

        }else if(color_name.equals("GREEN")){

            linearLayout.setBackgroundColor(Color.GREEN);
        }else{
            Toast.makeText(this, "No Color Selected.", Toast.LENGTH_SHORT).show();
        }
    }
}
