package my.utils.map4;


import my.entity.Files;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 2022/2/22
 * NJL
 */
public class FilesUtil {
    public static List<Files> getFiles2(String path) {
        File file = new File(path);
        File[] tempList = file.listFiles();
        List<Files> filesList = new ArrayList<>();
        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                filesList.add(new Files(tempList[i].toString()));
            }
            if (tempList[i].isDirectory()) {
                Files f =  getFileSons(new Files(tempList[i].toString()));
                filesList.add(f);
            }
        }
        return filesList;
    }
    
    public static Files getFileSons(Files files){
        List<Files> filesList = new ArrayList<>();
        File[] tempList = files.listFiles();
        for(int i = 0; i < tempList.length; i++) {
            String url = tempList[i].toString();
            Files file = new Files(tempList[i].toString());
            if (file.isDirectory()) {
                file.setFileSons(getFiles2(url));
            }
            filesList.add(file);
        }
        files.setFileSons(filesList);
        return files;
    }
}
