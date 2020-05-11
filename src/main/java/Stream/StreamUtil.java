package Stream;

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

    public static <T> void printList(List<T> list){
        for (T t : list) {
            System.out.println(t);
        }
    }

}
