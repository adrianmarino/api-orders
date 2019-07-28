package com.navent.api.orders.controller;

import com.navent.api.orders.error.ErrorMessage;
import com.navent.api.orders.error.ErrorResponseFactory;
import com.navent.api.orders.exception.AppException;
import com.navent.api.orders.persistence.NotFoundEntityException;
import com.navent.api.orders.service.EntityService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@ControllerAdvice
public abstract class EntityController<SERVICE extends EntityService<ENTITY>, ENTITY, DTO> {

    protected final EntityService<ENTITY> service;

    protected final MapperFacade mapper;

    private ErrorResponseFactory errorResponseFactory;

    public EntityController(
            EntityService<ENTITY> service,
            MapperFacade mapper,
            ErrorResponseFactory errorResponseFactory
    ) {
        this.service = service;
        this.mapper = mapper;
        this.errorResponseFactory = errorResponseFactory;
    }

    @PostMapping
    public ResponseEntity<DTO> create(@Valid @RequestBody DTO entityDto) {
        return new ResponseEntity<>(toDto(service.save(toDomain(entityDto))), CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DTO> update(@PathVariable String id, @Valid @RequestBody DTO entityDto) {
        return new ResponseEntity<>(toDto(service.save(toDomain(entityDto))), OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) throws NotFoundEntityException {
        service.remove(id);
    }

    @RequestMapping("/{id}")
    public ResponseEntity<DTO> findById(@PathVariable String id) throws NotFoundEntityException {
        return service.findById(id)
                .map(entity -> new ResponseEntity<>(toDto(entity), OK))
                .orElseThrow(() -> new NotFoundEntityException(id));
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ErrorMessage> exceptionHandler(HttpServletRequest request, AppException exception) {
        return errorResponseFactory.create(exception);
    }

    protected abstract DTO toDto(ENTITY order);

    protected abstract ENTITY toDomain(DTO order);
}
