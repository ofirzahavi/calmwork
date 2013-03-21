package com.calm.android.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.calm.android.R;
import roboguice.inject.InjectView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yoni
 * Date: 3/20/13
 * Time: 5:49 AM
 * To change this template use File | Settings | File Templates.
 */
public class NewWorkActivity extends CalmActivity {




    @InjectView(R.id.camera_picture)
    private ImageView mImageView;
    private String mCurrentPhotoPath;


    @Override
    protected int getLayoutId() {
        return R.layout.upload_exercise;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        mCurrentPhotoPath = intent.getStringExtra("photoPath");
        setPic();

        
    }



    private void setPic() {
        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath);
        mImageView.setImageBitmap(bitmap);

    }
}