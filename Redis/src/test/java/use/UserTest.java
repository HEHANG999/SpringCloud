package use;
import com.project.Main;
import com.project.bean.UserBean;
import com.project.servcie.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
public class UserTest {


    @Autowired
    private IUserService service;


    @Test
    public void findByID(){
        UserBean userBean = service.findById(1);
        System.out.println(userBean);
    }

}
