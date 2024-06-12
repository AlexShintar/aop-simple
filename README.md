# Реализация системы учета времени выполнения методов

## Описание:

Разработать систему учета времени выполнения методов в приложении с использованием Spring AOP.
Система должна быть способна асинхронно логировать и анализировать данные о времени выполнения методов.

## Требования:

1. Создайте аннотации @TrackTime и @TrackAsyncTime, которые можно применять к методам для отслеживания времени их выполнения.
2. Реализуйте аспекты, используя Spring AOP, для асинхронного и синхронного отслеживания времени выполнения методов, помеченных соответствующими аннотациями.
3. Создайте сервис, который будет асинхронно сохранять данные о времени выполнения методов в базе данных.
4. Реализуйте REST API для получения статистики по времени выполнения методов (например, среднее время выполнения, общее время выполнения) для различных методов и их групп.
5. Настройте приложение с помощью конфигурации Spring для включения использования AOP и асинхронной обработки данных.

## Стек
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white "Java 17")
![Maven](https://img.shields.io/badge/Maven-green.svg?style=for-the-badge&logo=mockito&logoColor=white "Maven")
![Spring](https://img.shields.io/badge/Spring-blueviolet.svg?style=for-the-badge&logo=spring&logoColor=white "Spring")
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![GitHub](https://img.shields.io/badge/git-%23121011.svg?style=for-the-badge&logo=github&logoColor=white "Git")

* Язык: *Java 17*
* Автоматизация сборки: *Maven*
* Фреймворк: *Spring*
* База данных: *PostgreSQL*
* Контроль версий: *Git*

## Взаимодействие с REST-сервисом

- Доступен Swagger-UI во время работы приложения:

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)