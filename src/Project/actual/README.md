###  Утилита для внедрения совместных изменений одного файла.
Внедрение изменений возможны, если редакции затрагивают различные участки кода и не затронуты якоря 3 строки до измененного кода и 3 после, в противном будем выдана ошибка.
Создана в рамках курсового проекта.

Название файла  | Содержание файла
----------------|----------------------
Diff.java       | Сравнивает 1ю редакцию файла с оригиналом, и создает патч-файл отражающий изменения (*.patch)
Patch.java      | Применяет изменения 1ой редакции записанные в патч-файле к отличной от нее (2ой) редации оригинального файла, в случае конфликта редакций сообщает об ошибке. Выдает  файл - результат применения обеих редакций.

### Руководство пользователя

#### Diff  - создание патч файла.
Программа запрашивает в консоли пути сравниваемых файлов.
1. "** Input 1st file (original) path:" - путь ОРИГИНАЛЬНОГО файла.
2. "** Input 2nd file (1st programer edition) path:" - путь ПЕРВОЙ редакции оригинального файла.
3. При отсутвии ошибок, будет создан патч-файл с именем: ОРИГИНАЛЬНЫЙ_ФАЙЛ+ТЕКУЩАЯ-ДАТА.patch 

#### Patch  - внедрение патча.
1. "** Input 1st file (2nd programer edition) path:" - путь ВТОРОЙ редакции файла.
2. "** Input PATCH file (*.patch) path:" - путь ПАТЧ файла.
3. При отсутствии ошибок, будет создан файл с именем: NEW_+ИМЯ_ФАЙЛА_2йРЕДАКЦИИ 


### ПРИМЕР РАБОТЫ УТИЛИТЫ  * файлы в папке /TestFiles
изменения  в разных частях файлов, добавление в конец файла, "якори" не затронуты  /TestFiles/TEST1
![1st example](http://easy4you.ru/pic/1stEXM.png)