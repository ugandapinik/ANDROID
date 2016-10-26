package www.jewel_mahmud.com.json_object_request;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by wohhie on 10/1/2016.
 */

public class SingleTon {
    private static SingleTon instance;
    private RequestQueue requestQueue;
    private static Context thisContext;


    private SingleTon(Context context){
        thisContext = context;
        requestQueue = getRequestQueue();
    }

    public RequestQueue getRequestQueue(){
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(thisContext.getApplicationContext());
        }

        return  requestQueue;
    }

    public void addToRequestQueue(Request request){
        requestQueue.add(request);
    }

    public static synchronized SingleTon getInstance(Context context){
        if (instance == null){
            instance = new SingleTon(context);
        }

        return instance;
    }


}
