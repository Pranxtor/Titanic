package com.example.titanic;

public class iceberg {
    float x,y;
    static int nbIceberg;
    int id;

    public iceberg(float x){
        nbIceberg++;
        id=nbIceberg;
        x=this.x;
    }
    public int getID(){
        return id;
    }
    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }

    public void setX(float x){
        this.x=x;
    }
    public void setY(float y){
        this.y=y;
    }

    @Override
    public int hashCode(){
        return id;
    }
}
