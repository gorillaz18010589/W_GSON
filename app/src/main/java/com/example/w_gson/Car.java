package com.example.w_gson;
//https://docs.google.com/document/d/1IOXfHmrMGYKZmioh0Wilew3ItpjO4XV0eyxJDhd2R8c/edit#heading=h.je48h895g8vf
import com.google.gson.annotations.Expose;
/**/
public class Car {
    //有Expose,會轉成bean
    @Expose
    private String name;

    //序列化設定為false時,將不會轉成Json
    @Expose(serialize = false)
    private String age;

    //反序列化設定為,false時,當從jons轉成bean時將不會被反序列化所以null
    @Expose(deserialize = false)
    private String pwd;

    //短暫的序列反序列化都忽視
    private transient int id;

    public Car(String name, String age, String pwd, int id) {
        this.name = name;
        this.age = age;
        this.pwd = pwd;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
