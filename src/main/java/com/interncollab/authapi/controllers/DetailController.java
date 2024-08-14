package com.interncollab.authapi.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.interncollab.authapi.payloads.DetailDto;
import com.interncollab.authapi.services.DetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;




@RestController
@RequestMapping("/api/")
public class DetailController {

    @Autowired
    private DetailService detailService;
    
    @PostMapping("/auth/{authId}/detail")
    public ResponseEntity<DetailDto> createDetail(@RequestBody DetailDto detailDto, @PathVariable Integer authId) {
        DetailDto createDetail = this.detailService.createDetail(detailDto, authId);
        return new ResponseEntity<DetailDto>(createDetail, HttpStatus.CREATED);
    }

    @GetMapping("/auth/{authId}/detail")
    public ResponseEntity<DetailDto> getDetailByAuthId(@PathVariable Integer authId) {
        // System.out.println(authId);
        DetailDto getDetail = this.detailService.getDetailByAuth(authId);
        return ResponseEntity.ok(getDetail);
    }
    
    @PutMapping("/detail/{detailId}")
    public ResponseEntity<DetailDto> updateDetail(@RequestBody DetailDto detailDto, @PathVariable Integer detailId) {
        DetailDto updateDetail = this.detailService.updateDetail(detailDto, detailId);
        return new ResponseEntity<DetailDto>(updateDetail, HttpStatus.OK);
    }

    @GetMapping("/detail/{detailId}")
    public ResponseEntity<DetailDto> getDetailById(@PathVariable Integer detailId) {
        // System.out.println(authId);
        // DetailDto getDetail = this.detailService.getDetailByAuth(detailId);
        DetailDto getdetail = this.detailService.getDetailById(detailId);
        return ResponseEntity.ok(getdetail);
    }

    @GetMapping("/detail")
    public ResponseEntity<DetailDto> getAllDetail() {
        // System.out.println(authId);
        DetailDto getDetails = this.detailService.getAllDetail();
        return ResponseEntity.ok(getDetails);
    }
}
