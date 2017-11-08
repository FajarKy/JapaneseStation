package com.hafiizh.japanesestation.data.remote.model;

import com.hafiizh.japanesestation.data.local.entity.LatestEntity;

import java.util.List;

/**
 * Created by HAFIIZH on 10/23/2017.
 * Author Havea Crenata
 * Email havea.crenata@gmail.com
 * Github https://github.com/crenata
 */

public class LatestResponse {
    private String status;
    private String total_page;
    private List<LatestEntity> data;

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

    public List<LatestEntity> getData() {
        return data;
    }

    public void setData(List<LatestEntity> data) {
        this.data = data;
    }
}