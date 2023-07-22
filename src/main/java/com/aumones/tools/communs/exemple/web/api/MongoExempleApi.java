package com.aumones.tools.communs.exemple.web.api;

import com.aumones.tools.communs.exemple.web.dto.request.MongoExempleCreateRequestDto;
import com.aumones.tools.communs.exemple.web.dto.request.ExempleSearchRequestDto;
import com.aumones.tools.communs.exemple.web.dto.request.MongoExempleUpdateRequestDto;
import com.aumones.tools.communs.exemple.web.dto.response.MongoExempleResponseDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = {"/api/exemple-mongo"})
public interface MongoExempleApi {

  @GetMapping("/list")
  ResponseEntity<List<MongoExempleResponseDto>> list(@Valid ExempleSearchRequestDto searchRequest);

  @GetMapping("/list/pageable")
  ResponseEntity<Page<MongoExempleResponseDto>> list(@Valid ExempleSearchRequestDto searchRequest, Pageable page);

  @GetMapping("/single/{id}")
  ResponseEntity<MongoExempleResponseDto> single(@PathVariable(value = "id") String id);

  @PostMapping("/create")
  ResponseEntity<MongoExempleResponseDto> create(@Valid @RequestBody MongoExempleCreateRequestDto createRequest);

  @PutMapping("/update/{id}")
  ResponseEntity<MongoExempleResponseDto> update(@PathVariable(value = "id") String id,
                                                 @Valid @RequestBody MongoExempleUpdateRequestDto updateRequest);
}
