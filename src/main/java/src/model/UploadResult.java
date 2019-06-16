package src.model;

import java.util.List;

public class UploadResult {
    private List<UploadData> data;
    private Check check;
    private String file;
    private int submissionCount;

    public List<UploadData> getData() {
        return data;
    }

    public void setData(List<UploadData> data) {
        this.data = data;
    }

    public Check getCheck() {
        return check;
    }

    public void setCheck(Check check) {
        this.check = check;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public int getSubmissionCount() {
        return submissionCount;
    }

    public void setSubmissionCount(int submissionCount) {
        this.submissionCount = submissionCount;
    }
}
