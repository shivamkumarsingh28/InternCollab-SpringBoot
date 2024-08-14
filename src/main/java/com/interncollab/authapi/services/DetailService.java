package com.interncollab.authapi.services;

import com.interncollab.authapi.payloads.DetailDto;

public interface DetailService {

    // create
    DetailDto createDetail(DetailDto detailDto, Integer authId);

    // update
    DetailDto updateDetail(DetailDto detailDto, Integer authId);

    // delete
    void deleteDetail(Integer detailId);

    // get all Auth detail
    DetailDto getAllDetail();

    // get single detail
    DetailDto getDetailById(Integer detailId);

    // get all detail by authid
    DetailDto getDetailByAuth(Integer authId);
}
