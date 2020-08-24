package com.nisum.onlineRegistro.service;

import com.nisum.onlineRegistro.repository.ClientRepository;
import com.nisum.onlineRegistro.repository.PhoneRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly=true)
public class PhoneService {
    private final PhoneRepository phoneRepository;

    public PhoneService(PhoneRepository phoneRepository) {
        this.phoneRepository=phoneRepository;
    }



}
