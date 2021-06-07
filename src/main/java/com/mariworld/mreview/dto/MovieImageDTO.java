package com.mariworld.mreview.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URLEncoder;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieImageDTO {

    private String uuid;
    private String imgName;
    private String path;

    public String getImageURL(){
        String uri="";
        try{
            uri  = URLEncoder.encode(path+ "/"+uuid+"_"+imgName ,"UTF-8");
        }catch (Exception e){
            e.printStackTrace();
        }
        return uri;
    }

    public String getThumbnailURL(){
        String uri="";
        try{
            uri  = URLEncoder.encode(path+ "/s_"+uuid+"_"+imgName ,"UTF-8");
        }catch (Exception e){
            e.printStackTrace();
        }
        return uri;
    }
}
