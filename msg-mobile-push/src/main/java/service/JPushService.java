package service;

import entry.JPushMessageEntry;

/**
 * <p>标题：</p>
 * <p>功能：</p>
 * <p>作者：屈晓东</p>
 * <p>类名称：service.JPushService</p>
 * <p>创建日期：2021/5/19 12:11</p>
 */
public interface JPushService {
    public boolean push(JPushMessageEntry entry);
}
