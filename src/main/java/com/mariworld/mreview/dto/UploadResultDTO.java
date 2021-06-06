package com.mariworld.mreview.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.File;
import java.io.Serializable;
import java.net.URLEncoder;

@Data
@AllArgsConstructor
public class UploadResultDTO implements Serializable {
    private String uuid;
    private String fileName;
    private String folderPath;

    public String getImageURL(){
        String uri="";
        try{
            uri  = URLEncoder.encode(folderPath+ "/"+uuid+"_"+fileName ,"UTF-8");
        }catch (Exception e){
            e.printStackTrace();
        }
        return uri;
    }

    public String getThumbnailURL(){
        String uri="";
        try{
            uri  = URLEncoder.encode(folderPath+ "/s_"+uuid+"_"+fileName ,"UTF-8");
        }catch (Exception e){
            e.printStackTrace();
        }
        return uri;
    }

}
