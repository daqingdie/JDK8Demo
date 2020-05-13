package Stream;

import POJO.User;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class StreamUtil {

    public static  List<String>  init(){
        List<String> movies = new LinkedList<>();
        movies.add("海贼王");
        movies.add("火影忍者");
        movies.add("哆啦A梦");
        movies.add("蜡笔小新");
        return movies;
    }

    public static  List<User> initUser(){
        List<User> users = new ArrayList<>();
        users.add(new User(1, "小王", 18));
        users.add(new User(1, "小王八", 15));
        users.add(new User(2, "小强", 22));
        users.add(new User(3, "小白", 16));
        users.add(new User(4, "小黑", 38));
        users.add(new User(5, "小绿", 22));
        return users;
    }

    public static <T> void printList(List<T> list){
        for (T t : list) {
            System.out.println(t);
        }
    }

}
