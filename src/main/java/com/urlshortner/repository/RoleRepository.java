package com.urlshortner.repository;

import com.urlshortner.model.BaseRole;
import com.urlshortner.model.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(BaseRole role);
}
