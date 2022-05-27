package com.backend.utils;

import com.backend.dtos.UserDto;
import javax.servlet.http.HttpServletRequest;

public class Validations {

    public static String getUserLogin(HttpServletRequest request){
        if (request.getSession(false) != null) {
            return (String) request.getSession(false).getAttribute("user");
        } else {
            throw new ApiException(401, Constantes.ERROR_NO_AUTORIZADO);
        }
    }

    public static boolean validarDatosCreateUser(UserDto entrada) {
        return entrada.getUsername().length() >= 8 && entrada.getUsername().length() <= 30
                && entrada.getPassword().length() >= 8 && entrada.getPassword().length() <= 50
                && entrada.getMail().length() <= 100 && entrada.getMail().contains("@") && !entrada.getMail().contains("+");
    }

}