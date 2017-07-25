package net.zndo.zhuque.common.exception;

import net.zndo.zhuque.util.AppUtil;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class AccountNotExistException extends UsernameNotFoundException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5104171615631079295L;

	public AccountNotExistException() {
        super(AppUtil.getMessage("accountNotExistException"));
    }

    public AccountNotExistException(String msg) {
        super(msg);
    }
}
