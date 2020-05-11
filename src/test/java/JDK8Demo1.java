import POJO.User;
import org.junit.Test;

public class JDK8Demo1 {

    @Test
    /**
     * 测试实体类的Builder原理
     */
    public void testBuilder() {
        User user = User.builder().id(1).name("小强").build();

        System.out.println(user.getName());
        System.out.println(user.getId());
        System.out.println(user.getAge());

    }
}
