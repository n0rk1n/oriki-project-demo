package cn.oriki.demo.datetime;

import org.junit.Assert;
import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateTimeTest {

    // Java对日期，日历及时间的处理一直以来都饱受诟病：Date 为可修改 & SimpleDateFormat 为线程不安全

    // JDK8 新的时间日期库的最大的优点就在于它定义清楚了时间日期相关的一些概念。
    // 比方说：
    // 瞬时时间（Instant），
    // 持续时间（duration），
    // 日期（date），
    // 时间（time），
    // 时区（time-zone），
    // 时间段（Period）。

    // Java 8仍然延用了ISO的日历体系，java.time包中的类是不可变且线程安全的。
    // 新的时间及日期API位于java.time包中，下面是里面的一些关键的类：

    // Instant——它代表的是时间戳
    // LocalDate——不包含具体时间的日期，比如2014-01-14。它可以用来存储生日，周年纪念日，入职日期等。
    // LocalTime——它代表的是不含日期的时间
    // LocalDateTime——它包含了日期及时间，不过还是没有偏移信息或者说时区。
    // ZonedDateTime——这是一个包含时区的完整的日期时间，偏移量是以UTC/格林威治时间为基准的。

    // 新的库还增加了ZoneOffset及Zoned，可以为时区提供更好的支持。

    @Test
    public void test() {
        // 在Java 8中获取当天的日期
        LocalDate today = LocalDate.now();
        System.out.println("today is : " + today);
    }

    @Test
    public void test1() {
        // 在Java 8中获取当前的年月日
        LocalDate today = LocalDate.now();

        int year = today.getYear();
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();

        System.out.printf("Year : %d Month : %d day : %d \t %n", year, month, day);
    }

    @Test
    public void test2() {
        // 在Java 8中如何获取某个特定的日期
        LocalDate localDate = LocalDate.of(2008, 8, 8);
        System.out.println("date is : " + localDate);
    }

    // 在Java 8 检查两个日期是否相等 , 使用 equals ，不演示

    @Test
    public void test3() {
        // 检查重复事件，比如生日
        // 与时间日期相关的实际任务 -- 检查重复事件

        LocalDate birthday = LocalDate.of(1993, 8, 4);
        MonthDay birth = MonthDay.of(birthday.getMonth(), birthday.getDayOfMonth());

        LocalDate now = LocalDate.now();
        MonthDay day = MonthDay.of(now.getMonth(), now.getDayOfMonth());

        if (birth.equals(day)) {
            System.out.println("is birthday");
        } else {
            System.out.println("is not birthday");
        }
    }

    @Test
    public void test4() {
        // Java 8中获取当前时间
        LocalTime time = LocalTime.now();
        System.out.println("local time now : " + time); // 19:02:26.736
        // 默认的格式是hh:mm:ss:nnn，这里的nnn是纳秒。
    }

    @Test
    public void test5() {
        // 增加时间里面的小时数
        LocalTime time = LocalTime.now();
        System.out.println("local time now : " + time);
        LocalTime after = time.plusHours(2);
        System.out.println("after 2 hour :" + after);
    }

    @Test
    public void test6() {
        // 获取1周后的日期
        LocalDate now = LocalDate.now();
        // Java API中的ChronoUnit类可获取更多选项
        LocalDate date = now.plus(1, ChronoUnit.WEEKS);
        System.out.println(date);
    }

    // 减少 1 年，可以使用 minus 方法

    @Test
    public void test7() {
        // 在Java 8中使用时钟
        // Returns the current time based on your system clock and set to UTC.
        Clock clock = Clock.systemUTC();
        System.out.println("Clock : " + clock);

        // Returns time based on system clock zone Clock defaultClock =
        Clock clock1 = Clock.systemDefaultZone();
        System.out.println("Clock : " + clock1);
    }

    @Test
    public void test8() throws InterruptedException {
        // 指定的日期来和这个时钟进行比较
        LocalDateTime before = LocalDateTime.now();

        Thread.sleep(1000L);
        Clock clock = Clock.systemUTC();
        LocalDateTime after = LocalDateTime.now(clock);

        Assert.assertTrue(before.isAfter(after));
    }

    @Test
    public void test9() {
        // 在Java 8中处理不同的时区
        ZoneId america = ZoneId.of("America/New_York");
        LocalDateTime localDateTime = LocalDateTime.now();
        // 针对本地时区，获取对应时区的 LocalDatetime
        ZonedDateTime dateTimeInNewYork = ZonedDateTime.of(localDateTime, america);
        System.out.println("Current date and time in a particular timezone : " + dateTimeInNewYork);
    }

    @Test
    public void test10() {
        // 表示固定的日期，比如信用卡过期时间，可以使用 YearMonth 类
        // 模拟信用卡过期判断
        YearMonth credit = YearMonth.of(2020, Month.MAY);// 或者书写月份也可以

        YearMonth now = YearMonth.now();

        if (now.isAfter(credit)) {
            System.out.println("你的信用卡已过期");
        } else {
            System.out.println("你的信用卡未过期");
        }
    }

    @Test
    public void test11() {
        // 在 JDK8 中检查闰年
        LocalDate now = LocalDate.now();
        boolean leapYear = now.isLeapYear();

        System.out.println("是否为闰年：" + leapYear);
    }

    @Test
    public void test12() {
        // 两个日期之间包含多少天，多少个月
        LocalDate yearOf2008 = LocalDate.of(2008, 8, 8);
        LocalDate yearOf2018 = LocalDate.of(2018, 10, 2);

        Period between = Period.between(yearOf2008, yearOf2018);

        // 比较迷
        System.out.println("日期差：" + between.getDays()); // 日期差：24
        System.out.println("月份差：" + between.getMonths()); // 月份差：1
        System.out.println("年份差：" + between.getYears()); // 年份差：10
    }

    @Test
    public void test13() {
        // 在Java 8里面，你可以用ZoneOffset类来代表某个时区，
        // 比如印度是GMT或者UTC5：30，
        // 你可以使用它的静态方法ZoneOffset.of()方法来获取对应的时区。
        LocalDateTime datetime = LocalDateTime.of(2018, Month.JANUARY, 14, 19, 30);

        ZoneOffset offset = ZoneOffset.of("+05:30");
        OffsetDateTime date = OffsetDateTime.of(datetime, offset);
        System.out.println(date);
        // 可以看到现在时间日期与时区是关联上了。
        // OffSetDateTime主要是给机器来理解的，如果是给人看的，可以使用ZoneDateTime类。
    }

    @Test
    public void test14() {
        // 获取当前时间戳
        Instant timestamp = Instant.now();
        System.out.println("instant :" + timestamp);

        // 当前时间戳是包含日期与时间的，与java.util.Date很类似，
        // 事实上Instant就是Java 8前的Date，
        // 你可以使用这两个类中的方法来在这两个类型之间进行转换，
        // 比如Date.from(Instant)是用来将Instant转换成java.util.Date的，
        // 而Date.toInstant()是将Date转换成Instant的。
    }

    @Test
    public void test15() {
        // 使用自定义格式器来对日期进行解析/格式化
        String s = "20140116";
        LocalDate formatted = LocalDate.parse(s,
                DateTimeFormatter.ofPattern("yyyyMMdd"));
        // DateTimeFormatter.BASIC_ISO_DATE 预定义解释器
        System.out.printf("Date generated from String %s is %s %n", s, formatted);
    }

    @Test
    public void test16() {
        // 对日期进行格式化，转换成字符串
        String s = "2008-08-08 00:00:00 PM";
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a");
        String landing = s.format(s, format);
        System.out.printf(" %s %n", landing);
    }

}
