package www.jewel_mahmud.com.volley_singleton_pattern;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by wohhie on 10/1/2016.
 */

public class MySingleton {

    private static MySingleton instance;
    private RequestQueue requestQueue;
    private static Context singletonContext;

    private MySingleton(Context context){
        singletonContext = context;
        requestQueue = getRequestQueue();
    }

    public void addToRequestQueue(Request request){
        requestQueue.add(request);
    }

    public RequestQueue getRequestQueue(){
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(singletonContext.getApplicationContext());
        }

        return requestQueue;
    }


    public static synchronized MySingleton getInstance(Context context){

        if(singletonContext == null){
            instance = new MySingleton(context);
        }

        return instance;
    }


}
