package com.poc.reportengine.Repository;

import com.poc.reportengine.Model.Rpt04004;
import com.poc.reportengine.Repository.Mapper.Rpt04004Mapper;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

@Slf4j
@Repository
public class ReportRepository {

    @Autowired
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public JasperPrint exportPdfFile(Integer id) throws SQLException, JRException, IOException {
        Connection conn = jdbcTemplate.getDataSource().getConnection();
        //String path = resourceLoader.getResource("classpath:PersonTest.jrxml").getURI().getPath();
        ClassPathResource cpr = new ClassPathResource("RPT_04004_PDF.jrxml");
        ClassPathResource cprLogo = new ClassPathResource("Horizontail-Banner.png");
        JasperReport jasperReport = JasperCompileManager.compileReport(cpr.getInputStream());
        List<Rpt04004> rpt04004s = getDataById(id);
        JRDataSource datasource = new JRBeanCollectionDataSource(rpt04004s);
        Map<String, Object> paramaters = new HashMap<String, Object>();
        paramaters.put("logo", cprLogo.getPath());

        JasperPrint print = JasperFillManager.fillReport(jasperReport, paramaters, datasource);
        return print;
    }

    public List<Rpt04004> getDataById(Integer id) {
        StringJoiner sql = new StringJoiner(" ");
        sql.add("select rpt_id,rpt_account_no,rpt_account_name,rpt_amount,rpt_status,rpt_create_date from report_data")
                .add(" where rpt_id = :rptId");
        HashMap<String, Object> params = new HashMap<>();
        params.put("rptId", id);
        return namedParameterJdbcTemplate.query(sql.toString(), params, new Rpt04004Mapper());
    }
}
