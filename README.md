#ex-prod

## Запуск сервиса

Необходима база данных - PostgreSQL.

Для инициализации с дефолтными параметрами (не для продакшена) - создать базу данных exprod и выполнить закомментированные 
в engine/db/migration/V*_init.sql строчки.

Для запуска необходимо прописать Environment Variables DB_HOST, DB_PORT, DB_NAME, DB_USER, DB_PASSWORD, PROFILE.
```
DB_HOST=localhost;DB_PORT=5432;DB_NAME=exprod;DB_USER=exprod;DB_PASSWORD=exprod;PROFILE=local
```

Дефолтная учетка для доступа к API после первого запуска - admin:admin

После первого запуска на продакшене необходимо сменить пароль пользователю admin в базе данных (по дефолту bcrypt 13 rounds).

Изначально база инициализируется для использования на тестовом юните (юнит - магазин)

## Добавление пользователя
1. добавить в таблицу manager 
    * firstname, lastname, email - как есть
    * password - зашифрованный через bcrypt пароль (rounds 13) - https://www.browserling.com/tools/bcrypt
    
1. добавить в таблицу manager_has_role необходимую для нового менеджера роль из таблицы role.

## Создание нового юнита

1. Прописать в таблицу unit новый данные нового юнита - название и token для доступа по API. 
   Токен лучше брать от отдельного API-аккаунта, чтоб избежать случайного его сбрасывания. Брать здесь: Настройки -> Токены -> + Токен.
   Аккаунт лучше не админский.
   
1. Добавить в таблицу role продажную роль для этого юнита

1. Добавить в таблицу role_has_unit связь роли с юнитом (как минимум для продажника и для админа)

1. Добавить в таблицу unit_moysklad_id:
   - идентификаторы статусов заказов для позиций new и confirmed https://online.moysklad.ru/api/remap/1.2/entity/metadata?filter=type=customerorder
   - идентификаторы полей заказа https://online.moysklad.ru/api/remap/1.2/entity/customerorder/metadata/attributes
   - идентификатор клиента по-умолчанию https://online.moysklad.ru/api/remap/1.2/entity/counterparty
   - идентификатор организации по-умолчанию https://online.moysklad.ru/api/remap/1.2/entity/organization
   - идентификатор склада по-умолчанию https://online.moysklad.ru/api/remap/1.2/entity/store
   
      
   
## Установка сервера под CI/CD:
это примерный флоу (не проверялся с нуля)

***todo: автоматизировать это флоу***

1. установить Docker
1. создать пользователя, добавить его в группу docker. 
   прописать креденшиалы пользователя в secrets в github согласно .github/workflows/cidi.yml. 
   установить aws и сконфигурить (aws configure)
   создать папку ~/images и дать на нее права на запись
1. запустить где-то postgres-базу, прописать необходимые настройки в env-файл ~/store-crm.env
1. если база запускается на том же серваке то прописать единую docker-сеть для контейнеров
1. поднять тут же контейнер с nginx, натравить домен media на images, api на сервис и основной домен на фронт
