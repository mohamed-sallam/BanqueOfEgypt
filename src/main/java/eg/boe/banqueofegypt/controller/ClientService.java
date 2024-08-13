package eg.boe.banqueofegypt.controller;

import eg.boe.banqueofegypt.service.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public interface ClientService {
    private final ClientRepository clientRepository;
}
