package www.jewel_mahmud.com.volley_request_image;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by wohhie on 10/1/2016.
 */

public class SingletonImage {

    private static SingletonImage instance;
    private RequestQueue requestQueue;
    private static Context thisContext;

    private SingletonImage(Context context){
        thisContext = context;
        requestQueue = getRequestQueue();
    }

    public RequestQueue getRequestQueue() {
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(thisContext.getApplicationContext());
        }

        return requestQueue;
    }

    public void addToRequestQueue(Request request) {
        requestQueue.add(request);
    }

    public static synchronized SingletonImage getInstance(Context context){
        if(thisContext == null){
            instance = new SingletonImage(context);
        }

        return instance;
    }

}
