package net.zndo.zhuque.common.exception;

import net.zndo.zhuque.util.AppUtil;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class AccountFrozenException extends UsernameNotFoundException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1564474294036586895L;

	public AccountFrozenException() {
        super(AppUtil.getMessage("accountFrozenException"));
    }

    public AccountFrozenException(String msg) {
        super(msg);
    }
}
