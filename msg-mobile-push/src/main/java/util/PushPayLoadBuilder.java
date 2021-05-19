package util;

import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import config.JPushConfig;
import entry.JPushMessageEntry;

/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <p>作者：屈晓东</p>
 * <p>类名称：util.PushPayLoadBuilder</p>
 * <p>创建日期：2021/5/19 15:02</p>
 */
public class PushPayLoadBuilder {
    public static PushPayload build(JPushMessageEntry entry, JPushConfig config) {
        return PushPayload.newBuilder()
                //指定要推送的平台，all代表当前应用配置了的所有平台，也可以传android等具体平台
                .setPlatform(config.getPlatform())
                //指定推送的接收对象，all代表所有人，也可以指定已经设置成功的tag或alias或该应应用客户端调用接口获取到的registration id
                .setAudience(entry.getAudience())
                //jpush的通知，android的由jpush直接下发，iOS的由apns服务器下发，Winphone的由mpns下发
                .setNotification(Notification.newBuilder()
                        //指定当前推送的android通知
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                //通知内容
                                .setAlert(entry.getContent())
                                //通知标题
                                .setTitle(entry.getTitle())
                                //此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
                                .addExtras(entry.getExtrasMap())
                                .build())
                        //指定当前推送的iOS通知
                        .addPlatformNotification(IosNotification.newBuilder()
                                //通知内容
                                .setAlert(entry.getContent())
                                //此项是指定此推送的badge自动加1
                                .incrBadge(1)
                                //此字段的值default表示系统默认声音；传sound.caf表示此推送以项目里面打包的sound.caf声音来提醒，
                                // 如果系统没有此音频则以系统默认声音提醒；此字段如果传空字符串，iOS9及以上的系统是无声音提醒，以下的系统是默认声音
                                .setSound("sound.caf")
                                //扩展字段，不会显示在通知栏
                                .addExtras(entry.getExtrasMap())
                                //此项说明此推送是一个background推送，想了解background看：http://docs.jpush.io/client/ios_tutorials/#ios-7-background-remote-notification
                                .setContentAvailable(true)
                                .build())
                        .build())
                //Platform指定了哪些平台就会像指定平台中符合推送条件的设备进行推送。 jpush的自定义消息，
                // sdk默认不做任何处理，不会有通知提示。建议看文档http://docs.jpush.io/guideline/faq/的
                // [通知与自定义消息有什么区别？]了解通知和自定义消息的区别
                .setMessage(Message.newBuilder()
                        .setMsgContent(entry.getContent())
                        .setTitle(entry.getTitle())
                        .addExtras(entry.getExtrasMap())
                        .build())
                .setOptions(Options.newBuilder()
                        //此字段的值是用来指定本推送要推送的apns环境，false表示开发，true表示生产；对android和自定义消息无意义
                        .setApnsProduction(config.isApnsProduction())
                        //此字段是给开发者自己给推送编号，方便推送者分辨推送记录
                        .setSendno(entry.getSendno())
                        //此字段的值是用来指定本推送的离线保存时长，如果不传此字段则默认保存一天，最多指定保留十天，单位为秒
                        .setTimeToLive(Long.valueOf(config.getLiveTime()))
                        .build())
                .build();
    }
}
