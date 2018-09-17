package data.volive.mydata;

public class DataModel {
    String name;

    public DataModel(String first) {
        this.name=first;
    }

    public DataModel() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    byte[] img;
}
