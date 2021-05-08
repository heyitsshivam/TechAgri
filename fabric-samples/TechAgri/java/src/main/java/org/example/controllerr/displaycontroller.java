package org.example.controller;

import org.example.ClientApp;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@Controller
public class displaycontroller {

@RequestMapping(value = "/")
public String callll(){
    return "indexxx";
}
    @RequestMapping(value = "/dostuff")
    public ResponseEntity<StreamingResponseBody> handleRequest2()  throws Exception{
        ClientApp clientApp=new ClientApp();
        StreamingResponseBody stream = out -> {

            try {
                out.write((    clientApp.main(null)+ " - <br />").getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }


        };
        return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(stream);
    }

}
