# Readme Spint_7
Данный учебный проект выполнен для отработки практических навыков в процессе учебы в Яндекс.Практикум на программе "Автоматизация тестирования на Java"

## Тестируемый сервис https://qa-scooter.praktikum-services.ru/
Его документация https://qa-scooter.praktikum-services.ru/docs/
По условию задания необходимо было протестировать api сервиса с использованием JUnit 4, RestAssured, Allure

## Используемые технологии
Java11
Maven
JUnit 4.13.2
Rest Assured
Allure(Отчет загружен в репозиторий. Сгенерировать новый отчет можно при помощи команды "mvn clean test")

## Автотесты написаны по чек-листу
Создание курьера:
-- курьера можно создать;

-- нельзя создать двух одинаковых курьеров;

-- чтобы создать курьера, нужно передать в ручку все обязательные поля;

-- запрос возвращает правильный код ответа;

-- успешный запрос возвращает ok: true;

-- если одного из полей нет, запрос возвращает ошибку;

-- если создать пользователя с логином, который уже есть, возвращается ошибка.

Авторизация курьера:
-- курьер может авторизоваться;

-- для авторизации нужно передать все обязательные поля;

-- система вернёт ошибку, если неправильно указать логин или пароль;

-- если какого-то поля нет, запрос возвращает ошибку;

-- если авторизоваться под несуществующим пользователем, запрос возвращает ошибку;

-- успешный запрос возвращает id.

Создание заказа:
-- можно указать один из цветов — BLACK или GREY;

-- можно указать оба цвета;

-- можно совсем не указывать цвет;

-- тело ответа содержит track.

Список заказов:
-- проверить, что в тело ответа возвращается список заказов.

