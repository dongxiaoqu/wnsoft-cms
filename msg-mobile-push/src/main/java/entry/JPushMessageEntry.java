package entry;

import cn.jpush.api.push.model.audience.Audience;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <p>作者：屈晓东</p>
 * <p>类名称：entry.JPushMessageEntry</p>
 * <p>创建日期：2021/5/19 10:49</p>
 */

public class JPushMessageEntry implements Serializable {
    private static final long serialVersionUID = -2322155061998650968L;

    /**
     * 标题
     */
    private String title;
    /**
     * 通知内容
     */
    private String content;
    /**
     * 别名
     */
    private List<String> alias;
    /**
     * 额外推送消息
     */
    private Map<String, String> extrasMap;
    /**
     * 设备号
     */
    private String deviceTagAlias;
    /**
     * 推送编号
     */
    private int sendno;
    /**
     * 是否推送所有人
     */
    private boolean pushAll;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getAlias() {
        return alias;
    }

    public void setAlias(List<String> alias) {
        this.alias = alias;
    }

    public Map<String, String> getExtrasMap() {
        return extrasMap;
    }

    public void setExtrasMap(Map<String, String> extrasMap) {
        this.extrasMap = extrasMap;
    }

    public String getDeviceTagAlias() {
        return deviceTagAlias;
    }

    public void setDeviceTagAlias(String deviceTagAlias) {
        this.deviceTagAlias = deviceTagAlias;
    }

    public int getSendno() {
        return sendno;
    }

    public void setSendno(int sendno) {
        this.sendno = sendno;
    }

    public boolean isPushAll() {
        return pushAll;
    }

    public void setPushAll(boolean pushAll) {
        this.pushAll = pushAll;
    }

    public Audience getAudience() {
        if (this.pushAll) {
            return Audience.all();
        }
        if (this.alias != null && this.alias.size() > 0) {
            return Audience.alias(alias);
        }
        return null;
    }
}
