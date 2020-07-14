package com.poc.reportengine.Controller;

import com.poc.reportengine.Service.ReportService;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

@RestController
@Slf4j
@RequestMapping("/v1")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/hello")
    public String helloWord() {
        return "Hello World";
    }

    @GetMapping("/rpt04004-download/{id}")
    public void personJasperById(HttpServletResponse response, @PathVariable Integer id)
            throws IOException, JRException, SQLException {
        response.setContentType("application/x-download");
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"RPT_04004.pdf\""));
        OutputStream out = response.getOutputStream();
        reportService.exportPdfFile(id, out);
    }

    @GetMapping("/rpt04004-view/{id}")
    public void personJasperByIdView(HttpServletResponse response, @PathVariable Integer id)
            throws IOException, JRException, SQLException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", String.format("inline; filename=\"RPT_04004.pdf\""));
        OutputStream out = response.getOutputStream();
        reportService.exportPdfFile(id, out);
    }

}
