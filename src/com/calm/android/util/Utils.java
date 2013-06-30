package com.calm.android.util;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.CursorLoader;
import android.webkit.MimeTypeMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by yoni on 6/29/13.
 */
public class Utils {

    protected static final String FILES_UPLOAD_URL = "http://biuninja2013.appspot.com/images";
    public static final String PREFERENCES = "preferences";
    public static final String ACCOUNT_NAME = "account name";


    public static String postFileToServer(File file, String mime) throws IOException, HttpHostConnectException {
        HttpPost httpPost = new HttpPost(FILES_UPLOAD_URL);
        MultipartEntity entity = new MultipartEntity();
        FileBody fileBody = new FileBody(file, mime);
        entity.addPart("imageFile", fileBody);
        httpPost.setEntity(entity);
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse response;
        response = httpClient.execute(httpPost);
        return getResponseData(response);
    }

    public static String getResponseData(HttpResponse response) {
        BufferedReader bufferedReader = null;
        try {
            InputStream is = response.getEntity().getContent();
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            bufferedReader = new BufferedReader(isr);
            StringBuffer stringBuffer = new StringBuffer("");
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }
            bufferedReader.close();
            String data = stringBuffer.toString();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static String getRealPathFromURI(Context context, Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        CursorLoader loader = new CursorLoader(context, contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }


    public static String getMimeType(String path, Context context) {
        String extention = path.substring(path.lastIndexOf(".") );
        String mimeTypeMap = MimeTypeMap.getFileExtensionFromUrl(extention);
        String mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(mimeTypeMap);
        return mimeType;
    }
}

