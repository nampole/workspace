package sample1;
import org.springframework.beans.factory.*;

public class MessageBeanImpl implements MessageBean, BeanNameAware, BeanFactoryAware, 
InitializingBean, DisposableBean {
    
    private String greeting;
    private String beanName;
    private BeanFactory beanFactory;
    
    public MessageBeanImpl() {
        System.out.println("① Bean의 생성자 실행");
    }
    
    public void setGreeting(String greeting) {
        this.greeting = greeting;
        System.out.println("② 세터 메서드 실행");
    }
    
    public void setBeanName(String beanName) {
        System.out.println("③ Bean명 지정");
        this.beanName = beanName;
        System.out.println(" -> " + beanName);
    }
    
    public void setBeanFactory(BeanFactory beanFactory) {
        System.out.println("④ BeanFactory 지정");
        this.beanFactory = beanFactory;
        System.out.println(" -> " +beanFactory.getClass());
    }
    
    public void init() {
        System.out.println("⑦ 초기화 메서드 실행");
    }
    
    public void destroy() {
        System.out.println("종료");
    }
    
    public void afterPropertiesSet() {
        System.out.println("⑥ 프로퍼티 지정 완료");
    }
    
    public void sayHello() {
        System.out.println(greeting + beanName + "!");
    }
}