package net.zndo.training.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import net.zndo.training.dto.ErrorInfoDto;

@ControllerAdvice
public class GlobalExceptionHandler {

	public static final String DEFAULT_ERROR_VIEW = "/error";

	public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.addObject("exception", e);
		mv.addObject("url", request.getRequestURL());
		mv.setViewName(DEFAULT_ERROR_VIEW);
		return mv;
	}
	
	/**
	 * 自定义异常处理程序
	 * @param request
	 * @param e
	 * @return
	 * @throws Exception
	 */
	public ErrorInfoDto<String> udExceptionHandler(HttpServletRequest request, Exception e) throws Exception {
		ErrorInfoDto<String> ei = new ErrorInfoDto<String>();
		ei.setMessage(e.getMessage());
		ei.setCode(ErrorInfoDto.ERROR);
		ei.setData("aaaaaaaaaaaa");
		ei.setUrl(request.getRequestURL().toString());
		return null;
	}
}
