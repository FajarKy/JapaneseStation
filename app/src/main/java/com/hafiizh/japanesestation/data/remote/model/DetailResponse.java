package com.hafiizh.japanesestation.data.remote.model;

import com.hafiizh.japanesestation.data.local.entity.DetailEntity;

/**
 * Created by HAFIIZH on 10/26/2017.
 * Author Havea Crenata
 * Email havea.crenata@gmail.com
 * Github https://github.com/crenata
 */

public class DetailResponse {
    private String status;
    private String total_page;
    private DetailEntity data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotal_page() {
        return total_page;
    }

    public void setTotal_page(String total_page) {
        this.total_page = total_page;
    }

    public DetailEntity getData() {
        return data;
    }

    public void setData(DetailEntity data) {
        this.data = data;
    }
}