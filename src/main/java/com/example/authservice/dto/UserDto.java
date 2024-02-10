package com.example.authservice.dto;



import com.example.authservice.model.Role;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDto {

    private String userName;
    private String password;
    private String email;
    private Role role;

    public UserDto(Builder builder){
        this.userName = builder.userName;
        this.email = builder.email;
        this.password = builder.password;
        this.role = builder.role;
    }


    public static final class Builder{

        private String userName;
        private String password;
        private String email;
        private Role role;


        public Builder(){
        }


        public Builder userName(String userName){
            this.userName = userName;
            return this;
        }

        public Builder password(String password){
            this.password = password;
            return this;
        }

        public Builder email(String email){
            this.email = email;
            return this;
        }

        public Builder role(Role role){
            this.role = role;
            return this;
        }

        public UserDto build(){
            return new UserDto(this);
        }


    }




}













