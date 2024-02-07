package com.portaexperiencia.portex.infraestructura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@Profile("desarrollo")
public class DatabaseInitializer implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DatabaseInitializer(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        executeSqlScript("schema.sql");
    }

    private void executeSqlScript(String scriptPath) throws IOException {
        ClassPathResource resource = new ClassPathResource(scriptPath);
        byte[] scriptBytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
        String scriptContent = new String(scriptBytes, StandardCharsets.UTF_8);
        jdbcTemplate.execute(scriptContent);
    }
}


