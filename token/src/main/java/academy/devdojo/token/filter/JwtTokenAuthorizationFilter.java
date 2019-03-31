package academy.devdojo.token.filter;

import academy.devdojo.core.Property.JwtConfiguration;
import academy.devdojo.token.converter.TokenConverter;
import academy.devdojo.token.util.SecurityContextUtil;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JwtTokenAuthorizationFilter extends OncePerRequestFilter {

    protected final JwtConfiguration jwtConfiguration;
    protected final TokenConverter tokenConverter;

    @Override
    @SuppressWarnings("Duplicates")
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain chain) throws ServletException, IOException {
        String header = request.getHeader(jwtConfiguration.getHeader().getName());
        if(header == null || !header.startsWith(jwtConfiguration.getHeader().getPrefix())){
            chain.doFilter(request, response);
            return;
        }
        String token = header.replace(jwtConfiguration.getHeader().getPrefix(),"").trim();
        SecurityContextUtil.setSecurityContext(StringUtils.equalsIgnoreCase("signed", jwtConfiguration.getType())? validate(token) : decryptValidating(token));
        chain.doFilter(request, response);
    }

    @SneakyThrows
    private SignedJWT decryptValidating(String encrypetedToken){
        String signedToken = tokenConverter.decryptToken(encrypetedToken);
        tokenConverter.validadeTokenSignature(signedToken);
        return SignedJWT.parse(signedToken);
    }

    @SneakyThrows
    private SignedJWT validate(String signedToken){
        tokenConverter.validadeTokenSignature(signedToken);
        return SignedJWT.parse(signedToken);
    }
}
