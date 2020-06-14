package com.cadastro_empresarial.erp.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.interceptor.InterceptorBinding;

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@InterceptorBinding
public @interface Transacional { // anotação a ser inserida em todos os métodos que precisem abrir e fechar uma transação.
}
