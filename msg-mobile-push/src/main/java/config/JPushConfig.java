package config;

import cn.jpush.api.push.model.Platform;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <p>作者：屈晓东</p>
 * <p>类名称：config.JPushConfig</p>
 * <p>创建日期：2021/5/19 11:08</p>
 */
@ConfigurationProperties(prefix = "jpush")
public class JPushConfig {
    private String appKey;
    private String masterSecret;
    private String liveTime;
    /**
     * 推送平台
     */
    private Platform platform;
    /**
     * 推送的apns环境;false表示开发，true表示生产
     */
    private boolean apnsProduction;

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getMasterSecret() {
        return masterSecret;
    }

    public void setMasterSecret(String masterSecret) {
        this.masterSecret = masterSecret;
    }

    public String getLiveTime() {
        return liveTime;
    }

    public void setLiveTime(String liveTime) {
        this.liveTime = liveTime;
    }

    public Platform getPlatform() {
        if (platform == null) {
            platform = Platform.all();
        }
        return platform;
    }

    public boolean isApnsProduction() {
        return apnsProduction;
    }

    public void setApnsProduction(boolean apnsProduction) {
        this.apnsProduction = apnsProduction;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

}
