import Stream.StreamUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 包括：Filter（过滤）、Map(映射)、sort(排序）等，
 * 集合数据通过Java Stream管道处理之后，转化为另一组集合或数据输出。
 */
public class StreamTest {
    private List<String> movies = StreamUtil.init();

    @Test
    /**
     * filter方法联系
     * 将名字大于三个字的电影过滤出来
     */
    public void testFilter(){

        //传统方法循环
        List<String> newMovies = new LinkedList<>();
        for (String movie : movies) {
            if(movie.length()>3)
                newMovies.add(movie);
        }
        StreamUtil.printList(newMovies);

        //Stream流的filter方法
        StreamUtil.printList(movies.stream()
                .filter(m->m.length()>3)        //不满足条件的会被过滤掉
                .collect(Collectors.toList()));  //重新返回List);
    }

    @Test
    /**
     * map方法练习
     * 在每个名字后面添加"大电影"
     */
    public void testMap() {
        //传统方法
        List<String> newMovies = new ArrayList<>();
        for (String movie : movies) {
            movie = movie + "大电影";
            newMovies.add(movie);
        }
        StreamUtil.printList(newMovies);


        //map方法
        StreamUtil.printList(movies.stream()
                .map(m->m+"大电影")    //每次将返回的对象作为新值替代原对象
                .collect(Collectors.toList())
        );
    }

}
