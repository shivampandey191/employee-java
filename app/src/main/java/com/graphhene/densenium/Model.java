package com.graphhene.densenium;

public class Model {
    private int lottie;
    private String title;

    public Model(int lottie, String title, String desc) {
        this.lottie = lottie;
        this.title = title;
        this.desc = desc;
    }

    private String desc;

    public int getLottie() {
        return lottie;
    }

    public void setLottie(int lottie) {
        this.lottie = lottie;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
