#ex-prod


Необходима база данных - PostgreSQL.

Для инициализации с дефолтными параметрами (не для продакшена) - создать базу данных exprod и выполнить закомментированные 
в engine/db/migration/V*_init.sql строчки.

Для запуска необходимо прописать Environment Variables DB_HOST, DB_PORT, DB_NAME, DB_USER, DB_PASSWORD.
```
DB_HOST=localhost;DB_PORT=5432;DB_NAME=exprod;DB_USER=exprod;DB_PASSWORD=exprod
```

После первого запуска на продакшене необходимо сменить пароль пользователю admin в базе данных (bcrypt 13 rounds).
