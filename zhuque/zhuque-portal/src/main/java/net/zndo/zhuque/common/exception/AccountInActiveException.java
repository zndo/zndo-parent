package net.zndo.zhuque.common.exception;

import net.zndo.zhuque.util.AppUtil;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AccountInActiveException extends UsernameNotFoundException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2022438052893499859L;
	
	public AccountInActiveException() {
        super(AppUtil.getMessage("accountInActiveException"));
    }
    public AccountInActiveException(String msg) {
        super(msg);
    }
}
