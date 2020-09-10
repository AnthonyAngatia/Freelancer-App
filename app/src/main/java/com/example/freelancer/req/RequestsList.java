package com.example.freelancer.req;

public class RequestsList {

    private int projectId;
    private String projectStatus;
    private String projectReview;
    private String projectDescription;
    private double projectPrice;
    private String projectDeliveryTime;
    private String projectProgress;
    private int appuser_inviter_id;
    private int appuser_freelancer_id;

    private String projectItemRequestorName;
    private String projectItemRequestorLocation;
    private String projectItemRequestorPhone;

    public RequestsList(int projectId, String projectStatus, String projectReview, String projectDescription, double projectPrice, String projectDeliveryTime, String projectProgress, int appuser_inviter_id, int appuser_freelancer_id, String projectItemRequestorName, String projectItemRequestorLocation, String projectItemRequestorPhone) {
        this.projectId = projectId;
        this.projectStatus = projectStatus;
        this.projectReview = projectReview;
        this.projectDescription = projectDescription;
        this.projectPrice = projectPrice;
        this.projectDeliveryTime = projectDeliveryTime;
        this.projectProgress = projectProgress;
        this.appuser_inviter_id = appuser_inviter_id;
        this.appuser_freelancer_id = appuser_freelancer_id;

        this.projectItemRequestorName = projectItemRequestorName;
        this.projectItemRequestorLocation = projectItemRequestorLocation;
        this.projectItemRequestorPhone = projectItemRequestorPhone;
    }




    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }

    public String getProjectReview() {
        return projectReview;
    }

    public void setProjectReview(String projectReview) {
        this.projectReview = projectReview;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public Double getProjectPrice() {
        return projectPrice;
    }

    public void setProjectPrice(Double projectPrice) {
        this.projectPrice = projectPrice;
    }

    public String getProjectDeliveryTime() {
        return projectDeliveryTime;
    }

    public void setProjectDeliveryTime(String projectDeliveryTime) {
        this.projectDeliveryTime = projectDeliveryTime;
    }

    public int getAppuser_inviter_id() {
        return appuser_inviter_id;
    }

    public void setAppuser_inviter_id(int appuser_inviter_id) {
        this.appuser_inviter_id = appuser_inviter_id;
    }

    public int getAppuser_freelancer_id() {
        return appuser_freelancer_id;
    }

    public void setAppuser_freelancer_id(int appuser_freelancer_id) {
        this.appuser_freelancer_id = appuser_freelancer_id;
    }

    //others
    public String getProjectItemRequestorName() {
        return projectItemRequestorName;
    }

    public void setProjectItemRequestorName(String projectItemRequestorName) {
        this.projectItemRequestorName = projectItemRequestorName;
    }

    public String getProjectItemRequestorLocation() {
        return projectItemRequestorLocation;
    }

    public void setProjectItemRequestorLocation(String projectItemRequestorLocation) {
        this.projectItemRequestorLocation = projectItemRequestorLocation;
    }

    public String getProjectItemRequestorPhone() {
        return projectItemRequestorPhone;
    }

    public void setProjectItemRequestorPhone(String projectItemRequestorPhone) {
        this.projectItemRequestorPhone = projectItemRequestorPhone;
    }

    public void setProjectPrice(double projectPrice) {
        this.projectPrice = projectPrice;
    }

    public String getProjectProgress() {
        return projectProgress;
    }

    public void setProjectProgress(String projectProgress) {
        this.projectProgress = projectProgress;
    }

}