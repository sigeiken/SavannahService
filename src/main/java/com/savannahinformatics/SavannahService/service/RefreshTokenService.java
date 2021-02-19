package com.savannahinformatics.SavannahService.service;

import com.savannahinformatics.SavannahService.entity.RefreshToken;

public interface RefreshTokenService {
    RefreshToken generateRefreshToken() throws Exception;
}
