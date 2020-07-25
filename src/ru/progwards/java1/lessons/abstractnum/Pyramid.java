package ru.progwards.java1.lessons.abstractnum;

class Pyramid extends Figure3D {

    public Pyramid(Number segment) {
        super(segment);
    }

    @Override
    public Number volume() {
        Number temp = segment.mul(segment);
        Number temp2=temp.mul(segment);
        return temp2.div(temp.newNumber(String.valueOf(3)));

    }
}