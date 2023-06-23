package uz.pdp.store.service.main;


import uz.pdp.store.dto.JwtResponseDto;
import uz.pdp.store.dto.LoginDto;
import uz.pdp.store.dto.ResponseDto;
import uz.pdp.store.dto.UserDto;

public interface UserService {

    ResponseDto<String> registerUserCustomer(UserDto userDto);


    ResponseDto<String> registerUserAdmin(UserDto userDto);

    ResponseDto<UserDto> updateUser(UserDto userDto);

    ResponseDto<UserDto> getUser(Integer id);

    ResponseDto<Boolean> deleteUser(Integer id);

    ResponseDto<JwtResponseDto> login(LoginDto loginDto);

    ResponseDto<String> confirmToken(String token);
}
