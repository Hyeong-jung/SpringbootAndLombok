package com.springboot.lombok.property;
import org.springframework.boot.context.properties.ConfigurationProperties;


// 프라퍼티 파일 file 경로 설정
@ConfigurationProperties(prefix="file")
public class FileUploadProperties {
	
    private String uploadDir;
    
    public String getUploadDir() {
        return uploadDir;
    }
 
    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }	

}
