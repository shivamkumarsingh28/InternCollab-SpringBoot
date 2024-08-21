package com.interncollab.authapi.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interncollab.authapi.entities.Auth;
import com.interncollab.authapi.entities.Detail;
import com.interncollab.authapi.exceptions.ResourceNotFoundException;
import com.interncollab.authapi.payloads.DetailDto;
import com.interncollab.authapi.repository.AuthRepo;
import com.interncollab.authapi.repository.DetailRepo;
import com.interncollab.authapi.services.DetailService;

@Service
public class DetailServiceImpl implements DetailService{

    @Autowired
    private DetailRepo detailRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AuthRepo authRepo;

    @Override
    public DetailDto createDetail(DetailDto detailDto, Integer authId) {
        Auth auth = this.authRepo.findById(authId).orElseThrow(()->new ResourceNotFoundException("Auth", "Auth Id", authId));

        Detail detail = this.modelMapper.map(detailDto, Detail.class);
        detail.setFullName(detailDto.getFullName());
        detail.setContact(detailDto.getContact());
        detail.setAbout(detailDto.getAbout());
        detail.setAddedDate(new Date());
        detail.setAuth(auth);

        Detail newDetail = this.detailRepo.save(detail);
        return this.modelMapper.map(newDetail, DetailDto.class);
    }

    @Override
    public DetailDto updateDetail(DetailDto detailDto, Integer detailId) {

        Detail detail = this.detailRepo.findById(detailId).orElseThrow(()->new ResourceNotFoundException("detail", "detail Id", detailId));

        detail.setFullName(detailDto.getFullName());
        detail.setContact(detailDto.getContact());
        detail.setAbout(detailDto.getAbout());
        detail.setProfilepic(detailDto.getProfilepic());
        detail.setDocument(detailDto.getDocument());
        Detail updateDetail = this.detailRepo.save(detail);
        return this.modelMapper.map(updateDetail, DetailDto.class);
    }

    @Override
    public void deleteDetail(Integer detailId) {
        
        Detail detail = this.detailRepo.findById(detailId).orElseThrow(()-> new ResourceNotFoundException("Detail", "Detail Id", detailId));
        this.detailRepo.delete(detail);
    }

    @Override
    public DetailDto getAllDetail() {
        List<Detail> details = this.detailRepo.findAll();

        List<Detail> detailAuth = details.stream().map((detail) -> this.modelMapper.map(details, Detail.class)).collect(Collectors.toList());
        return this.modelMapper.map(detailAuth, DetailDto.class);
    }

    @Override
    public DetailDto getDetailById(Integer detailId) {

        Detail detail = this.detailRepo.findById(detailId).orElseThrow(()-> new ResourceNotFoundException("Detail", "Detail Id", detailId));
        return this.modelMapper.map(detail, DetailDto.class);
    }

    @Override
    public DetailDto getDetailByAuth(Integer authId) {

        Auth auth = this.authRepo.findById(authId).orElseThrow(()-> new ResourceNotFoundException("Auth","Auth Id", authId));
        Detail details = this.detailRepo.findByAuth(auth);
        return this.modelMapper.map(details, DetailDto.class);
    }
    
}
