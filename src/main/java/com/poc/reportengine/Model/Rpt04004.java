package com.poc.reportengine.Model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Rpt04004 {
    private String rpt_id;
    private String rpt_account_no;
    private String rpt_account_name;
    private Integer rpt_amount;
    private String rpt_status;
    private String rpt_create_date;
}
