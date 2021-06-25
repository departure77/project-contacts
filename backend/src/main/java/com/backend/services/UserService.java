package com.backend.services;

import com.backend.dtos.GetUserDto;
import com.backend.dtos.PutUserDto;
import com.backend.dtos.UserDto;
import com.backend.models.UserModels;
import com.backend.repository.UserRepository;
import com.backend.utils.ApiException;
import com.backend.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.Random;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    public GetUserDto pickUser(int idUser) {
        try {
            Optional<UserModels> user = userRepository.findById(idUser);

            if (user.isPresent()) {
                GetUserDto exit = new GetUserDto();
                exit.setIdUser(user.get().getIdUser());
                exit.setUsername(user.get().getUsername());
                exit.setMail(user.get().getMail());
                exit.setName(user.get().getName());
                exit.setSurname(user.get().getSurname());
                exit.setVerificationCode(user.get().getVerificationCode());
                exit.setVerifiedMail(user.get().getVerifiedMail());

                return exit;
            } else {
                throw new ApiException(404, "El usuario no existe");
            }
        } catch (ApiException error) {
            throw error;
        } catch (Exception error) {
            throw new ApiException(500, Constantes.GENERAL_ERROR);
        }
    }

    public Integer createUser(UserDto in) {
        try {

            Optional<UserModels> existingUser = userRepository.chekRepeatUser(in.getUsername(), in.getMail());
            if (existingUser.isPresent()) {
                throw new ApiException(409, "El usuario ya existe");
            }

            if (in.getUsername().length() <= 40 && in.getUsername().length() >= 5 &&
                    in.getPassword().length() <= 40 && in.getPassword().length() >=5 &&
                    in.getMail().length() <= 50 && in.getMail().length() >=5  &&
                    in.getName().length() <= 70 &&
                    in.getSurname().length() <= 70) {

                UserModels user = new UserModels();
                user.setUsername(in.getUsername());
                user.setPassword(in.getPassword());
                user.setMail(in.getMail());
                user.setName(in.getName());
                user.setSurname(in.getSurname());
                user.setCreationDate(new Date());
                user.setVerifiedMail(false);

                Random rand = new Random();
                Integer n = rand.nextInt(999999);
                user.setVerificationCode(n.toString());

                user = userRepository.save(user);
                return user.getIdUser();

            } else {
                throw new ApiException(400, "Los datos enviados no son validos.");
            }
        } catch (ApiException error) {
            throw error;
        } catch (Exception error) {
            throw new ApiException(500, Constantes.GENERAL_ERROR);
        }
    }

    public void deleteUser(int idUser) {
        try {
            if (userRepository.existsById(idUser)) {
                userRepository.deleteById(idUser);
            } else {
                throw new ApiException(404, "El usuario no existe");
            }
        } catch (ApiException error) {
            throw error;
        } catch (Exception error) {
            throw new ApiException(500, Constantes.GENERAL_ERROR);
        }
    }

    public int refreshUser (int idUser, PutUserDto body){
        try {
            Optional<UserModels> userDB = userRepository.findById(idUser);

            if (userDB.isPresent()){
                UserModels in = userDB.get();

                if (body.getUsername() != null){
                    in.setUsername(body.getUsername());
                }

                if (body.getMail() !=  null){
                    in.setMail(body.getMail());
                }

                if (body.getPassword() != null){
                    in.setPassword(body.getPassword());
                }

                if (body.getNewPassword() != null){

                    if (in.getPassword().equals(body.getPassword()) && body.getNewPassword().length() > 8 &&
                        body.getNewPassword().length() < 50){

                        in.setPassword(body.getNewPassword());

                    } else {
                        throw new ApiException(400, "Los datos enviados no son validos.");
                    }
                }


                userRepository.save(in);
                return in.getIdUser();
            } else {
                throw new ApiException(404, "El usuario no existe.");
            }
        } catch (ApiException error){
            throw error;
        } catch (Exception error){
            throw new ApiException(500, Constantes.GENERAL_ERROR);
        }
    }
}
