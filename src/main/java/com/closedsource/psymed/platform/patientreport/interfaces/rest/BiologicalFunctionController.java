package com.closedsource.psymed.platform.patientreport.interfaces.rest;

import com.closedsource.psymed.platform.patientreport.domain.model.aggregates.BiologicalFunction;
import com.closedsource.psymed.platform.patientreport.domain.services.BiologicalFunctionCommandService;
import com.closedsource.psymed.platform.patientreport.domain.services.BiologicalFunctionQueryService;
import com.closedsource.psymed.platform.patientreport.interfaces.rest.resources.BiologicalFunctionResource;
import com.closedsource.psymed.platform.patientreport.interfaces.rest.resources.CreateBiologicalFunctionRecordResource;
import com.closedsource.psymed.platform.patientreport.interfaces.rest.transform.BiologicalFunctionResourceFromEntityAssembler;
import com.closedsource.psymed.platform.patientreport.interfaces.rest.transform.CreateBiologicalFunctionRecordCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "/api/v1/biological-functions", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Biological Functions", description = "Biological Functions Endpoints")
public class BiologicalFunctionController {
    private final BiologicalFunctionCommandService biologicalFunctionCommandService;
    private final BiologicalFunctionQueryService biologicalFunctionQueryService;

    public BiologicalFunctionController(BiologicalFunctionCommandService biologicalFunctionCommandService,
                                        BiologicalFunctionQueryService biologicalFunctionQueryService){
        this.biologicalFunctionCommandService = biologicalFunctionCommandService;
        this.biologicalFunctionQueryService = biologicalFunctionQueryService;
    }

    @PostMapping
    public ResponseEntity<BiologicalFunctionResource> createBiologicalFunction
            (@RequestBody CreateBiologicalFunctionRecordResource resource) {
        Optional<BiologicalFunction> biologicalFunction = biologicalFunctionCommandService
                .handle(CreateBiologicalFunctionRecordCommandFromResourceAssembler.toCommandFromResource(resource));
        return biologicalFunction.map(biological ->
                new ResponseEntity<>(BiologicalFunctionResourceFromEntityAssembler
                        .toResourceFromEntity(biological), CREATED)).orElseGet(() -> ResponseEntity.badRequest().build());
    }

}
