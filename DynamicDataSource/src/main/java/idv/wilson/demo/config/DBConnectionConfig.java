package idv.wilson.demo.config;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import idv.wilson.demo.config.company_datasource.DynamicDataSourceRouter;

@Configuration
@EnableJpaRepositories("idv.wilson.demo")
@EnableTransactionManagement
public class DBConnectionConfig {

	@Autowired
	SQLConfig sql;
	@Autowired
	JPAConfig jpa;

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {

		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setDatabase(Database.valueOf(jpa.database));
		jpaVendorAdapter.setShowSql(jpa.showSql);
		jpaVendorAdapter.setGenerateDdl(jpa.generateDdl);
		jpaVendorAdapter.setDatabasePlatform(jpa.databasePlatform);
		jpaVendorAdapter.setGenerateDdl(false);
		return jpaVendorAdapter;
	}

	@Primary
	@Bean(value = "ParaDM")
	public DataSource adminPortalDataSource() {
		BasicDataSource ds = new BasicDataSource();

		ds.setUrl(sql.url);
		ds.setUsername(sql.username);
		ds.setPassword(sql.password);

		return ds;
	}

	// 配置实体管理器工厂
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(AbstractRoutingDataSource dataSource,
			JpaVendorAdapter jpaVendorAdapter) {
		LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
		// 注入数据源
		emfb.setDataSource(dataSource);
		// 注入jpa厂商适配器
		emfb.setJpaVendorAdapter(jpaVendorAdapter);
		// 设置扫描基本包
		emfb.setPackagesToScan("idv.wilson.demo.admin_portal", "idv.wilson.demo.dms");
		return emfb;
	}

	// 配置jpa事务管理器
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		// 配置实体管理器工厂
		transactionManager.setEntityManagerFactory(emf);
		return transactionManager;
	}

	@Bean(name = "routingDataSource")
	public AbstractRoutingDataSource routingDataSource(DataSource adminPortalDataSource) {
		DynamicDataSourceRouter proxy = new DynamicDataSourceRouter();
		Map<Object, Object> targetDataSources = new HashMap<>(2);
		targetDataSources.put("masterDataSource", adminPortalDataSource);

		proxy.setDefaultTargetDataSource(adminPortalDataSource);
		proxy.setTargetDataSources(targetDataSources);
		return proxy;
	}
}
