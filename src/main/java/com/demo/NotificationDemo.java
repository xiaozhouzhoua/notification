package com.demo;

import fr.jcgay.notification.*;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class NotificationDemo {
    public static void main(String[] args) {
        Notifier notifier = null;
        try {
            URL icon = NotificationDemo.class.getResource("/image/dialog-clean.png");
            Application application = Application.builder()
                    .id("notify-send")
                    .name("测试通知")
                    .icon(Icon.create(icon, "app"))
                    .timeout(TimeUnit.SECONDS.toMillis(3))
                    .build();

            notifier = new SendNotification()
                    .setApplication(application)
                    // 老式通知
                    //.setChosenNotifier("notify")
                    // 脚本编辑器通知
                    .setChosenNotifier("simplenc")
                    // 状态栏通知
                    //.setChosenNotifier("systemtray")
                    .initNotifier();

            Notification notification = Notification.builder()
                    .title("notification")
                    .message("现在时间：" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                    .icon(Icon.create(icon, "ok"))
                    .build();

            notifier.send(notification);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            notifier.close();
        }
    }
}
