package app;

import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.loopj.android.http.RequestParams;

import java.io.File;

import es.dmoral.toasty.Toasty;

public class app {

    public static class main{
        public static final String TAG = "MyArt";
        public static final String URL = "http://10.0.2.2/myArt/";
    }

    public static void L(String message){
        Log.e(main.TAG,message);
    }
    public static void T(String message, ToastType toastType){
        switch (toastType){
            case ERROR:    Toasty.error(Application.getContext()   , message , Toast.LENGTH_LONG).show();break;
            case WARNING:  Toasty.warning(Application.getContext() , message , Toast.LENGTH_LONG).show(); break;
            case SUCCESS:  Toasty.success(Application.getContext() , message , Toast.LENGTH_LONG).show(); break;
            case NORMAL:   Toasty.normal(Application.getContext()  , message , Toast.LENGTH_LONG).show(); break;
            case INFO:     Toasty.info(Application.getContext()    , message , Toast.LENGTH_LONG).show(); break;
        }
    }

    public static RequestParams getRequestParams(){
        RequestParams params = new RequestParams();
        params.put(ROUTER.SESSION , spref.getOurInstance().getString(ROUTER.SESSION , ""));
        params.put(ROUTER.USER_ID , spref.getOurInstance().getInt(ROUTER.USER_ID , -1) + "");

        return params;
    }

    public static void animateError(View view){
        YoYo.with(Techniques.Wobble).duration(900).playOn(view);
    }


    public static File Uri2File(Uri uri){

        String [] items = {MediaStore.Images.Media.DATA} ;
        Cursor cursor = Application.getContext().getContentResolver().query(uri , items ,null , null ,null);

        if (cursor == null){
            return null;
        }
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String s = cursor.getString(column_index);
        cursor.close();
        return new File(s);


    }


}
