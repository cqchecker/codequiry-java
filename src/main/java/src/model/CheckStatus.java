package src.model;

public class CheckStatus {
    private Check check;
    private String status;
    private boolean dbcheck;
    private boolean webcheck;
    private int submissionCount;
    private String dashURL;

    public Check getCheck() {
        return check;
    }

    public String getStatus() {
        return status;
    }

    public boolean getDbcheck() {
        return dbcheck;
    }

    public boolean getWebcheck() {
        return webcheck;
    }

    public int getSubmissionCount() {
        return submissionCount;
    }

    public String getDashURL() {
        return dashURL;
    }

    public void setCheck(Check check) {
        this.check = check;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDbcheck(boolean dbcheck) {
        this.dbcheck = dbcheck;
    }

    public void setWebcheck(boolean webcheck) {
        this.webcheck = webcheck;
    }

    public void setSubmissionCount(int submissionCount) {
        this.submissionCount = submissionCount;
    }

    public void setDashURL(String dashURL) {
        this.dashURL = dashURL;
    }
}