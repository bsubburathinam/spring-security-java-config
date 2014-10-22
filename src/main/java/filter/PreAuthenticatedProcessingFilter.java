package filter;

import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import javax.servlet.http.HttpServletRequest;

public class PreAuthenticatedProcessingFilter extends AbstractPreAuthenticatedProcessingFilter
{
    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest httpServletRequest)
    {
        return "user";
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest httpServletRequest)
    {
        return "ROLE_USER";
    }
}

