package sample1;

import org.springframework.beans.factory.config.*;

public class CustomBeanPostProcessor implements BeanPostProcessor {
    
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        System.out.println("�� �ʱ�ȭ �� Bean�� ���� ó�� ����");
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) {
        System.out.println("�� �ʱ�ȭ �� Bean�� ���� ó�� ����");
        return bean;
    }        
}