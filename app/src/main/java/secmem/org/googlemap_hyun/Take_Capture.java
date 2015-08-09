package secmem.org.googlemap_hyun;

import android.graphics.Bitmap;
import android.os.Environment;
import android.view.View;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by HyunJae on 2015-08-10.
 * 사용법
 *    View 파라미터에는 Layout을 넣어주면 Layout이 찍인다
 *   Take_Capture.getInstance().takeScreenshot(linearLayout);
 *
 *
 *    전체 화면을 찍기 위해서는
 *    View rootView = findViewById(android.R.id.content).getRootView();
 *    Take_Capture.getInstance().takeScreenshot(rootView);
 *
 *    를 실행시킨다.
 */


public class Take_Capture {
    private static Take_Capture instance =null;
    public static Take_Capture getInstance(){
        if(instance==null){
            instance = new Take_Capture();
        }
        return instance;
    }

    private Take_Capture(){}


    public void takeScreenshot(View view) {

        view.setDrawingCacheEnabled(true);
        saveBitmap(view.getDrawingCache());

    }

    public void saveBitmap(Bitmap bitmap) {

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_hhmmss");

        String namePostfix = format.format(new Date());

        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath() + "/Beenzido";
        File file = new File(path);

        if(!file.exists()){
            file.mkdirs();
        }


        File imagePath = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath() + "/Beenzido/beenzido_"+ namePostfix +".png");


        FileOutputStream fos;
        try {
            fos = new FileOutputStream(imagePath);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
    }

}
