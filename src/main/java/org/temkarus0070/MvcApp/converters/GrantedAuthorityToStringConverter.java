package org.temkarus0070.MvcApp.converters;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter()
public class GrantedAuthorityToStringConverter implements AttributeConverter<GrantedAuthority,String> {
    @Override
    public String convertToDatabaseColumn(GrantedAuthority grantedAuthority) {
       return grantedAuthority.getAuthority();
    }

    @Override
    public GrantedAuthority convertToEntityAttribute(String s) {
        return new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return s;
            }
        };
    }
}
