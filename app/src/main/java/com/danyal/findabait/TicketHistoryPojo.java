package com.danyal.findabait;

public class TicketHistoryPojo {

    private int ID;
    private String name;
    private String logo;
    private String status;
    private String serviceRequestNo;
    private String state;


    public int getID() {
        return ID;
    }
    public void setID (int ID)
    {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }
    public void setName (String name)
    {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }
    public void setLogo (String logo)
    {
        this.logo = logo;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus (String status)
    {
        this.status = status;
    }

    public String getServiceRequestNo() {
        return serviceRequestNo;
    }

    public void setServiceRequestNo (String serviceRequestNo)
    {
        this.serviceRequestNo = serviceRequestNo;
    }


    public String getState() {
        return state;
    }

    public void setState (String state)
    {
        this.state = state;
    }


}
