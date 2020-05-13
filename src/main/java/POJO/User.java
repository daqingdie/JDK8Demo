package POJO;

import lombok.ToString;

@ToString
public class User {
    private int id;
    private String name;
    private int age;

    public User(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static User.UserBuilder builder() {
        return new UserBuilder();
    }

    /**
     * 尝试写user的builder构造
     */
    public static  class UserBuilder{
        private int id;
        private String name;
        private int age;

        public UserBuilder(){}

        public User.UserBuilder id(int id) {
            this.id = id;
            return this;
        }

        public User.UserBuilder name(String name) {
            this.name = name;
            return this;
        }
        public User.UserBuilder age(int age) {
            this.age = age;
            return this;
        }

        public User build(){
            return new User(id, name, age);
        }

    }
}
