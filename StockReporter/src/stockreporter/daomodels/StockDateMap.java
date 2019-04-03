/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockreporter.daomodels;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * Model class to hold stock StockDateMap data
 */
public class StockDateMap{
    private long id;
    private String date;
    private long tickerId;
    private long sourceId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getTickerId() {
        return tickerId;
    }

    public void setTickerId(long tickerId) {
        this.tickerId = tickerId;
    }

    public long getSourceId() {
        return sourceId;
    }

    public void setSourceId(long sourceId) {
        this.sourceId = sourceId;
    }
}