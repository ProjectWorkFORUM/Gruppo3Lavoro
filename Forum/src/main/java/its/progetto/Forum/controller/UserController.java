package its.progetto.Forum.controller;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import its.progetto.Forum.Dao.UtentiDao;
import its.progetto.Forum.Model.LoginForm;
import its.progetto.Forum.Model.Utenti;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UtentiDao utentiDao;

    // da togliere in pagecontroller o chiedere
    @GetMapping("/login")
    public String loginPage(LoginForm loginForm) {
        return "Login_page";
    }


    // login
    @PostMapping(value="/login")
    // uso una classe più piccola  per problema blocco validazioni
    public String postLogin(@Valid LoginForm LoginForm, BindingResult bindingResult , HttpSession session) {


        if (bindingResult.hasErrors()) {
            return "Login_page";
        }

        Optional<Utenti> utente = utentiDao.findByUsername(LoginForm.getUsername());

        // faccio il check tra la password database e password login a livello java per semplicità
        // potervo farmi un metodo del dao find utentiepassword ma problema se hashiamo password
        if (utente.isEmpty() ||
                !utente.get().getPassword().equals(LoginForm.getPassword())) {
            bindingResult.reject("credenziali", "Username o password errati");
            return "Login_page";

        }

        session.setAttribute("loggedUser", utente.get());
        return "redirect:/";

    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
    

    @GetMapping("/register")
    public String registerPage(Utenti utenti) {
        return "Register_page";
    }


    @PostMapping("/registrazione")
    public String postRegistrazione(@Valid Utenti utente, BindingResult bindingResult, HttpSession session){

        if(bindingResult.hasErrors()){
            return "Register_page";
        }

        // check se l'utente esiste già come username o email
        if (utentiDao.existsByUsername(utente.getUsername())) {
            bindingResult.rejectValue("username", "duplicato", "Username già in uso");
            return "Register_page";
        }

        if (utentiDao.existsByEmail(utente.getEmail())) {
            bindingResult.rejectValue("email", "duplicata", "Email già registrata");
            return "Register_page";
        }




        utente.setDataRegistrazione(LocalDate.now());
        utentiDao.save(utente);
        session.setAttribute("loggedUser", utente);
        return "redirect:/";



    }

    // logut
    // uso remove attribute perchè cancella solo l'attribute loggedUser-
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("loggedUser");
        return "redirect:/";
    }


}
