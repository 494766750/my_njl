package my.entity;

import java.io.File;
import java.net.URI;
import java.util.List;

/**
 * 2022/2/21
 * NJL
 */
public class Files extends File {
    
    public Files(String pathname) {
        super(pathname);
    }
    
    public Files(String parent, String child) {
        super(parent, child);
    }
    
    public Files(File parent, String child) {
        super(parent, child);
    }
    
    public Files(URI uri) {
        super(uri);
    }

    String name;
    
    
    List<Files> fileSons;
    
    @Override
    public String getName() {
        return super.getName();
    }
    
    public void setName() {
        this.name = super.getName();
    }
    
    public List<Files> getFileSons() {
        return fileSons;
    }
    
    public void setFileSons(List<Files> fileSons) {
        this.fileSons = fileSons;
    }
}
