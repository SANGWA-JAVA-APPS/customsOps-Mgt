package com.rusumo.controller;

import com.rusumo.exception.ResourceNotFoundException;
import com.rusumo.models.Mdl_account;
import com.rusumo.DTO.MultipleAccounts;
import com.rusumo.models.Mdl_account_category;
import com.rusumo.models.Mdl_profile;
import com.rusumo.repository.AccountRepository;
import com.rusumo.repository.Account_categoryRepository;
import com.rusumo.repository.ProfileRepository;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;

/**
 *
 * @author SANGWA Emmanuel code [CODEGURU - info@codeguru.com]
 */
@RestController
@RequestMapping("/rusumo_warehouses/api/account")
@CrossOrigin("*")
@Slf4j
public class AccountController {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    ProfileRepository profileRepositpry;

    @Autowired
    Account_categoryRepository account_categoryRepository;

    @ApiOperation("Getting all the Account only")
    @GetMapping("/")

    public ResponseEntity<List<Mdl_account>> getAll() {
        List<Mdl_account> struc = new ArrayList<>();
        accountRepository.findAll().forEach(struc::add);
        if (struc.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(struc, HttpStatus.OK);
    }

    @ApiOperation("Creating a user account")
    @PostMapping("/{categoryId}/{profileId}")
    public ResponseEntity<Mdl_account> createStructure(@PathVariable(value = "categoryId") long categoryId, @PathVariable(value = "profileId") long profileId, @RequestBody Mdl_account mdl_account) {

        Mdl_account_category mdl_account_category = account_categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceAccessException("Not cateogry foudnd: " + categoryId));
        Mdl_profile mdl_profile = profileRepositpry.findById(profileId).orElseThrow(() -> new ResourceAccessException("Profile id " + profileId + " Not found"));;
        mdl_account.setMdl_account_category(mdl_account_category);
        mdl_account.setMdl_profile(mdl_profile);
        return new ResponseEntity<>(accountRepository.save(mdl_account), HttpStatus.CREATED);
    }

    @GetMapping("/{username}/{password}")
    public ResponseEntity<Mdl_account> Login(@PathVariable(name = "username") String username,
            @PathVariable(name = "password") String password) {
        Mdl_account mdl_account = accountRepository.findAccountByUsernamePassword(username, password);
        return new ResponseEntity<>(mdl_account, HttpStatus.OK);
    }

    @PutMapping("/{id}/{categoryId}")
    @ApiOperation(value = "Updating  a single Structure")
    public ResponseEntity<Mdl_account> updateStructure(@PathVariable(value = "id") long id, @PathVariable(value = "categoryId") long categoryId, @RequestBody Mdl_account mdl_account) {
        Mdl_account mdl_account1 = accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account with id =" + id + " not found"));
        mdl_account1.setId(mdl_account.getId());
        mdl_account1.setUsername(mdl_account.getUsername());
        mdl_account1.setPassword(mdl_account.getPassword());
        Mdl_account_category mdl_account_category = account_categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceAccessException("Not cateogry foudnd: " + categoryId));
//        mdl_account1.setMdl_account_category(mdl_account_category);
        mdl_account1.setProfile(mdl_account.getProfile());
        return new ResponseEntity<>(accountRepository.save(mdl_account), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAccount(@PathVariable("id") long id) {
        accountRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    Adding more items at the same time
    @PostMapping("/multiaccount")
    public ResponseEntity<String> multipleaccounts(@RequestBody MultipleAccounts multipleAccounts) {
        List<Mdl_account> accountsList = multipleAccounts.getMultiAccounts();
        try {
            for (Mdl_account mdl_account : accountsList) {
                accountRepository.save(mdl_account);
            }
            ResponseEntity<String> responseEntity = new ResponseEntity<>("Saved", HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            System.out.println("Error " + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
