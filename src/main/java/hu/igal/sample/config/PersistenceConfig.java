package hu.igal.sample.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaDialect;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
//@EnableJpaRepositories
@EnableTransactionManagement
@PropertySource("classpath:persistence.properties")
public class PersistenceConfig {

    @Inject
    private Environment env;

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setDriverClassName(env.getProperty("driverClassName"));
        dataSource.setUrl(env.getProperty("url"));
        dataSource.setUsername(env.getProperty("username"));
        dataSource.setPassword(env.getProperty("password"));
        return dataSource;

    }

    @Bean
    public JpaDialect jpaDialect() {
        return new EclipseLinkJpaDialect();
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        EclipseLinkJpaVendorAdapter vendorAdapter = new EclipseLinkJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        Map<String, String> jpaProperties = new HashMap<String, String>();
        jpaProperties.put("eclipselink.weaving", "false");
        jpaProperties.put("jpaDialect", " org.springframework.orm.jpa.vendor.EclipseLinkJpaDialect");
        factory.setJpaPropertyMap(jpaProperties);
        factory.setPackagesToScan("hu.igal.sample.persistence.entity");
        factory.setDataSource(dataSource());
        factory.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
        factory.setJpaDialect(jpaDialect());

        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory());
        return txManager;
    }
}
