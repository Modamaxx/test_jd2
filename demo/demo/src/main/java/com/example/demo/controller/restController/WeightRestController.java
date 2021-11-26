package com.example.demo.controller.restController;

import com.example.demo.dto.filter.ESortDirection;
import com.example.demo.dto.filter.WeightFilter;
import com.example.demo.dto.models.ActiveDto;
import com.example.demo.dto.models.WeightDto;
import com.example.demo.model.Weight;
import com.example.demo.service.api.IWeightService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneId;

@RestController
@RequestMapping("api/profile/{id_profile}/journal/weight")
public class WeightRestController {
    private final IWeightService weightService;

    public WeightRestController(IWeightService weightService) {
        this.weightService = weightService;
    }

    @GetMapping
    public ResponseEntity<Page<Weight>> getAll(@PathVariable("id_profile") Long idProfile,
                                               @RequestParam(value = "page", required = false) Integer page,
                                               @RequestParam(value = "size", required = false) Integer size,
                                               @RequestParam(value = "dt_start", required = false) Long dtStart,
                                               @RequestParam(value = "dt_end", required = false) Long dtEnd) {

        WeightFilter weightFilter = new WeightFilter(page, size, ESortDirection.DESC, dtStart, dtEnd);
        Page<Weight> weights = weightService.getAll(weightFilter, idProfile);
        return new ResponseEntity<>(weights, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@PathVariable("id_profile") Long idProfile, @RequestBody Weight weight) {
        weightService.save(weight, idProfile);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id_weight}")
    public ResponseEntity<WeightDto> get(@PathVariable("id_profile") Long idProfile,
                                      @PathVariable("id_weight") Long idWeight) {
        Weight weights = weightService.get(idWeight, idProfile);
        long microseconds= weights.getDtUpdate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        WeightDto weightDto=new WeightDto();
        weightDto.setWeight(weights);
        weightDto.setMicroseconds(microseconds);
        return new ResponseEntity<>(weightDto, HttpStatus.OK);
    }

    @PutMapping("/{id_weight}/dt_update/{dt_update}")
    public ResponseEntity<?> update(@PathVariable("id_profile") Long idProfile,
                                    @PathVariable("id_weight") Long idWeight,
                                    @PathVariable("dt_update") long dtUpdate,
                                    @RequestBody Weight updateWeight) {
        weightService.update(updateWeight, idWeight, idProfile,dtUpdate);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id_weight}/dt_update/{dt_update}")
    public ResponseEntity<?> delete(@PathVariable("id_profile") Long idProfile,
                                    @PathVariable("id_weight") Long idWeight,
                                    @PathVariable("dt_update") long dtUpdate) {
        weightService.delete(idWeight,idProfile,dtUpdate);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
