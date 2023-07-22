package com.aumones.tools.communs.exemple.web.api;

import com.aumones.tools.communs.exemple.web.dto.request.ExempleSearchRequestDto;
import com.aumones.tools.communs.exemple.web.dto.request.JpaExempleCreateRequestDto;
import com.aumones.tools.communs.exemple.web.dto.request.JpaExempleUpdateRequestDto;
import com.aumones.tools.communs.exemple.web.dto.response.JpaExempleResponseDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = {"/api/exemple-jpa"})
public interface JpaExempleApi {

  @GetMapping("/list")
  ResponseEntity<List<JpaExempleResponseDto>> list(@Valid ExempleSearchRequestDto searchRequest);

  @GetMapping("/list/pageable")
  ResponseEntity<Page<JpaExempleResponseDto>> list(@Valid ExempleSearchRequestDto searchRequest, Pageable page);

  @GetMapping("/single/{id}")
  ResponseEntity<JpaExempleResponseDto> single(@PathVariable(value = "id") Long id);

  @PostMapping("/create")
  ResponseEntity<JpaExempleResponseDto> create(@Valid @RequestBody JpaExempleCreateRequestDto createRequest);

  @PutMapping("/update/{id}")
  ResponseEntity<JpaExempleResponseDto> update(@PathVariable(value = "id") Long id,
                                                 @Valid @RequestBody JpaExempleUpdateRequestDto updateRequest);
}
