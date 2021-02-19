package com.savannahinformatics.SavannahService.repository;

import com.savannahinformatics.SavannahService.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
}
