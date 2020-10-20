package com.yz.utils;

public class FileUtils {
    public static String getExtName(String filename){
        String[] split = filename.split("\\.");
        if (split.length==1){
            return "";
        }else{
            int splitLength=split.length;
            return "."+split[splitLength-1];
        }
    }
}
