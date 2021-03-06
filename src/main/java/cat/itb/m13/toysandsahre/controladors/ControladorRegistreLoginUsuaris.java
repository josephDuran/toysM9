package cat.itb.m13.toysandsahre.controladors;

import cat.itb.m13.toysandsahre.model.entitats.Usuaris;
import cat.itb.m13.toysandsahre.model.entitats.UsuariConsultaDTO;
import cat.itb.m13.toysandsahre.model.serveis.ServeisUser;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ControladorRegistreLoginUsuaris {


    private final ServeisUser serveisUser;


    @GetMapping("/userDetail")
    public UsuariConsultaDTO consultaQuiEts(@AuthenticationPrincipal Usuaris usu) {
        return new UsuariConsultaDTO(usu.getName(), usu.getLastname(), usu.getEmail(), usu.getAddress(), usu.getCity(), usu.getCountry(), usu.getPhone(), usu.getPostalCode(), usu.getDateCreated());
    }

    @PostMapping("/usuaris")
    public ResponseEntity<?> nouUsuari(@RequestBody Usuaris nouUsuari) {
        try {
            Usuaris res = serveisUser.crearNouUsuari(nouUsuari);
            UsuariConsultaDTO usu = new UsuariConsultaDTO(res.getName(), res.getLastname(), res.getEmail(), res.getAddress(), res.getCity(), res.getCountry(), res.getPhone(), res.getPostalCode(), res.getDateCreated());
            return new ResponseEntity<UsuariConsultaDTO>(usu, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException ex) {
            //per si intentem afegir 2 usuaris amb el mateix username, saltarà excepció
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @GetMapping("/usuaris")
    public ResponseEntity<?> llistarUsuarisDTO() {
        List<Usuaris> res = serveisUser.llistarUsuaris();
        System.out.println(res + " Esto Res");
        List<UsuariConsultaDTO> aux = new ArrayList();
        System.out.println("Esto es Aux "+ aux);
        for (Usuaris usu : res) {
            aux.add(new UsuariConsultaDTO(usu.getName(), usu.getLastname(), usu.getEmail(), usu.getAddress(), usu.getCity(), usu.getCountry(), usu.getPhone(), usu.getPostalCode(), usu.getDateCreated()));
        }
        if (res == null) {
            return ResponseEntity.notFound().build();
        } else return ResponseEntity.ok(aux);
    }
}
