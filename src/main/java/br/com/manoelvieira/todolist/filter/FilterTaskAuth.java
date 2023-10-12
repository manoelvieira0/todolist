package br.com.manoelvieira.todolist.filter;

import java.io.IOException;

import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

@Component // Classe para o Spring Gerenciar
public class FilterTaskAuth implements Filter {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    // Executar alguma ação

    System.out.println("Chegou no filtro");
    chain.doFilter(request, response);
  }
}
