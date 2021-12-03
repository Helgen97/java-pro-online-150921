package com.task.siteCheck.urlStatus;

public class SiteStatus {
    private String siteStatus;

    public SiteStatus(int code) {
        if (code == 200) {
            siteStatus = "Site available";
        } else {
            siteStatus = "Site shutdown";
        }
    }

    public String getSiteStatus() {
        return siteStatus;
    }

    public void setSiteStatus(String siteStatus) {
        this.siteStatus = siteStatus;
    }
}
