package org.app;

public class Registro {

    private String name;
    private String gps;
    private double pm101h;
    private String pm10;


    public Registro() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public double getPm101h() {
        return pm101h;
    }

    public void setPm101h(double pm101h) {
        this.pm101h = pm101h;
    }

    public String getPm10() {
        return pm10;
    }

    public void setPm10(String pm10) {
        this.pm10 = pm10;
    }

    public String toString() {
        return String.format("%s, %s, %s, %s", this.name, this.gps, this.pm101h, this.pm10);
    }
}
