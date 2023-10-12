package br.com.manoelvieira.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.manoelvieira.todolist.user.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component // Classe para o Spring Gerenciar
public class FilterTaskAuth extends OncePerRequestFilter {

  @Autowired
  private IUserRepository userRepository;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    var servletPath = request.getServletPath();
    if (servletPath.equals("/tasks/")) {
      // Pegar a autenticação (usuário e senha)
      var authorization = request.getHeader("Authorization"); // Recuperar autorização do Header

      var authEncode = authorization.substring("Basic".length()).trim(); // Para separar o Basic do restante | Trim =>
                                                                         // Remover espaços que sobraram
      byte[] authDecode = Base64.getDecoder().decode(authEncode); // Decodificar

      var authString = new String(authDecode); // Para trazer em formato de String

      String[] credentials = authString.split(":"); // Para separar o username do password
      String username = credentials[0];
      String password = credentials[1];

      // Validar usuário
      var user = this.userRepository.findByUsername(username);

      if (user == null) {
        response.sendError(401);
      } else {
        // Validar a senha
        var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
        if (passwordVerify.verified) {
          filterChain.doFilter(request, response);
        } else {
          response.sendError(401);
        }
        // Segue viagem
      }
    } else {
      filterChain.doFilter(request, response);
    }
  }
}
