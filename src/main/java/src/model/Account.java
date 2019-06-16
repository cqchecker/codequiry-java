package src.model;

public class Account {
    private String user;
    private String email;
    private String peerChecksRemaining;
    private String proChecksRemaining;
    private int submissions;

    public String getUser() {
        return user;
    }

    public String getEmail() {
        return email;
    }

    public String getPeerChecksRemaining() {
        return peerChecksRemaining;
    }

    public String getProChecksRemaining() {
        return proChecksRemaining;
    }

    public int getSubmissions() {
        return submissions;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPeerChecksRemaining(String peerChecksRemaining) {
        this.peerChecksRemaining = peerChecksRemaining;
    }

    public void setProChecksRemaining(String proChecksRemaining) {
        this.proChecksRemaining = proChecksRemaining;
    }

    public void setSubmissions(int submissions) {
        this.submissions = submissions;
    }
}