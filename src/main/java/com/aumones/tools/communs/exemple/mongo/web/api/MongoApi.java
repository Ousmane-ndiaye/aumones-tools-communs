package com.aumones.tools.communs.exemple.mongo.web.api;

import com.aumones.tools.communs.exemple.mongo.web.dto.request.MongoCreateRequestDto;
import com.aumones.tools.communs.exemple.mongo.web.dto.request.MongoSearchRequestDto;
import com.aumones.tools.communs.exemple.mongo.web.dto.request.MongoUpdateRequestDto;
import com.aumones.tools.communs.exemple.mongo.web.dto.response.MongoResponseDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = {"/api/mongos"})
public interface MongoApi {

  @GetMapping("/list")
  ResponseEntity<List<MongoResponseDto>> list(@Valid MongoSearchRequestDto productSearchRequest);

  @GetMapping("/list/pageable")
  ResponseEntity<Page<MongoResponseDto>> list(@Valid MongoSearchRequestDto productSearchRequest, Pageable page);

  @GetMapping("/single/{id}")
  ResponseEntity<MongoResponseDto> single(@PathVariable(value = "id") String id);

  @PostMapping("/create")
  ResponseEntity<MongoResponseDto> create(@Valid @RequestBody MongoCreateRequestDto productCreateRequest);

  @PutMapping("/update/{id}")
  ResponseEntity<MongoResponseDto> update(@PathVariable(value = "id") String id,
                                                @Valid @RequestBody MongoUpdateRequestDto productCreateRequest);
}
