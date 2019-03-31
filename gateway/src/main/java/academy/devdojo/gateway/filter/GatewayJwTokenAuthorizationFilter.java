package academy.devdojo.gateway.filter;

import academy.devdojo.core.Property.JwtConfiguration;
import academy.devdojo.token.converter.TokenConverter;
import academy.devdojo.token.filter.JwtTokenAuthorizationFilter;
import academy.devdojo.token.util.SecurityContextUtil;
import com.netflix.zuul.context.RequestContext;
import com.nimbusds.jwt.SignedJWT;
import lombok.SneakyThrows;
import org.springframework.lang.NonNull;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GatewayJwTokenAuthorizationFilter extends JwtTokenAuthorizationFilter {

    public GatewayJwTokenAuthorizationFilter(JwtConfiguration jwtConfiguration, TokenConverter tokenConverter) {
        super(jwtConfiguration, tokenConverter);
    }


    @Override
    @SneakyThrows
    @SuppressWarnings("Duplicates")
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain chain){
        String header = request.getHeader(jwtConfiguration.getHeader().getName());
        if(header == null || !header.startsWith(jwtConfiguration.getHeader().getPrefix())){
            chain.doFilter(request, response);
            return;
        }
        String token = header.replace(jwtConfiguration.getHeader().getPrefix(),"").trim();

        String signedToken = tokenConverter.decryptToken(token);

        SecurityContextUtil.setSecurityContext(SignedJWT.parse(signedToken));


        tokenConverter.validadeTokenSignature(signedToken);

        if(jwtConfiguration.getType().equalsIgnoreCase("signed"))
            RequestContext.getCurrentContext().addZuulRequestHeader("Authorization", jwtConfiguration.getHeader().getPrefix() + signedToken);
        chain.doFilter(request, response);
    }
}
