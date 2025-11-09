package org.ms.authentification_service.web;

import lombok.AllArgsConstructor;

import org.ms.authentification_service.entities.AppRole;
import org.ms.authentification_service.entities.AppUser;
import org.ms.authentification_service.services.UserService;
import org.springframework.web.bind.annotation.*;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserServiceREST {

    private UserService userService;
    public final String PREFIXE_JWT="Bearer "; 
    public final String CLE_SIGNATURE ="MaClé";
    @GetMapping
    public List<AppUser> users() {
        return userService.getAllUsers();
    }
    @GetMapping(path="/refreshToken") 
    public void refreshToken (HttpServletRequest request , 
HttpServletResponse response) 
    { 
        // récupérer le header "Authorization" (refresh-token) 
        String refreshToken = request.getHeader("Authorization"); 
        // vérifier l'état du header 
        if (refreshToken!=null && refreshToken.startsWith(PREFIXE_JWT)) 
        { 
            try { 
                //récupérer la valeur du refresh-token 
                String jwtRefresh = 
refreshToken.substring(PREFIXE_JWT.length()); 
           //Préparer une instance du même algorithme de cryptage (HMAC256) 
                Algorithm algo = Algorithm.HMAC256(CLE_SIGNATURE); 
           // vérifier la validité du JWT par la vérification de sa signature 
                JWTVerifier jwtVerifier = JWT.require(algo).build(); 
                //décoder le refresh-JWT 
                DecodedJWT decodedJWT =jwtVerifier.verify(jwtRefresh); 
                //récupérer la valeur de "username" 
                String username = decodedJWT.getSubject(); 
                //Recharger l'utilisateur à partir de la BD 
                AppUser user = userService.getUserByName(username); 
                 
              //récupérer la liste des rôles 
                String[] roles = new String[user.getRoles().size()]; 
                int index=0; 
                for (AppRole r : user.getRoles()) 
                { 
                 roles[index]=r.getRoleName(); 
                 index++;          
                }    
                 
                //Construire le  access JWT 
                String jwtAccessToken = JWT.create() 
                        // stocker le nom de l'utilisateur 
                        .withSubject(user.getUsername()) 
                        // date d'expiration après 1 minute 
                        .withExpiresAt(new 
Date(System.currentTimeMillis()+1*60*1000)) 
                        //url de la reuête d'origine 
                        .withIssuer(request.getRequestURL().toString()) 
                //placer la liste des rôles associés à l'utilisateur courant 
                        .withArrayClaim("roles", roles) 
                        //signer le access JWT avec l'algorithme choisi 
                        .sign(algo); 

                //stocker les deux tokens dans un objet HashMap 
                Map<String,String> mapTokens = new HashMap<>(); 
                mapTokens.put("access-token",jwtAccessToken); 
                mapTokens.put("refresh-token",jwtRefresh); 

                //Spécifier le format du contenu de la réponse 
                response.setContentType("application/json"); 
                //place l'objet HashMap dans le corps de la réponse 
                new 
ObjectMapper().writeValue(response.getOutputStream(),mapTokens); 

            } catch (Exception e) { 
                new RuntimeException(e); 
            } 
        } 
        else 
        { 
            new RuntimeException("Refresh Token non disponible.."); 
        } 
    }
}