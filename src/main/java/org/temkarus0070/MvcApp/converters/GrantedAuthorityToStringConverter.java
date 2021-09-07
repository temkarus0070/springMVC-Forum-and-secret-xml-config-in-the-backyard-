package org.temkarus0070.MvcApp.converters;

import org.temkarus0070.MvcApp.models.GrantedAuthority;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Converter(autoApply = true)
public class GrantedAuthorityToStringConverter implements AttributeConverter<List<GrantedAuthority>,String> {

    @Override
    public String convertToDatabaseColumn(List<GrantedAuthority> grantedAuthorities) {
        StringBuilder stringBuilder=new StringBuilder();
        grantedAuthorities.forEach(e->stringBuilder.append(e.getAuthority()+" "));
        return stringBuilder.toString().trim();
    }

    @Override
    public List<GrantedAuthority> convertToEntityAttribute(String s) {
        return Arrays.stream(s.split(" ")).map(GrantedAuthority::new).collect(Collectors.toList());
    }
}
