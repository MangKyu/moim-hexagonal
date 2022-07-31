package com.mangkyu.moim.hexagonal.app.login.domain.out;

import com.mangkyu.moim.hexagonal.app.login.adapter.persistence.SaveLoginTokenPersistenceAdapter;
import com.mangkyu.moim.hexagonal.app.login.adapter.persistence.SaveLoginTokenRepository;
import com.mangkyu.moim.hexagonal.app.login.domain.LoginToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static com.mangkyu.moim.hexagonal.app.login.LoginTestSource.loginToken;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class SaveLoginTokenPortTest {

    private SaveLoginTokenPort target;

    @Autowired
    private SaveLoginTokenRepository saveLoginTokenRepository;

    @BeforeEach
    void setUp() {
        target = new SaveLoginTokenPersistenceAdapter(saveLoginTokenRepository);
    }

    @Test
    void 토큰저장() {
        final LoginToken result = target.save(loginToken());
        assertThat(result).isNotNull();
        assertThat(result.getId()).isNotNull();
    }

}