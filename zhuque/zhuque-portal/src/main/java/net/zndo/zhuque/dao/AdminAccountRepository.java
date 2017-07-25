package net.zndo.zhuque.dao;

import net.zndo.zhuque.entity.AdminAccount;
import org.springframework.data.repository.CrudRepository;

public interface AdminAccountRepository extends CrudRepository <AdminAccount, Long> {

    public AdminAccount findByAdminName(String adminName);

}
