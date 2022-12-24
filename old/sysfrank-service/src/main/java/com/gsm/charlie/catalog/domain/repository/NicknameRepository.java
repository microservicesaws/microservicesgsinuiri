package com.gsm.charlie.catalog.domain.repository;

import com.gsm.charlie.catalog.domain.model.Nickname;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NicknameRepository extends JpaRepository<Nickname, Long> {
}
