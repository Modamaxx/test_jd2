package com.example.demo.controller.restController;

import com.example.demo.dto.filter.JournalFilter;
import com.example.demo.dto.models.JournalDto;
import com.example.demo.model.Journal;
import com.example.demo.dto.filter.BaseFilter;
import com.example.demo.service.api.IActiveService;
import com.example.demo.service.api.IJournalsFoodService;
import com.example.demo.service.api.IWeightService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneId;
import java.util.List;

@RestController
@RequestMapping("api/profile/{id_profile}/journal/food")
public class JournalsFoodRestController {
    private final IJournalsFoodService journalService;

    public JournalsFoodRestController(IJournalsFoodService journalService, IWeightService weightService, IActiveService activeService) {
        this.journalService = journalService;
    }

    @GetMapping
    public ResponseEntity<Page<Journal>> getAll(@PathVariable("id_profile") Long idProfile,
                                                @RequestParam(value = "page", required = false) Integer page,
                                                @RequestParam(value = "size", required = false) Integer size) {
        JournalFilter filter = new JournalFilter();
        filter.setPage(page);
        filter.setSize(size);

        Page<Journal> journals = journalService.getAll(idProfile, filter);
        return new ResponseEntity<>(journals, HttpStatus.OK);
    }

    @GetMapping("/byDay/{day}")
    public ResponseEntity<List<Journal>> getOneDay(@PathVariable("id_profile") Long idProfile, @PathVariable long day) {
        List<Journal> journals = journalService.getOneDay(idProfile, day);
        return new ResponseEntity<>(journals, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@PathVariable("id_profile") Long idProfile, @RequestBody Journal journal) {
        journalService.save(journal,idProfile);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id_food}")
    public ResponseEntity<JournalDto> get(@PathVariable("id_profile") Long idProfile,
                                       @PathVariable("id_food") Long idFood) {

        Journal journal = journalService.get(idProfile, idFood);
        long microseconds= journal.getDtUpdate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        JournalDto journalDto=new JournalDto();
        journalDto.setJournal(journal);
        journalDto.setMicroseconds(microseconds);

        return new ResponseEntity<>(journalDto, HttpStatus.OK);
    }

    @PutMapping("/{id_food}/dt_update/{dt_update}")
    public ResponseEntity<?> update(@PathVariable("id_profile") Long idProfile,@PathVariable("id_food") Long idFood,
                                    @PathVariable("dt_update") long dtUpdate,
                                    @RequestBody Journal updateJournal) {
        journalService.update(updateJournal,idFood,idProfile, dtUpdate);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id_food}/dt_update/{dt_update}")
    public ResponseEntity<?> delete(@PathVariable("id_profile") Long idProfile,
                                    @PathVariable("id_food") Long idFood,
                                    @PathVariable("dt_update") long dtUpdate) {
        journalService.delete(idFood,idProfile, dtUpdate);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
