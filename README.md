# attendance-statistics
### Информация о приложении
Это REST-сервис по сбору статистики посещаемости WEB-сайта.\
Программа принимает:
- POST-запросы с маппингом */counter*
  - Параметры:
    - *id* - идентификатор пользователя
    - *page* - идентификатор страницы сайта
  - Пример:
    `/counter?page=index.html&id=123`
  - Ответ содержит поля:
    - *dailyHits* - общее количество посещений за текущие сутки
    - *uniqueUsers* - количество уникальных пользователей за текущие сутки
- GET-запросы с маппингом */stats* для получения статистики посещаемости за заданный период.
  - Параметры:
    - *from* - начало периода
    - *till* - конец периода
  - Пример:
  `/stats?from=2019-01-01T00:00&till=2019-12-12T00:00`
  - Ответ:
    - *overallHits* - общее количество посещений за указанный период
    - *uniqueUsers* - количество уникальных пользователей за указанный период
    - *regularUsers* - количество постоянных пользователей за указанный период (пользователей, которые за период просмотрели не менее 10 различных страниц)
    
### Сборка
Текущая версия работает только с СУБД PostgreSQL.

1. В файле *src/main/resources/application.properties* необходимо задать параметры соединения:
  - *spring.datasource.url* - URL базы данных
  - *spring.datasource.username* - имя пользователя
  - *spring.datasource.password* - пароль
  
  Пример:\
  ![Datasource settings example](/images/datasource.jpg)
  
2. Из корневого каталога приложения вызвать команду:
`mvn clean install` (должен быть установлен Apache Maven)

3. Из подкаталога *target* теперь можно извлечь файл *attendance-statistics.war* и развернуть в контейнере сервлетов (например Apache Tomcat) в соответствии с инструкцией.

### Отправка запросов
Пример POST-запроса:\
![POST request example](/images/post-example.jpg)

Пример GET-запроса:\
![GET request example](/images/get-example.jpg)