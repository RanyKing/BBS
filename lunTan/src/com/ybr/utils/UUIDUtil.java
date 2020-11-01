package com.ybr.utils;

import java.util.UUID;

public class UUIDUtil {
	
	
	//获取随机名称用于保存图片的名称
	public static String getUUID() {
		UUID id = UUID.randomUUID();
		String uuid = id.toString();
		uuid=uuid.replace("-", "");
		System.out.println(uuid);
		return uuid;
	}
}
