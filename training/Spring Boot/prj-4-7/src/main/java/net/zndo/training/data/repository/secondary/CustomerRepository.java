package net.zndo.training.data.repository.secondary;

import org.springframework.data.jpa.repository.JpaRepository;

import net.zndo.training.data.entity.secondary.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
