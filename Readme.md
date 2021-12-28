<h2>Тестовое задание.</h2>

<h3>Описание:</h3>
Создать сервис, который обращается к сервису курсов валют, и отображает gif:</br>
• если курс по отношению к USD за сегодня стал выше вчерашнего, то отдаем рандомную отсюда https://giphy.com/search/rich </br>
• если ниже - отсюда https://giphy.com/search/broke </br>
Ссылки</br>
• REST API курсов валют - https://docs.openexchangerates.org/ </br>
• REST API гифок - https://developers.giphy.com/docs/api#quick-start-guide </br>
Must Have</br>
• Сервис на Spring Boot 2 + Java / Kotlin</br>
• Запросы приходят на HTTP endpoint (должен быть написан в соответствии с rest conventions), туда передается код валюты по отношению с которой сравнивается USD</br>
• Для взаимодействия с внешними сервисами используется Feign</br>
• Все параметры (валюта по отношению к которой смотрится курс, адреса внешних сервисов и т.д.) вынесены в настройки</br>
• На сервис написаны тесты (для мока внешних сервисов можно использовать @mockbean или WireMock)</br>
• Для сборки должен использоваться Gradle</br>
• Результатом выполнения должен быть репо на GitHub с инструкцией по запуску</br>
Nice to Have</br>
• Сборка и запуск Docker контейнера с этим сервисом</p>

<h2><a name="instruction">Инструкция по запуску</a></h2>
<ol>
<li><p>Открываем терминал.</p></li>
<li><p>С помощью git клонируем проект к себе на компьютер:</br>
git clone https://github.com/Garomir/exchangerate</p></li>
<li><p>Переходим в папку с проектом:</br>
cd {путь к папке}/exchangerate</p></li>
<li><p>Собираем образ из Dockerfile командой:</br>
docker build -t exchangerate .</p></li>
<li><p>Запускаем контейнер командой:</br>
docker run -d -p 8080:8080 -t exchangerate</p></li>
<li><p>В адресной строке браузера пишем адрес с указанием кода валюты, которую мы хотим сравнить с USD, например:</br>
    http://localhost:8080/static/AUD</p>

<h2><a name="author">Автор</a></h2>
  <p>Сафуанов Рамиль</p>
  <p>Java-разработчик</p>
  <p><a href="mailto:ramilfin@mail.ru">ramilfin@mail.ru</a></p>
  <p>+7 9172282922</p>