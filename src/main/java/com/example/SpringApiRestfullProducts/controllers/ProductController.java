package com.example.SpringApiRestfullProducts.controllers;

import com.example.SpringApiRestfullProducts.dtos.ProductRecordsDto;
import com.example.SpringApiRestfullProducts.models.ProductModel;
import com.example.SpringApiRestfullProducts.repositories.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    //Métodos
    //Post
    @PostMapping("/products")
    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordsDto productRecordsDto){
        var productModel = new ProductModel();
        BeanUtils.copyProperties(productRecordsDto, productModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(productModel));
    }

    //Metodos get

    //Get All
    @GetMapping("/products")
    public ResponseEntity<List<ProductModel>> getAllProducts(){
        return ResponseEntity.status(HttpStatus.OK).body(productRepository.findAll());
    }

    //Get One - Por meio de chave de produtos
    @GetMapping("/products/{id}")
    public ResponseEntity<Object> getOneproduct(@PathVariable(value="id")UUID id){
        Optional<ProductModel> productO = productRepository.findById(id);
        if(productO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product is not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(productO.get());
    }


}
