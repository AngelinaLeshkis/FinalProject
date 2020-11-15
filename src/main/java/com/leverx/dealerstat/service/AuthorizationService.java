package com.leverx.dealerstat.service;

import com.leverx.dealerstat.dto.AuthenticationRequestDTO;

import java.util.Map;

public interface AuthorizationService {

    Map<String, String> login(AuthenticationRequestDTO authenticationRequestDTO);
}
