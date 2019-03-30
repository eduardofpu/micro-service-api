package academy.devdojo.auth.security.user;

import academy.devdojo.core.model.ApplicationUser;
import academy.devdojo.core.repository.ApplicationUserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserDetailsServiceImpl implements UserDetailsService {

    private final ApplicationUserRepository applicationUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username){

        log.info("Find by User Name '{}'",username);
        ApplicationUser applicationUser = applicationUserRepository.findByUserName(username);
        log.info("Sucesso");

        if(applicationUser == null)
            throw new UsernameNotFoundException(String.format("Application User '%s' not found", username));

        return new CustomUserDatails(applicationUser);
    }

    private static final class CustomUserDatails extends ApplicationUser implements UserDetails{

         CustomUserDatails(@NonNull ApplicationUser applicationUser){
            super(applicationUser);
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_" +this.getRole());
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
}
