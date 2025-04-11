package com.employeeManagement.entity;

public class HealthCheckResponse {
    private String appId;
    private String version;
    private String status;

    public HealthCheckResponse(String appId,String version,String status){
        this.appId=appId;
        this.version=version;
        this.status=status;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
