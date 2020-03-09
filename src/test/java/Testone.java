import com.freedom.myproject.utils.TestMail;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

public class Testone {
    @Test
    public void test(){
        TestMail.sendMail("xiaobai","w1787098791@163.com");
    }
    @Test
    public void test2(){
        String password="";
        Random random = new Random();
        for (int i=0;i<=5;i++){
            password+=random.nextInt(10);
        }
        System.out.println(password);
        System.out.println(password.length());
    }
    @Test
    public void test3() throws InterruptedException {
        String serverPath = String.format("%s://%s:%s%s%s", 1,2,3,4,5);
        System.out.println(serverPath);
        new Thread(){
            public  void run(){
                while(true){
                    System.out.println("233");
                }
            }
        }.join();
        System.out.println("end");

    }
    @Test
    public void test4() {
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid);
        String s = uuid.toString().replaceAll("-", "");
        System.out.println(s);
        System.out.println(s.length());

    }
    @Test
    public void test5() {
        File file = new File("./log");
        file.mkdirs();

    }


}
