package com.JavaSemProj;

enum IsEatable {
    EATABLE(80), NOT_EATABLE(70);
    private double percentage;

    private IsEatable(double percentage) {
        this.percentage = percentage;
    }

    public double getPercentage() { return this.percentage; }
    public void setPercentage(double percentage) { this.percentage = percentage; }
}
