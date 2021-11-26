package com.example.demo.controller.restController;

import com.example.demo.dto.filter.ActiveFilter;
import com.example.demo.dto.filter.ESortDirection;
import com.example.demo.dto.models.ActiveDto;
import com.example.demo.model.Active;
import com.example.demo.service.api.IActiveService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneId;

@RestController
@RequestMapping("api/profile/{id_profile}/journal/active")
public class ActiveRestController {
    private final IActiveService activeService;

    public ActiveRestController(IActiveService activeService) {
        this.activeService = activeService;
    }

    @GetMapping
    public ResponseEntity<Page<Active>> getAll(@PathVariable("id_profile") Long idProfile,
                                               @RequestParam(value = "page", required = false) Integer page,
                                               @RequestParam(value = "size", required = false) Integer size,
                                               @RequestParam(value = "dt_start", required = false) Long dtStart,
                                               @RequestParam(value = "dt_end", required = false) Long dtEnd) {

        ActiveFilter activeFilter = new ActiveFilter(page, size, ESortDirection.DESC, dtStart, dtEnd);
        Page<Active> actives = activeService.getAll(activeFilter,idProfile);
        return new ResponseEntity<>(actives, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@PathVariable("id_profile") Long idProfile, @RequestBody Active active) {
        activeService.save(active,idProfile);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id_active}")
    public ResponseEntity<ActiveDto> get(@PathVariable("id_profile") Long idProfile,
                                      @PathVariable("id_active") Long idActive) {
        Active actives = activeService.get(idActive,idProfile);
        long microseconds= actives.getDtUpdate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        ActiveDto activeDto=new ActiveDto();
        activeDto.setActive(actives);
        activeDto.setMicroseconds(microseconds);
        return new ResponseEntity<>(activeDto, HttpStatus.OK);
    }

    @PutMapping("/{id_active}/dt_update/{dt_update}")
    public ResponseEntity<?> update(@PathVariable("id_profile") Long idProfile,
                                    @PathVariable("id_active") Long idActive,
                                    @PathVariable("dt_update") long dtUpdate,
                                    @RequestBody Active updateActive) {
        activeService.update(updateActive,idActive,idProfile,dtUpdate);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id_active}/dt_update/{dt_update}")
    public ResponseEntity<?> delete(@PathVariable("id_profile") Long idProfile,
                                    @PathVariable("id_active") Long idActive,
                                    @PathVariable("dt_update") long dtUpdate) {
        activeService.delete(idActive,idProfile,dtUpdate);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
