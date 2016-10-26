package www.jewel_mahmud.com.androidloginregistration;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by wohhie on 10/2/2016.
 */

public class SingleTon {
    private static SingleTon instance;
    private RequestQueue requestQueue;
    private static Context myContext;

    public RequestQueue getRequestQueue(){
        if (requestQueue == null){
            requestQueue = Volley.newRequestQueue(myContext.getApplicationContext());
        }

        return  requestQueue;
    }

    private SingleTon(Context context){
        this.myContext = context;
        requestQueue = getRequestQueue();
    }


    public static synchronized SingleTon getInstance(Context context){
        if(instance == null){
            instance = new SingleTon(context);
        }

        return  instance;
    }

    public<T> void addToRequestQueue(Request<T> request){
        requestQueue.add(request);
    }
}


