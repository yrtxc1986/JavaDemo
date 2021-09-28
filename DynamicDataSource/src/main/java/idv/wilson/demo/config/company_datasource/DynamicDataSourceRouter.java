package idv.wilson.demo.config.company_datasource;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSourceRouter extends AbstractRoutingDataSource {

	private Map<Object, Object> _targetDataSources;

	@Override
	protected Object determineCurrentLookupKey() {
		return DataSourceContextHolder.getDataSource();
	}

	@Override
	public void setTargetDataSources(Map<Object, Object> targetDataSources) {
		this._targetDataSources = targetDataSources;
		super.setTargetDataSources(this._targetDataSources);
		super.afterPropertiesSet();
	}

	public void addTargetDataSource(String key, DataSource dataSource) {
		this._targetDataSources.put(key, dataSource);
		this.setTargetDataSources(this._targetDataSources);
	}
}
