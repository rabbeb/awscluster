package aws.cluster.config;

import java.util.Properties;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

@Configuration
@PropertySource("classpath:/data.properties")
public class HibernateConfig {
    private final static Logger logger = LoggerFactory.getLogger(HibernateConfig.class);
    @Autowired
    private DataSource datasource;
    @Value("${hibernate.dialect}")
    private String hibernateDialect;
    @Value("${hibernate.current_session_context_class}")
    private String hibernateCurrentSessionContextClass;
    @Value("${hibernate.show_sql}")
    private String hibernateShowSql;

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        logger.info("configuring hibernate session factory");
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(datasource);
        sessionFactory.setAnnotatedPackages("aws.cluster.order.entity");
        Properties props = new Properties();
        props.put("hibernate.dialect", hibernateDialect);
        props.put("hibernate.current_session_context_class", hibernateCurrentSessionContextClass);
        props.put("hibernate.show_sql", hibernateShowSql);
        logger.info("configured hibernate session factory");
        return sessionFactory;
    }
}
