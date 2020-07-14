package com.poc.reportengine.Service;

import com.poc.reportengine.Repository.ReportRepository;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

@Service
@Slf4j
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    public void exportPdfFile(Integer id, OutputStream outputStream) throws SQLException, JRException, IOException {
        JasperPrint jasperPrint = reportRepository.exportPdfFile(id);
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
        //File pdf = File.createTempFile("test2.", ".pdf");
        //JasperExportManager.exportReportToPdfStream(jasperPrint, new FileOutputStream(pdf));
        //uploadFileTos3bucket(pdf.getName(),pdf);
    }

}
