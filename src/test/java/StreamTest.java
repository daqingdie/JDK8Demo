import POJO.User;
import Stream.StreamUtil;
import org.junit.Test;

import javax.sound.midi.Soundbank;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.*;
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
    public void testFilter() {

        //传统方法循环
        List<String> newMovies = new LinkedList<>();
        for (String movie : movies) {
            if (movie.length() > 3)
                newMovies.add(movie);
        }
        StreamUtil.printList(newMovies);

        //Stream流的filter方法
        StreamUtil.printList(movies.stream()
                .filter(m -> m.length() > 3)        //不满足条件的会被过滤掉
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
                .map(m -> m + "大电影")    //每次将返回的对象作为新值替代原对象
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
        Stream<String> stream1 = Stream.of("你好", "不好", "还好");  //或直接用多个对象生成


        //将集合对象转换为Stream,调用集合的.stream方法
        Stream stream2 = movies.stream();

        //将文本文件转换为管道流
        //Stream<String> lines = Files.lines(Paths.get("file.txt"));
    }

    @Test
    /**
     * 测试stream的sorted方法
     */
    public void testSort() {
        List<User> users = StreamUtil.initUser();
        //先根据id正序再根据年龄正序
        users.stream().sorted(
                Comparator.comparingInt(User::getId)
                        .thenComparingInt(User::getAge))
                .forEach(u -> System.out.println(u));
        System.out.println("---------------------------------------------------");

        //先根据id正序再根据年龄倒序(特别注意reverse会对上方所有比较都生效,所以第一个id被倒两次变正,年龄倒一次)
        users.stream().sorted(
                Comparator.comparingInt(User::getId)
                        .reversed()
                        .thenComparingInt(User::getAge)
                        .reversed()
        ).forEach(u -> System.out.println(u));
        ;
        System.out.println("---------------------------------------------------");

        //先根据id倒序再根据年龄正序
        users.stream().sorted(
                Comparator.comparingInt(User::getId)
                        .reversed()
                        .thenComparingInt(User::getAge)
        ).forEach(u -> System.out.println(u));
        ;
        System.out.println("---------------------------------------------------");

        //先根据id倒序再根据年龄倒序
        users.stream().sorted(
                Comparator.comparingInt(User::getId)
                        .thenComparingInt(User::getAge)
                        .reversed()
        ).forEach(u -> System.out.println(u));
        ;

    }


    @Test
    /**
     * 学习map与flatMap的区别
     */
    public void testMapAndFloatMap() {
        //当遇到二位数组,即管道中还能有管道,
        // 当想要输出顺序输出所有单个属性时,仅用map会有局限,得不到想要的结果

        List<String> data = StreamUtil.init();

        //使用map方法
        data.stream().map(d -> Arrays.stream(d.split(""))).forEach(System.out::println);

        //使用floatMap方法,会将二级流全部平铺
        data.stream().flatMap(d -> Arrays.stream(d.split("")) //将拆分后的字符串数组转为流返回
        ).forEach(System.out::println);
    }


    @Test
    /**
     * 学习map,peek,forEach的区别
     */
    public void testMapAndPeekAndForEach() {
        List<String> data = StreamUtil.init();

        //使用map
        data.stream().map(d -> d);  //map中的回调方法必须有返回值,可以是传入的对象或其他类型,所以也能拿来作转类型方法

        //使用peek
        data.stream().peek(d -> System.out.println("哈哈哈")); //peek的回调函数无需返回值,可以用来遍历输出等

        //使用forEach
        data.stream().forEach(d -> System.out.println("哈哈哈")); //类似peek,但是peek是返回流,可以进行下一步操作,
        // fooEach没有返回值,会直接结束流,两者看情况使用
    }

    @Test
    /**
     * 学习stream中match(匹配)相关函数的使用
     */
    public void testMatch() {
        //匹配有点类似filter,但是返回结果是一个布尔值,filter是直接把符合规则的过滤出来新流
        List<User> users = StreamUtil.initUser();

        Stream<User> stream = users.stream();

        StreamUtil.printList(users);

        //是否所有用户的年龄都大于20,使用allMatch
        System.out.println(stream.allMatch(u -> u.getAge() > 20));

        //一个流经历一系列操作之后会自动关闭,所有像上面一样先初始化再使用,下面再使用就会报流已关闭的错误,须注意

        //是否有用户年龄大于20,任意一个使用anyMatch
        System.out.println(users.stream().anyMatch(u -> u.getAge() > 20));

        //是否不存在用户年龄小于10岁,不存在使用noneMatch,有点像anyMatch的对立面
        System.out.println(users.stream().noneMatch(u -> u.getAge() < 10));


    }

    @Test
    /**
     * 学习Stream中的与查找功能相关的find函数
     * 学习Optional类的使用
     */
    public void TestFind() {
        //可以先用filter对流进行过滤

        List<User> users = StreamUtil.initUser();

        //查找年龄大于20岁的第一个用户,使用findFirst,返回的是一个Optional<T>的对象
        Optional<User> user = users.stream().filter(u -> u.getAge() > 20).findFirst();

        //Optional相对直接返回实体更加灵活,比如使用get()获取里面保存的对象,若为空则报错

        //可以先判断是否存在对象
        if(user.isPresent()){
            //可以自定义如果存在对象则进行什么操作
            user.ifPresent(o-> System.out.println("存在对象进行的操作"+o));
        }

        //可以给对象进行默认值初始化,避免为空时的错误,当无值则使用默认值
        System.out.println(user.orElse(new User(0, "默认名", 88)));

        //有点类似findFirst,findAny并不是随机地选一个，
        // 如果是数据较少，串行地情况下，一般会返回第一个结果，如果是并行的情况，那就不能确保是第一个。
        Optional<User> optionalUser = users.stream().filter(u -> u.getAge() > 20).findAny();
        System.out.println(optionalUser.get());


    }


    @Test
    /**
     * 学习Stream中有关集合归约的内容(reduce)
     */
    public void testReduce() {
        //计算所以用户的年龄和
        List<User> users = StreamUtil.initUser();

        //reduce参数格式为(初始值,累加器,合并器),累加器和合并器都是一个函数型接口
        //函数型接口的意思是只有一个实现方法的接口,在调用时可以直接用lambda表达式使用
        //累加器的参数格式为(上一次的结果(或初始值),本次对象),合并器类似
        Integer sum1 = users.stream().map(User::getAge).reduce(0, (last, now) -> last + now);
        System.out.println(sum1);

        //合并器主要用在并联流时的数据合并,在数据量大时可以提高性能
        Integer sum2 = users.parallelStream().map(User::getAge).reduce(0, Integer::sum, Integer::sum);
        System.out.println(sum2);

        //合并器也可以在串行时对数据流进行一个数据转换
        Integer sum3 = users.stream().reduce(0, (sum, u) -> sum + u.getAge(), Integer::sum);
        System.out.println(sum3);
    }

}
