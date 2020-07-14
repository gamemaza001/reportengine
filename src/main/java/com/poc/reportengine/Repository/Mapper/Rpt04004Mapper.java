package com.poc.reportengine.Repository.Mapper;

import com.poc.reportengine.Model.Rpt04004;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Rpt04004Mapper implements RowMapper<Rpt04004> {
    @Override
    public Rpt04004 mapRow(ResultSet resultSet, int i) throws SQLException {
        Rpt04004 rpt04004 = new Rpt04004();
        rpt04004.setRpt_id(resultSet.getString("rpt_id"));
        rpt04004.setRpt_account_no(resultSet.getString("rpt_account_no"));
        rpt04004.setRpt_account_name(resultSet.getString("rpt_account_name"));
        rpt04004.setRpt_amount(resultSet.getInt("rpt_amount"));
        rpt04004.setRpt_status(resultSet.getString("rpt_status"));
        rpt04004.setRpt_create_date(resultSet.getString("rpt_create_date"));
        return rpt04004;
    }
}
