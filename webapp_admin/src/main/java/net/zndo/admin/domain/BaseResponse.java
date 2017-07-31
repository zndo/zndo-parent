package net.zndo.admin.domain;

import lombok.Data;

/**
 * 响应 DTO
 * @author zndo
 *
 */
@Data
public class BaseResponse {

	// 代码
	private int code;
	
	// 描述
	private String desc;
	
	// 内容
	private Object content;
}
