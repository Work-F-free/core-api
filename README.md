# API for coworking booking system
## Запуск

Для запуска контейнера с api используется docker-compose. Из папки проекта необходимо выполнить команду
```sh
docker-compose -p bookcow up -d
```
После старта контейнера приложение должно быть доступно по адресу localhost:8080

swagger доступен по адресу http://localhost:8080/swagger-ui/index.html#/

В качестве сервера авторизации используется keycloak, доступный по адресу http://localhost:7080/admin/master/console/

При первом запуске его необходимо настроить импортировав конфигурации (файл dev-export.json) 