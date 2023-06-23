package uz.pdp.store.mapper;

import org.apache.commons.compress.utils.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import uz.pdp.store.dto.UserDto;
import uz.pdp.store.model.Authority;
import uz.pdp.store.model.User;

import java.util.Set;

import static uz.pdp.store.mapper.AddressCustomMapper.AddressDtoListToEntityList;
import static uz.pdp.store.mapper.AddressCustomMapper.AddressListToDtoList;
import static uz.pdp.store.mapper.CardCustomMapper.CardDtoListToEntityList;
import static uz.pdp.store.mapper.CardCustomMapper.CardListToDtoList;


public class UserCustomMapper {

    public static UserDto toDto(User user){
        return UserDto.builder()
                        .id(user.getId())
                        .firstname(user.getFirstname())
                        .lastname(user.getLastname())
                        .password(user.getPassword())
                        .phoneNumber(user.getPhoneNumber())
                        .email(user.getEmail())
                        .createdAt(user.getCreatedAt())
                        .modifiedAt(user.getModifiedAt())
                        .address(AddressListToDtoList(user.getAddress()))
                        .cards(CardListToDtoList(user.getCards()))
                        .authorities(toSimple(user.getAuthorities()))
                        .build();
    }


    public static User toEntity(UserDto userDto){
        return User.builder()
                .id(userDto.getId())
                .firstname(userDto.getFirstname())
                .lastname(userDto.getLastname())
                .password(userDto.getPassword())
                .phoneNumber(userDto.getPhoneNumber())
                .email(userDto.getEmail())
                .createdAt(userDto.getCreatedAt())
                .modifiedAt(userDto.getModifiedAt())
                .address(AddressDtoListToEntityList(userDto.getAddress()))
                .cards(CardDtoListToEntityList(userDto.getCards()))
                .build();
    }

    public static UserDto toDtoWithId(User user){
        return UserDto.builder()
                .id(user.getId())
                .build();
    }


    public static User toEntityWithId(UserDto userDto){
        return User.builder()
                .id(userDto.getId())
                .build();
    }

    public static Set<SimpleGrantedAuthority> toSimple(Set<Authority> set){
        Set<SimpleGrantedAuthority> simpleGrantedAuthorities = Sets.newHashSet();
        for(Authority s : set){
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority(s.getName()));
        }
        return simpleGrantedAuthorities;
    }

}
