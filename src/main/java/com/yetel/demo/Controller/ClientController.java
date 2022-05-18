package com.yetel.demo.Controller;

import com.yetel.demo.Entity.Client;
import com.yetel.demo.Entity.Contract;
import com.yetel.demo.Entity.Provider;
import com.yetel.demo.Entity.TaxesPayments;
import com.yetel.demo.Repositories.*;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@CrossOrigin (origins = "http://localhost:8090")
@RequestMapping("/client")
public class ClientController {
    private final ClientRepository clientRep;
    private final TaxesPaymentsRepository taxesRep;
    private final ContractRepository contractRep;
    private final PackagesRepository packageRep;
    private final ProviderRepository providerRep;

    public String Token = "5432!";


    public Boolean Login(String Token) {
        try {
            if (this.Token.equals(Token)) {
                return Boolean.TRUE;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Boolean.FALSE;
    }

    @GetMapping("/clients")
    public ResponseEntity<?> getAllClient(String token) {
        if (!Login(token))
       return ResponseEntity.ok("Wrong token");
        return ResponseEntity.ok(clientRep.findAll());
    }

    @GetMapping("/taxes")
    public  ResponseEntity<?> getAllTaxesPayments(String token) {
        if (!Login(token))
            return ResponseEntity.ok("Wrong token");
        return ResponseEntity.ok(taxesRep.findAll());
    }

    @GetMapping("/contracts")
    public ResponseEntity<?>  getAllContracts(String token) {
        if (!Login(token))
            return ResponseEntity.ok("Wrong token");
        return ResponseEntity.ok(contractRep.findAll());
    }

    @GetMapping("/find/name")
    public ResponseEntity<?> findClient(String firstName, String lastName, String token) {
        try {
            if (!Login(token))
                return ResponseEntity.ok("Wrong token");
            Optional<Client> client = clientRep.findClientByFirstNameAndLastName(firstName, lastName);
            return client.isPresent() ? ResponseEntity.ok(client.get()) : ResponseEntity.ok("Not Found");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("Exception thrown");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteClient(Long id, String token) {
        try {
            if (!Login(token))
                return ResponseEntity.ok("Wrong token");
            Optional<Client> result = clientRep.findById(id);
            if (result.isEmpty()) {
                return ResponseEntity.ok("Client not found");
            }

            clientRep.delete(result.get());
            return ResponseEntity.ok(result.get().getFirstName() + " " + result.get().getLastName() + " was deleted");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("Exception thrown");
    }

    @DeleteMapping("/delete/taxes")
    public ResponseEntity<?> deleteTax(Long id, String token) {
        try {
            if (!Login(token))
                return ResponseEntity.ok("Wrong token");
            Optional<TaxesPayments> result = taxesRep.findById(id);
            if (result.isEmpty()) {
                return ResponseEntity.ok("Tax not found");
            }

            taxesRep.delete(result.get());
            return ResponseEntity.ok(result.get().getClients().getFirstName() + "/'s  tax with id:"
                    + result.get().getId() + " was deleted");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("Exception thrown");
    }

    @DeleteMapping("/delete/contract")
    public ResponseEntity<?> deleteContract(Long NumOfContract, String token) {
        try {
            if (!Login(token))
                return ResponseEntity.ok("Wrong token");
            Optional<Contract> result = contractRep.findContractByNumOfContract(NumOfContract);
            if (result.isEmpty()) {
                return ResponseEntity.ok("Contract not found");
            }

            contractRep.delete(result.get());
            return ResponseEntity.ok("Contract №" + result.get().getNumOfContract() + " was deleted");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("Exception thrown");
    }

    @PostMapping("/save")
    public ResponseEntity<?> newClient(String firstName, String lastName, String egn, String email, String token) {
        try {

            if (!Login(token))
                return ResponseEntity.ok("Wrong token");
            Optional<Client> client = clientRep.findClientByEgn(egn);
            if (client.isPresent()) {
                return ResponseEntity.ok("Client already exists");
            }
            return ResponseEntity.ok(clientRep.save(new Client(firstName, lastName, egn, email)).getFirstName() + " is added!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("Exception thrown");
    }

    @PostMapping("/save/contract")
    public ResponseEntity<?> newContract(Long NumOfContract, Date startDate, Date endDate, Long clientId, Long packageId, Long providersId, String token) {
        try {
            if (!Login(token))
                return ResponseEntity.ok("Wrong token");
            Optional<Contract> contract = contractRep.findContractByNumOfContract(NumOfContract);
            if (contract.isPresent()) {
                return ResponseEntity.ok("Contract already exists");
            }

            Contract contracts = new Contract(NumOfContract, startDate, endDate);
            contracts.setClients(clientRep.findById(clientId).isPresent() ? clientRep.findById(clientId).get() : null);
            contracts.setPackages(packageRep.findById(packageId).isPresent() ? packageRep.findById(packageId).get() : null);
            contracts.setProvider(providerRep.findById(providersId).isPresent() ? providerRep.findById(providersId).get() : null);


            return ResponseEntity.ok("Contract with №" + contractRep.save(contracts)
                    .getNumOfContract() + " is added!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("Exception thrown");
    }

    @PostMapping("/save/tax")
    public ResponseEntity<?> newTax(Double paymentAmount, Date dateOfPayment, Long clientId, Long providerId, String token) {
        try {
            if (!Login(token))
                return ResponseEntity.ok("Wrong token");
            Optional<TaxesPayments> tax = taxesRep.findTaxesPaymentsByDateOfPayment(dateOfPayment);
            if (tax.isPresent()) {
                return ResponseEntity.ok("Tax already exists");
            }
            TaxesPayments newTax = new TaxesPayments(paymentAmount, dateOfPayment);
            newTax.setClients(clientRep.findById(clientId).isPresent() ? clientRep.findById(clientId).get() : null);
            newTax.setProvider(providerRep.findById(providerId).isPresent() ? providerRep.findById(providerId).get() : null);

            return ResponseEntity.ok("New tax with id = " + taxesRep.save(newTax).getId() + " is added!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("Exception thrown");
    }




    @PostMapping("/tax/changer")
    public ResponseEntity<?> taxChanger(Double value, String token)
    {
        try {
            if (!Login(token))
                return ResponseEntity.ok("Wrong token");

            if (providerRep.findAll().isEmpty())
            {
                return ResponseEntity.ok("No providers");
            }else{
                for (Provider providers : providerRep.findAll()) {
                    Double price = providers.getPrice();
                    providers.setPrice(price*(value/100) + price);
                    providerRep.save(providers);
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.ok("Exception thrown");
        }
        return ResponseEntity.ok("Ready");
    }
}
