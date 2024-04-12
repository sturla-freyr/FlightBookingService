package com.example;

public interface Service {
    String serviceName = "";
    Double servicePrice = 0.0;
    String serviceDescription = "";

    public String getServiceName();
    public void setServiceName(String sn);
    public Double getServicePrice();
    public void setServicePrice(Double sp);
    public String getServiceDescription();
    public void setServiceDescription(String sd);
}
