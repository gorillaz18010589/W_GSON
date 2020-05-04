package com.example.w_gson;
//4.將Address的物件放入User資料結構
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Gson gson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gson = new Gson();
    }

    //1.將JavaBean轉成Json
    public void toJson(View view) {
        User user = new User("kidd","18","fioefei@gmail.com");
        String json = gson.toJson(user);
    }

    //2.將Json轉成JavaBean,//{"age":"18","email":"fioefei@gmail.com","name":"kidd"}
    public void fromJson(View view) {
        String json ="{\"age\":\"18\",\"email\":\"fioefei@gmail.com\",\"name\":\"kidd\"}";
        User user = gson.fromJson(json,User.class);
    }


    public void jsonTree(View view) {
        User user = new User("kidd","18","fioefei@gmail.com");
        JsonElement jsonElement = gson.toJsonTree(user);
    }

    //4.原來就有的User Bean加入新的Bean,並且轉成Json
    public void joinJsonObj(View view) {
        Address address = new Address("taiwan","taipei");
        User user = new User("kidd","18","fioefei@gmail.com",address);
        String json = gson.toJson(user);
    }

    //5.將User新加入address的Json String轉成 UserBean看資料
    public void joinJsonObjFromJson(View view) {
      //  { "address": { "city": "taipei", "country": "taiwan" },"age": "18", "email": "fioefei@gmail.com", "name": "kidd" }
        String json = "{\n" +
                "  \"address\": {\n" +
                "    \"city\": \"taipei\",\n" +
                "    \"country\": \"taiwan\"\n" +
                "  },\n" +
                "  \"age\": \"18\",\n" +
                "  \"email\": \"fioefei@gmail.com\",\n" +
                "  \"name\": \"kidd\"\n" +
                "}";

        User user = gson.fromJson(json,User.class);
    }


    //6.將ArrayList加入倒User轉型成Bean
    public void joinArrayListToJson(View view) {

        //準備加入address
        Address address = new Address("taiwan","taipei");

        //準備加入skillList => [{}]
        List<Skill> skillList = new ArrayList<>();
        skillList.add(new Skill("magic",1));
        skillList.add(new Skill("smart",2));

        //User新增了skillList
        User user = new User("kidd", "18", "fioefei@gmail.com", address, skillList);

        String json = gson.toJson(user,User.class);
    }

    //7.將List<Skill>的json轉回UserBean
    public void JsonToArryListBean(View view) {
//     json =  {"address":{"city":"taipei","country":"taiwan"},"age":"18","email":"fioefei@gmail.com","name":"kidd","skill":[{"nickname":"magic","nid":1},{"nickname":"smart","nid":2}]}
       String json ="{\n" +
               "  \"address\": {\n" +
               "    \"city\": \"taipei\",\n" +
               "    \"country\": \"taiwan\"\n" +
               "  },\n" +
               "  \"age\": \"18\",\n" +
               "  \"email\": \"fioefei@gmail.com\",\n" +
               "  \"name\": \"kidd\",\n" +
               "  \"skill\": [\n" +
               "    {\n" +
               "      \"nickname\": \"magic\",\n" +
               "      \"nid\": 1\n" +
               "    },\n" +
               "    {\n" +
               "      \"nickname\": \"smart\",\n" +
               "      \"nid\": 2\n" +
               "    }\n" +
               "  ]\n" +
               "}";

        User user = gson.fromJson(json,User.class);
    }

    //8.從User裡面抓取只抓Skill結構資料,存到SkillBean
    // json = "skill":[{"nickname":"magic","nid":1},{"nickname":"smart","nid":2}]
    public void listBeanFromJson(View view) {

        //A.舊方法是解析回到Skill[]
        String json ="[{\"nickname\":\"magic\",\"nid\":1},{\"nickname\":\"smart\",\"nid\":2}]";
        //.竟然是陣列結構,就用陣列類別回傳
//        Skill[] skills = gson.fromJson(json,Skill[].class);

        //B.用TypeToken.getType取得List<Skill>資料結構
        Type type = new TypeToken<List<Skill>>(){}.getType();
        List<Skill> skillList = gson.fromJson(json,type);
    }

    //9.exponse註解 1.serialize序列化,2.deserialize反序列化,transient短暫的不管序列化反序列化介紹
    public void exponse(View view) {
//        {"age":"20","id":1,"name":"lexux","pwd":"123"}

        //A.一般to Json序列化
        Car car = new Car("lexux","20","123",1);
        String json = gson.toJson(car);

        //B.要用exsponse序列化或反序列化方法
        //GsonBuilder.excludeFieldsWithoutExposeAnnotation()://將Gson配置排除沒有exponse的屬性,設定序列化,反序列化等(回傳GsonBuilder)
        Gson gson1 = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation() //將Gson配置排除沒有exponse的屬性,設定序列化,反序列化等
                .create();
        String json1 = gson1.toJson(car);
//        {"name":"lexux","pwd":"123"}
        String js = "{\"name\":\"lexux\",\"pwd\":\"123\"}";
        Car car1 = gson1.fromJson(js,Car.class);
    }
}
