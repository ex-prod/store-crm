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

1. Добавить в таблицу unit_order_state идентификаторы статусов заказов для позиций new и confirmed. 
   Идентификаторы можно посмотреть по URL https://online.moysklad.ru/api/remap/1.2/entity/metadata?filter=type=customerorder с учеткой этого юнита
   
   