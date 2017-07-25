create table IF NOT EXISTS t_account  (
  id BIGINT  NOT NULL PRIMARY KEY AUTO_INCREMENT,
  email varchar(255) DEFAULT NULL,
  pwd varchar(255) DEFAULT NULL,
  role varchar(255) DEFAULT NULL,
  telephone varchar(255) DEFAULT NULL,
  username varchar(255) DEFAULT NULL,
  UNIQUE KEY UK_3rtvu0jks0sopn5ub30o86h2p (email),
  UNIQUE KEY UK_63bbrdr5cu6f8px510wg9rroe (telephone),
  UNIQUE KEY UK_5ypyc2njeg25il4hhuyb04mf1 (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;