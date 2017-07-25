package net.zndo.zhuque.common.security;

import net.zndo.zhuque.common.enums.AcccountStatus;
import net.zndo.zhuque.common.exception.AccountFrozenException;
import net.zndo.zhuque.common.exception.AccountNotExistException;
import net.zndo.zhuque.dao.AdminAccountRepository;
import net.zndo.zhuque.entity.AdminAccount;
import net.zndo.zhuque.mapper.AuthMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class ZhuqueUserDetailService implements UserDetailsService {

    @Autowired
    private AdminAccountRepository adminAccountRepository;

    @Autowired
    private AuthMapper authMapper;

    @Autowired
    private Environment environment;

    @Override
    public ZhuqueUserDetail loadUserByUsername(String s) throws UsernameNotFoundException {

        if(StringUtils.isBlank(s)) {
            throw new AccountNotExistException();
        }

        //Step-01: 根据名称查找用户Entity
        AdminAccount account = adminAccountRepository.findByAdminName(s);

        if (null == account) {
            throw new AccountNotExistException();
        }

        if(account.getStatus().equals(AcccountStatus.FROZEN)) {
            throw new AccountFrozenException();
        }

        //Step-02: 查找用户角色, 默认自带ROLE_ADMIN
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_TEST"));
        List<String> roles = authMapper.findRolesByAdminId(account.getId());

        if(roles != null && roles.size() > 0) {
            roles.stream().forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(String.format("ROLE_%s", role.toUpperCase().trim())));
            });
        }
        return new ZhuqueUserDetail(account, authorities);
    }
}
