package com.example.hw1.activities;

public class Img {
    private int res = 0;
    private boolean isPrize = false;

    public Img() { }

    public int getRes() {
        return res;
    }

    public Img setRes(int res) {
        this.res = res;
        return this;
    }

    public boolean isPrize() {
        return isPrize;
    }

    public Img setPrize(boolean prize) {
        isPrize = prize;
        return this;
    }

    @Override
    public String toString() {
        return "Img{" +
                "res=" + res +
                ", isPrize=" + isPrize +
                '}';
    }
}
