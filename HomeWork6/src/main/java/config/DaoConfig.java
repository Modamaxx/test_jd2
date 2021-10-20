package config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import storage.Hibernate.HQLDepartmentStorage;
import storage.Hibernate.HQLEmployerStorage;
import storage.Hibernate.HQLPositionStorage;
import storage.SQL.EmployerStorage;
import storage.api.IDepartmentStorage;
import storage.api.IEmployerStorage;
import storage.api.IPositionStorage;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Configuration
public class DaoConfig {
    @Bean
    public IEmployerStorage employerStorage(SessionFactory sessionFactory){
        return new HQLEmployerStorage(sessionFactory);
    }

    @Bean
    public IDepartmentStorage departmentStorage(SessionFactory sessionFactory){
        return new HQLDepartmentStorage(sessionFactory);
    }

    @Bean
    public IPositionStorage positionStorage(SessionFactory sessionFactory){
        return new HQLPositionStorage(sessionFactory);
    }

    @Bean
    public SessionFactory sessionFactory(DataSource dataSource){
        LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource);
        builder.scanPackages("model");
        builder.setProperty("hibernate.show_sql", "true");
        builder.setProperty("hibernate.default_schema", "application");
        builder.setProperty("hibernate.hbm2ddl.auto", "update");
        builder.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL10Dialect");
        return builder.buildSessionFactory();
    }

    @Bean
    public DataSource dataSource(){
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        try {
            cpds.setDriverClass( "org.postgresql.Driver" ); //loads the jdbc driver
        } catch (PropertyVetoException e) {
            throw new RuntimeException("Ошибка загрузки драйвера" ,e);
        }
        cpds.setJdbcUrl( "jdbc:postgresql://localhost:5432/crm" );
        cpds.setUser("postgres");
        cpds.setPassword("eputet91");
        return cpds;
    }
}
