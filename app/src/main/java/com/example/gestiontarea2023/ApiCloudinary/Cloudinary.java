package com.example.gestiontarea2023.ApiCloudinary;


import android.content.Context;
import android.widget.Toast;

import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.cloudinary.android.callback.UploadResult;

import java.util.HashMap;
import java.util.Map;

public class Cloudinary {

    private MediaManager mediaManager;
    Context context;
    public Cloudinary(Context context){
        this.context = context;
        Map config = new HashMap();
        config.put("cloud_name", "dauz6sio9");
        config.put("api_key", "983257745251619");
        config.put("api_secret", "9H-o4xiO55gT-78W4Zi1YbH3zlY");
        config.put("secure", true);
        MediaManager.init(this.context, config);
    }

    public void UploadFile(){
        MediaManager.get().upload("").dispatch();
    }
}
