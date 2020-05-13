import POJO.User;
import Stream.StreamUtil;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Test
    /**
     * 将不同类型转换为Stream
     *
     */
    public void toStream() throws Exception {
        //将数组转换为Stream
        Integer[] nums = new Integer[]{12, 45, 365, 456, 12,};
        Stream<Integer> stream = Stream.of(nums);  //使用Stream.of方法
        Stream<String> stream1 = Stream.of("你好","不好","还好");  //或直接用多个对象生成


        //将集合对象转换为Stream,调用集合的.stream方法
        Stream stream2 = movies.stream();

        //将文本文件转换为管道流
        //Stream<String> lines = Files.lines(Paths.get("file.txt"));
    }

    @Test
    /**
     * 测试stream的sort方法
     */
    public void testSort() {
        List<User> users = StreamUtil.initUser();
        //先根据id正序再根据年龄正序
        users.stream().sorted(
                Comparator.comparingInt(User::getId)
                .thenComparingInt(User::getAge))
                .forEach(u-> System.out.println(u));
        System.out.println("---------------------------------------------------");

        //先根据id正序再根据年龄倒序(特别注意reverse会对上方所有比较都生效,所以第一个id被倒两次变正,年龄倒一次)
        users.stream().sorted(
                Comparator.comparingInt(User::getId)
                        .reversed()
                        .thenComparingInt(User::getAge)
                        .reversed()
        ).forEach(u-> System.out.println(u));;
        System.out.println("---------------------------------------------------");

        //先根据id倒序再根据年龄正序
        users.stream().sorted(
                Comparator.comparingInt(User::getId)
                        .reversed()
                        .thenComparingInt(User::getAge)
        ).forEach(u-> System.out.println(u));;
        System.out.println("---------------------------------------------------");

        //先根据id倒序再根据年龄倒序
        users.stream().sorted(
                Comparator.comparingInt(User::getId)
                        .thenComparingInt(User::getAge)
                        .reversed()
        ).forEach(u-> System.out.println(u));;

    }

}
