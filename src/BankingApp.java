import model.*;
import observer.*;
import factory.*;
import builder.*;
import strategy.*;
import facade.*;
import decorator.*;

/**
 * Комплексное демонстрационное приложение банковской системы.
 * Показывает использование всех 6 паттернов проектирования:
 * 1. Singleton (BankSystem)
 * 2. Observer (Устройства и уведомления)
 * 3. Decorator (Бонусы на счетах)
 * 4. Factory Method (Создание отчетов)
 * 5. Builder (Кредитные соглашения)
 * 6. Strategy (Расчет процентов)
 * 7. Facade (Упрощенный интерфейс банковских операций)
 */
public class BankingApp {

    private static final String SEPARATOR = "═══════════════════════════════════════════════════════════════";
    private static final String LINE = "───────────────────────────────────────────────────────────────";

    public static void main(String[] args) {
        printHeader("ДОБРО ПОЖАЛОВАТЬ В БАНКОВСКУЮ СИСТЕМУ", '═');

        // Создание клиентов
        Client client1 = createClient1();
        Client client2 = createClient2();

        // 1. Демонстрация паттерна Singleton + Observer
        demonstrateSingletonAndObserver(client1, client2);

        // 2. Демонстрация паттерна Decorator (бонусы на счетах)
        Account[] accounts = demonstrateDecorator(client1, client2);

        // 3. Демонстрация паттерна Strategy (расчет процентов)
        demonstrateStrategy(accounts);

        // 4. Демонстрация паттерна Factory Method (отчеты)
        demonstrateFactoryMethod();

        // 5. Демонстрация паттерна Builder (кредитные соглашения)
        demonstrateBuilder(client1, client2);

        // 6. Демонстрация паттерна Facade (упрощенный интерфейс)
        demonstrateFacade(client1, accounts);

        // Итоговая сводка
        printFinalSummary(client1, client2);

        printHeader("ВСЕ ПАТТЕРНЫ УСПЕШНО ПРОДЕМОНСТРИРОВАНЫ!", '═');
    }

    /**
     * Создание первого клиента
     */
    private static Client createClient1() {
        printSection("СОЗДАНИЕ КЛИЕНТА #1");
        Client client = new Client("Сабулла", "Диана", "diana@bank.kz", "+77009890450");
        client.showClientInfo();
        return client;
    }

    /**
     * Создание второго клиента
     */
    private static Client createClient2() {
        printSection("СОЗДАНИЕ КЛИЕНТА #2");
        Client client = new Client("Иванов", "Иван", "ivan@bank.kz", "+77001234567");
        client.showClientInfo();
        return client;
    }

    /**
     * 1. ПАТТЕРН SINGLETON + OBSERVER
     * BankSystem - Singleton, который управляет уведомлениями
     * Device - Observer, который получает уведомления
     */
    private static void demonstrateSingletonAndObserver(Client client1, Client client2) {
        printHeader("ПАТТЕРН #1 & #2: SINGLETON + OBSERVER", '═');

        System.out.println("📱 Singleton: BankSystem - единственный экземпляр системы");
        System.out.println("👁️ Observer: Устройства подписываются на уведомления\n");

        // Получение единственного экземпляра BankSystem (Singleton)
        BankSystem bankSystem = BankSystem.getInstance();
        System.out.println("✓ BankSystem (Singleton) инициализирован\n");

        // Добавление устройств для клиента 1 (Observer pattern)
        System.out.println("→ Клиент 1 подключает устройства:");
        Device phone1 = new MobilePhoneDevice();
        Device laptop1 = new LaptopDevice();
        client1.addDevice(phone1);
        client1.addDevice(laptop1);
        client1.seeDevices();

        // Добавление устройств для клиента 2
        System.out.println("\n→ Клиент 2 подключает устройства:");
        Device phone2 = new MobilePhoneDevice();
        client2.addDevice(phone2);

        // Отправка уведомления всем подписчикам
        System.out.println("\n📢 Отправка уведомления всем устройствам:");
        bankSystem.notifyObservers("Добро пожаловать в нашу банковскую систему!");

        System.out.println();
    }

    /**
     * 2. ПАТТЕРН DECORATOR
     * Добавляет дополнительную функциональность (бонусы) к счетам
     */
    private static Account[] demonstrateDecorator(Client client1, Client client2) {
        printHeader("ПАТТЕРН #3: DECORATOR (Бонусы на счетах)", '═');

        System.out.println("🎁 Decorator добавляет бонусы к базовому функционалу счетов\n");

        // Создание счетов с различными декораторами бонусов
        System.out.println("→ Создание счетов с автоматическим применением бонусов:");

        Account savings1 = new Account(client1, AccountType.SAVINGS, "Накопительный счет");
        System.out.println("✓ SAVINGS счет создан (AccountBalanceDecorator применен)");

        Account deposit1 = new Account(client1, AccountType.DEPOSIT, "Депозит для большой цели");
        System.out.println("✓ DEPOSIT счет создан (DepositBalanceDecorator применен)");

        Account savings2 = new Account(client2, AccountType.SAVINGS, "Сбережения");
        System.out.println("✓ SAVINGS счет создан для клиента 2");

        // Добавление счетов к клиентам
        client1.createAccount(savings1);
        client1.createAccount(deposit1);
        client2.createAccount(savings2);

        // Операции со счетами
        System.out.println("\n→ Выполнение операций со счетами:");
        savings1.deposit(150000);
        deposit1.deposit(500000);
        savings2.deposit(200000);

        System.out.println("\n→ Операции с бонусами (pay):");
        savings1.pay(10000); // Будет применен бонус от декоратора

        client1.showAccounts();
        client2.showAccounts();

        return new Account[]{savings1, deposit1, savings2};
    }

    /**
     * 3. ПАТТЕРН STRATEGY
     * Различные стратегии расчета процентов
     */
    private static void demonstrateStrategy(Account[] accounts) {
        printHeader("ПАТТЕРН #4: STRATEGY (Расчет процентов)", '═');

        System.out.println("💰 Strategy позволяет использовать разные алгоритмы расчета процентов\n");

        InterestCalculator calculator = new InterestCalculator();

        // Стратегия 1: SavingsInterest (3%)
        System.out.println("→ Стратегия 1: SavingsInterest (3% годовых)");
        calculator.setStrategy(new SavingsInterest());
        double interest1 = calculator.execute(accounts[0]);
        System.out.printf("  Проценты для счета [%s]: %.2f ₸%n",
            accounts[0].getAccountType(), interest1);

        // Стратегия 2: VIPInterest (5%)
        System.out.println("\n→ Стратегия 2: VIPInterest (5% годовых)");
        calculator.setStrategy(new VIPInterest());
        double interest2 = calculator.execute(accounts[1]);
        System.out.printf("  Проценты для счета [%s]: %.2f ₸%n",
            accounts[1].getAccountType(), interest2);

        // Стратегия 3: LoanInterest (7%)
        System.out.println("\n→ Стратегия 3: LoanInterest (7% годовых)");
        calculator.setStrategy(new LoanInterest());
        double interest3 = calculator.execute(accounts[2]);
        System.out.printf("  Проценты для счета [%s]: %.2f ₸%n",
            accounts[2].getAccountType(), interest3);

        System.out.println("\n✓ Динамическая смена стратегий расчета успешна!\n");
    }

    /**
     * 4. ПАТТЕРН FACTORY METHOD
     * Создание различных типов отчетов
     */
    private static void demonstrateFactoryMethod() {
        printHeader("ПАТТЕРН #5: FACTORY METHOD (Отчеты)", '═');

        System.out.println("🏭 Factory Method создает разные типы отчетов через единый интерфейс\n");

        // Простой фабричный подход
        System.out.println("→ Простая фабрика (ReportFactory):");
        Report clientReport = ReportFactory.createReport("client");
        clientReport.generateReport();
        System.out.println();

        Report bankReport = ReportFactory.createReport("bank");
        bankReport.generateReport();
        System.out.println();

        Report auditReport = ReportFactory.createReport("audit");
        auditReport.generateReport();

        // Полный подход с иерархией создателей
        System.out.println("\n→ Полный Factory Method с Creator иерархией:");
        ReportCreator clientCreator = ReportFactory.getReportCreator("client");
        System.out.println("  Creator: " + clientCreator.getCreatorInfo());
        clientCreator.generateAndShow();
        System.out.println();

        ReportCreator bankCreator = ReportFactory.getReportCreator("bank");
        System.out.println("  Creator: " + bankCreator.getCreatorInfo());
        bankCreator.generateAndShow();
        System.out.println();
    }

    /**
     * 5. ПАТТЕРН BUILDER
     * Пошаговое создание сложных объектов (кредитных соглашений)
     */
    private static void demonstrateBuilder(Client client1, Client client2) {
        printHeader("ПАТТЕРН #6: BUILDER (Кредитные соглашения)", '═');

        System.out.println("🏗️ Builder позволяет создавать сложные объекты шаг за шагом\n");

        // Прямое использование Builder
        System.out.println("→ Прямое использование Builder:");
        LoanAgreement simpleLoan = new LoanAgreementBuilder()
                .setClient(client1)
                .setAmount(600_000)
                .setInterestRate(7.5)
                .setTermMonths(60)
                .setPurpose("Личный кредит")
                .build();
        simpleLoan.displayAgreementInfo();

        // Сложный кредит с дополнительными параметрами
        System.out.println("\n→ Сложный кредит с дополнительными параметрами:");
        LoanAgreement complexLoan = new LoanAgreementBuilder()
                .setClient(client2)
                .setAmount(2_500_000)
                .setInterestRate(6.5)
                .setTermMonths(180)
                .setPurpose("Покупка недвижимости")
                .setInsuranceRequired(true)
                .setAgreementNumber("LOAN-2025-VIP-001")
                .build();
        complexLoan.displayAgreementInfo();

        // Использование Director для стандартных конфигураций
        System.out.println("\n→ Использование Director для стандартных типов кредитов:");
        LoanBuilder builder = new LoanAgreementBuilder();
        LoanAgreementDirector director = new LoanAgreementDirector(builder);

        LoanAgreement mortgageLoan = director.constructMortgageLoan(client1, 3_000_000);
        System.out.println("\n  [Director] Ипотечный кредит:");
        mortgageLoan.displayAgreementInfo();

        LoanAgreement carLoan = director.constructCarLoan(client2, 1_200_000);
        System.out.println("\n  [Director] Автокредит:");
        carLoan.displayAgreementInfo();

        System.out.println();
    }

    /**
     * 6. ПАТТЕРН FACADE
     * Упрощенный интерфейс для работы с банковской системой
     */
    private static void demonstrateFacade(Client client, Account[] accounts) {
        printHeader("ПАТТЕРН #7: FACADE (Упрощенный интерфейс)", '═');

        System.out.println("🎭 Facade предоставляет простой интерфейс к сложной подсистеме\n");

        BankingFacade facade = new BankingFacade();

        // 1. Начисление процентов через фасад
        System.out.println("→ Начисление процентов через Facade:");
        facade.applyInterest(accounts[0], new SavingsInterest());

        // 2. Перевод между счетами
        System.out.println("→ Перевод между счетами через Facade:");
        facade.transfer(accounts[0], accounts[1], 30000);

        // 3. Создание кредита
        System.out.println("→ Создание кредита через Facade:");
        LoanAgreement newLoan = facade.createLoan(client, 750_000);
        newLoan.displayAgreementInfo();

        // 4. Генерация отчета
        System.out.println("\n→ Генерация отчета через Facade:");
        facade.generateReport("bank");

        // 5. Отправка уведомлений
        System.out.println("\n→ Отправка уведомлений через Facade:");
        facade.notifyClients("Новые тарифы вступят в силу с 1 декабря 2025 года.");

        System.out.println("\n✓ Все операции выполнены через единый простой интерфейс Facade!\n");
    }

    /**
     * Вывод итоговой сводки по клиентам
     */
    private static void printFinalSummary(Client client1, Client client2) {
        printHeader("ИТОГОВАЯ СВОДКА", '═');

        System.out.println("📊 Состояние клиентов после всех операций:\n");

        System.out.println("→ Клиент 1:");
        client1.showClientInfo();
        client1.showAccounts();

        System.out.println("\n→ Клиент 2:");
        client2.showClientInfo();
        client2.showAccounts();

        System.out.println();
    }

    // ============ Утилиты для форматирования вывода ============

    private static void printHeader(String title, char symbol) {
        String border = String.valueOf(symbol).repeat(SEPARATOR.length());
        System.out.println("\n" + border);
        System.out.println(centerText(title, SEPARATOR.length()));
        System.out.println(border + "\n");
    }

    private static void printSection(String title) {
        System.out.println("\n" + LINE);
        System.out.println("  " + title);
        System.out.println(LINE + "\n");
    }

    private static String centerText(String text, int width) {
        int padding = (width - text.length()) / 2;
        return " ".repeat(Math.max(0, padding)) + text;
    }
}

