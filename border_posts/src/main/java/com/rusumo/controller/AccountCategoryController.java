package com.rusumo.controller;

import com.rusumo.exception.ResourceNotFoundException;
import com.rusumo.models.Mdl_account_category;
import com.rusumo.DTO.MultipleAccount_categorys;
import com.rusumo.repository.Account_categoryRepository;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/rusumo_warehouses/api/account_category")
@Slf4j
public class AccountCategoryController {

    @Autowired
    Account_categoryRepository account_categoryRepository;
    @ApiOperation("Getting all the Account_category only")
    @GetMapping("/")
    public ResponseEntity<List<Mdl_account_category>> getAll() {
        List<Mdl_account_category> categories = new ArrayList<>();
        account_categoryRepository.findAll().forEach(categories::add);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

//    @ApiOperation("Getting all the Account_category only")
//    @GetMapping("/")
//    public ResponseEntity<Map<String, Object>> getAll(@RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "3") int size) {
//
//        Map<String, Object> response = new HashMap<>();
//        Pageable paging = PageRequest.of(page, size);
//        Page<Mdl_account_category> pgAccountCategory;
//        pgAccountCategory = account_categoryRepository.findAll(paging);
//        List<Mdl_account_category> struc = new ArrayList<>();
//        struc = pgAccountCategory.getContent();
//        response.put("accountCategories", struc);
//        response.put("currentPage", pgAccountCategory.getNumber());
//        response.put("totalItems", pgAccountCategory.getTotalElements());
//        response.put("totalPages", pgAccountCategory.getTotalPages());
//
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
    @GetMapping("/{catId}")
    public ResponseEntity<Mdl_account_category> getCategoryById(@PathVariable(name = "catId") long catId) {
        Mdl_account_category  mdl_account_category = account_categoryRepository.findById(catId).orElseThrow(() -> new ResourceAccessException("Id Not found"));
        return new ResponseEntity<>(mdl_account_category, HttpStatus.OK);

    }

    @ApiOperation("Total categries")
    @GetMapping("/count")
    public long countAll() {
        return account_categoryRepository.count();
    }

    @ApiOperation("Creating a user category")
    @PostMapping("/")
    public ResponseEntity<Mdl_account_category> createStructure(@RequestBody @Valid Mdl_account_category mdl_account_category) {
        return new ResponseEntity<>(account_categoryRepository.save(mdl_account_category), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Updating  a single Structure")
    public ResponseEntity<Mdl_account_category> updateStructure(@PathVariable(value = "id") long id, @RequestBody Mdl_account_category mdl_account_category) {
        Mdl_account_category mdl_account_category1 = account_categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account category " + id + " not found"));
        mdl_account_category1.setId(mdl_account_category.getId());
        mdl_account_category1.setName(mdl_account_category.getName());
        return new ResponseEntity<>(account_categoryRepository.save(mdl_account_category), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAccount_category(@PathVariable("id") long id) {
        Mdl_account_category mdl_account_category1 = account_categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account category " + id + " not found"));
        account_categoryRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    Adding more items at the same time
    @PostMapping("/multiaccount_category")
    public ResponseEntity<String> multipleaccount_categorys(@RequestBody MultipleAccount_categorys multipleAccount_categorys) {
        List<Mdl_account_category> account_categorysList = multipleAccount_categorys.getMultiAccount_categorys();
        for (Mdl_account_category mdl_account_category : account_categorysList) {
            account_categoryRepository.save(mdl_account_category);
        }
        ResponseEntity<String> responseEntity = new ResponseEntity<>("Saved", HttpStatus.OK);
        return responseEntity;
    }

}
