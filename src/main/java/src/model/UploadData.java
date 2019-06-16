package src.model;

import java.time.LocalDateTime;
import java.util.List;

public class UploadData {
    private float id;
    private String filename;
    private float statusId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String result1;
    private String result2;
    private String result3;
    private String totalResult;
    private LocalDateTime modifyUpdatedAt;
    private List<AssignmentStatus> assignmentStatuses;

    public float getId() {
        return id;
    }

    public void setId(float id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public float getStatusId() {
        return statusId;
    }

    public void setStatusId(float statusId) {
        this.statusId = statusId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getResult1() {
        return result1;
    }

    public void setResult1(String result1) {
        this.result1 = result1;
    }

    public String getResult2() {
        return result2;
    }

    public void setResult2(String result2) {
        this.result2 = result2;
    }

    public String getResult3() {
        return result3;
    }

    public void setResult3(String result3) {
        this.result3 = result3;
    }

    public String getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(String totalResult) {
        this.totalResult = totalResult;
    }

    public LocalDateTime getModifyUpdatedAt() {
        return modifyUpdatedAt;
    }

    public void setModifyUpdatedAt(LocalDateTime modifyUpdatedAt) {
        this.modifyUpdatedAt = modifyUpdatedAt;
    }

    public List<AssignmentStatus> getAssignmentStatuses() {
        return assignmentStatuses;
    }

    public void setAssignmentStatuses(List<AssignmentStatus> assignmentStatuses) {
        this.assignmentStatuses = assignmentStatuses;
    }
}