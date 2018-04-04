package sample1;
import org.springframework.beans.factory.*;

public class MessageBeanImpl implements MessageBean, BeanNameAware, BeanFactoryAware, 
InitializingBean, DisposableBean {
    
    private String greeting;
    private String beanName;
    private BeanFactory beanFactory;
    
    public MessageBeanImpl() {
        System.out.println("�� Bean�� ������ ����");
    }
    
    public void setGreeting(String greeting) {
        this.greeting = greeting;
        System.out.println("�� ���� �޼��� ����");
    }
    
    public void setBeanName(String beanName) {
        System.out.println("�� Bean�� ����");
        this.beanName = beanName;
        System.out.println(" -> " + beanName);
    }
    
    public void setBeanFactory(BeanFactory beanFactory) {
        System.out.println("�� BeanFactory ����");
        this.beanFactory = beanFactory;
        System.out.println(" -> " +beanFactory.getClass());
    }
    
    public void init() {
        System.out.println("�� �ʱ�ȭ �޼��� ����");
    }
    
    public void destroy() {
        System.out.println("����");
    }
    
    public void afterPropertiesSet() {
        System.out.println("�� ������Ƽ ���� �Ϸ�");
    }
    
    public void sayHello() {
        System.out.println(greeting + beanName + "!");
    }
}