package com.yetel.demo.Controller;

import com.yetel.demo.Entity.Channel;
import com.yetel.demo.Entity.Packages;
import com.yetel.demo.Entity.Provider;
import com.yetel.demo.Repositories.ChannelRepository;
import com.yetel.demo.Repositories.PackagesRepository;
import com.yetel.demo.Repositories.ProviderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/provider")
@CrossOrigin("http://localhost:8090")
public class ProviderController {
    private final ProviderRepository providerRep;
    private final ChannelRepository channelRep;
    private final PackagesRepository packageRep;

    public String Token = "5432!";

    @GetMapping("/login")
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

    @PostMapping("save/channel")
    public ResponseEntity<?> newChannel(String name, Double price, String token)
    {
        try {
            if (!Login(token))
                return ResponseEntity.ok("Wrong token");
            Optional<Channel> channel = channelRep.findChannelByName(name);
            if (channel.isPresent()) {
                return ResponseEntity.ok("Channel already exists");
            }

            return ResponseEntity.ok("Channel " + channelRep.save(new Channel(name,price)).getName() + " was saved!");
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok("Exception thrown");

    }

    @PostMapping("save/package")
    public ResponseEntity<?> newPackage(String name, Double price, String token)
    {
        try {
            if (!Login(token))
                return ResponseEntity.ok("Wrong token");
            Optional<Packages> Package = packageRep.findPackagesByName(name);
            if (Package.isPresent()) {
                return ResponseEntity.ok("Package already exists");
            }

            return ResponseEntity.ok("Package " + packageRep.save(new Packages(name, price)).getName() + " was saved!");
        } catch (Exception e) {
                e.printStackTrace();
        }
        return ResponseEntity.ok("Exception thrown");
    }

    @PostMapping("save")
    public ResponseEntity<?> newProvider(String name, Double price, String token)
    {
        try {
            if (!Login(token))
                return ResponseEntity.ok("Wrong token");
            Optional<Provider> Provider = providerRep.findProviderByName(name);
            if (Provider.isPresent()) {
                return ResponseEntity.ok("Provider already exists");
            }

            return ResponseEntity.ok("Provider " + providerRep.save(new Provider(name, price)).getName() + " was saved!");
        } catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok("Exception thrown");
    }

    @DeleteMapping("/delete/channel")
    public ResponseEntity<?> deleteChannel(Long id, String token) {
        try {
            if (!Login(token))
                return ResponseEntity.ok("Wrong token");
            Optional<Channel> result = channelRep.findById(id);
            if (result.isEmpty()) {
                return ResponseEntity.ok("Channel not found");
            }
            channelRep.delete(result.get());
            return ResponseEntity.ok(result.get().getName() + " was deleted");
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok("Exception thrown");
    }

    @DeleteMapping("/delete/package")
    public ResponseEntity<?> deletePackage(Long id, String token)
    {
        try {
            if (!Login(token))
                return ResponseEntity.ok("Wrong token");
        Optional<Packages> result = packageRep.findById(id);
        if (result.isEmpty()) {
            return ResponseEntity.ok("Package not found");
        }
        packageRep.delete(result.get());
        return ResponseEntity.ok(result.get().getName() + " was deleted");
         }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok("Exception thrown");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteProvider(Long id, String token)
    {
        try {
            if (!Login(token))
                return ResponseEntity.ok("Wrong token");
            Optional<Provider> result = providerRep.findById(id);
            if (result.isEmpty()) {
                return ResponseEntity.ok("Provider not found");
            }
            providerRep.delete(result.get());
            return ResponseEntity.ok(result.get().getName() + " was deleted");
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok("Exception thrown");
    }

    @GetMapping("/fetch")
    public ResponseEntity<?> getAllProviders(String token){
        if (!Login(token))
            return ResponseEntity.ok("Wrong token");
        return ResponseEntity.ok(providerRep.findAll());
    }

    @GetMapping("/fetch/Package")
    public ResponseEntity<?> getAllPackages(String token){
        if (!Login(token))
            return ResponseEntity.ok("Wrong token");
        return ResponseEntity.ok(packageRep.findAll());
    }

    @GetMapping("/fetch/Channel")
    public ResponseEntity<?> getAllChannels(String token){
        if (!Login(token))
            return ResponseEntity.ok("Wrong token");
        return ResponseEntity.ok(channelRep.findAll());
    }



}
