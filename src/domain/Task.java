package domain;

public class Task {
    private int id;
    private Status status;
    private String denumire;
    private String utilizator;

    public Task(int id, Status status, String denumire, String utilizator) {
        this.id = id;
        this.status = status;
        this.denumire = denumire;
        this.utilizator = utilizator;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getUtilizator() {
        return utilizator;
    }

    public void setUtilizator(String utilizator) {
        this.utilizator = utilizator;
    }
}
