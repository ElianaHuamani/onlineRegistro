package com.nisum.onlineRegistro.view.rest;

import com.nisum.onlineRegistro.model.Client;
import com.nisum.onlineRegistro.service.ClientService;
import com.nisum.onlineRegistro.view.vo.ResponseVO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/client")
public class ClientRest {

    private final ClientService clientService;

    public ClientRest(ClientService clientService) {
        this.clientService=clientService;
    }

    @GetMapping
    public ResponseVO<List<Client>> searchClients(){
        ResponseVO<List<Client>> clients = this.clientService.searchClients();
        return clients;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseVO<Client> createClient(@RequestBody Client client){
        client.setId(null);
        ResponseVO<Client> responseVo = this.clientService.createClient(client);
        return responseVo;
    }
    @PutMapping(consumes = "application/json", produces = "application/json")
    public ResponseVO<Client> editClient(@RequestBody Client client){
        ResponseVO<Client> clientResult= this.clientService.editClient(client);
        return clientResult;
    }

    @DeleteMapping(consumes = "application/json", produces = "application/json")
    public ResponseVO<String> removeClient(@RequestBody Client client){
        return this.clientService.removeClient(client);
    }


    @GetMapping(value = "/csrf")
    public ResponseVO<String> createCsrf(){
        return this.clientService.createCsrf();
    }

}
