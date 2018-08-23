import com.rensm.study.service.HelloService;
import com.rensm.study.service.HelloServiceImpl;
import com.rensm.study.service.HelloServiceProxy;
import org.junit.Test;

/**
 * Created by rensm on 2018/8/22.
 */
public class ProxyTest {

    public static void main(String[] args) {
        HelloService service = new HelloServiceImpl();
        //绑定代理对象。
        service = (HelloService) HelloServiceProxy.wrap(service, new Class[] {HelloService.class});
        //这里service经过绑定，就会进入invoke方法里面了。
        service.sayHello("张三");
    }

    @Test
    public void test2(){
        HelloService service = new HelloServiceImpl();
        service = (HelloService) HelloServiceProxy.wrap(service, new Class[] {HelloService.class});
        service = (HelloService) HelloServiceProxy.wrap(service, new Class[]{HelloService.class});
        service.sayHello("111");
    }
}
