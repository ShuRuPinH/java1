package ru.progwards.java1.lessons.abstractnum;

abstract class Figure3D {
    Number segment;

    public Figure3D(Number segment) {
        this.segment = segment;

    }

    ;

    public abstract Number volume();
//  nested classes
    static class Cube extends Figure3D {

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

    static class Pyramid extends Figure3D {

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

}
