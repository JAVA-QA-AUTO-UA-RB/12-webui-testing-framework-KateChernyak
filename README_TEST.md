# 🚀 SELENIUM WEBDRIVER HOMEWORK
### Auto QA — Selenium WebDriver + TestNG + CSS/XPath Locators

---

## 🎯 Мета завдання
-реалізація автотестів для [**The Internet App**](https://the-internet.herokuapp.com/)

| № | Сценарій                   | Короткий опис |
|--- |----------------------------|---------------|
| 1  | 🧾 **A/B Testing**         | Відкрити сторінку й перевірити наявність тексту “A/B Test Control”. |
| 2  | ➕ **Add/Remove Elements** | Додай 3 кнопки “Delete”, переконайся, що вони з’явилися, потім видали їх і перевір, що вони зникли. |
| 3  | ☑️ **Checkboxes**          | Встанови всі чекбокси у вибране положення та перевір, що всі вибрані. |
| 4  | 🔽 **Dropdown**            | Вибери “Option 2” і перевір, що вона стала активною. |
| 5  | 🔐 **Form Authentication** | Увійди з валідними даними, перевір логін, потім виконай logout і переконайся, що сесію завершено. |
| 6  | 🧲 **Drag and Drop**       | Перетягни елемент A на місце B і перевір, що вони помінялися місцями. |
| 7  | 🎚️ **Horizontal Slider**   | Перетягни слайдер у будь-яке ненульове положення та перевір, що відображене значення змінилося. |


## 🧰 Початок роботи

1. Зклонувати репозиторій https://github.com/JAVA-QA-AUTO-UA-RB/12-webui-testing-framework-KateChernyak

2. Переконатися, що встановлений не нижче Java 11+, Maven та відповідний WebDriver (наприклад, ChromeDriver для Chrome).

3. Запустити тести через --bash
   mvn clean test

4. отримати результати тестів

---

## 🧰 Конфігурація

- Браузер і WebDriver налаштовуються у базовому класі `BasicSetupTest`.
- URL для тестування вказаний безпосередньо в тестах, можна винести в конфігураційний файл для гнучкості.
- Час очікувань (Thread.sleep) можна замінити на WebDriverWait для більш стабільних тестів.

---




## 💻 Вивід очікуваного результату `mvn test`

```shell
[INFO] Tests run: 7, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 41.35 s -- in TestSuite
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 7, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  43.967 s
[INFO] Finished at: 2025-10-29T18:59:44+02:00
[INFO] ------------------------------------------------------------------------

Process finished with exit code 0

---



> “Автоматизація — це не магія, це просто код, який не втомлюється.” ⚙️



**Викладач магії:** 👨‍🏫 *Rodion Baronov*  
**Тема:** *Selenium WebDriver, Maven, TestNG — WebUI Automation Foundation*
