package com.nisum.onlineRegistro.repository;

import com.nisum.onlineRegistro.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client,String> {

    public List<Client> findClientByEmail(String accountName);

    public Client findByClientId(String clientId);

}
