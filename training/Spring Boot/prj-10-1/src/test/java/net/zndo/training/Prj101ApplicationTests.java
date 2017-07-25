package net.zndo.training;

import java.io.File;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.commons.collections.map.HashedMap;
import org.apache.velocity.app.VelocityEngine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.velocity.VelocityEngineUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Prj101ApplicationTests {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private VelocityEngine velocityEngine;

	@Test
	public void contextLoads() {
	}

	// 简单邮件测试
	public void mailTest() throws Exception {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("USER0PEJGJV0@outlook.com");
		message.setTo("imzndo@gmail.com");
		message.setSubject("test");
		message.setText("mail-content");

		mailSender.send(message);
	}

	// 附件邮件
	public void sendAttachmentsMail() throws Exception {
		MimeMessage mimeMessage = mailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

		helper.setFrom("USER0PEJGJV0@outlook.com");
		helper.setTo("imzndo@gmail.com");
		helper.setSubject("test-附件邮件");
		helper.setText("mail-content");

		FileSystemResource file = new FileSystemResource(new File("weixin.jpg"));
		helper.addAttachment("附件-1.jpg", file);
		helper.addAttachment("附件-2.jpg", file);

		mailSender.send(mimeMessage);
	}

	// 网页邮件-包含静态内容
	@Test
	public void sendInlineMail() throws Exception {

		MimeMessage mimeMessage = mailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

		helper.setFrom("USER0PEJGJV0@outlook.com");
		helper.setTo("imzndo@gmail.com");
		helper.setSubject("test-网页邮件-静态资源");
		helper.setText("<html><body><img src=\"cid:weixin\" ></body></html>", true);

		FileSystemResource file = new FileSystemResource(new File("weixin.jpg"));
		helper.addInline("weixin", file);

		mailSender.send(mimeMessage);
	}

	// VM 模板邮件
	@Test
	public void sendTemplateMail() throws Exception {

		MimeMessage mimeMessage = mailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setFrom("USER0PEJGJV0@outlook.com");
		helper.setTo("imzndo@gmail.com");
		helper.setSubject("主题：模板邮件");

		Map<String, Object> model = new HashedMap();
		model.put("username", "didi");
		String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "template.vm", "UTF-8", model);
		helper.setText(text, true);

		mailSender.send(mimeMessage);
	}

}
