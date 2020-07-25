package ru.progwards.java1.lessons.abstractnum;

 class Cube extends Figure3D {

    public Cube(Number segment) {
        super(segment);
        this.segment=segment;
    }

    @Override
    public Number volume() {
        Number temp = segment.mul(segment);
        return segment.mul(temp);

    }
}