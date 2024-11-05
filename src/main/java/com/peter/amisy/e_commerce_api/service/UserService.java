package com.peter.amisy.e_commerce_api.service;

import com.peter.amisy.e_commerce_api.dto.AuthenticationRequest;
import com.peter.amisy.e_commerce_api.dto.AuthenticationResponse;
import com.peter.amisy.e_commerce_api.dto.UserDto;


public interface UserService extends AbstractService<UserDto> {



    AuthenticationResponse register(UserDto user);

    AuthenticationResponse authenticate(AuthenticationRequest request);

    Integer update(UserDto userDto);
}
