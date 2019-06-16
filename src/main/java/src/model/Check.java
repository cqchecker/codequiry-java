package src.model;

public class Check {
    private float id;
    private String name;
    private String createdAt;
    private String updatedAt;
    private int statusId;
    private int jobId;

    public float getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public float getStatusId() {
        return statusId;
    }

    public int getJobId() {
        return jobId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }
}