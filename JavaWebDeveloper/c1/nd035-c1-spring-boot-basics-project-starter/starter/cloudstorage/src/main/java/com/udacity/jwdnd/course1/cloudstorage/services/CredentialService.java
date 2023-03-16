package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.entity.CredentialEntity;
import com.udacity.jwdnd.course1.cloudstorage.entity.NoteEntity;
import com.udacity.jwdnd.course1.cloudstorage.exception.DataNotFoundException;
import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.services.serviceInterface.ICredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CredentialService implements ICredentialService {
    @Autowired
    private CredentialMapper credentialMapper;

    @Autowired
    private EncryptionService encryptionService;

    @Override
    public int createOrUpdateCredential(CredentialEntity credentialForSave, Integer userId) throws Exception {
        if (Objects.isNull(credentialForSave.getCredentialId())) {
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16];
            random.nextBytes(salt);
            String key = Base64.getEncoder().encodeToString(salt);
            String encryptPassword = encryptionService.encryptValue(credentialForSave.getPassword(), key);

            credentialForSave.setKey(key);
            credentialForSave.setPassword(encryptPassword);
            credentialForSave.setUserId(userId);

            return credentialMapper.insert(credentialForSave);
        }

        CredentialEntity credentialEntity = credentialMapper.findByCredentialId(credentialForSave.getCredentialId()).orElseThrow();
        String hashPassword = encryptionService.encryptValue(credentialForSave.getPassword(), credentialEntity.getKey());
        credentialForSave.setPassword(hashPassword);
        return credentialMapper.update(credentialForSave);
    }

    @Override
    public List<CredentialEntity> findAllByUserId(Integer userId) {
        return credentialMapper.findAllByUserid(userId);
    }

    @Override
    public int deleteByCredentialId(Integer credentialId, Integer userId) {
        CredentialEntity credential = credentialMapper.findByCredentialId(credentialId)
                .orElseThrow(() -> new DataNotFoundException("This credential does not exist!!!"));

        if(credential.getUserId() != userId) {
            throw new AccessDeniedException("Access denied!!!");
        }

        return credentialMapper.deleteByCredentialId(credentialId);
    }

    @Override
    public Map<Integer, String> decryptPasswords (List<CredentialEntity> credentialList){
        return credentialList
                .stream()
                .collect(Collectors.toMap(e -> e.getCredentialId(),
                        e -> encryptionService.decryptValue(e.getPassword(), e.getKey())
                ));
    }
}
