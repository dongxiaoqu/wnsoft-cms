package service.impl;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.PushPayload;
import config.JPushConfig;
import entry.JPushMessageEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import service.JPushService;
import util.PushPayLoadBuilder;

/**
 * <p>标题：手机极光推送服务</p>
 * <p>功能：</p>
 * <p>作者：屈晓东</p>
 * <p>类名称：service.impl.JPushServiceImpl</p>
 * <p>创建日期：2021/5/19 12:11</p>
 */
@Service("CMS-JPushService")
public class JPushServiceImpl implements JPushService {

    private static final Logger logger = LoggerFactory.getLogger(JPushServiceImpl.class);
    @Autowired
    public JPushConfig config;

    @Override
    public boolean push(JPushMessageEntry entry) {
        //获取极光参数
        ClientConfig clientConfig = ClientConfig.getInstance();
        //设置消息存活时间
        clientConfig.setTimeToLive(Long.valueOf(config.getLiveTime()));
        //
        if (!StringUtils.hasText(config.getAppKey()) || !StringUtils.hasText(config.getMasterSecret())) {
            logger.error("JPush send message fail ,course by appKey or masterSecret is null!");
        }
        //声明jpushClient对象
        JPushClient jpushClient = new JPushClient(config.getAppKey(), config.getMasterSecret());
        boolean result = false;
        try {
            PushPayload pushPayload = PushPayLoadBuilder.build(entry, config);
            PushResult pushResult = jpushClient.sendPush(pushPayload);
            if (pushResult.getResponseCode() == 200) {
                result = true;
            }
            logger.info("[极光推送]PushResult result is " + pushResult);
        } catch (APIConnectionException e) {
            logger.error("[极光推送]Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            logger.error("[极光推送]Error response from JPush server. Should review and fix it. ", e);
            logger.info("[极光推送]HTTP Status: " + e.getStatus());
            logger.info("[极光推送]Error Code: " + e.getErrorCode());
            logger.info("[极光推送]Error Message: " + e.getErrorMessage());
        }
        return result;
    }
}
