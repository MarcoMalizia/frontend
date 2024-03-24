package com.marco.frontend;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;


@Controller
public class MainController {
     
    @GetMapping("/login")
    public String showForm(Model model) {
        Data data = new Data();
        model.addAttribute("data", data);
         
        return "login";
    }
    
    @PostMapping("/login")
    public String submitForm(@ModelAttribute("data") Data data) throws URISyntaxException {
        System.out.println(data);
        
        WebClient client = WebClient.create();
       
        MultiValueMap<String, String> bodyValues = new LinkedMultiValueMap<>();

        bodyValues.add("email", data.getEmail());
        bodyValues.add("password", data.getPassword());
        
        System.out.println(bodyValues);

        String response = client.post()
            .uri(new URI("https://authenticator.happyforest-825d7b85.northeurope.azurecontainerapps.io/api/auth"))
            .header("No_Auth", "")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .accept(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromFormData(bodyValues))
            .retrieve()
            .bodyToMono(String.class)
            .block();
        
		System.out.println(response);
		if (response.equals("Authorized")) {
			return "authorized";
		}else {
			return "unauthorized";
		}
    }
     
}
