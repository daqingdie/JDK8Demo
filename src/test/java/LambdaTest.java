import Lambda.LambdaImpl;
import Lambda.LambdaInterface;
import Lambda.LambdaUsed;
import POJO.LombokUser;
import POJO.User;
import org.junit.Test;

import javax.swing.*;

public class LambdaTest {

    @Test
    /**
     * 测试实体类的Builder原理
     */
    public void testBuilder() {
        //手写所有方法
        User user = User.builder().id(1).name("小强").build();

        System.out.println(user.getName());
        System.out.println(user.getId());
        System.out.println(user.getAge());


        //使用lombok
        LombokUser lu=LombokUser.builder().id(1).name("小强").build();
        System.out.println(lu);

    }

    @Test
    /**
     * lambda表达式与传统对比
     */
    public void testLambda1() {

        String message = "你好吗";
        //传统写实现类再一步步创建调用
        LambdaInterface li = new LambdaImpl();
        LambdaUsed.printMessage(message,li);

        //匿名内部类写法
        LambdaUsed.printMessage(message, new LambdaInterface() {
            public void print(String message) {
                System.out.println(message);
            }
        });

        //lambda写法,(参数)->{方法体},当参数只有一个,或方法体一行时,可省略()或{}
        //当接口只有一个方法时,也可以直接用lambda表达式返回
        LambdaUsed.printMessage(message,m-> System.out.println(m));
    }
}
