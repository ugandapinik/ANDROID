package www.jewel_mahmud.com.fragment_activity_communication;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

/**
 * Created by wohhie on 10/1/2016.
 */

public class ColorFragment extends Fragment {

    OnColorChangeListener onColorChangeListener;


    RadioGroup colorGroup;
    RadioButton colorRed;
    RadioButton colorGreen;
    RadioButton colorBlue;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.color_fragment_layout, container, false);

        colorGroup = (RadioGroup) getView().findViewById(R.id.color_group);
        colorGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedID) {

                switch(checkedID){
                    case R.id.red_id:
                        onColorChangeListener.colorChange("RED");
                        break;
                    case R.id.geen_id:
                        onColorChangeListener.colorChange("GREEN");
                        break;


                    case R.id.blue_id:
                        onColorChangeListener.colorChange("BLUE");
                        break;
                }
            }
        });

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try{

            onColorChangeListener = (OnColorChangeListener) activity;

        }catch (Exception ex){
            ex.getMessage();
        }


    }

    public interface OnColorChangeListener{


        public void colorChange(String color_name);

    }
}
