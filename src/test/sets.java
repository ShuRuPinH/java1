package test;

import java.util.Comparator;
import java.util.TreeSet;

public class sets {

    class User {
        public Integer id;
        public String name;

        User(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public TreeSet<User> createSet() {
            TreeSet<User> uSet = new TreeSet<>(new Comparator<User>() {
                @Override
                public int compare(User o1, User o2) {
                    return Integer.compare(o2.id, o1.id);
                }

            });
            return uSet;
        }
    }
}