package ru.progwards.java2.lessons.graph;

import java.util.ArrayList;
import java.util.List;

public class FindUnused {


    /*
    * Задача поиска неиспользуемых объектов

Метод public static List<CObject> find(List<CObject> roots, List<CObject> objects)
- List<CObject> roots -  список корневых объектов
- List<CObject> objects - список всех объектов системы
- возвращает список неиспользуемых объектов

class CObject {
     public List<CObject> references; // ссылки на другие объекты
      int mark;  // пометка для алгоритма
                      // 0 - не используется
                      // 1 - посещен
                      // 2 - используется
    *
    * */

/*

НеИспользуемыеОбъекты():
для v из корневые
если цвет[v] == белый то
DFS(v)
 */


    public static List<CObject> find(List<CObject> roots, List<CObject> objects) {
        List<CObject> rez = new ArrayList<>();
        for (CObject co : objects) {
            co.mark = 0;
        }


        for (CObject co : roots) {
            if (co.mark == 0) dFS(co);
        }

        for (CObject co : objects) {
            if (co.mark == 0) rez.add(co);
        }

        return rez;
    }

    /*
    DSF(v):
    цвет[v] = серый;
    для u из v
    если цвет[u] == белый то
    DFS(u)
    цвет[v] = черный
    вернуть истина
     */
    private static void dFS(CObject co) {
        co.mark = 1;
        for (CObject coD : co.references) {
            if (coD.mark == 0) {
                dFS(coD);
            }
        }
        co.mark = 2;
        return;

    }

    public static void main(String[] args) {

    }
}
