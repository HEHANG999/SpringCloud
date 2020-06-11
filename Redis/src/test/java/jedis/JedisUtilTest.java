package jedis;

import com.project.Main;
import com.project.util.RedisCacheUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
public class JedisUtilTest {


    @Autowired
    private RedisCacheUtil redisCacheUtil;


    @Test//Redis语法保存数据
    public void setString(){
        redisCacheUtil.set("user", "666");
    }

}
