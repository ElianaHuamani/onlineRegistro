package com.nisum.onlineRegistro.service;

import com.nisum.onlineRegistro.model.Client;
import com.nisum.onlineRegistro.model.Phone;
import com.nisum.onlineRegistro.repository.ClientRepository;
import com.nisum.onlineRegistro.repository.PhoneRepository;
import com.nisum.onlineRegistro.util.Utils;
import com.nisum.onlineRegistro.view.vo.ClientVO;
import com.nisum.onlineRegistro.view.vo.ResponseVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly=true)
public class ClientService {

    private final ClientRepository clientRepository;
    private final PhoneRepository phoneRepository;


    public ClientService(ClientRepository clientRepository, PhoneRepository phoneRepository) {
        this.clientRepository=clientRepository;
        this.phoneRepository=phoneRepository;
    }

    public ResponseVO<List<Client>> searchClients(){
        ResponseVO<List<Client>> responseVo = new ResponseVO<List<Client>>();
        List<Client> clients = null;
        clients = this.clientRepository.findAll();
        responseVo.setData(clients);
        return responseVo;
    }

    @Transactional
    public ResponseVO<Client> createClient(Client client){
        ResponseVO<Client> clientView = new ResponseVO<Client>();
        try {
            //validating client
            if(!validateClient(client,clientView))
                return clientView;

            if(existEmail(client,clientView))
                return clientView;

            //client
            client.setIsActive("ACTIVE");
            client.setCreated(new Date());
            client.setLast_login(new Date());
            this.clientRepository.save(client);

            //phone
            List<Phone> phones = client.getPhones();
            for (Phone phone:phones) {
                phone.setClient(client);
                this.phoneRepository.save(phone);
            }

            clientView.setData(client);
            clientView.setMessage("Client and phones have been saved successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return clientView;
    }

    @Transactional
    public ResponseVO<Client> editClient(Client client){
        ResponseVO<Client> clientView = new ResponseVO<Client>();
        try {
            if (client.getId() != null && !client.getId().isEmpty()) {
                boolean clientModified=false;
                boolean phoneModified=false;

                //validating client: password format and email format
                if(!validateClient(client,clientView))
                    return clientView;

                //validating phone ids
                List<Phone> phones = client.getPhones();
                for (Phone phone: phones) {
                    if (!(phone.getId() != null && !phone.getId().isEmpty())) {
                        clientView.setMessage("Phone id is required for updating.");
                        return clientView;
                    }
                }

                Client clientFound = this.clientRepository.findByClientId(client.getId());
                if(clientFound!=null) {
                    String email=client.getEmail();
                    String emailFound=clientFound.getEmail();
                    if(!email.equals(emailFound)){
                        //exist email
                        if(existEmail(client,clientView))
                            return clientView;
                    }

                    clientModified = clientFound.modified(client);
                    List<Phone> phonesFound = clientFound.getPhones();
                    for (Phone phoneFound: phonesFound) {
                        for (Phone phone: phones) {
                            if(phoneFound.getId().equals(phone.getId())) {
                                phoneModified = phoneFound.modified(phone);
                            }
                        }
                    }
                    if(clientModified || phoneModified) {
                        clientFound.setModified(new Date());
                        clientFound.setLast_login(new Date());
                        this.clientRepository.save(clientFound);
                        clientView.setData(client);
                    }
                } else {
                    clientView.setMessage("Client is not found.");
                }
            } else {
                clientView.setMessage("Client id is required for updating.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            clientView.setMessage("Server error");
        }
        return clientView;
    }

    @Transactional
    public ResponseVO<String> removeClient(Client client){
        ResponseVO<String> clientView = new ResponseVO<String>();
        try {
            if (client.getId() != null && !client.getId().isEmpty()) {
                Client clientFound = this.clientRepository.findByClientId(client.getId());
                if(clientFound != null){
                    this.clientRepository.delete(clientFound);
                    clientView.setMessage("Id " + client.getId() + " was removed.");
                } else {
                    clientView.setMessage("Client is not found.");
                }
            } else {
                clientView.setMessage("Client id is required for removing.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            clientView.setMessage("Server error.");
        }
        return clientView;
    }

    private boolean validateClient(Client client, ResponseVO<Client> clientView) {
        //email format
        if (client.getEmail() != null && client.getEmail().trim().length() > 0 && !Utils.validateEmail(client.getEmail())) {
            clientView.setMessage("Email does not have the email format.");
            return false;
        }

        //password format
        if (client.getPassword() != null && client.getPassword().trim().length() > 0 && !Utils.validatePassword(client.getPassword())) {
            clientView.setMessage("Password should have at least 1 digit, 1 uppercase, 1 lowercase and no space.");
            return false;
        }

        return true;
    }

    private boolean existEmail(Client client, ResponseVO<Client> clientView) {
        List<Client> emailfound = this.clientRepository.findClientByEmail(client.getEmail());
        if (emailfound != null && !emailfound.isEmpty()) {
            clientView.setMessage("Email is already registered.");
            return true;
        }
        return false;
    }

    public ResponseVO<String> createCsrf(){
        ResponseVO<String> response = new ResponseVO<String>();
        response.setData(Utils.generateCsrfToken());
        response.setMessage("Csrf has been created successfully.");
        return response;
    }

}
