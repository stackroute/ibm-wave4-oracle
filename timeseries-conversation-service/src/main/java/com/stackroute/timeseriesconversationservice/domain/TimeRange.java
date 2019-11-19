package com.stackroute.timeseriesconversationservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TimeRange {
    private String userName;
    private String fromDate;
    private String toDate;
}
