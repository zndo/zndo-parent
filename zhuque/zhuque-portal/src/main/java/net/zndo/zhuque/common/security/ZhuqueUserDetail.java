package net.zndo.zhuque.common.security;

import net.zndo.zhuque.entity.AdminAccount;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


public class ZhuqueUserDetail extends AdminAccount implements UserDetails {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5122959038382961307L;

	public ZhuqueUserDetail(AdminAccount account, Collection<? extends GrantedAuthority> authorities) {

        super.setId(account.getId());
        super.setAdminName(account.getAdminName());
        super.setOriginPassword(account.getPassword());
        super.setStatus(account.getStatus());
        super.setCreateDate(account.getCreateDate());
        super.setModifyDate(account.getModifyDate());

        this.authorities = authorities;
    }

    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getAdminName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
