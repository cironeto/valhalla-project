package dev.cironeto.accesscontrolservice.factory;

import dev.cironeto.accesscontrolservice.dto.AppUserPostRequestBody;
import dev.cironeto.accesscontrolservice.model.AppUser;

import java.util.UUID;

public class UserFactory {

    public static AppUserPostRequestBody createValidPostUser(){
        AppUserPostRequestBody dto = new AppUserPostRequestBody();
        dto.setFirstName("Ciro teste");
        dto.setLastName("Neto teste");
        dto.setEmail("xx123@gmail.com");
        dto.setPassword("123");
        return dto;
    }

    public static AppUserPostRequestBody createValidPostUser2(){
        AppUserPostRequestBody dto = new AppUserPostRequestBody();
        dto.setFirstName("Ciro");
        dto.setLastName("Neto");
        dto.setEmail("netociro12345.teste32@gmail.com");
        dto.setPassword("123");
        return dto;
    }

    public static AppUser createValidUser(){
        AppUser appUser = new AppUser();
        appUser.setFirstName("Ciro");
        appUser.setLastName("Neto");
        appUser.setEmail("amanda123@gmail.com");
        appUser.setPassword("123");
        appUser.setId(new UUID(123, 123));
        return appUser;
    }
}
