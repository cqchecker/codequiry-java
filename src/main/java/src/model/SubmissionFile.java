package src.model;

public class SubmissionFile {
    private int id;
    private int submissionId;
    private String filedir;
    private String content;
    private String createdAt = null;
    private String updatedAt = null;
    private int languageId;

    public int getId() {
        return id;
    }

    public int getSubmissionId() {
        return submissionId;
    }

    public String getFiledir() {
        return filedir;
    }

    public String getContent() {
        return content;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public int getLanguageId() {
        return languageId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSubmissionId(int submissionId) {
        this.submissionId = submissionId;
    }

    public void setFiledir(String filedir) {
        this.filedir = filedir;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }
}