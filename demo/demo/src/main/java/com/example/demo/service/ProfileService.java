package com.example.demo.service;

import com.example.demo.dao.api.IProfileDao;
import com.example.demo.dto.filter.ProfileFilter;
import com.example.demo.dto.models.ProfileDto;
import com.example.demo.model.Profile;
import com.example.demo.dto.filter.BaseFilter;
import com.example.demo.model.User;
import com.example.demo.service.api.ICalculationService;
import com.example.demo.service.api.IJournalsFoodService;
import com.example.demo.service.api.IProfileService;
import com.example.demo.service.api.IUserService;
import com.example.security.UserHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProfileService implements IProfileService {

    private final IProfileDao profileDao;
    private final UserHolder userHolder;
    private final IUserService userService;

    public ProfileService(IProfileDao profileDao, UserHolder userHolder, IUserService userService) {
        this.profileDao = profileDao;
        this.userHolder = userHolder;
        this.userService = userService;
    }

    @Override
    public void save(Profile profile) {
        profile.setDtCreate(LocalDateTime.now());
        profile.setDtUpdate(LocalDateTime.now());
        String login = userHolder.getAuthentication().getName();
        User user = userService.findByLogin(login);
        profile.setUser(user);

        profileDao.save(profile);
    }

    @Override
    public void delete(Long id, long dtUpdate) {
        profileDao.deleteById(id);
    }

    @Override
    public Profile get(Long id) {
        return profileDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Profile по id не найден"));
    }

    @Override
    public void update(Profile updateProfile, Long idProfile, long dtUpdate) {
        Profile profile = get(idProfile);

        updateProfile.setId(idProfile);
        updateProfile.setDtCreate(profile.getDtCreate());
        updateProfile.setDtUpdate(LocalDateTime.now());

        String login = userHolder.getAuthentication().getName();
        User user = userService.findByLogin(login);
        updateProfile.setUser(user);

        profileDao.save(updateProfile);
    }

    @Override
    public Page<Profile> getAll(ProfileFilter filter) {
        Pageable pageable = PageRequest.of(filter.getPage(), filter.getSize());
        return profileDao.findAll(pageable);
    }
}
