package com.udacity.jwdnd.course1.cloudstorage.services.serviceInterface;

import com.udacity.jwdnd.course1.cloudstorage.entity.CredentialEntity;

import java.util.List;
import java.util.Map;

public interface ICredentialService {
    int createOrUpdateCredential(CredentialEntity credential, Integer userId) throws Exception;

    List<CredentialEntity> findAllByUserId(Integer userId);

    int deleteByCredentialId(Integer credentialId, Integer userId);

    Map<Integer, String> decryptPasswords(List<CredentialEntity> credentialList);
}
