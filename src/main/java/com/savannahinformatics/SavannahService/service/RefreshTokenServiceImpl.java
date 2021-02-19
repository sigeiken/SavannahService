package com.savannahinformatics.SavannahService.service;

import com.savannahinformatics.SavannahService.entity.RefreshToken;
import com.savannahinformatics.SavannahService.repository.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService{

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Override
    public RefreshToken generateRefreshToken() throws Exception {
        try {
            RefreshToken refreshToken = new RefreshToken();
            refreshToken.setToken(UUID.randomUUID().toString());
            refreshToken.setCreatedDate(Instant.now());

            return refreshTokenRepository.save(refreshToken);
        }catch (Exception e){
            throw new Exception("Error occurred " + e);
        }
    }
}
