package br.com.manoelvieira.todolist.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/primeirarota")
public class MinhaPrimeiraController {
    @GetMapping("/")
    public String primeiraMensagem(){
        return "Funcionou";
    }
}
