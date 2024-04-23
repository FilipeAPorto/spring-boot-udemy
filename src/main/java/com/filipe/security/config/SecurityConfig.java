package com.filipe.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    //role -> grupo de usuário (perfil de usuário) -> Master, gerente, frente de loja, vendedor.
    //authority -> permissões -> cadastrar usuário, acessar tela de relatório.

    @Bean//Seta quem passa e em q pagina pode acessar
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            SenhaMasterAuthenticationProvider senhaMasterAuthenticationProvider,
            CustomAutheticationProvider customAutheticationProvider,
            CustomFilter customFilter) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable) //Temporário
                .authorizeHttpRequests(customizer -> {
                    customizer.requestMatchers("/public").permitAll();
                    customizer.anyRequest().authenticated(); //Caso esqueça de proteger alguma rota,aqui bloquea somente para os autenticados
                })
                .httpBasic(Customizer.withDefaults()) //Forma de Autenticação
                .formLogin(Customizer.withDefaults()) //Forma de Autenticação
                .authenticationProvider(senhaMasterAuthenticationProvider) //Forma de Validação
                .authenticationProvider(customAutheticationProvider) //Forma de Validação
                .addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    //2 formas para criar formas de validação -> com UserDetailsService ou AuthenticationProvider
    @Bean
    public UserDetailsService userDetailsService() { //Cria Autenticação em memória - Default

        UserDetails commonUser = User.builder()
                .username("user")
                .password(passwordEncoder().encode("123")) // Já criptografa na fonte
                .roles("USER")
                .build();

        UserDetails adminUser = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("USER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(commonUser, adminUser);
    }

    @Bean
    public PasswordEncoder passwordEncoder() { //Criptográfa a Senha junto com a Metodo no Password Acima
        return new BCryptPasswordEncoder();
    }

    @Bean //Serve para settar qual o nome prefixo da role q deve ser colocado para cada nivel de usuário, vazio não precisará colocar nada
    public GrantedAuthorityDefaults grantedAuthorityDefaults(){
        return new GrantedAuthorityDefaults("");
    }
}
