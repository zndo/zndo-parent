insert into t_account(email, telephone, username, pwd, role) select tmp.*
from (
	(select 'admin@stackbox.cn' as email, '123456' as telephone, 'admin' as username, '34d1281a61f0257a3c9494544d7a999469155bc8057780ffd790de8975f00acc0ad21379b058c6b8' as pwd, 'admin' as role)
	union all
	(select 'customer@stackbox.cn' as email, '654321' as telephone, 'customer' as username, '3220ddabb5d606613c57cbfd172c211206b260ca81ac526ec8f580faac4d2e0e79d0385dfc2b71ad' as pwd, 'customer' as role)

) tmp WHERE NOT EXISTS  (select * from t_account);