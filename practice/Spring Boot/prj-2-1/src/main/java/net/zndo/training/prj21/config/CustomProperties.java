package net.zndo.training.prj21.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 自定义配置属性
 * @author zndo
 *
 */
@Component
@ConfigurationProperties(prefix="zndo.custom")
public class CustomProperties {

	@Value("${zndo.custom.name}")
	private String name;// 姓名
	
	/**
	 * 获取配置失败时，使用默认值
	 */
	@Value("${zndo.custom.age:28}")
	private Integer age;// 年龄
	
	@Value("${zndo.custom.random1}")
	private String random1;// 随机值1
	
	@Value("${zndo.custom.random2}")
	private Integer random2;// 随机值2
	
	@Value("${zndo.custom.random3}")
	private Long random3;// 随机值3
	
	@Value("${zndo.custom.random4}")
	private Integer random4;// 随机值4
	
	@Value("${zndo.custom.random5}")
	private Integer random5;// 随机值5
	
	// Getters & Setters

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getRandom1() {
		return random1;
	}

	public void setRandom1(String random1) {
		this.random1 = random1;
	}

	public Integer getRandom2() {
		return random2;
	}

	public void setRandom2(Integer random2) {
		this.random2 = random2;
	}

	public Long getRandom3() {
		return random3;
	}

	public void setRandom3(Long random3) {
		this.random3 = random3;
	}

	public Integer getRandom4() {
		return random4;
	}

	public void setRandom4(Integer random4) {
		this.random4 = random4;
	}

	public Integer getRandom5() {
		return random5;
	}

	public void setRandom5(Integer random5) {
		this.random5 = random5;
	}
	
}
