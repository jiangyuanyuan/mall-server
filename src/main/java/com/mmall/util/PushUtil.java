package com.mmall.util;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import javax.security.auth.login.Configuration;
import java.util.Map;

/**
 *
 * 极光推送工具类
 * @author mengjicai
 *
 */
class PushUtil {

    // 初始化app_key和masterSecret
    Configuration config;
    private static String APP_KEY;
    private static String MASTER_SECRET;
    private static String EXTRA;

    //static代码块,随着类加载时读取APP_KEY和MASTER_SECRET
    static {
        try {
           // Configuration config = new PropertiesConfiguration("jiguangConfig.properties");
            APP_KEY = "1f490c75885215e4ebd8d705";
            MASTER_SECRET = "995e07cc8b3b817e9dd6a84d";
            EXTRA = "";

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    //极光推送>>Android
    //Map<String, String> parm是我自己传过来的参数,可以自定义参数
    public static void jpushAndroid(Map<String, String> parm) {

        //创建JPushClient(极光推送的实例)
        JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY);
        //推送的关键,构造一个payload
        PushPayload payload = PushPayload.newBuilder()
                .setPlatform(Platform.android())//指定android平台的用户
                .setAudience(Audience.all())//你项目中的所有用户
//                .setAudience(Audience.alias(parm.get("alias")))//设置别名发送,单发，点对点方式
                //.setAudience(Audience.tag("tag1"))//设置按标签发送，相当于群发
//                .setAudience(Audience.registrationId(parm.get("id")))//registrationId指定用户

                .setNotification(Notification.android(parm.get("msg"), parm.get("title"), parm))  //发送内容
                .setOptions(Options.newBuilder().setApnsProduction(true).setTimeToLive(7200).build())
                // apnProduction指定开发环境 true为生产模式 false 为测试模式 (android不区分模式,ios区分模式) 不用设置也没关系
                // TimeToLive 两个小时的缓存时间
                .setMessage(Message.newBuilder()
                        .setMsgContent(parm.get("msg"))
                        .setTitle(parm.get("title"))
                        .addExtra(EXTRA,parm.get("extra"))
                        .build())//自定义信息
                .build();

        try {
            PushResult pu = jpushClient.sendPush(payload);
            System.out.println(pu.toString());
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
    }

    //极光推送>>ios
    //Map<String, String> parm是我自己传过来的参数,可以自定义参数
    public static  void jpushIOS(Map<String, String> parm) {

        //创建JPushClient
        JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY);
        PushPayload payload = PushPayload.newBuilder()
                .setPlatform(Platform.ios())//ios平台的用户
                .setAudience(Audience.all())//所有用户
                //.setAudience(Audience.registrationId(parm.get("id")))//registrationId指定用户
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert(parm.get("msg"))
                                .setBadge(+1)
                                .setSound("happy")//这里是设置提示音(更多可以去官网看看)
                                .addExtras(parm)
                                .build())
                        .build())
                .setOptions(Options.newBuilder().setApnsProduction(false).build())
                .setMessage(Message.newBuilder().setMsgContent(parm.get("msg")).addExtras(parm).build())//自定义信息
                .build();

        try {
            PushResult pu = jpushClient.sendPush(payload);
            System.out.println(pu.toString());
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
    }



    //极光推送>>All所有平台
    public static void jpushAll(Map<String, String> parm) {

        //创建JPushClient
        JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY);
        //创建option
        PushPayload payload = PushPayload.newBuilder()
                .setPlatform(Platform.all())  //所有平台的用户
                .setAudience(Audience.registrationId(parm.get("id")))//registrationId指定用户
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder() //发送ios
                                .setAlert(parm.get("msg")) //消息体
                                .setBadge(+1)
                                .setSound("happy") //ios提示音
                                .addExtras(parm) //附加参数
                                .build())
                        .addPlatformNotification(AndroidNotification.newBuilder() //发送android
                                .addExtras(parm) //附加参数
                                .setAlert(parm.get("msg")) //消息体
                                .build())
                        .build())
                .setOptions(Options.newBuilder().setApnsProduction(true).build())//指定开发环境 true为生产模式 false 为测试模式 (android不区分模式,ios区分模式)
                .setMessage(Message.newBuilder().setMsgContent(parm.get("msg")).addExtras(parm).build())//自定义信息
                .build();
        try {
            PushResult pu = jpushClient.sendPush(payload);
            System.out.println(pu.toString());
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
    }
}