package com.mangkyu.moim.hexagonal.app.login.adapter.persistence;

import com.mangkyu.moim.hexagonal.app.login.domain.LoginToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaveLoginTokenRepository extends JpaRepository<LoginToken, Long> {

}
