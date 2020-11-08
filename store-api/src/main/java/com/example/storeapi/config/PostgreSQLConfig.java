package com.example.storeapi.config;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostgreSQLConfig {

    @Bean(name = "datasource")
    public PGSimpleDataSource getDatasource() {

        PGSimpleDataSource ds = new PGSimpleDataSource() ;
        ds.setServerName( "db" );
        ds.setDatabaseName( "store" );
        ds.setUser( "postgres" );
        ds.setPassword( "12345678" );

        return ds;
    }
}
