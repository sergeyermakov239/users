package sergey.ermakov.project1;

public class User {
    private String name;
    private int age;



    public User(String name1,int age1){
        name=name1;
        age=age1;
    };

    public int getAge() {
        return age;
    };

    public String getName() {
        return name;
    };

    public void setAge(int age) {
        this.age = age;
    };

    public void setName(String name) {
        this.name = name;
    };
    public String print(){
        String s="["+name+","+age+"] ";
         return  s;
    };
}
