package com.example.demo.controller.restController;

import com.example.demo.dto.filter.ProfileFilter;
import com.example.demo.dto.models.ProfileDto;
import com.example.demo.model.Profile;
import com.example.demo.dto.filter.BaseFilter;
import com.example.demo.service.api.ICalculationService;
import com.example.demo.service.api.IProfileService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneId;

@RestController
@RequestMapping("api/profile")
public class ProfileRestController {
    private final IProfileService profileService;
    private final ICalculationService calculationService;

    public ProfileRestController(IProfileService profileService, ICalculationService calculationService) {
        this.profileService = profileService;
        this.calculationService = calculationService;
    }

    @GetMapping
    public ResponseEntity<Page<Profile>> getAll(@RequestParam(value = "page", required = false) Integer page,
                                                @RequestParam(value = "size", required = false) Integer size) {

        ProfileFilter filter = new ProfileFilter();
        filter.setPage(page);
        filter.setSize(size);

        Page<Profile> profiles = profileService.getAll(filter);
        return new ResponseEntity<>(profiles, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Profile profile) {
        profileService.save(profile);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileDto> get(@PathVariable Long id) {
        Profile profile = profileService.get(id);

        ProfileDto profileDto = calculationService.normaUser(profile);
        profileDto.setProfile(profile);
        calculationService.normaEat(profileDto,id);

        long microseconds = profile.getDtUpdate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        profileDto.setMicroseconds(microseconds);

        return new ResponseEntity<>(profileDto, HttpStatus.OK);
    }

    @PutMapping("/{id}/dt_update/{dt_update}")
    public ResponseEntity<?> update(@RequestBody Profile updateProfile, @PathVariable Long id,
                                    @PathVariable("dt_update") long dtUpdate) {

        profileService.update(updateProfile, id, dtUpdate);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}/dt_update/{dt_update}")
    public ResponseEntity<?> delete(@PathVariable Long id, @PathVariable("dt_update") long dtUpdate) {

        profileService.delete(id, dtUpdate);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
