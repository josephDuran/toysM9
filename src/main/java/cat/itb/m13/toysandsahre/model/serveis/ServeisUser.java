package cat.itb.m13.toysandsahre.model.serveis;

import cat.itb.m13.toysandsahre.model.entitats.Users;
import cat.itb.m13.toysandsahre.model.repositoris.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
//@Component
public class ServeisUser {
    private final UserRepository userRepository;

//    private final UserRepository repositoriUsuari;
    private final PasswordEncoder xifrat;


    //Llista tots els usuaris
    public List<Users> getUsers(){
        return userRepository.findAll();
    }

    // user by id
    public Users getById( int id){
        return userRepository.findById(id).orElse(null);
    }
    public Users getByEmailPassword( String email, String password){
        return userRepository.findByEmailPassword(email, password).orElse(null);
    }
    // afegeix un usuari
    public Users set(Users it){
        return userRepository.save(it);
    }

    public Users delete(int id){
        Users user = userRepository.findById(id).orElse(null);
        if (user != null){
            userRepository.deleteById(id);
        }
        return user;
    }

    public Users consultarPerUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);

    }

    public Users crearNouUsuari(Users nouUsuari) {
        //falta controlar que els 2 passwords del client coincideixen
        //passar un UsuariCreacioDTO
        nouUsuari.setPassword(xifrat.encode(nouUsuari.getPassword()));
        userRepository.save(nouUsuari);
        return nouUsuari;
    }

    public List<Users> llistarUsuaris(){
        return userRepository.findAll();
    }
}
