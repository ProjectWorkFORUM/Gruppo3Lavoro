package its.progetto.Forum.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import its.progetto.Forum.Model.Utenti;
import jakarta.servlet.http.HttpSession;

// rende disponibile l'utente loggato (o null) come "user" in tutti i template,
// così i th:if="${user != null and user.isAdmin()}" funzionano su ogni pagina
@ControllerAdvice
public class GlobalModelAdvice {

    @ModelAttribute("user")
    public Utenti utenteLoggato(HttpSession session) {
        return (Utenti) session.getAttribute("loggedUser");
    }
}
