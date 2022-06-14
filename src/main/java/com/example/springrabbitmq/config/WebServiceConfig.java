package com.example.springrabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {
    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext){
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    @Bean(name="RabbitMq") //wsdlName
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema rabbitMqSchema){
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("RabbitMq");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://arasdenizhan.github.io/springRabbitMQ");
        wsdl11Definition.setSchema(rabbitMqSchema);
        return wsdl11Definition;
    }

    @Bean
    XsdSchema rabbitMqSchema(){
        return new SimpleXsdSchema(new ClassPathResource("springRabbitMQ.xsd"));
    }

    @Bean("senderQueue")
    public Queue senderQueue() {
        return new Queue("senderQueue", false);
    }

    @Bean("testQueue")
    public Queue testQueue(){
        return new Queue("testQueue", false);
    }
}
