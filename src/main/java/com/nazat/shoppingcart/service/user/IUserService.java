package com.nazat.shoppingcart.service.user;

import com.nazat.shoppingcart.dto.UserDto;
import com.nazat.shoppingcart.entities.User;
import com.nazat.shoppingcart.request.CreateUserRequest;
import com.nazat.shoppingcart.request.UpdateUserRequest;

public interface IUserService {
    User getUserById(Long userId);

    User createUser(CreateUserRequest request);

    User updateUser(UpdateUserRequest request, Long userId);

    void deleteUser(Long userId);

    UserDto convertToUserDto(User user);

    User getAuthenticatedUser();
}
