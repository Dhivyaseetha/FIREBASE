package nfnlabs.mtx1.firebaseintegration.FirebaseDatabase.json_model;

/**
 * Created by acer on 7/6/2016.
 */
public class UserInfo {

   public String name;
    public String age;

    public UserInfo(){

    }

    public UserInfo(String name, String age) {
        this.name = name;
        this.age = age;
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
}
