package com.mangkyu.moim.hexagonal.app.login.adapter.persistence;

import com.mangkyu.moim.hexagonal.app.login.domain.LoginToken;
import com.mangkyu.moim.hexagonal.app.login.domain.out.SaveLoginTokenPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@RequiredArgsConstructor
public class SaveLoginTokenPersistenceAdapter implements SaveLoginTokenPort {

    private final SaveLoginTokenRepository saveLoginTokenRepository;

    @Override
    public LoginToken save(final LoginToken loginToken) {
        return saveLoginTokenRepository.save(loginToken);
    }

}
