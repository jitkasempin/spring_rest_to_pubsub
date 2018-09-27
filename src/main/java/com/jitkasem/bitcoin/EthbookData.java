package com.jitkasem.bitcoin;

import java.time.LocalDateTime;

public class EthbookData {

    private Long id;
    private Float ethPri;
    private LocalDateTime timeRecord;

    public EthbookData(Long id, Float ethPri, LocalDateTime timeRecord) {
        this.id = id;
        this.ethPri = ethPri;
        this.timeRecord = timeRecord;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getEthPri() {
        return ethPri;
    }

    public void setEthPri(Float ethPri) {
        this.ethPri = ethPri;
    }

    public LocalDateTime getTimeRecord() {
        return timeRecord;
    }

    public void setTimeRecord(LocalDateTime timeRecord) {
        this.timeRecord = timeRecord;
    }


}
