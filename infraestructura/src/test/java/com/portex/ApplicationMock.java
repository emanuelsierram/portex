package com.portex;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@ComponentScan("com.portex")
@Profile("test")
public class ApplicationMock {


    @Bean
    public DataSource dataSourceH2() {
        // Configura y crea una base de datos H2 en memoria
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .build();
    }

    @Bean(initMethod = "migrate")
    public Flyway flyway(DataSource dataSource) {
        // Configura Flyway para usar la base de datos H2 y los scripts de migración
        return Flyway.configure().locations("filesystem:../src/main/resources","filesystem:src/test/resources").baselineOnMigrate(true)
                .dataSource(dataSource).load();
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }
}
