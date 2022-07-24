package top.b0x0.demo.domain;

import java.io.Serializable;

/**
 * 文件实体类
 *
 * @author TANG
 */
public class FileEntity implements Serializable {
    private int id;
    private byte[] img;
    /**
     * 图片路径
     */
    private String path;


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

}
