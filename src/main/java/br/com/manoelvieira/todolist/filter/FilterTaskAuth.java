package br.com.manoelvieira.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component // Classe para o Spring Gerenciar
public class FilterTaskAuth extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

        // Pegar a autenticação (usuário e senha)
        var authorization = request.getHeader("Authorization"); // Recuperar autorização do Header

        var authEncode = authorization.substring("Basic".length()).trim(); // Para separar o Basic do restante | Trim => Remover espaços que sobraram

        byte[] authDecode = Base64.getDecoder().decode(authEncode); // Decodificar

        var authString = new String(authDecode); // Para trazer em formato de String

        String[] credentials = authString.split(":"); // Para separar o username do password
        String username = credentials[0];
        String password = credentials[1];

        System.out.println("Authorization");
        System.out.println(username);
        System.out.println(password);

        // Validar usuário

        // Validar a senha

        // Segue viagem
    
        filterChain.doFilter(request, response);
  }

}
