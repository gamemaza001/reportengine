package com.poc.reportengine.Repository;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Repository
public class ReportRepository {

    @Autowired
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public JasperPrint exportPdfFile(String id) throws SQLException, JRException, IOException {
        Connection conn = jdbcTemplate.getDataSource().getConnection();
        ClassPathResource cpr = new ClassPathResource("RPT_04004_PDF.jrxml");
        ClassPathResource cprLogo = new ClassPathResource("Horizontail-Banner.png");
        JasperReport jasperReport = JasperCompileManager.compileReport(cpr.getInputStream());

        Map<String, Object> paramaters = new HashMap<String, Object>();
        paramaters.put("logo", cprLogo.getPath());

        JasperPrint print = JasperFillManager.fillReport(jasperReport, paramaters, conn);
        return print;
    }
}
