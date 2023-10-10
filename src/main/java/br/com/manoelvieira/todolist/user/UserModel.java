package br.com.manoelvieira.todolist.user;

import lombok.Data; // Para colocar os Getters e Setters

@Data
public class UserModel {
    private String username;
    private String name;
    private String password;
}
