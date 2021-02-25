### Согласно условию задачи
Отправляется письмо  с файлом Calc.rename, где написано, как пользоваться калькулятором и что полученный Calc.rename нужно переименовать в Calc.jar
Это для того чтобы  система безопасности почтового сервера, не обращала на него внимание.

В pom указываем необходимые настройки почтового сервиса и

<emailTo>shurupin@gmail.com</emailTo>
<emailFrom>shu_ru_pin@mail.ru</emailFrom>
<authServ>smtp.mail.ru</authServ>
<authUser>shu_ru_pin@mail.ru</authUser>
<authPass>****</authPass>



