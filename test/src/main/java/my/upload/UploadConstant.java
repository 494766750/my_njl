package my.upload;

/**
 * 2022/2/21
 * NJL
 *
 *   00 文件大小为0
 *   01 文件存在,不需要重新上传
 *   02 临时文件存在,竖线后是文件大小
 *   03 文件存在,但是md5不同
 *
 *   04 上传后重命名失败
 *   05 上传后MD5不一致
 *   06 上传成功,MD5一致,重命名正常
 *
 */
public class UploadConstant {
    
    public static final String E00 = "00";//文件大小为0
    public static final String E01 = "01";//文件存在,不需要重新上传
    public static final String E02 = "02";//临时文件存在,竖线后是文件大小
    public static final String E03 = "03";//文件存在,但是md5不同
    
    public static final String E04 = "04";//上传后重命名失败
    public static final String E05 = "05";//上传后MD5不一致
    public static final String E06 = "06";//上传成功,MD5一致,重命名正常
    
    public static final String CHARSET = "gbk";//文本编码
    
}
